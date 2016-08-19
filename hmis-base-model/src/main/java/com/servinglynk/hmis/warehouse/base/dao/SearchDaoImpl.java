package com.servinglynk.hmis.warehouse.base.dao;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.time.DateUtils;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.SortField;
import org.hibernate.CacheMode;
import org.hibernate.FlushMode;
import org.hibernate.ScrollMode;
import org.hibernate.ScrollableResults;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.search.FullTextQuery;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.dsl.TermMatchingContext;

import com.servinglynk.hmis.warehouse.SearchRequest;
import com.servinglynk.hmis.warehouse.model.base.Client;

public class SearchDaoImpl
  extends QueryExecutorImpl
  implements SearchDao
{
  public List<?> search(SearchRequest searchVO)
  {
    Session session = getCurrentSession();
    FullTextSession fullTextSession = Search.getFullTextSession(session);
    
    QueryBuilder queryBuilder = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(searchVO.getSearchEntity()).get();
    
    Query luceneQuery = queryBuilder.all().createQuery();
    
    TermMatchingContext termMatchingContext = queryBuilder.keyword().wildcard().onField(searchVO.getDbfileds()[0]);
    for (int i = 1; i < searchVO.getDbfileds().length; i++) {
      termMatchingContext.andField(searchVO.getDbfileds()[i]);
    }
    luceneQuery = termMatchingContext.matching("*" + searchVO.getFreeText() + "*").createQuery();
    
    FullTextQuery hibernateQuery = fullTextSession.createFullTextQuery(luceneQuery, new Class[] {searchVO
      .getSearchEntity() });
    
    int resultSize = hibernateQuery.getResultSize();
    if (searchVO.getPagination() != null)
    {
      searchVO.getPagination().setTotal(Integer.valueOf(resultSize));
      hibernateQuery.setFirstResult(searchVO.getPagination().getFrom().intValue());
      hibernateQuery.setMaxResults(searchVO.getPagination().getMaximum().intValue());
      if ((searchVO.getPagination().getSort() != null) && 
        (searchVO.getPagination().getSort().getField() != null))
      {
        String order = searchVO.getPagination().getSort().getOrder();
        boolean reverse = false;
        if ("desc".equalsIgnoreCase(order)) {
          reverse = true;
        }
        org.apache.lucene.search.Sort sort = new org.apache.lucene.search.Sort(new SortField(searchVO.getPagination().getSort().getField(), SortField.STRING, reverse));
        hibernateQuery.setSort(sort);
      }
    }
    return hibernateQuery.list();
  }
  
  
  public List<?> search(SearchRequest searchVO,boolean isIndexSearch){
	  if(isIndexSearch){
		  return this.search(searchVO);
	  }else{
		  return this.searchData(searchVO);
	  }
  }
  
  
  public List<?> searchData(SearchRequest searchRequest){

	  DetachedCriteria criteria = DetachedCriteria.forClass(Client.class);
		  DateFormat formatter = new SimpleDateFormat("MM-dd-yyyy");
		  Date date=null;
		try {
			date = formatter.parse(searchRequest.getFreeText());
			
			  criteria.add(Restrictions.ge("dob", LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault())));
			  criteria.add(Restrictions.lt("dob", LocalDateTime.ofInstant(DateUtils.addDays(date,1).toInstant(), ZoneId.systemDefault())));
		} catch (ParseException e) {
			  Criterion firstName = Restrictions.ilike("firstName",searchRequest.getFreeText(),MatchMode.ANYWHERE);
			  Criterion lastName = Restrictions.ilike("lastName",searchRequest.getFreeText(),MatchMode.ANYWHERE);
			  Criterion middleName = Restrictions.ilike("middleName",searchRequest.getFreeText(),MatchMode.ANYWHERE);
			 Criterion sourceSystemId = Restrictions.ilike("sourceSystemId",searchRequest.getFreeText(),MatchMode.ANYWHERE);
			  Criterion ssn = Restrictions.ilike("ssn",searchRequest.getFreeText(),MatchMode.ANYWHERE);
			  if(Arrays.asList(searchRequest.getExcludeFields()).contains("ssi"))
				  criteria.add(Restrictions.or(firstName,lastName,middleName,ssn));
			  else if(Arrays.asList(searchRequest.getExcludeFields()).contains("all")){
				  
			  }	 else
				  criteria.add(Restrictions.or(firstName,lastName,middleName,ssn,sourceSystemId));
	  }
		criteria.add(Restrictions.eq("projectGroupCode",searchRequest.getProjectGroupCode()));
	  searchRequest.getPagination().setTotal((int) countRows(criteria));
	  
	  if(searchRequest.getSort().getOrder().equals("asc"))
		  criteria.addOrder(Order.asc(searchRequest.getSort().getField()));
	  else
		  criteria.addOrder(Order.desc(searchRequest.getSort().getField())); 
	  
	  return findByCriteria(criteria,searchRequest.getPagination().getFrom(),searchRequest.getPagination().getMaximum());
  }
  
  public boolean indexing(String indexClassList)
  {
    boolean status = true;
    
    Session session = getCurrentSession();
    
    FullTextSession fullTextSession = Search.getFullTextSession(session);
    
   // String indexClassList = "com.servinglynk.hmis.warehouse.model.v2015.Client,com.servinglynk.hmis.warehouse.model.v2014.Client";

    if (indexClassList != null)
    {
      String[] indexClasses = indexClassList.split(",");
      for (String entityClassName : indexClasses) {
        try
        {
          indexing(Class.forName(entityClassName), fullTextSession);
        }
        catch (ClassNotFoundException e)
        {
          status = false;
          e.printStackTrace();
        }
      }
    }
    else
    {
      indexing(indexClassList);
    }
    return status;
  }
  
  protected void indexing(Class cls, FullTextSession fullTextSession)
  {
    fullTextSession.setFlushMode(FlushMode.MANUAL);
    fullTextSession.setCacheMode(CacheMode.IGNORE);
    
    fullTextSession.purgeAll(cls);
    
    ScrollableResults results = fullTextSession.createCriteria(cls).scroll(ScrollMode.FORWARD_ONLY);
    
    int index = 0;
    while (results.next())
    {
      index++;
      fullTextSession.index(results.get(0));
      if (index % 150 == 0)
      {
        fullTextSession.flushToIndexes();
        fullTextSession.clear();
      }
    }
  }
  public void doIndex(Object indexObject){
	  FullTextSession fullTextSession = Search.getFullTextSession(getCurrentSession());
	  Transaction tx = fullTextSession.beginTransaction();
	  fullTextSession.index(indexObject);
	  tx.commit();
  }
  
}
