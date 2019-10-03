package com.servinglynk.hmis.warehouse.service.converter;

import com.servinglynk.hmis.warehouse.core.model.Healthstatus;
import com.servinglynk.hmis.warehouse.enums.DataCollectionStageEnum;
import com.servinglynk.hmis.warehouse.enums.HealthStatusHealthCategoryEnum;
import com.servinglynk.hmis.warehouse.enums.HealthStatusHealthStatusEnum;
public class HealthstatusConverter  extends BaseConverter {

   public static com.servinglynk.hmis.warehouse.model.v2020.HealthStatus modelToEntity (Healthstatus model ,com.servinglynk.hmis.warehouse.model.v2020.HealthStatus entity) {
       if(entity==null) entity = new com.servinglynk.hmis.warehouse.model.v2020.HealthStatus();
       entity.setId(model.getHealthstatusId());
       if(model.getDataCollectionStage() !=null)
    	   	entity.setDataCollectionStage(DataCollectionStageEnum.lookupEnum(model.getDataCollectionStage().toString()));
       if(model.getInformationDate()!=null)
    	       entity.setInformationDate(model.getInformationDate());
		 if(model.getHealthCategory()!=null)
		       entity.setHealthCategory(HealthStatusHealthCategoryEnum.lookupEnum(model.getHealthCategory().toString()));
		 if(model.getHealthStatus()!=null)
		       entity.setHealthStatus(HealthStatusHealthStatusEnum.lookupEnum(model.getHealthStatus().toString()));
		 if(model.getDueDate()!=null)
		       entity.setDueDate(model.getDueDate());
		if(model.getSubmissionDate()!=null) entity.setSubmissionDate(model.getSubmissionDate());
       return entity;
   }


   public static Healthstatus entityToModel (com.servinglynk.hmis.warehouse.model.v2020.HealthStatus entity) {
       Healthstatus model = new Healthstatus();
       model.setHealthstatusId(entity.getId());
       if(entity.getInformationDate()!=null)
           model.setInformationDate(entity.getInformationDate());
        if(entity.getDataCollectionStage() !=null)
            model.setDataCollectionStage(Integer.parseInt(entity.getDataCollectionStage().getValue()));

if(entity.getHealthCategory()!=null)
       model.setHealthCategory(Integer.parseInt(entity.getHealthCategory().getValue()));
if(entity.getHealthStatus()!=null)
       model.setHealthStatus(Integer.parseInt(entity.getHealthStatus().getValue()));
if(entity.getDueDate()!=null)
       model.setDueDate(entity.getDueDate());

if(entity.getSubmissionDate()!=null) model.setSubmissionDate(entity.getSubmissionDate());

       return model;
   }


}
