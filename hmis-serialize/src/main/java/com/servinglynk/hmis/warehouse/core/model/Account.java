package com.servinglynk.hmis.warehouse.core.model;import java.util.Date;import java.util.UUID;import com.fasterxml.jackson.annotation.JsonProperty;import com.fasterxml.jackson.annotation.JsonRootName;@JsonRootName("account")public class Account extends ClientModel {	private UUID accountId;	private String username;	private String emailAddress;	private String password;	private String firstName;	private String middleName;	private String lastName;	private String gender;	private String status;	private Date createdAt;	private Date modifiedAt;	private String otp;	private boolean twoFactorAuthentication=false;		private UUID organizationId;		private String verificationToken;		@JsonProperty("projectGroup")	private ProjectGroup projectGroup;		@JsonProperty("role")	private Role role;		@JsonProperty("profile")	private Profile profile;	public String getUsername() {		return username;	}	public void setUsername(String username) {		this.username = username;	}	public String getEmailAddress() {		return emailAddress;	}	public void setEmailAddress(String emailAddress) {		this.emailAddress = emailAddress;	}	public String getPassword() {		return password;	}	public void setPassword(String password) {		this.password = password;	}	public String getFirstName() {		return firstName;	}	public void setFirstName(String firstName) {		this.firstName = firstName;	}	public String getMiddleName() {		return middleName;	}	public void setMiddleName(String middleName) {		this.middleName = middleName;	}	public String getLastName() {		return lastName;	}	public void setLastName(String lastName) {		this.lastName = lastName;	}	public String getStatus() {		return status;	}	public void setStatus(String status) {		this.status = status;	}	public UUID getAccountId() {		return accountId;	}	public void setAccountId(UUID accountId) {		this.accountId = accountId;	}		public Date getCreatedAt() {		return createdAt;	}	public void setCreatedAt(Date createdAt) {		this.createdAt = createdAt;	}	public Date getModifiedAt() {		return modifiedAt;	}	public void setModifiedAt(Date modifiedAt) {		this.modifiedAt = modifiedAt;	}		public void setLocale(Locale locale) {		 getPreferences().setLocale(locale);	}		@JsonProperty(value="preferences")	private Preferences preferences;		public Preferences getPreferences() {		if(preferences==null) preferences = new Preferences();		return preferences;	}	public void setPreferences(Preferences preferences) {		this.preferences = preferences;	}				public Locale getLocale(){		if(this.getPreferences()!=null)  return getPreferences().getLocale();		return null;	}		public String getNewsletterOptIn(){		if(this.getPreferences()!=null)  return getPreferences().getNewsletterOptIn();		return null;			}			public String getGender(){		return gender;	}		public void setGender(String gender){		this.gender= gender;	}	public Profile getProfile() {		return profile;	}	public void setProfile(Profile profile) {		this.profile = profile;	}	public UUID getOrganizationId() {		return organizationId;	}	public void setOrganizationId(UUID organizationId) {		this.organizationId = organizationId;	}	public String getVerificationToken() {		return verificationToken;	}	public void setVerificationToken(String verificationToken) {		this.verificationToken = verificationToken;	}	public ProjectGroup getProjectGroup() {		return projectGroup;	}	public void setProjectGroup(ProjectGroup projectGroup) {		this.projectGroup = projectGroup;	}	public Role getRole() {		return role;	}	public void setRole(Role role) {		this.role = role;	}	public String getOtp() {		return otp;	}	public void setOtp(String otp) {		this.otp = otp;	}	public boolean isTwoFactorAuthentication() {		return twoFactorAuthentication;	}	public void setTwoFactorAuthentication(boolean twoFactorAuthentication) {		this.twoFactorAuthentication = twoFactorAuthentication;	}		} 