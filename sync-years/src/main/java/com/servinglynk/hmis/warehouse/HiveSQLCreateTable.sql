CREATE EXTERNAL TABLE IF NOT EXISTS client(PersonalID string ,dedup_client_id string ,name_data_quality string ,name_data_quality_desc string ,ssn_data_quality string ,ssn_data_quality_desc string ,dob date ,dob_data_quality string ,dob_data_quality_desc string,gender string ,gender_desc string,other_gender string ,ethnicity string ,ethnicity_desc string,race string ,race_desc string ,veteran_status string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:dedup_client_id,CF:name_data_quality,CF:name_data_quality_desc,CF:ssn_data_quality,CF:ssn_data_quality_desc,CF:dob,CF:dob_data_quality,CF:dob_data_quality_desc,CF:gender,CF:gender_desc,CF:other_gender,CF:ethnicity,CF:ethnicity_desc,CF:race,CF:race_desc,CF:veteran_status") TBLPROPERTIES ("hbase.table.name" = "client_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS client_veteran_info(key string,id string ,year_entrd_service string ,year_seperated string ,world_war_2 string ,korean_war string ,vietnam_war string ,desert_storm string ,afghanistan_oef string ,iraq_oif string ,iraq_ond string ,other_theater string ,military_branch string ,military_branch_desc string ,discharge_status string ,discharge_status_desc string,date_created timestamp ,date_updated timestamp ,user_id string ,client_id string ,export_id string,deleted string,project_group_code string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:year_entrd_service,CF:year_seperated,CF:world_war_2,CF:korean_war,CF:vietnam_war,CF:desert_storm,CF:afghanistan_oef,CF:iraq_oif,CF:iraq_ond,CF:other_theater,CF:military_branch,CF:military_branch_desc,CF:discharge_status,CF:discharge_status_desc,CF:date_created,CF:date_updated,CF:user_id,CF:client_id,CF:export_id,CF:deleted, CF:project_group_code") TBLPROPERTIES ("hbase.table.name" = "veteran_info_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS contact(key string,id string ,contact_date timestamp ,contact_location string ,contact_location_desc string ,enrollmentid string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:contact_date,CF:contact_location,CF:contact_location_desc,CF:enrollmentid") TBLPROPERTIES ("hbase.table.name" = "contact_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS dateofengagement(key string,dateofengagement timestamp ,id string ,projectEntryID string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:dateofengagement,CF:id,CF:enrollmentid") TBLPROPERTIES ("hbase.table.name" = "dateofengagement_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS disabilities(key string,id string ,disabilityresponse string ,disabilityresponse_desc string ,disabilitytype string ,disabilitytype_desc string,documentationonfile string ,documentationonfile_desc string ,indefiniteandimpairs string ,indefiniteandimpairs_desc string ,pathhowconfirmed string ,pathhowconfirmed_desc string ,pathsmiinformation string ,pathsmiinformation_desc string ,projectEntryID string ,receivingservices string ,receivingservices_desc string  ,tcellcountavailable string ,tcellcountavailable_desc string ,tcellcount string ,tcellcount_desc string ,tcellcountsource string ,tcellcountsource_desc string ,viral_load_available string ,viral_load_available_desc string ,viral_load string ,viral_load_desc string ,viral_load_source string ,viral_load_source_desc string ,information_date timestamp) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:disabilityresponse,CF:disabilityresponse_desc,CF:disabilitytype,CF:disabilitytype_desc,CF:documentationonfile,CF:documentationonfile_desc,CF:indefiniteandimpairs,CF:indefiniteandimpairs_desc,CF:pathhowconfirmed,CF:pathhowconfirmed_desc,CF:pathsmiinformation,CF:pathsmiinformation_desc,CF:enrollmentid,CF:receivingservices,CF:receivingservices_desc,CF:tcellcountavailable,CF:tcellcountavailable_desc,CF:tcellcount,CF:tcellcount_desc,CF:tcellcountsource,CF:tcellcountsource_desc,CF:viral_load_available,CF:viral_load_available_desc,CF:viral_load,CF:viral_load_desc,CF:viral_load_source,CF:viral_load_source_desc,CF:information_date") TBLPROPERTIES ("hbase.table.name" = "disabilities_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS domesticviolence(key string,id string ,domesticviolencevictim string ,domesticviolencevictim_desc string ,projectEntryID string ,whenoccurred string ,whenoccurred_desc string ,currently_fleeing string ,currently_fleeing_desc string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:domesticviolencevictim,CF:domesticviolencevictim_desc,CF:enrollmentid,CF:whenoccurred,CF:whenoccurred_desc,CF:currently_fleeing,CF:currently_fleeing_desc") TBLPROPERTIES ("hbase.table.name" = "domesticviolence_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS education(key string,id string ,lastgradecompleted string ,lastgradecompleted_desc string ,school_status string ,school_status_desc string ,enrollmentid string ,project_group_code string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:lastgradecompleted,CF:lastgradecompleted_desc,CF:school_status,CF:school_status_desc,CF:enrollmentid,CF:project_group_code" ) TBLPROPERTIES ("hbase.table.name" = "education_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS employment(key string,id string ,information_date timestamp ,employed string ,employment_type string ,employment_type_desc string,not_employed_reason string ,not_employed_reason_desc string ,enrollmentid string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:information_date,CF:employed,CF:employment_type,CF:employment_type_desc,CF:not_employed_reason,CF:not_employed_reason_desc,CF:enrollmentid") TBLPROPERTIES ("hbase.table.name" = "employment_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS enrollment(key string,id string ,person_id string ,entrydate timestamp ,householdid string ,relationshiptohoh string ,relationshiptohoh_desc string ,otherresidenceprior string ,residenceprior string ,residenceprior_desc string ,residencepriorlengthofstay string ,residencepriorlengthofstay_desc string ,disablingcondition string ,disablingcondition_desc string ,housingstatus string ,housingstatus_desc string ,entryfromstreetessh string ,datetostreetessh string ,timeshomelesspastthreeyears string ,timeshomelesspastthreeyears_desc string ,monthshomelesspastthreeyears string ,monthshomelesspastthreeyears_desc string ,ProjectID string,string chronichomeless  ) STORED BY 'org.apae.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:client_id,CF:entrydate,CF:householdid,CF:relationshiptohoh,CF:relationshiptohoh_desc,CF:otherresidenceprior,CF:residenceprior,CF:residenceprior_desc,CF:residencepriorlengthofstay,CF:residencepriorlengthofstay_desc,CF:disablingcondition,CF:disablingcondition_desc,CF:housingstatus,CF:housingstatus_desc,CF:entryfromstreetessh,CF:datetostreetessh,CF:timeshomelesspastthreeyears,CF:timeshomelesspastthreeyears_desc,CF:monthshomelesspastthreeyears,CF:monthshomelesspastthreeyears_desc,CF:projectid,CF:chronichomeless") TBLPROPERTIES ("hbase.table.name" = "enrollment_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS enrollment_coc(key string,id string ,client_code string ,projectEntryID string ,information_date timestamp ,project_group_code string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:client_code,CF:enrollmentid,CF:information_date,CF:project_group_code") TBLPROPERTIES ("hbase.table.name" = "enrollment_coc_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS entryrhsp(key string,id string ,worst_housing_situation string ,enrollmentid string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:worst_housing_situation,CF:enrollmentid") TBLPROPERTIES ("hbase.table.name" = "entryrhsp_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS entryrhy(key string,id string,projectEntryID string,sexual_orientation string,sexual_orientation_desc string,formerly_ward_child_welfr_forest_care string,formerly_ward_child_welfr_forest_care_desc string,  years_child_welfr_forest_care string,years_child_welfr_forest_care_desc string,  months_child_welfr_forest_care string,months_child_welfr_forest_care_desc string,  formerly_ward_of_juvenile_justice string,formerly_ward_of_juvenile_justice_desc string,years_juvenile_justice string,years_juvenile_justice_desc string,  house_hold_dynamics string,house_hold_dynamics_desc string,  sexual_orientatiion_gender_identity_youth string,sexual_orientatiion_gender_identity_youth_desc string, sexual_orientatiion_gender_identity_family_mbr string,sexual_orientatiion_gender_identity_family_mbr_desc string,  housing_issues_youth string,housing_issues_youth_desc string,  housing_issues_family_mbr string,housing_issues_family_mbr_desc string,school_education_issues_youth string,school_education_issues_youth_desc string,school_education_issues_family_mbr string,school_education_issues_family_mbr_desc string, unemployement_youth string,unemployement_youth_desc string,unemployement_family_mbr string,unemployement_family_mbr_desc string,  mental_health_issues_youth string,mental_health_issues_youth_desc string,mental_health_issues_family_mbr string,mental_health_issues_family_mbr_desc string,health_issues_youth string,health_issues_youth_desc string,health_issues_family_mbr string,health_issues_family_mbr_desc string,  physical_disability_youth string,physical_disability_youth_desc string,physical_disability_family_mbr string,physical_disability_family_mbr_desc string,mental_disability_youth string,mental_disability_youth_desc string,mental_disability_family_mbr string,mental_disability_family_mbr_desc string,abuse_and_neglect_youth string,abuse_and_neglect_youth_desc string,abuse_and_neglect_family_mbr string,abuse_and_neglect_family_mbr_desc string,alcohol_drug_abuse_youth string,alcohol_drug_abuse_youth_desc string,alcohol_drug_abuse_family_mbr string,alcohol_drug_abuse_family_mbr_desc string,insufficient_income_to_support_youth string,insufficient_income_to_support_youth_desc string,active_military_parent string,active_military_parent_desc string,incarcerated_parent string,incarcerated_parent_desc string,incarcerated_parent_status string,incarcerated_parent_status_desc string,referral_source string,referral_source_desc string,count_out_reach_referral_approaches string,count_out_reach_referral_approaches_desc string,exchange_for_sex string,exchange_for_sex_desc string,exchange_for_sex_past_three_months string,exchange_for_sex_past_three_months_desc string,count_of_exchange_for_sex string,count_of_exchange_for_sex_desc string,asked_of_forced_to_exchange_for_sex string,asked_of_forced_to_exchange_for_sex_desc string,asked_of_forced_to_exchange_for_sex_past_three_months string,asked_of_forced_to_exchange_for_sex_past_three_months_desc string,work_place_violence_threat string,work_place_violence_threat_desc string,work_place_promise_difference string,work_place_promise_difference_desc string,coerced_to_continue_work string,coerced_to_continue_work_desc string,labor_exploit_past_three_months string,labor_exploit_past_three_months_desc string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:enrollmentid,CF:sexual_orientation,CF:sexual_orientation_desc,CF:formerly_ward_child_welfr_forest_care,CF:formerly_ward_child_welfr_forest_care_desc,CF:years_child_welfr_forest_care,CF:years_child_welfr_forest_care_desc,CF:months_child_welfr_forest_care,CF:months_child_welfr_forest_care_desc,CF:formerly_ward_of_juvenile_justice,CF:formerly_ward_of_juvenile_justice_desc,CF:years_juvenile_justice,CF:years_juvenile_justice_desc,CF:house_hold_dynamics,CF:house_hold_dynamics_desc,CF:sexual_orientatiion_gender_identity_youth,CF:sexual_orientatiion_gender_identity_youth_desc,CF:sexual_orientatiion_gender_identity_family_mbr,CF:sexual_orientatiion_gender_identity_family_mbr_desc,CF:housing_issues_youth,CF:housing_issues_youth_desc,CF:housing_issues_family_mbr,CF:housing_issues_family_mbr_desc,CF:school_education_issues_youth,CF:school_education_issues_youth_desc,CF:school_education_issues_family_mbr,CF:school_education_issues_family_mbr_desc,CF:unemployement_youth,CF:unemployement_youth_desc,CF:unemployement_family_mbr,CF:unemployement_family_mbr_desc,CF:mental_health_issues_youth,CF:mental_health_issues_youth_desc,CF:mental_health_issues_family_mbr,CF:mental_health_issues_family_mbr_desc,CF:health_issues_youth,CF:health_issues_youth_desc,CF:health_issues_family_mbr,CF:health_issues_family_mbr_desc,CF:physical_disability_youth,CF:physical_disability_youth_desc,CF:physical_disability_family_mbr,CF:physical_disability_family_mbr_desc,CF:mental_disability_youth,CF:mental_disability_youth_desc,CF:mental_disability_family_mbr,CF:mental_disability_family_mbr_desc,CF:abuse_and_neglect_youth,CF:abuse_and_neglect_youth_desc,CF:abuse_and_neglect_family_mbr,CF:abuse_and_neglect_family_mbr_desc,CF:alcohol_drug_abuse_youth,CF:alcohol_drug_abuse_youth_desc,CF:alcohol_drug_abuse_family_mbr,CF:alcohol_drug_abuse_family_mbr_desc,CF:insufficient_income_to_support_youth,CF:insufficient_income_to_support_youth_desc,CF:active_military_parent,CF:active_military_parent_desc,CF:incarcerated_parent,CF:incarcerated_parent_desc,CF:incarcerated_parent_status,CF:incarcerated_parent_status_desc,CF:referral_source,CF:referral_source_desc,CF:count_out_reach_referral_approaches,CF:count_out_reach_referral_approaches_desc,CF:exchange_for_sex,CF:exchange_for_sex_desc,CF:exchange_for_sex_past_three_months,CF:exchange_for_sex_past_three_months_desc,CF:count_of_exchange_for_sex,CF:count_of_exchange_for_sex_desc,CF:asked_of_forced_to_exchange_for_sex,CF:asked_of_forced_to_exchange_for_sex_desc,CF:asked_of_forced_to_exchange_for_sex_past_three_months,CF:asked_of_forced_to_exchange_for_sex_past_three_months_desc,CF:work_place_violence_threat,CF:work_place_violence_threat_desc,CF:work_place_promise_difference,CF:work_place_promise_difference_desc,CF:coerced_to_continue_work,CF:coerced_to_continue_work_desc,CF:labor_exploit_past_three_months,CF:labor_exploit_past_three_months_desc") TBLPROPERTIES ("hbase.table.name" = "entryrhy_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS exit(key string,destination string ,destination_desc string,exitdate timestamp ,id string ,otherdestination string ,projectEntryID string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:destination,CF:destination_desc,CF:exitdate,CF:id,CF:otherdestination,CF:enrollmentid") TBLPROPERTIES ("hbase.table.name" = "exit_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS exitpath(key string,connection_with_soar string ,connection_with_soar_desc string ,project_group_code string,exitid string ,id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:connection_with_soar,CF:connection_with_soar_desc,CF:project_group_code,CF:exitid,CF:id") TBLPROPERTIES ("hbase.table.name" = "exitpath_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS exithousingassessment(key string,id string ,exitid string ,housingassessment string ,housingassessment_desc string ,subsidyinformation string ,subsidyinformation_desc string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:exitid,CF:housingassessment,CF:housingassessment_desc,CF:subsidyinformation,CF:subsidyinformation_desc") TBLPROPERTIES ("hbase.table.name" = "exithousingassessment_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS exitplansactions(key string,assistancemainstreambenefits string ,exitcounseling string ,exitid string ,id string ,furtherfollowupservices string ,otheraftercareplanoraction string ,permanenthousingplacement string ,resourcepackage string ,scheduledfollowupcontacts string ,temporaryshelterplacement string ,writtenaftercareplan string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:assistancemainstreambenefits,CF:exitcounseling,CF:exitid,CF:id,CF:furtherfollowupservices,CF:otheraftercareplanoraction,CF:permanenthousingplacement,CF:resourcepackage,CF:scheduledfollowupcontacts,CF:temporaryshelterplacement,CF:writtenaftercareplan") TBLPROPERTIES ("hbase.table.name" = "exitplansactions_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS exitrhy(key string,id string ,exitid string ,project_completion_status string ,early_exit_reason string ,early_exit_reason_desc string ,family_reunification_achieved string ,family_reunification_achieved_desc string ,written_after_care_plan string ,written_after_care_plan_desc string ,assistance_main_stream_benefits string ,assistance_main_stream_benefits_desc string ,permenant_housing_placement string ,permenant_housing_placement_desc string ,temp_shelter_placement string ,temp_shelter_placement_desc string ,exit_counseling string ,exit_counseling_desc string ,further_followup_services string ,further_followup_services_desc string ,scheduled_followup_contacts string ,scheduled_followup_contacts_desc string ,resource_package string ,resource_package_desc string ,other_aftercare_plan_or_action string ,other_aftercare_plan_or_action_desc string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:exitid,CF:project_completion_status,CF:early_exit_reason,CF:early_exit_reason_desc,CF:family_reunification_achieved,CF:family_reunification_achieved_desc,CF:written_after_care_plan,CF:written_after_care_plan_desc,CF:assistance_main_stream_benefits,CF:assistance_main_stream_benefits_desc,CF:permenant_housing_placement,CF:permenant_housing_placement_desc,CF:temp_shelter_placement,CF:temp_shelter_placement_desc,CF:exit_counseling,CF:exit_counseling_desc,CF:further_followup_services,CF:further_followup_services_desc,CF:scheduled_followup_contacts,CF:scheduled_followup_contacts_desc,CF:resource_package,CF:resource_package_desc,CF:other_aftercare_plan_or_action,CF:other_aftercare_plan_or_action_desc") TBLPROPERTIES ("hbase.table.name" = "exitrhy_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS funder(key string,id string ,enddate timestamp ,funder string ,funder_desc string,grantid string ,projectid string ,startdate timestamp  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:enddate,CF:funder,CF:funder_desc,CF:grantid,CF:project_id,CF:startdate") TBLPROPERTIES ("hbase.table.name" = "funder_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS health_status(key string,id string ,enrollmentid string ,information_date timestamp ,health_category string ,health_category_desc string,health_status string ,health_status_desc string,due_date timestamp  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:enrollmentid,CF:information_date,CF:health_category,CF:health_category_desc,CF:health_status,CF:health_status_desc,CF:due_date") TBLPROPERTIES ("hbase.table.name" = "healthstatus_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS healthinsurance(key string,cobra string ,employerprovided string ,id string ,insurancefromanysource string ,medicaid string ,medicare string ,nocobrareason string ,nocobrareason_desc string ,noemployerprovidedreason string ,noemployerprovidedreason_desc string ,nomedicaidreason string ,nomedicaidreason_desc string ,nomedicarereason string ,nomedicarereason_desc string ,noprivatepayreason string ,noprivatepayreason_desc string ,noschipreason string ,noschipreason_desc string ,nostatehealthinsreason string ,nostatehealthinsreason_desc string ,novamedreason string ,novamedreason_desc string ,privatepay string ,projectEntryID string ,schip string ,statehealthins string ,vamedicalservices string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:cobra,CF:employerprovided,CF:id,CF:insurancefromanysource,CF:medicaid,CF:medicare,CF:nocobrareason,CF:nocobrareason_desc,CF:noemployerprovidedreason,CF:noemployerprovidedreason_desc,CF:nomedicaidreason,CF:nomedicaidreason_desc,CF:nomedicarereason,CF:nomedicarereason_desc,CF:noprivatepayreason,CF:noprivatepayreason_desc,CF:noschipreason,CF:noschipreason_desc,CF:nostatehealthinsreason,CF:nostatehealthinsreason_desc,CF:novamedreason,CF:novamedreason_desc,CF:privatepay,CF:enrollmentid,CF:schip,CF:statehealthins,CF:vamedicalservices") TBLPROPERTIES ("hbase.table.name" = "healthinsurance_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS housingassessmentdisposition(key string,assessmentdisposition string ,assessmentdisposition_desc string,exitid string ,id string ,otherdisposition string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:assessmentdisposition,CF:assessmentdisposition_desc,CF:exitid,CF:id,CF:otherdisposition") TBLPROPERTIES ("hbase.table.name" = "housingassessmentdisposition_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS incomeandsources(key string,alimony string ,alimonyamount string ,childsupport string ,childsupportamount string ,earned string ,earnedamount string ,ga string ,gaamount string ,id string ,incomefromanysource string ,othersource string ,othersourceamount string ,othersourceidentify string ,pension string ,pensionamount string ,privatedisability string ,privatedisabilityamount string ,projectEntryID string ,socsecretirement string ,socsecretirementamount string ,ssdi string ,ssdiamount string ,ssi string ,ssiamount string ,tanf string ,tanfamount string ,totalmonthlyincome string ,unemployment string ,unemploymentamount string ,vadisabilitynonservice string ,vadisabilitynonserviceamount string ,vadisabilityservice string ,vadisabilityserviceamount string ,workerscomp string ,workerscompamount string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:alimony,CF:alimony_desc,CF:alimonyamount,CF:childsupport,CF:childsupport_desc,CF:childsupportamount,CF:earned,CF:earned_desc,CF:earnedamount,CF:ga,CF:ga_desc,CF:gaamount,CF:id,CF:incomefromanysource,CF:incomefromanysource_desc,CF:othersource,CF:othersource_desc,CF:othersourceamount,CF:othersourceidentify,CF:pension,CF:pension_desc,CF:pensionamount,CF:privatedisability,CF:privatedisability_desc,CF:privatedisabilityamount,CF:enrollmentid,CF:socsecretirement,CF:socsecretirement_desc,CF:socsecretirementamount,CF:ssdi,CF:ssdi_desc,CF:ssdiamount,CF:ssi,CF:ssi_desc,CF:ssiamount,CF:tanf,CF:tanf_desc,CF:tanfamount,CF:totalmonthlyincome,CF:unemployment,CF:unemployment_desc,CF:unemploymentamount,CF:vadisabilitynonservice,CF:vadisabilitynonservice_desc,CF:vadisabilitynonserviceamount,CF:vadisabilityservice,CF:vadisabilityservice_desc,CF:vadisabilityserviceamount,CF:workerscomp,CF:workerscomp_desc,CF:workerscompamount") TBLPROPERTIES ("hbase.table.name" = "incomeandsources_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS inventory(key string,id string ,availabilty string ,bedinventory string ,bedtype string ,bedtype_desc string ,hmisparticipatingbeds string ,householdtype string ,householdtype_desc string,inventoryenddate timestamp ,inventorystartdate timestamp ,project_coc_id string ,unitinventory string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:availabilty,CF:bedinventory,CF:bedtype,CF:bedtype_desc,CF:hmisparticipatingbeds,CF:householdtype,CF:householdtype_desc,CF:inventoryenddate,CF:inventorystartdate,CF:project_coc_id,CF:unitinventory") TBLPROPERTIES ("hbase.table.name" = "inventory_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS medicalassistance(key string,id string ,hivaidsassistance string ,hivaidsassistance_desc string ,nohivaidsassistancereason string ,nohivaidsassistancereason_desc string ,adap string ,adap_desc string ,noadapreason string ,noadapreason_desc string ,information_date timestamp ,enrollmentid string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:hivaidsassistance,CF:hivaidsassistance_desc,CF:nohivaidsassistancereason,CF:nohivaidsassistancereason_desc,CF:adap,CF:adap_desc,CF:noadapreason,CF:noadapreason_desc,CF:information_date,CF:enrollmentid") TBLPROPERTIES ("hbase.table.name" = "medicalassistance_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS noncashbenefits(key string,benefitsfromanysource string ,benefitsfromanysource_desc string ,id string ,othersource string ,othersource_desc string ,othersourceidentify string ,othertanf string ,othertanf_desc string ,projectEntryID string ,rentalassistanceongoing string ,rentalassistanceongoing_desc string ,rentalassistancetemp string ,rentalassistancetemp_desc string ,snap string ,snap_desc string ,tanfchildcare string ,tanfchildcare_desc string ,tanftransportation string ,tanftransportation_desc string ,wic string ,wic_desc string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:benefitsfromanysource,CF:benefitsfromanysource_desc,CF:id,CF:othersource,CF:othersource_desc,CF:othersourceidentify,CF:othertanf,CF:othertanf_desc,CF:enrollmentid,CF:rentalassistanceongoing,CF:rentalassistanceongoing_desc,CF:rentalassistancetemp,CF:rentalassistancetemp_desc,CF:snap,CF:snap_desc,CF:tanfchildcare,CF:tanfchildcare_desc,CF:tanftransportation,CF:tanftransportation_desc,CF:wic,CF:wic_desc") TBLPROPERTIES ("hbase.table.name" = "noncashbenefits_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS organization(key string,organizationcommonname string ,id string ,organizationname string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:organizationcommonname,CF:id,CF:organizationname") TBLPROPERTIES ("hbase.table.name" = "organization_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS path_status(key string,id string ,date_of_status timestamp ,client_enrolled_in_path string ,reason_not_enrolled string ,reason_not_enrolled_desc string ,projectEntryID string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:date_of_status,CF:client_enrolled_in_path,CF:reason_not_enrolled,CF:reason_not_enrolled_desc,CF:enrollmentid") TBLPROPERTIES ("hbase.table.name" = "pathstatus_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS project(key string,id string ,projectEntryID string ,continuumproject string ,organizationid string ,projectcommonname string ,projectname string ,projecttype string ,projecttype_desc string ,residentialaffiliation string ,targetpopulation string ,targetpopulation_desc string ,trackingmethod string ,trackingmethod_desc string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:enrollmentid,CF:continuumproject,CF:organizationid,CF:projectcommonname,CF:projectname,CF:projecttype,CF:projecttype_desc,CF:residentialaffiliation,CF:targetpopulation,CF:targetpopulation_desc,CF:trackingmethod,CF:trackingmethod_desc") TBLPROPERTIES ("hbase.table.name" = "project_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS coc(key string,id string ,coccode string ,projectid string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:coccode,CF:projectid") TBLPROPERTIES ("hbase.table.name" = "coc_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS residentialmoveindate(key string,inpermanenthousing string ,inpermanenthousing_desc string ,projectEntryID string ,residentialmoveindate timestamp ,id string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:inpermanenthousing,CF:inpermanenthousing_desc,CF:enrollmentid,CF:residentialmoveindate,CF:id") TBLPROPERTIES ("hbase.table.name" = "residentialmoveindate_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS rhybcp_status(key string,id string ,status_date timestamp ,fysb_youth string ,fysb_youth_desc string ,reason_no_services string ,reason_no_services_desc string,projectEntryID string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:status_date,CF:fysb_youth,CF:fysb_youth_desc,CF:reason_no_services,CF:reason_no_services_desc,CF:enrollmentid") TBLPROPERTIES ("hbase.table.name" = "rhybcpstatus_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS service_fa_referral(key string,dateprovided timestamp ,service_category string ,funder_list string ,type_provided string ,other_type_provided string ,sub_type_provided string ,fa_amount string ,referral_outcome string ,referraloutcome_desc string ,enrollmentid string ,id string ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:dateprovided,CF:service_category,CF:funder_list,CF:type_provided,CF:other_type_provided,CF:sub_type_provided,CF:fa_amount,CF:referral_outcome,CF:referraloutcome_desc,CF:enrollmentid,CF:id") TBLPROPERTIES ("hbase.table.name" = "service_fa_referral_MO0010");
CREATE EXTERNAL TABLE IF NOT EXISTS site(key string,id string ,address string ,city string ,geocode string ,principal_site string ,project_coc_id string ,state string ,zip string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:id,CF:address,CF:city,CF:geocode,CF:principal_site,CF:project_coc_id,CF:state,CF:zip") TBLPROPERTIES ("hbase.table.name" = "site_MO0010");

CREATE EXTERNAL TABLE IF NOT EXISTS client_for_home(PersonalID string ,first_name string,middle_name string,last_name string,ssn string,dedup_client_id string ,name_data_quality string ,name_data_quality_desc string ,ssn_data_quality string ,ssn_data_quality_desc string ,dob timestamp ,dob_data_quality string ,dob_data_quality_desc string,gender string ,gender_desc string,other_gender string ,ethnicity string ,ethnicity_desc string,race string ,race_desc string ,veteran_status string  ) STORED BY 'org.apache.hadoop.hive.hbase.HBaseStorageHandler' WITH SERDEPROPERTIES ("hbase.columns.mapping" = ":key,CF:first_name, CF:midele_name, CF:last_name,CF:ssn_old,CF:dedup_client_id,CF:name_data_quality,CF:name_data_quality_desc,CF:ssn_data_quality,CF:ssn_data_quality_desc,CF:dob_old,CF:dob_data_quality,CF:dob_data_quality_desc,CF:gender,CF:gender_desc,CF:other_gender,CF:ethnicity,CF:ethnicity_desc,CF:race,CF:race_desc,CF:veteran_status") TBLPROPERTIES ("hbase.table.name" = "client_MO0010");
--CREATE INDEX client_index_project_grp ON TABLE client(project_group_code)
--AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'  WITH DEFERRED REBUILD; 
--
--CREATE INDEX client_index_export_id ON TABLE client(export_id)
--AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'  WITH DEFERRED REBUILD;
--
--CREATE INDEX enrollment_index_export_id ON TABLE enrollment(export_id)
--AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'  WITH DEFERRED REBUILD;
--
--CREATE INDEX enrollment_index_project_grp ON TABLE enrollment(project_group_code)
--AS 'org.apache.hadoop.hive.ql.index.compact.CompactIndexHandler'  WITH DEFERRED REBUILD;