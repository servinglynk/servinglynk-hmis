package com.servinglynk.hmis.warehouse.dao;

import java.util.Map;
import java.util.UUID;

import com.servinglynk.hmis.warehouse.domain.ExportDomain;
import com.servinglynk.hmis.warehouse.domain.Sources.Source.Export;
import com.servinglynk.hmis.warehouse.model.v2014.HmisBaseModel;
import com.servinglynk.hmis.warehouse.util.BasicDataGenerator;

public class ExportDaoImpl extends ParentDaoImpl implements ExportDao {

	@Override
	public void hydrateStaging(ExportDomain domain , Map<String,HmisBaseModel> exportModelMap, Map<String,HmisBaseModel> relatedModelMap) {
		Export export = domain.getExport();
		Data data=new Data();
		Map<String,HmisBaseModel> modelMap = getModelMap(com.servinglynk.hmis.warehouse.model.v2014.Export.class, getProjectGroupCode(domain));
		com.servinglynk.hmis.warehouse.model.v2014.Export exportModel  = getModelObject(domain, export,data,modelMap);
		exportModel.setExportDate(BasicDataGenerator.getLocalDateTime(export.getExportDate()));
		exportModel.setExportdirective(export.getExportDirective());
		exportModel.setExportperiodtype(export.getExportPeriodType());
//		com.servinglynk.hmis.warehouse.model.staging.HmisUser user = (com.servinglynk.hmis.warehouse.model.staging.HmisUser) get(com.servinglynk.hmis.warehouse.model.staging.HmisUser.class, upload.getUser().getId());
	//	exportModel.setUser(user);
		com.servinglynk.hmis.warehouse.model.v2014.Source sourceEntity = (com.servinglynk.hmis.warehouse.model.v2014.Source) getModel(com.servinglynk.hmis.warehouse.model.v2014.Source.class, domain.getSource().getSourceID(),getProjectGroupCode(domain),true,relatedModelMap);
		exportModel.setSource(sourceEntity);
		performSaveOrUpdate(exportModel);		
		hydrateBulkUploadActivityStaging(data.i, data.j, com.servinglynk.hmis.warehouse.model.v2014.Export.class.getSimpleName(), domain, exportModel);
	}
	
	public com.servinglynk.hmis.warehouse.model.v2014.Export getModelObject(ExportDomain domain, Export export ,Data data, Map<String,HmisBaseModel> modelMap) {
		com.servinglynk.hmis.warehouse.model.v2014.Export exportModel = null;
		// We always insert for a Full refresh and update if the record exists for Delta refresh
		if(!isFullRefresh(domain))
			exportModel = (com.servinglynk.hmis.warehouse.model.v2014.Export) getModel(com.servinglynk.hmis.warehouse.model.v2014.Export.class, export.getExportID(), getProjectGroupCode(domain),false,modelMap);
		
		if(exportModel == null) {
			exportModel = new com.servinglynk.hmis.warehouse.model.v2014.Export();
			exportModel.setId(UUID.randomUUID());
			exportModel.setInserted(true);
			++data.i;
		}else{
			++data.j;
		}
		hydrateCommonFields(exportModel, domain,export.getExportID(),data.i+data.j);
		return exportModel;
	}
}
