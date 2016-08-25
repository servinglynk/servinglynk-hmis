/**
 *
 */
package com.servinglynk.hmis.warehouse.dao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.servinglynk.hmis.warehouse.base.util.ErrorType;
import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export.Employment;
import com.servinglynk.hmis.warehouse.domain.SyncDomain;
import com.servinglynk.hmis.warehouse.enums.DataCollectionStageEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentEmployedEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentEmploymentTypeEnum;
import com.servinglynk.hmis.warehouse.enums.EmploymentNotEmployedReasonEnum;
import com.servinglynk.hmis.warehouse.model.v2015.Enrollment;
import com.servinglynk.hmis.warehouse.model.v2015.Error2015;
import com.servinglynk.hmis.warehouse.model.v2015.HmisBaseModel;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

/**
 * @author Sandeep
 *
 */
public class EmploymentDaoImpl extends ParentDaoImpl implements EmploymentDao {
	private static final Logger logger = LoggerFactory
			.getLogger(EmploymentDaoImpl.class);
	/* (non-Javadoc)
	 * @see com.servinglynk.hmis.warehouse.dao.ParentDao#hydrate(com.servinglynk.hmis.warehouse.dao.Sources.Source.Export, java.util.Map)
	 */
	@Override
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) throws Exception {
		List<Employment> employmentList  = domain.getExport().getEmployment();
		com.servinglynk.hmis.warehouse.model.v2015.Export exportEntity = (com.servinglynk.hmis.warehouse.model.v2015.Export) getModel(com.servinglynk.hmis.warehouse.model.v2015.Export.class,domain.getExport().getExportID(),getProjectGroupCode(domain),false,exportModelMap, domain.getUpload().getId());
		Data data =new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2015.Employment.class, getProjectGroupCode(domain));
		if(employmentList!=null && !employmentList.isEmpty())
		{
			for(Employment employment : employmentList)
			{
				com.servinglynk.hmis.warehouse.model.v2015.Employment employmentModel = null;
				try {
					employmentModel = getModelObject(domain, employment, data, modelMap);
					employmentModel.setDateCreatedFromSource(BasicDataGenerator.getLocalDateTime(employment.getDateCreated()));
					employmentModel.setDateUpdatedFromSource(BasicDataGenerator.getLocalDateTime(employment.getDateUpdated()));
					employmentModel.setEmployed(EmploymentEmployedEnum.lookupEnum(BasicDataGenerator.getStringValue(employment.getEmployed())));
					employmentModel.setEmploymentType(EmploymentEmploymentTypeEnum.lookupEnum(BasicDataGenerator.getStringValue(employment.getEmploymentType())));;
					employmentModel.setNotEmployedReason(EmploymentNotEmployedReasonEnum.lookupEnum(BasicDataGenerator.getStringValue(employment.getNotEmployedReason())));
					employmentModel.setInformationDate(BasicDataGenerator.getLocalDateTime(employment.getInformationDate()));
					Enrollment enrollmentModel = (Enrollment) getModel(Enrollment.class, employment.getProjectEntryID(),getProjectGroupCode(domain),true,relatedModelMap, domain.getUpload().getId());
					employmentModel.setEnrollmentid(enrollmentModel);
					employmentModel.setExport(exportEntity);
					employmentModel.setInformationDate(BasicDataGenerator.getLocalDateTime(employment.getInformationDate()));
					employmentModel.setDataCollectionStage(DataCollectionStageEnum.lookupEnum(BasicDataGenerator.getStringValue(employment.getDataCollectionStage())));

					performSaveOrUpdate(employmentModel);
				}catch(Exception e) {
					String errorMessage = "Exception beause of the Employment::"+employment.getEmploymentID() +" Exception ::"+e.getMessage();
					if(employmentModel != null){
						Error2015 error = new Error2015();
						error.model_id = employmentModel.getId();
						error.bulk_upload_ui = domain.getUpload().getId();
						error.project_group_code = domain.getUpload().getProjectGroupCode();
						error.source_system_id = employmentModel.getSourceSystemId();
						error.type = ErrorType.ERROR;
						error.error_description = errorMessage;
						error.date_created = employmentModel.getDateCreated();
						performSave(error);
					}
					logger.error(errorMessage);
				}
			}
			hydrateBulkUploadActivityStaging(data.i,data.j, com.servinglynk.hmis.warehouse.model.v2015.Employment.class.getSimpleName(), domain,exportEntity);
		}
	}
	public com.servinglynk.hmis.warehouse.model.v2015.Employment getModelObject(ExportDomain domain, Employment Employment ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2015.Employment employmentModel = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			employmentModel = (com.servinglynk.hmis.warehouse.model.v2015.Employment) getModel(com.servinglynk.hmis.warehouse.model.v2015.Employment.class, Employment.getEmploymentID(), getProjectGroupCode(domain),false,modelMap, domain.getUpload().getId());
		
		if(employmentModel == null) {
			employmentModel = new com.servinglynk.hmis.warehouse.model.v2015.Employment();
			employmentModel.setId(UUID.randomUUID());
			employmentModel.setInserted(true);
			++data.i;
		}else{
			++data.j;
		}
		hydrateCommonFields(employmentModel, domain,Employment.getEmploymentID(),data.i+data.j);
		return employmentModel;
	}
	@Override
	public void hydrateHBASE(SyncDomain syncDomain) {
		// TODO Auto-generated method stub

	}
	   public com.servinglynk.hmis.warehouse.model.v2015.Employment createEmployment(com.servinglynk.hmis.warehouse.model.v2015.Employment employment){
	       employment.setId(UUID.randomUUID());
	       insert(employment);
	       return employment;
	   }
	   public com.servinglynk.hmis.warehouse.model.v2015.Employment updateEmployment(com.servinglynk.hmis.warehouse.model.v2015.Employment employment){
	       update(employment);
	       return employment;
	   }
	   public void deleteEmployment(com.servinglynk.hmis.warehouse.model.v2015.Employment employment){
	       delete(employment);
	   }
	   public com.servinglynk.hmis.warehouse.model.v2015.Employment getEmploymentById(UUID employmentId){
	       return (com.servinglynk.hmis.warehouse.model.v2015.Employment) get(com.servinglynk.hmis.warehouse.model.v2015.Employment.class, employmentId);
	   }
	   public List<com.servinglynk.hmis.warehouse.model.v2015.Employment> getAllEnrollmentEmployments(UUID enrollmentId,Integer startIndex, Integer maxItems){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2015.Employment.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return (List<com.servinglynk.hmis.warehouse.model.v2015.Employment>) findByCriteria(criteria,startIndex,maxItems);
	   }
	   public long getEnrollmentEmploymentsCount(UUID enrollmentId){
	       DetachedCriteria criteria=DetachedCriteria.forClass(com.servinglynk.hmis.warehouse.model.v2015.Employment.class);
	       criteria.createAlias("enrollmentid", "enrollmentid");
	       criteria.add(Restrictions.eq("enrollmentid.id", enrollmentId));
	       return countRows(criteria);
	   }
}
