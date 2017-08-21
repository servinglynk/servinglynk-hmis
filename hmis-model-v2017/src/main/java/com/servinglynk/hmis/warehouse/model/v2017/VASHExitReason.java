package com.servinglynk.hmis.warehouse.model.v2017;

import java.util.UUID;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

import org.hibernate.annotations.Type;

public class VASHExitReason {
	
	private java.util.UUID id;
	private java.util.UUID exitId;
	private CMExitReasonEnum cmExitReason;
	/**
	 * @return the id
	 */
	@Id
	@Basic( optional = false )
	@Column( name = "id", nullable = false  ) @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public java.util.UUID getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(java.util.UUID id) {
		this.id = id;
	}
	/**
	 * @return the exitId
	 */
	@Basic( optional = false )
	@Column( name = "exitid", nullable = false  ) @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public java.util.UUID getExitId() {
		return exitId;
	}
	/**
	 * @param exitId the exitId to set
	 */
	public void setExitId(java.util.UUID exitId) {
		this.exitId = exitId;
	}
	
	/**
	 * @return the cmExitReason
	 */
	@Type(type = "com.servinglynk.hmis.warehouse.enums.GeographyEnum")
	@Basic(optional=true)
	@Column(name = "cm_exit_reason")
	public CMExitReasonEnum getCmExitReason() {
		return cmExitReason;
	}
	/**
	 * @param cmExitReason the cmExitReason to set
	 */
	public void setCmExitReason(CMExitReasonEnum cmExitReason) {
		this.cmExitReason = cmExitReason;
	}
	
	
	
	/**
	 * @param id
	 * @param exitId
	 * @param cmExitReason
	 */
	public VASHExitReason(UUID id, UUID exitId, Integer cmExitReason) {
		super();
		this.id = id;
		this.exitId = exitId;
		this.cmExitReason = cmExitReason;
	}
	
	
}
