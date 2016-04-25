package com.servinglynk.hmis.warehouse.dao;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.apache.hadoop.hbase.thrift2.generated.THBaseService.Iface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.warehouse.dao.helper.BulkUploadHelper;
import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources;
import com.servinglynk.hmis.warehouse.domain.Sources.Source;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export;
import com.servinglynk.hmis.warehouse.enums.UploadStatus;
import com.servinglynk.hmis.warehouse.model.base.Client;
import com.servinglynk.hmis.warehouse.model.base.BulkUpload;
import com.servinglynk.hmis.warehouse.model.base.ProjectGroupEntity;
import com.servinglynk.hmis.warehouse.model.v2015.Affiliation;
import com.servinglynk.hmis.warehouse.model.v2015.Bedinventory;
import com.servinglynk.hmis.warehouse.model.v2015.ClientVeteranInfo;
import com.servinglynk.hmis.warehouse.model.v2015.Dateofengagement;
import com.servinglynk.hmis.warehouse.model.v2015.Disabilities;
import com.servinglynk.hmis.warehouse.model.v2015.Domesticviolence;
import com.servinglynk.hmis.warehouse.model.v2015.Employment;
import com.servinglynk.hmis.warehouse.model.v2015.Enrollment;
import com.servinglynk.hmis.warehouse.model.v2015.EnrollmentCoc;
import com.servinglynk.hmis.warehouse.model.v2015.Exit;
import com.servinglynk.hmis.warehouse.model.v2015.Exithousingassessment;
import com.servinglynk.hmis.warehouse.model.v2015.Funder;
import com.servinglynk.hmis.warehouse.model.v2015.HealthStatus;
import com.servinglynk.hmis.warehouse.model.v2015.Healthinsurance;
import com.servinglynk.hmis.warehouse.model.v2015.Housingassessmentdisposition;
import com.servinglynk.hmis.warehouse.model.v2015.Incomeandsources;
import com.servinglynk.hmis.warehouse.model.v2015.Inventory;
import com.servinglynk.hmis.warehouse.model.v2015.Medicalassistance;
import com.servinglynk.hmis.warehouse.model.v2015.Noncashbenefits;
import com.servinglynk.hmis.warehouse.model.v2015.Organization;
import com.servinglynk.hmis.warehouse.model.v2015.Pathstatus;
import com.servinglynk.hmis.warehouse.model.v2015.Project;
import com.servinglynk.hmis.warehouse.model.v2015.Residentialmoveindate;
import com.servinglynk.hmis.warehouse.model.v2015.RhybcpStatus;
import com.servinglynk.hmis.warehouse.model.v2015.Site;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

public class BulkUploaderDaoImpl extends ParentDaoImpl implements
		BulkUploaderDao {
	
	private static final Logger logger = LoggerFactory
			.getLogger(BulkUploaderDaoImpl.class);
	
	@Autowired
	ParentDaoFactory parentDaoFactory;
	
	@Autowired
	BulkUploadHelper bulkUploadHelper;
	
	@Override
	@Transactional
	public BulkUpload performBulkUpload(BulkUpload upload, ProjectGroupEntity projectGroupdEntity) {
		try {
			//upload.setId(UUID.randomUUID());
			logger.debug("Bulk Uploader Process Begins..........");
			upload.setStatus(UploadStatus.INPROGRESS.getStatus());
			parentDaoFactory.getBulkUploaderWorkerDao().insertOrUpdate(upload);
			Sources sources = bulkUploadHelper.getSourcesFromFiles(upload,projectGroupdEntity);
			
			Source source = sources.getSource();
			Export export = source.getExport();
			UUID exportId = UUID.randomUUID();
			ExportDomain domain = new ExportDomain();
			domain.setExport(export);
			domain.setExportId(exportId);
			domain.setUpload(upload);
			domain.setSource(source);
			parentDaoFactory.getSourceDao().hydrateStaging(domain);
			logger.debug("Staging Source table.........");
			if(export != null)
			{
				com.servinglynk.hmis.warehouse.model.v2015.Export exportModel  = new com.servinglynk.hmis.warehouse.model.v2015.Export();
				exportModel.setExportDate(BasicDataGenerator.getLocalDateTime(export.getExportDate()));
				exportModel.setExportdirective(export.getExportDirective());
				exportModel.setExportperiodtype(export.getExportPeriodType());
				exportModel.setId(exportId);
				exportModel.setDateCreated(LocalDateTime.now());
				exportModel.setDateUpdated(LocalDateTime.now());
//				com.servinglynk.hmis.warehouse.model.staging.HmisUser user = (com.servinglynk.hmis.warehouse.model.staging.HmisUser) get(com.servinglynk.hmis.warehouse.model.staging.HmisUser.class, upload.getUser().getId());
			//	exportModel.setUser(user);
				com.servinglynk.hmis.warehouse.model.v2015.Source sourceEntity = (com.servinglynk.hmis.warehouse.model.v2015.Source) get(com.servinglynk.hmis.warehouse.model.v2015.Source.class, domain.getSourceId());
				exportModel.setSource(sourceEntity);
				//export.getExportPeriod()
				insert(exportModel);
				logger.debug("Staging Export table.........");
			}
			parentDaoFactory.getClientDao().hydrateStaging(domain);
			parentDaoFactory.getVeteranInfoDao().hydrateStaging(domain);
			//Inserting organization inserts Org,Project,Funder,Coc,Inventory,Site and Affiliation.
			parentDaoFactory.getProjectDao().hydrateStaging(domain);
			parentDaoFactory.getAffiliationDao().hydrateStaging(domain);
			parentDaoFactory.getCocDao().hydrateStaging(domain);
			parentDaoFactory.getFunderDao().hydrateStaging(domain);
			parentDaoFactory.getSiteDao().hydrateStaging(domain);
			parentDaoFactory.getInventoryDao().hydrateStaging(domain);
			parentDaoFactory.getOrganizationDao().hydrateStaging(domain);
			
			parentDaoFactory.getEnrollmentDao().hydrateStaging(domain);
			parentDaoFactory.getDateofengagementDao().hydrateStaging(domain);
			parentDaoFactory.getEnrollmentCocDao().hydrateStaging(domain);
			parentDaoFactory.getResidentialmoveindateDao().hydrateStaging(domain);
			parentDaoFactory.getDisabilitiesDao().hydrateStaging(domain);
			parentDaoFactory.getDomesticviolenceDao().hydrateStaging(domain);
			parentDaoFactory.getEmploymentDao().hydrateStaging(domain);
			parentDaoFactory.getExitDao().hydrateStaging(domain);
			parentDaoFactory.getEntryrhspDao().hydrateStaging(domain);
			parentDaoFactory.getEntryrhyDao().hydrateStaging(domain);
			parentDaoFactory.getEntryssvfDao().hydrateStaging(domain);
			parentDaoFactory.getExitpathDao().hydrateStaging(domain);
			parentDaoFactory.getExitrhyDao().hydrateStaging(domain);
			parentDaoFactory.getContactDao().hydrateStaging(domain);
			parentDaoFactory.getServiceFaReferralDao().hydrateStaging(domain);
			parentDaoFactory.getHealthinsuranceDao().hydrateStaging(domain);
			parentDaoFactory.getHealthStatusDao().hydrateStaging(domain);
			parentDaoFactory.getIncomeandsourcesDao().hydrateStaging(domain);
			parentDaoFactory.getMedicalassistanceDao().hydrateStaging(domain);
			parentDaoFactory.getNoncashbenefitsDao().hydrateStaging(domain);
			parentDaoFactory.getPathstatusDao().hydrateStaging(domain);
			parentDaoFactory.getRhybcpstatusDao().hydrateStaging(domain);
			upload.setStatus(UploadStatus.STAGING.getStatus());
			logger.debug("Chaning status of Bulk_upload table to STAGING");
			com.servinglynk.hmis.warehouse.model.v2015.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2015.Export) get(com.servinglynk.hmis.warehouse.model.v2015.Export.class, exportId);
			parentDaoFactory.getSourceDao().hydrateLive(exportEntity,upload.getId());
			logger.debug("Live Source table.........");
			if(exportEntity!=null) {
				com.servinglynk.hmis.warehouse.model.v2015.Export target = new com.servinglynk.hmis.warehouse.model.v2015.Export();
				BeanUtils.copyProperties(exportEntity, target,getNonCollectionFields(target));
				com.servinglynk.hmis.warehouse.model.v2015.Source sourceEntity = (com.servinglynk.hmis.warehouse.model.v2015.Source) get(com.servinglynk.hmis.warehouse.model.v2015.Source.class, domain.getSourceId());
				target.setSource(sourceEntity);
				insert(target);	
				logger.debug("Live Export table.........");
			}
			com.servinglynk.hmis.warehouse.model.v2015.Export exportLive = (com.servinglynk.hmis.warehouse.model.v2015.Export) get(com.servinglynk.hmis.warehouse.model.v2015.Export.class, exportId);
			upload.setExportId(exportLive.getId());
			parentDaoFactory.getBulkUploaderWorkerDao().insertOrUpdate(upload); 
			logger.debug("Bulk Upload Staging Process Ends.....");
		} catch (Exception e) {
			upload.setStatus(UploadStatus.ERROR.getStatus());
			upload.setDescription(e.getMessage());
			parentDaoFactory.getBulkUploaderWorkerDao().insertOrUpdate(upload);
			logger.error("Error executing the bulk upload process::",e.getMessage());
		}
		return upload;
	}
	
//	public HmisUser getHmisUser(String id) {
//		DetachedCriteria criteria = DetachedCriteria.forClass(HmisUser.class);
//		criteria.createAlias("projectGroupEntity","projectGroupEntity");
//		
//		criteria.add(Restrictions.eq("id", id));
//		
//		List<HmisUser> users = (List<HmisUser>) findByCriteria(criteria);
//		if(users!=null ) {
//			return users.get(0);
//		}
//		return null;
//	}
//	
	
	@Override
	@Transactional
	public void moveFromStagingToLive(BulkUpload bulkUpload) {
		try {
		UUID exportId = bulkUpload.getExportId();
		com.servinglynk.hmis.warehouse.model.v2015.Export export = (com.servinglynk.hmis.warehouse.model.v2015.Export) get(com.servinglynk.hmis.warehouse.model.v2015.Export.class, exportId);

		parentDaoFactory.getClientDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getVeteranInfoDao().hydrateLive(export,bulkUpload.getId());
	//	parentDaoFactory.getOrganizationDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getProjectDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getAffiliationDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getFunderDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getCocDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getSiteDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getInventoryDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getOrganizationDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getEnrollmentDao().hydrateLive(export,bulkUpload.getId());
		
		parentDaoFactory.getEnrollmentCocDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getResidentialmoveindateDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getDateofengagementDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getDisabilitiesDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getDomesticviolenceDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getEmploymentDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getExitDao().hydrateLive(export,bulkUpload.getId());
		
		parentDaoFactory.getEntryrhspDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getEntryrhyDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getEntryssvfDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getExitpathDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getExitrhyDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getContactDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getServiceFaReferralDao().hydrateLive(export,bulkUpload.getId());
		
		parentDaoFactory.getHealthinsuranceDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getHealthStatusDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getIncomeandsourcesDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getMedicalassistanceDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getNoncashbenefitsDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getPathstatusDao().hydrateLive(export,bulkUpload.getId());
		//parentDaoFactory.getResidentialmoveindateDao().hydrateLive(export,bulkUpload.getId());
		parentDaoFactory.getRhybcpstatusDao().hydrateLive(export,bulkUpload.getId());
		bulkUpload.setStatus(UploadStatus.LIVE.getStatus());
		parentDaoFactory.getBulkUploaderWorkerDao().insertOrUpdate(bulkUpload);
		logger.debug("Bulk Upload Live Process Ends.....");
		}
		catch (Exception e) {
			e.printStackTrace();
			bulkUpload.setStatus(UploadStatus.ERROR.getStatus());
			//bulkUpload.setd(e.getMessage());
			parentDaoFactory.getBulkUploaderWorkerDao().insertOrUpdate(bulkUpload);
		}
	}
	
	
	
	@Override
	public void deleteStagingByExportId(UUID exportId) {
		com.servinglynk.hmis.warehouse.model.v2015.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2015.Export) get(com.servinglynk.hmis.warehouse.model.v2015.Export.class,exportId);
		deleteFromDB(exportEntity);
	}

	@Override
	public void deleteLiveByExportId(UUID exportId) {
		com.servinglynk.hmis.warehouse.model.v2015.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2015.Export) get(com.servinglynk.hmis.warehouse.model.v2015.Export.class,exportId);
		hardDelete(exportEntity);
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
	
	@Override
	@Transactional
	public void undoDeleteLiveByExportId(UUID exportId) {
//		com.servinglynk.hmis.warehouse.model.live.Export exportEntity = (com.servinglynk.hmis.warehouse.model.live.Export) get(com.servinglynk.hmis.warehouse.model.live.Export.class,exportId);
//		unDeleteFromDB(exportEntity);
		undoSoftDeleteByExportId(Affiliation.class.getName(), exportId);
		undoSoftDeleteByExportId(Bedinventory.class.getName(), exportId);
		undoSoftDeleteByExportId(Dateofengagement.class.getName(), exportId);
		undoSoftDeleteByExportId(Disabilities.class.getName(), exportId);
		undoSoftDeleteByExportId(Domesticviolence.class.getName(), exportId);
		undoSoftDeleteByExportId(Employment.class.getName(), exportId);
		undoSoftDeleteByExportId(Enrollment.class.getName(), exportId);
		undoSoftDeleteByExportId(EnrollmentCoc.class.getName(), exportId);
		undoSoftDeleteByExportId(Client.class.getName(), exportId);
		undoSoftDeleteByExportId(Exit.class.getName(), exportId);
		undoSoftDeleteByExportId(Exithousingassessment.class.getName(), exportId);
		undoSoftDeleteByExportId(Export.class.getName(), exportId);
		undoSoftDeleteByExportId(Funder.class.getName(), exportId);
		undoSoftDeleteByExportId(Healthinsurance.class.getName(), exportId);
		undoSoftDeleteByExportId(HealthStatus.class.getName(), exportId);
		undoSoftDeleteByExportId(Housingassessmentdisposition.class.getName(), exportId);
		undoSoftDeleteByExportId(Incomeandsources.class.getName(), exportId);
		undoSoftDeleteByExportId(Inventory.class.getName(), exportId);
		undoSoftDeleteByExportId(Medicalassistance.class.getName(), exportId);
		undoSoftDeleteByExportId(Noncashbenefits.class.getName(), exportId);
		undoSoftDeleteByExportId(Organization.class.getName(), exportId);
		undoSoftDeleteByExportId(Project.class.getName(), exportId);
		undoSoftDeleteByExportId(Residentialmoveindate.class.getName(), exportId);
		undoSoftDeleteByExportId(RhybcpStatus.class.getName(), exportId);
		undoSoftDeleteByExportId(Site.class.getName(), exportId);
		undoSoftDeleteByExportId(Source.class.getName(), exportId);
		undoSoftDeleteByExportId(ClientVeteranInfo.class.getName(), exportId);
		undoSoftDeleteByExportId(BulkUpload.class.getName(), exportId);
	}
	
	@Override
	public void deleteLiveByProjectGroupCode(String projectGroupCode) {
		softDeleteByProjectGroupCode(Affiliation.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Bedinventory.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Client.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Dateofengagement.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Disabilities.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Domesticviolence.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Employment.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Enrollment.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(EnrollmentCoc.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Exit.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Exithousingassessment.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Export.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Funder.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Healthinsurance.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(HealthStatus.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Housingassessmentdisposition.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Incomeandsources.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Inventory.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Medicalassistance.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Noncashbenefits.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Organization.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Pathstatus.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Project.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Residentialmoveindate.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(RhybcpStatus.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Site.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(Source.class.getName(), projectGroupCode);
		softDeleteByProjectGroupCode(ClientVeteranInfo.class.getName(), projectGroupCode);
	}
	
}

