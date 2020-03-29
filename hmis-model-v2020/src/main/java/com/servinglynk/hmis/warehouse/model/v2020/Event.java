package com.servinglynk.hmis.warehouse.model.v2020;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;
import java.util.WeakHashMap;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.hibernate.proxy.HibernateProxy;

import com.servinglynk.hmis.warehouse.enums.EventTypeEnum;
import com.servinglynk.hmis.warehouse.enums.NoYesEnum;


/**
 * Object mapping for hibernate-handled table: affiliation.
 *
 *
 * @author autogenerated
 */


@Entity(name = "event_v2020")
@Table(name = "event", catalog = "hmis", schema = "v2020")
public class Event extends HmisBaseModel implements Cloneable, Serializable {

	/** Serial Version UID. */
	private static final long serialVersionUID = -4677579144054660425L;

	/** Use a WeakHashMap so entries will be garbage collected once all entities
		referring to a saved hash are garbage collected themselves. */
	private static final Map<Serializable, java.util.UUID> SAVED_HASHES =
		Collections.synchronizedMap(new WeakHashMap<Serializable, java.util.UUID>());

	/** hashCode temporary storage. */
	private volatile java.util.UUID hashCode;
	private UUID id;
	private Enrollment enrollmentid;
	private UUID clientId;
	private LocalDateTime eventDate;
	private LocalDateTime resultDate;
	private String locationcrisisorphhousing;
	private String referralResult;
	private EventTypeEnum event;
	private NoYesEnum referralCaseManageAfter;
	private NoYesEnum probsoldivrrresult;
	

	/*   event_date  date,
   event  v2020.event_type,
   referral_case_manage_after  v2020.no_yes_refused,
   locationcrisisorphhousing  character varying(500),
   referral_result character varying(500),
   result_date date,
   */
	
	/** Field mapping. */
	/**
	 * Default constructor, mainly for hibernate use.
	 */
	public Event() {
		// Default constructor
	}

	/**
	 * @return the eventDate
	 */
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column( name = "event_date")
	public LocalDateTime getEventDate() {
		return eventDate;
	}

	/**
	 * @param eventDate the eventDate to set
	 */
	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	/**
	 * @return the clientid
	 */
	@Basic( optional = false )
	@Column( name = "client_id", nullable = false  ) @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public UUID getClientId() {
		return clientId;
	}

	/**
	 * @param clientid the clientid to set
	 */
	public void setClientId(UUID clientid) {
		this.clientId = clientid;
	}
	/**
	 * @return the resultDate
	 */
	@Type(type="org.jadira.usertype.dateandtime.threeten.PersistentLocalDateTime")
	@Column( name = "result_date")
	public LocalDateTime getResultDate() {
		return resultDate;
	}

	/**
	 * @param resultDate the resultDate to set
	 */
	public void setResultDate(LocalDateTime resultDate) {
		this.resultDate = resultDate;
	}

	/**
	 * @return the locationcrisisorphhousing
	 */
	@Basic( optional = true )
	@Column( name = "locationcrisisorphhousing", length = 500  )
	public String getLocationcrisisorphhousing() {
		return locationcrisisorphhousing;
	}

	/**
	 * @param locationcrisisorphhousing the locationcrisisorphhousing to set
	 */
	public void setLocationcrisisorphhousing(String locationcrisisorphhousing) {
		this.locationcrisisorphhousing = locationcrisisorphhousing;
	}

	/**
	 * @return the referralResult
	 */
	@Basic( optional = true )
	@Column( name = "referral_result", length = 500  )
	public String getReferralResult() {
		return referralResult;
	}

	/**
	 * @param referralResult the referralResult to set
	 */
	public void setReferralResult(String referralResult) {
		this.referralResult = referralResult;
	}

	/**
	 * @return the event
	 */
	@Type(type = "com.servinglynk.hmis.warehouse.enums.EventTypeEnumType")
	@Basic( optional = true )
	@Column( name = "event" )
	public EventTypeEnum getEvent() {
		return event;
	}

	/**
	 * @param event the event to set
	 */
	public void setEvent(EventTypeEnum event) {
		this.event = event;
	}

	/**
	 * @return the referralCaseManageAfter
	 */
	
	@Type(type = "com.servinglynk.hmis.warehouse.enums.NoYesEnumType")
	@Basic( optional = true )
	@Column( name = "referral_case_manage_after" )
	public NoYesEnum getReferralCaseManageAfter() {
		return referralCaseManageAfter;
	}

	/**
	 * @param referralCaseManageAfter the referralCaseManageAfter to set
	 */
	public void setReferralCaseManageAfter(NoYesEnum referralCaseManageAfter) {
		this.referralCaseManageAfter = referralCaseManageAfter;
	}

	
	/**
	 * @return the probsoldivrrresult
	 */
	@Type(type = "com.servinglynk.hmis.warehouse.enums.NoYesEnumType")
	@Basic( optional = true )
	@Column( name = "probsoldivrrresult" )
	public NoYesEnum getProbsoldivrrresult() {
		return probsoldivrrresult;
	}

	/**
	 * @param probsoldivrrresult the probsoldivrrresult to set
	 */
	public void setProbsoldivrrresult(NoYesEnum probsoldivrrresult) {
		this.probsoldivrrresult = probsoldivrrresult;
	}

	/** Return the type of this class. Useful for when dealing with proxies.
	* @return Defining class.
	*/
	@Transient
	public Class<?> getClassType() {
		return Event.class;
	}

	 /**
	 * Return the value associated with the column: id.
	 * @return A java.util.UUID object (this.id)
	 */
	  @Id
	  @Basic( optional = false )
	  @Column( name = "id", nullable = false  ) @org.hibernate.annotations.Type(type="org.hibernate.type.PostgresUUIDType")
	public java.util.UUID getId() {
		return this.id;

	}



	 /**
	 * Set the value related to the column: id.
	 * @param id the id value you wish to set
	 */
	public void setId(final java.util.UUID id) {
		// If we've just been persisted and hashCode has been
		// returned then make sure other entities with this
		// ID return the already returned hash code
		if ( (this.id == null ) &&
				(id != null) &&
				(this.hashCode != null) ) {
		SAVED_HASHES.put( id, this.hashCode );
		}
		this.id = id;
	}


	 /**
		 * Return the value associated with the column: enrollmentid.
		 * @return A Enrollment object (this.enrollmentid)
		 */
		@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
		@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
		@Basic( optional = true )
		@JoinColumn(name = "enrollmentid", nullable = true )
		public Enrollment getEnrollmentid() {
			return this.enrollmentid;

		}



		 /**
		 * Set the value related to the column: enrollmentid.
		 * @param enrollmentid the enrollmentid value you wish to set
		 */
		public void setEnrollmentid(final Enrollment enrollmentid) {
			this.enrollmentid = enrollmentid;
		}
		
	/** Field mapping. */
	protected Export export;
	 /**
	 * Return the value associated with the column: export.
	 * @return A Export object (this.export)
	 */
	@ManyToOne( cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.LAZY )
	@org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE})
	@Basic( optional = true )
	@JoinColumn(name = "export_id", nullable = true )
	public Export getExport() {
		return this.export;

	}

	/**
	 * Set the value related to the column: export.
	 * @param export the export value you wish to set
	 */
	public void setExport(final Export export) {
		this.export = export;
	}
   /**
    * Deep copy.
	* @return cloned object
	* @throws CloneNotSupportedException on error
    */
    @Override
    public Event clone() throws CloneNotSupportedException {

        final Event copy = (Event)super.clone();

		copy.setDateCreated(this.getDateCreated());
		copy.setDateCreatedFromSource(this.getDateCreatedFromSource());
		copy.setDateUpdated(this.getDateUpdated());
		copy.setDateUpdatedFromSource(this.getDateUpdatedFromSource());
		copy.setDeleted(this.isDeleted());
		copy.setExport(this.getExport());
		copy.setId(this.getId());
		copy.setParentId(this.getParentId());
		copy.setProjectGroupCode(this.getProjectGroupCode());
		copy.setSync(this.isSync());
		copy.setUserId(this.getUserId());
		copy.setVersion(this.getVersion());
		return copy;
	}

	/** Provides toString implementation.
	 * @see java.lang.Object#toString()
	 * @return String representation of this class.
	 */
	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();

		sb.append("dateCreated: " + this.getDateCreated() + ", ");
		sb.append("dateCreatedFromSource: " + this.getDateCreatedFromSource() + ", ");
		sb.append("dateUpdated: " + this.getDateUpdated() + ", ");
		sb.append("dateUpdatedFromSource: " + this.getDateUpdatedFromSource() + ", ");
		sb.append("deleted: " + this.isDeleted() + ", ");
		sb.append("id: " + this.getId() + ", ");
		sb.append("parentId: " + this.getParentId() + ", ");
		sb.append("projectGroupCode: " + this.getProjectGroupCode() + ", ");
		sb.append("sync: " + this.isSync() + ", ");
		sb.append("userId: " + this.getUserId() + ", ");
		sb.append("version: " + this.getVersion());
		return sb.toString();
	}


	/** Equals implementation.
	 * @see java.lang.Object#equals(java.lang.Object)
	 * @param aThat Object to compare with
	 * @return true/false
	 */
	@Override
	public boolean equals(final Object aThat) {
		Object proxyThat = aThat;

		if ( this == aThat ) {
			 return true;
		}


		if (aThat instanceof HibernateProxy) {
 			// narrow down the proxy to the class we are dealing with.
 			try {
				proxyThat = ((HibernateProxy) aThat).getHibernateLazyInitializer().getImplementation();
			} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		   	}
		}
		if (aThat == null)  {
			 return false;
		}

		final Event that;
		try {
			that = (Event) proxyThat;
			if ( !(that.getClassType().equals(this.getClassType()))){
				return false;
			}
		} catch (org.hibernate.ObjectNotFoundException e) {
				return false;
		} catch (ClassCastException e) {
				return false;
		}


		boolean result = true;
		result = result && (((this.getId() == null) && ( that.getId() == null)) || (this.getId() != null  && this.getId().equals(that.getId())));
		result = result && (((getDateCreated() == null) && (that.getDateCreated() == null)) || (getDateCreated() != null && getDateCreated().equals(that.getDateCreated())));
		result = result && (((getDateCreatedFromSource() == null) && (that.getDateCreatedFromSource() == null)) || (getDateCreatedFromSource() != null && getDateCreatedFromSource().equals(that.getDateCreatedFromSource())));
		result = result && (((getDateUpdated() == null) && (that.getDateUpdated() == null)) || (getDateUpdated() != null && getDateUpdated().equals(that.getDateUpdated())));
		result = result && (((getDateUpdatedFromSource() == null) && (that.getDateUpdatedFromSource() == null)) || (getDateUpdatedFromSource() != null && getDateUpdatedFromSource().equals(that.getDateUpdatedFromSource())));
		result = result && (((getExport() == null) && (that.getExport() == null)) || (getExport() != null && getExport().getId().equals(that.getExport().getId())));
		result = result && (((getParentId() == null) && (that.getParentId() == null)) || (getParentId() != null && getParentId().equals(that.getParentId())));
		result = result && (((getEnrollmentid() == null) && (that.getEnrollmentid() == null)) || (getEnrollmentid() != null && getEnrollmentid().getId().equals(that.getEnrollmentid().getId())));
		result = result && (((getProjectGroupCode() == null) && (that.getProjectGroupCode() == null)) || (getProjectGroupCode() != null && getProjectGroupCode().equals(that.getProjectGroupCode())));
		result = result && (((getUserId() == null) && (that.getUserId() == null)) || (getUserId() != null && getUserId().equals(that.getUserId())));
		result = result && (((getVersion() == null) && (that.getVersion() == null)) || (getVersion() != null && getVersion().equals(that.getVersion())));
		return result;
	}
}
