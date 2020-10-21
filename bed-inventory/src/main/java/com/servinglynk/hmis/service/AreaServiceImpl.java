package com.servinglynk.hmis.service;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servinglynk.hmis.entity.AreaEntity;
import com.servinglynk.hmis.entity.ShelterEntity;
import com.servinglynk.hmis.model.AreaModel;
import com.servinglynk.hmis.model.Areas;
import com.servinglynk.hmis.model.SortedPagination;
import com.servinglynk.hmis.service.converter.AreaConverter;
import com.servinglynk.hmis.service.exception.ResourceNotFoundException;
@Service
public class AreaServiceImpl extends BaseService implements AreaService {

	@Transactional
	public AreaModel createArea(AreaModel area) {
		AreaEntity entity = AreaConverter.modelToEntity(area,null);
		ShelterEntity shelterEntity = daoFactory.getShelterRepository().findByIdAndProjectGroupCodeAndDeleted(area.getShelter().getId(),SecurityContextUtil.getUserProjectGroup(),false);
		if(shelterEntity == null) throw new ResourceNotFoundException("Shelter "+area.getShelter().getId()+" not found");
		entity.setShelterEntity(shelterEntity);
		daoFactory.getAreaRepository().save(entity);
		area.setId(entity.getId());
		return area;
	}
	
	@Transactional
	public void updateArea(AreaModel area) {
		AreaEntity entity =  daoFactory.getAreaRepository().findByIdAndProjectGroupCodeAndDeleted(area.getId(),SecurityContextUtil.getUserProjectGroup(),false);
		if(entity == null) throw new ResourceNotFoundException("Area "+area.getId()+" not found");
		entity = AreaConverter.modelToEntity(area,entity);
		daoFactory.getAreaRepository().save(entity);
	}
	
	@Transactional
	public void deleteArea(UUID areaId) {
		AreaEntity entity =  daoFactory.getAreaRepository().findByIdAndProjectGroupCodeAndDeleted(areaId,SecurityContextUtil.getUserProjectGroup(),false);
		if(entity == null) throw new ResourceNotFoundException("Area "+areaId+" not found");
		daoFactory.getAreaRepository().delete(entity);
	}
	
	@Transactional
	public AreaModel getArea(UUID areaId) {
		AreaEntity entity =  daoFactory.getAreaRepository().findByIdAndProjectGroupCodeAndDeleted(areaId,SecurityContextUtil.getUserProjectGroup(),false);
		if(entity == null) throw new ResourceNotFoundException("Area "+areaId+" not found");		
		return AreaConverter.entityToModel(entity);
	}
	
	@Transactional
	public Areas getAreas(UUID shelterId,Pageable pageable) {
		Areas areas = new Areas();
		ShelterEntity shelterEntity = daoFactory.getShelterRepository().findByIdAndProjectGroupCodeAndDeleted(shelterId,SecurityContextUtil.getUserProjectGroup(),false);
		if(shelterEntity == null) throw new ResourceNotFoundException("Shelter "+shelterId+" not found");
		Page<AreaEntity> entityPage = daoFactory.getAreaRepository().findByShelterEntity(shelterEntity,pageable);
		for(AreaEntity areaEntity : entityPage.getContent()) {
			areas.addArea(AreaConverter.entityToModel(areaEntity));
		}
		
		 SortedPagination pagination = new SortedPagination();
		   
	        pagination.setFrom(pageable.getPageNumber() * pageable.getPageSize());
	        pagination.setReturned(entityPage.getContent().size());
	        pagination.setTotal((int)entityPage.getTotalElements());
	        areas.setPagination(pagination);
		return areas;
	}	
}