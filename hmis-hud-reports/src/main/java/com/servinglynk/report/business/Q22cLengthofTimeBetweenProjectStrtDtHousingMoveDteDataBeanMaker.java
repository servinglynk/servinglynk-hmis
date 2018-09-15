package com.servinglynk.report.business;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.servinglynk.hive.connection.ImpalaConnection;
import com.servinglynk.report.bean.Q22cLengthofTimeBetweenProjectStrtDtHousingMoveDteDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.Q22BeanModel;

public class Q22cLengthofTimeBetweenProjectStrtDtHousingMoveDteDataBeanMaker extends BaseBeanMaker {
	
	public static List<Q22cLengthofTimeBetweenProjectStrtDtHousingMoveDteDataBean> getQ22cLengthofTimeBetweenProjectStrtDtHousingMoveDteDataBeanList(ReportData data){
		Q22cLengthofTimeBetweenProjectStrtDtHousingMoveDteDataBean q22cBean = new Q22cLengthofTimeBetweenProjectStrtDtHousingMoveDteDataBean();
		
		String query = " select distinct(e.dedup_client_id ),e.entrydate,mid.moveindate from %s.enrollment e join %s.project p  on (e.projectid = p.id  %p"+
				  " join  %s.enrollment e1 on  (e.householdid = e1.householdid  and e1.relationshiptohoh ='1') "+
			      " left outer join %s.moveindate mid on (e1.id = mid.enrollmentid and mid.moveindate  >=  '2016-06-24 00:00:00'  and  mid.moveindate<='2018-06-24 00:00:00') "+
					" order by e.dedup_client_id ";
//					" union all "+    
//					" select distinct(e.dedup_client_id ),e.entrydate,mid.moveindate  from enrollment e join project p  on (e.projectid = p.id  %p"+
//					" join exit ext on ( e.id = ext.enrollmentid and ext.exitdate >= '2016-06-24 00:00:00'  and ext.exitdate <='2018-06-24 00:00:00') "+
//					" join moveindate mid on (e.id = mid.enrollmentid) "+
//					" order by e.dedup_client_id ";		
//		         
		try {
			if(data.isLiveMode()) {
				List<Q22BeanModel> allData = getQ22Bean(data, query, null,true);
				List<Q22BeanModel> withoutChildren = getQ22Bean(data, query, data.getProjectsHHWithOutChildren(),false);
				List<Q22BeanModel> withChildAndAdults = getQ22Bean(data, query, data.getProjectsHHWithOneAdultChild(),false);
				List<Q22BeanModel> withChildren = getQ22Bean(data, query, data.getProjectsHHWithChildren(),false);
				List<Q22BeanModel> unknown = getQ22Bean(data, query, data.getProjectsUnknownHouseHold(),false);
				
				q22cBean.setQ22cPersonsMovedIntoHousingTotal(BigInteger.valueOf(allData != null ? allData.size() :0));
				q22cBean.setQ22cPersonsMovedIntoHousingWithoutChildren(BigInteger.valueOf(withoutChildren != null ? withoutChildren.size():0));
				q22cBean.setQ22cPersonsMovedIntoHousingWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22cPersonsMovedIntoHousingWithOnlychildren(BigInteger.valueOf(withChildAndAdults != null ? withChildAndAdults.size() :0));
				q22cBean.setQ22cPersonsMovedIntoHousingUnknowHousehold(BigInteger.valueOf(unknown != null ? unknown.size():0));
				
				
				List<Q22BeanModel>  q22Bean7DaysOrLessAllData = allData.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 7).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean7DaysOrLessWithoutChildren = withoutChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 7).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean7DaysOrLessWithChildAndAdults = withChildAndAdults.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 7).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean7DaysOrLessWithChildren = withChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 7).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean7DaysOrLessUnknown = unknown.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 7).collect(Collectors.toList());
					
				q22cBean.setQ22c7DaysLessTotal(BigInteger.valueOf(q22Bean7DaysOrLessAllData != null ? q22Bean7DaysOrLessAllData.size() :0));
				q22cBean.setQ22c7DaysLessWithoutChildren(BigInteger.valueOf(q22Bean7DaysOrLessWithoutChildren != null ?q22Bean7DaysOrLessWithoutChildren.size() :0));
				q22cBean.setQ22c7DaysLessWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22c7DaysLessWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22c7DaysLessUnknowHousehold(BigInteger.valueOf(0));
				
				List<Q22BeanModel>  q22Bean8To14DaysOrLessAllData = allData.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 8 && q22BeanModel.getNumberOfDays() <= 14).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean8To14DaysOrLessWithoutChildren = withoutChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 8 && q22BeanModel.getNumberOfDays() <= 14).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean8To14DaysOrLessWithChildAndAdults = withChildAndAdults.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 8 && q22BeanModel.getNumberOfDays() <= 14).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean8To14DaysOrLessWithChildren = withChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 8 && q22BeanModel.getNumberOfDays() <= 14).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean8To14DaysOrLessUnknown = unknown.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 8 && q22BeanModel.getNumberOfDays() <= 14).collect(Collectors.toList());
				
				q22cBean.setQ22c8To14DaysTotal(BigInteger.valueOf(0));
				q22cBean.setQ22c8To14DaysWithoutChildren(BigInteger.valueOf(0));
				q22cBean.setQ22c8To14DaysWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22c8To14DaysWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22c8To14DaysUnknowHousehold(BigInteger.valueOf(0));
				
				List<Q22BeanModel>  q22Bean15To21DaysOrLessAllData = allData.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 15 && q22BeanModel.getNumberOfDays() <= 21).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean15To21DaysOrLessWithoutChildren = withoutChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 15 && q22BeanModel.getNumberOfDays() <= 21).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean15To21DaysOrLessWithChildAndAdults = withChildAndAdults.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 15 && q22BeanModel.getNumberOfDays() <= 21).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean15To21DaysOrLessWithChildren = withChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 15 && q22BeanModel.getNumberOfDays() <= 21).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean15To21DaysOrLessUnknown = unknown.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 15 && q22BeanModel.getNumberOfDays() <= 21).collect(Collectors.toList());
				
				q22cBean.setQ22c15To21DaysTotal(BigInteger.valueOf(0));
				q22cBean.setQ22c15To21DaysWithoutChildren(BigInteger.valueOf(0));
				q22cBean.setQ22c15To21DaysWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22c15To21DaysWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22c15To21DaysUnknowHousehold(BigInteger.valueOf(0));
				
				
				List<Q22BeanModel>  q22Bean22To30DaysOrLessAllData = allData.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 22 && q22BeanModel.getNumberOfDays() <= 30).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean22To30DaysOrLessWithoutChildren = withoutChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 22 && q22BeanModel.getNumberOfDays() <= 30).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean22To30DaysOrLessWithChildAndAdults = withChildAndAdults.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 22 && q22BeanModel.getNumberOfDays() <= 30).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean22To30DaysOrLessWithChildren = withChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 22 && q22BeanModel.getNumberOfDays() <= 30).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean22To30DaysOrLessUnknown = unknown.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 22 && q22BeanModel.getNumberOfDays() <= 30).collect(Collectors.toList());
				
				q22cBean.setQ22c22To30DaysTotal(BigInteger.valueOf(0));
				q22cBean.setQ22c22To30DaysWithoutChildren(BigInteger.valueOf(0));
				q22cBean.setQ22c22To30DaysWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22c22To30DaysWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22c22To30DaysUnknowHousehold(BigInteger.valueOf(0));
				
				List<Q22BeanModel>  q22Bean31To60DaysOrLessAllData = allData.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 31 && q22BeanModel.getNumberOfDays() <= 60).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean31To60DaysOrLessWithoutChildren = withoutChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 31 && q22BeanModel.getNumberOfDays() <= 60).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean31To60DaysOrLessWithChildAndAdults = withChildAndAdults.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 31 && q22BeanModel.getNumberOfDays() <= 60).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean31To60DaysOrLessWithChildren = withChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 31 && q22BeanModel.getNumberOfDays() <= 60).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean31To60DaysOrLessUnknown = unknown.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 31 && q22BeanModel.getNumberOfDays() <= 60).collect(Collectors.toList());
				
				q22cBean.setQ22c31To60DaysTotal(BigInteger.valueOf(0));
				q22cBean.setQ22c31To60DaysWithoutChildren(BigInteger.valueOf(0));
				q22cBean.setQ22c31To60DaysWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22c31To60DaysWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22c31To60DaysUnknowHousehold(BigInteger.valueOf(0));
				
				List<Q22BeanModel>  q22Bean61To180DaysOrLessAllData = allData.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 61 && q22BeanModel.getNumberOfDays() <= 180).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean61To180DaysOrLessWithoutChildren = withoutChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 61 && q22BeanModel.getNumberOfDays() <= 180).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean61To180DaysOrLessWithChildAndAdults = withChildAndAdults.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 61 && q22BeanModel.getNumberOfDays() <= 180).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean61To180DaysOrLessWithChildren = withChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 61 && q22BeanModel.getNumberOfDays() <= 180).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean61To180DaysOrLessUnknown = unknown.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 61 && q22BeanModel.getNumberOfDays() <= 180).collect(Collectors.toList());
			
				q22cBean.setQ22c61To180DaysTotal(BigInteger.valueOf(0));
				q22cBean.setQ22c61To180DaysWithoutChildren(BigInteger.valueOf(0));
				q22cBean.setQ22c61To180DaysWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22c61To180DaysWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22c61To180DaysUnknowHousehold(BigInteger.valueOf(0));
				
				List<Q22BeanModel>  q22Bean181To365DaysOrLessAllData = allData.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 181 && q22BeanModel.getNumberOfDays() <= 365).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean181To365DaysOrLessWithoutChildren = withoutChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 181 && q22BeanModel.getNumberOfDays() <= 365).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean181To365DaysOrLessWithChildAndAdults = withChildAndAdults.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 181 && q22BeanModel.getNumberOfDays() <= 365).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean181To365DaysOrLessWithChildren = withChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 181 && q22BeanModel.getNumberOfDays() <= 365).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean181To365DaysOrLessUnknown = unknown.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 181 && q22BeanModel.getNumberOfDays() <= 365).collect(Collectors.toList());
			
				q22cBean.setQ22c181To365DaysTotal(BigInteger.valueOf(0));
				q22cBean.setQ22c181To365DaysWithoutChildren(BigInteger.valueOf(0));
				q22cBean.setQ22c181To365DaysWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22c181To365DaysWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22c181To365DaysUnknowHousehold(BigInteger.valueOf(0));
				
				List<Q22BeanModel>  q22Bean366To730DaysOrLessAllData = allData.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 366 && q22BeanModel.getNumberOfDays() <= 730).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean366To730DaysOrLessWithoutChildren = withoutChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 366 && q22BeanModel.getNumberOfDays() <= 730).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean366To730DaysOrLessWithChildAndAdults = withChildAndAdults.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 366 && q22BeanModel.getNumberOfDays() <= 730).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean366To730DaysOrLessWithChildren = withChildren.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 366 && q22BeanModel.getNumberOfDays() <= 730).collect(Collectors.toList());
				List<Q22BeanModel>  q22Bean366To730DaysOrLessUnknown = unknown.parallelStream().filter(q22BeanModel -> q22BeanModel.getNumberOfDays() <= 366 && q22BeanModel.getNumberOfDays() <= 730).collect(Collectors.toList());
			
				
				q22cBean.setQ22c366To730DaysTotal(BigInteger.valueOf(q22Bean366To730DaysOrLessAllData != null ? q22Bean366To730DaysOrLessAllData.size() :0));
				q22cBean.setQ22c366To730DaysWithoutChildren(BigInteger.valueOf(0));
				q22cBean.setQ22c366To730DaysWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22c366To730DaysWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22c366To730DaysUnknowHousehold(BigInteger.valueOf(0));
				
				
				if(CollectionUtils.isNotEmpty(allData)) {
					long count = 0;
					for(Q22BeanModel q22BeanModel : allData) {
						count = count + q22BeanModel.getNumberOfDays();
					}
					q22cBean.setQ22cAvgLengthTotal(BigInteger.valueOf(count/allData.size()));
				}
				
				if(CollectionUtils.isNotEmpty(withoutChildren)) {
					long count = 0;
					for(Q22BeanModel q22BeanModel : withoutChildren) {
						count = count + q22BeanModel.getNumberOfDays();
					}
					q22cBean.setQ22cAvgLengthWithoutChildren(BigInteger.valueOf(count/allData.size()));
				}
				
				if(CollectionUtils.isNotEmpty(withChildAndAdults)) {
					long count = 0;
					for(Q22BeanModel q22BeanModel : withChildAndAdults) {
						count = count + q22BeanModel.getNumberOfDays();
					}
					q22cBean.setQ22cAvgLengthWithChildAndAdults(BigInteger.valueOf(count/allData.size()));
				}
				if(CollectionUtils.isNotEmpty(withChildren)) {
					long count = 0;
					for(Q22BeanModel q22BeanModel : withChildren) {
						count = count + q22BeanModel.getNumberOfDays();
					}
					q22cBean.setQ22cAvgLengthWithOnlychildren(BigInteger.valueOf(count/allData.size()));
				}
				if(CollectionUtils.isNotEmpty(unknown)) {
					long count = 0;
					for(Q22BeanModel q22BeanModel : unknown) {
						count = count + q22BeanModel.getNumberOfDays();
					}
					q22cBean.setQ22cAvgLengthUnknowHousehold(BigInteger.valueOf(count/allData.size()));
				}
				
				q22cBean.setQ22cPersonsExitedTotal(BigInteger.valueOf(0)); 
				q22cBean.setQ22cPersonsExitedWithoutChildren(BigInteger.valueOf(0));
				q22cBean.setQ22cPersonsExitedWithChildAndAdults(BigInteger.valueOf(0));
				q22cBean.setQ22cPersonsExitedWithOnlychildren(BigInteger.valueOf(0));
				q22cBean.setQ22cPersonsExitedUnknowHousehold(BigInteger.valueOf(0));
				
				q22cBean.setQ22cTotPersonsTotal(data.getOverAllTotHouseHolds()); 
				q22cBean.setQ22cTotPersonsWithoutChildren(data.getTotHhWithoutChild());
				q22cBean.setQ22cTotPersonsWithChildAndAdults(data.getTotHhWithChildAndAdults());
				q22cBean.setQ22cTotPersonsWithOnlychildren(data.getTotHhWothOnlyChild());
				q22cBean.setQ22cTotPersonsUnknowHousehold(data.getTotHhUnknownHhType());
			}
		}catch(Exception e) {
			logger.error("Error in Q22cLengthofTimeBetweenProjectStrtDtHousingMoveDteDataBeanMaker:" + e);
		}
		return Arrays.asList(q22cBean);
		
		
	}

	public static List<Q22BeanModel> getQ22Bean(ReportData data,String query,List<String> filteredProjectIds, boolean allProjects) {
		 List<Q22BeanModel> q22Beans = new ArrayList<Q22BeanModel>();
			ResultSet resultSet = null;
			PreparedStatement statement = null;
			String projectQuery = " and p.id in ( ";
			StringBuilder builder = new StringBuilder(projectQuery);
			Connection connection = null;
			try {
				connection = ImpalaConnection.getConnection();
				 List<String> projectIds = data.getProjectIds();
				 if(CollectionUtils.isNotEmpty(projectIds)) {
					 int count = 0;
					 for(String project : projectIds) {
						 if ((filteredProjectIds !=null && filteredProjectIds.contains(project)) || allProjects) {
							 builder.append("'"+project+"'");
							 if(count != projectIds.size()) {
								 builder.append(",");
							 }
						 }
					 }
				 }
				 builder.append(" ) ");
				String newQuery = query.replace("%p", builder.toString());
				statement = connection.prepareStatement(formatQuery(newQuery,data.getSchema()));
				statement.setDate(1, data.getReportStartDate());
				statement.setDate(2, data.getReportEndDate());
				statement.setDate(3, data.getReportStartDate());
				statement.setDate(4, data.getReportEndDate());
				resultSet = statement.executeQuery();
				
			 while(resultSet.next()) {
				 Date entryDate = resultSet.getDate("entrydate");
				 Date moveinDate = resultSet.getDate("moveindate");
				 
				 Q22BeanModel bean = new Q22BeanModel(resultSet.getString("dedup_client_id"), null,null, 
						 null,resultSet.getDate("exitdate"),entryDate,moveinDate,resultSet.getDate("dateprovided") );
				 bean.setNumberOfDays(subtractDate(entryDate, moveinDate));
				 q22Beans.add(bean);
			 
			 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
						//connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return q22Beans;
		}	
	

}
