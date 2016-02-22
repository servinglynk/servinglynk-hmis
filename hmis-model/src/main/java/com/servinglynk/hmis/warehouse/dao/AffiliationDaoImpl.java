package com.servinglynk.hmis.warehouse.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import org.apache.hadoop.hbase.thrift2.generated.THBaseService.Iface;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.BeanUtils;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Affiliation;
import com.servinglynk.hmis.warehouse.domain.SyncDomain;
import com.servinglynk.hmis.warehouse.model.staging.Project;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class AffiliationDaoImpl extends ParentDaoImpl implements AffiliationDao {

		@Override
		public void hydrateStaging(ExportDomain domain) 
		{
			Export export = domain.getExport();
			List<Affiliation> affiliations = export.getAffiliation();
			if(affiliations!=null && !affiliations.isEmpty())
			{
				for(Affiliation affiliation :affiliations )
				{
					com.servinglynk.hmis.warehouse.model.staging.Affiliation affiliationModel = new com.servinglynk.hmis.warehouse.model.staging.Affiliation();
					affiliationModel.setId(UUID.randomUUID());
					affiliationModel.setResprojectid(affiliation.getResProjectID());
					affiliationModel.setDateCreated(LocalDateTime.now());
					affiliationModel.setDateUpdated(LocalDateTime.now());
					Project project = (Project) get(Project.class,domain.getAffiliationProjectMap().get(affiliation.getProjectID()));
					com.servinglynk.hmis.warehouse.model.staging.Export exportEntity = (com.servinglynk.hmis.warehouse.model.staging.Export) get(com.servinglynk.hmis.warehouse.model.staging.Export.class, domain.getExportId());
					affiliationModel.setExport(exportEntity);
					affiliationModel.setProjectid(project);
					exportEntity.addAffiliation(affiliationModel);
					affiliationModel.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(affiliation.getDateCreated()));
					affiliationModel.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(affiliation.getDateUpdated()));
					hydrateCommonFields(affiliationModel, domain);
					insertOrUpdate(affiliationModel);
				}
			}
		}

		@Override
		public void hydrateLive(
				com.servinglynk.hmis.warehouse.model.staging.Export export) {
			Set<com.servinglynk.hmis.warehouse.model.staging.Affiliation> affiliations = export.getAffiliations();
			if(affiliations !=null && !affiliations.isEmpty()) {
				for(com.servinglynk.hmis.warehouse.model.staging.Affiliation affiliation : affiliations ) {
					 com.servinglynk.hmis.warehouse.model.live.Affiliation target = new com.servinglynk.hmis.warehouse.model.live.Affiliation();
					 BeanUtils.copyProperties(affiliation, target,getNonCollectionFields(target));
					 com.servinglynk.hmis.warehouse.model.live.Export exportEntity = (com.servinglynk.hmis.warehouse.model.live.Export) get(com.servinglynk.hmis.warehouse.model.live.Export.class, export.getId());
					 target.setExport(exportEntity);
					 com.servinglynk.hmis.warehouse.model.live.Project projectModel = (com.servinglynk.hmis.warehouse.model.live.Project) get(com.servinglynk.hmis.warehouse.model.live.Project.class,affiliation.getProjectid().getId());
					 target.setProjectid(projectModel);
					 target.setDateCreated(LocalDateTime.now());
					 target.setDateUpdated(LocalDateTime.now());
					 insert(target);
				}
			}
		}

		@Override
		public void hydrateHBASE(SyncDomain syncDomain) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected void performSave(Iface client, Object entity) {
			// TODO Auto-generated method stub
			
		}

		@Override
		protected List performGet(Iface client, Object entity) {
			// TODO Auto-generated method stub
			return null;
		}
		
		   public com.servinglynk.hmis.warehouse.model.live.Affiliation createAffiliation(com.servinglynk.hmis.warehouse.model.live.Affiliation affiliation){
		       affiliation.setId(UUID.randomUUID()); 
		       insert(affiliation);
		       return affiliation;
		   }
		   public com.servinglynk.hmis.warehouse.model.live.Affiliation updateAffiliation(com.servinglynk.hmis.warehouse.model.live.Affiliation affiliation){
		       update(affiliation);
		       return affiliation;
		   }
		   public void deleteAffiliation(com.servinglynk.hmis.warehouse.model.live.Affiliation affiliation){
		       delete(affiliation);
		   }
		   public com.servinglynk.hmis.warehouse.model.live.Affiliation getAffiliationById(UUID affiliationId){ 
		       return (com.servinglynk.hmis.warehouse.model.live.Affiliation) get(com.servinglynk.hmis.warehouse.model.live.Affiliation.class, affiliationId);
		   }
		   public List<com.servinglynk.hmis.warehouse.model.live.Affiliation> getAllProjectAffiliations(UUID projectId,Integer startIndex, Integer maxItems){
		       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.live.Affiliation.class);
		       criteria.createAlias("projectId", "projectId");
		       criteria.add(Restrictions.eq("projectId.id", projectId));
		       return (List<com.servinglynk.hmis.warehouse.model.live.Affiliation>) findByCriteria(criteria,startIndex,maxItems);
		   }
		   public long getProjectAffiliationsCount(UUID projectId){
		       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.live.Affiliation.class);
		       criteria.createAlias("projectId", "projectId");
		       criteria.add(Restrictions.eq("projectId.id", projectId));
		       return countRows(criteria);
		   }
		
}

