package com.servinglynk.report.business;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import com.servinglynk.report.bean.Q26hTypeOfNonCashIncomeSourcesChronicallyHomelessDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.DataCollectionStage;

public class Q26hTypeOfNonCashIncomeSourcesChronicallyHomelessDataBeanMaker extends BaseBeanMaker {
	
	public static List<Q26hTypeOfNonCashIncomeSourcesChronicallyHomelessDataBean> getQ26hTypeOfNonCashIncomeSourcesChronicallyHomelessList(ReportData data){
		
		Q26hTypeOfNonCashIncomeSourcesChronicallyHomelessDataBean q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable = new Q26hTypeOfNonCashIncomeSourcesChronicallyHomelessDataBean();
		
		
		/********
		 * Any changes here needs change to Q26h
		 */
		String entryQuery = " select  count(distinct(e.dedup_client_id)) as cnt from %s.enrollment e, %s.noncashbenefits nb,%s.client c  where  "+
			      "   nb.enrollmentid = e.id "+
			      "  and TO_DATE(nb.information_date) = TO_DATE(e.entrydate) "+
				  "  and nb.information_date <= :endDate "+
				  " and e.ageatentry >=18  and nb.datacollectionstage = '1'   and c.id = e.client_id and e.chronichomeless=true   ";
			       
			String exitQuery = " select  count(distinct(e.dedup_client_id)) as cnt from %s.enrollment e,%s.noncashbenefits nb,%s.exit ext,%s.client c  where  "+
				      "   nb.enrollmentid = e.id and e.id = ext.enrollmentid   and c.id = e.client_id and e.chronichomeless=true  "+
					  "  and TO_DATE(nb.information_date) = TO_DATE(ext.exitdate) and  nb.information_date <= :endDate "+
					  " and e.ageatentry >=18  and nb.datacollectionstage = '3' ";
				       
			String stayersQuery = " select count(distinct(e.dedup_client_id)) as cnt  from  %s.enrollment e, %s.noncashbenefits nb,%s.client c  where "+
						"    nb.enrollmentid = e.id   and c.id = e.client_id and e.chronichomeless=true   "+
						" and  nb.information_date <= :endDate and e.ageatentry >= 18 "+
						" and nb.datacollectionstage in ('1','2','5')  ";
			try {
				if(data.isLiveMode()) {
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hSupplementalNutritionalATANFChildCareServicesstanceAtEntry(BigInteger.valueOf(getIncomeCnt(data, entryQuery +" and nb.snap='1' ", DataCollectionStage.ENTRY.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hSupplementalNutritionalATANFChildCareServicesstanceStayers(BigInteger.valueOf(getIncomeForAnnualAssesment(data, stayersQuery +" and nb.snap='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hSupplementalNutritionalATANFChildCareServicesstanceLeavers(BigInteger.valueOf(getIncomeCnt(data, exitQuery +" and nb.snap='1' ", DataCollectionStage.EXIT.getCode())));

					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hWICAtEntry(BigInteger.valueOf(getIncomeCnt(data, entryQuery +" and nb.wic='1' ", DataCollectionStage.ENTRY.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hWICStayers(BigInteger.valueOf(getIncomeForAnnualAssesment(data, stayersQuery +" and nb.wic='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hWICLeavers(BigInteger.valueOf(getIncomeCnt(data, exitQuery +" and  nb.wic='1' ", DataCollectionStage.EXIT.getCode())));

					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hTANFChildCareServicesAtEntry(BigInteger.valueOf(getIncomeCnt(data, entryQuery +" and nb.tanfchildcare='1' ", DataCollectionStage.ENTRY.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hTANFChildCareServicesStayers(BigInteger.valueOf(getIncomeForAnnualAssesment(data, stayersQuery +" and nb.tanfchildcare='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hTANFChildCareServicesLeavers(BigInteger.valueOf(getIncomeCnt(data, exitQuery +" and nb.tanfchildcare='1' ", DataCollectionStage.EXIT.getCode())));

					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hTANFTransportationServicesAtEntry(BigInteger.valueOf(getIncomeCnt(data, entryQuery +" and nb.tanftransportation='1' ", DataCollectionStage.ENTRY.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hTANFTransportationServicesStayers(BigInteger.valueOf(getIncomeForAnnualAssesment(data, stayersQuery +" and nb.tanftransportation='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hTANFTransportationServicesLeavers(BigInteger.valueOf(getIncomeCnt(data, exitQuery +" and nb.tanftransportation='1' ", DataCollectionStage.EXIT.getCode())));

					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hOtherTANFFundedServicesAtEntry(BigInteger.valueOf(getIncomeCnt(data, entryQuery +" and nb.othertanf='1' ", DataCollectionStage.ENTRY.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hOtherTANFFundedServicesStayers(BigInteger.valueOf(getIncomeForAnnualAssesment(data, stayersQuery +" and nb.othertanf='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hOtherTANFFundedServicesLeavers(BigInteger.valueOf(getIncomeCnt(data, exitQuery +" and nb.othertanf='1' ", DataCollectionStage.EXIT.getCode())));

					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hOtherSourceAtEntry(BigInteger.valueOf(getIncomeCnt(data, entryQuery +" and nb.othersource='1' ", DataCollectionStage.ENTRY.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hOtherSourceStayers(BigInteger.valueOf(getIncomeForAnnualAssesment(data, stayersQuery +" and nb.othersource='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode())));
					q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable.setQ26hOtherSourceLeavers(BigInteger.valueOf(getIncomeCnt(data, exitQuery +" and nb.othersource='1' ", DataCollectionStage.EXIT.getCode())));
				}
			}catch(Exception e) {
				logger.error("Error in Q26hBeanMaker:" + e);
			}
				
		return Arrays.asList(q26hTypeOfNonCashIncomeSourcesChronicallyHomelessTable);
	}
}