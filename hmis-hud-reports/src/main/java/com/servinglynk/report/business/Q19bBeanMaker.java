package com.servinglynk.report.business;

import java.math.BigInteger;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.servinglynk.hive.connection.ImpalaConnection;
import com.servinglynk.report.bean.Q25gTypeOfCashIncomeSourcesVeteransDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.ClientModel;
import com.servinglynk.report.model.DataCollectionStage;

public class Q19bBeanMaker extends BaseBeanMaker {
	
	public static List<Q25gTypeOfCashIncomeSourcesVeteransDataBean> getQ25gTypeOfCashIncomeSourcesVeteranList(ReportData data){
		
		Q25gTypeOfCashIncomeSourcesVeteransDataBean q25gDataBean = new Q25gTypeOfCashIncomeSourcesVeteransDataBean();
		
		if(data.isLiveMode()) {
			try{
			
			String exitQuery = " select count(e.dedup_client_id) as cnt  from %s.incomeandsources i, %s.enrollment e ,%s.client c,%s.exit ext where  e.client_id = c.id and   e.id=i.enrollmentid  and   e.id=ext.enrollmentid "+ 
					" and TO_DATE(i.information_date) = TO_DATE(ext.exitdate)  and i.information_date <= :endDate  and i.datacollectionstage='3' and e.ageatentry >= 18 and i.incomefromanysource is not null and i.incomefromanysource not in ('8','9','99) and e.disablingcondition is not null and e.disablingcondition not in ('8','9','99)  ";
	
			int alimonyAOWithDisab = getIncomeCnt(data.getSchema(), exitQuery +" and alimony ='1' and e.disablingcondition ='1' ", DataCollectionStage.EXIT.getCode(),data);
		
			int alimonyIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +" and  alimony ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int alimonyIncomeAtAnnualAssesment = getIncomeCntForAnnualAssesment(data.getSchema(), exitQuery +"  and alimony ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gAlimonyAtEntry(BigInteger.valueOf(alimonyAOWithDisab));
			q25gDataBean.setQ25gAlimonyLeavers(BigInteger.valueOf(alimonyIncomeAtExit));
			q25gDataBean.setQ25gAlimonyStayers(BigInteger.valueOf(alimonyIncomeAtAnnualAssesment));
			
			int childsupportIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +"  and childsupport ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int childsupportIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +"  and childsupport ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int childsupportIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and childsupport ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gChildSupportAtEntry(BigInteger.valueOf(childsupportIncomeAtEntry));
			q25gDataBean.setQ25gChildSupportLeavers(BigInteger.valueOf(childsupportIncomeAtExit));
			q25gDataBean.setQ25gChildSupportStayers(BigInteger.valueOf(childsupportIncomeAtAnnualAssesment));
			
			int earnedIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +" and  earned ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int earnedIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +" and  earned ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int earnedIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and earned ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gEarnedIncomeAtEntry(BigInteger.valueOf(earnedIncomeAtEntry));
			q25gDataBean.setQ25gEarnedIncomeLeavers(BigInteger.valueOf(earnedIncomeAtExit));
			q25gDataBean.setQ25gEarnedIncomeStayers(BigInteger.valueOf(earnedIncomeAtAnnualAssesment));
			
			
			int gaIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +"  and ga ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int gaIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +"  and ga ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int gaIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and ga ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gGeneralAssistanceAtEntry(BigInteger.valueOf(gaIncomeAtEntry));
			q25gDataBean.setQ25gGeneralAssistanceLeavers(BigInteger.valueOf(gaIncomeAtExit));
			q25gDataBean.setQ25gGeneralAssistanceStayers(BigInteger.valueOf(gaIncomeAtAnnualAssesment));
			
			int othersourceIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +"  and othersource ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int othersourceIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +"  and othersource ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int othersourceIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +" and  othersource ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			
			q25gDataBean.setQ25gOtherSourceAtEntry(BigInteger.valueOf(othersourceIncomeAtEntry));
			q25gDataBean.setQ25gOtherSourceLeavers(BigInteger.valueOf(othersourceIncomeAtExit));
			q25gDataBean.setQ25gOtherSourceStayers(BigInteger.valueOf(othersourceIncomeAtAnnualAssesment));
			
			int pensionIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +" and  pension ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int pensionIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +"  and pension ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int pensionIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and pension ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gPensionFromFormerJobAtEntry(BigInteger.valueOf(pensionIncomeAtEntry));
			q25gDataBean.setQ25gPensionFromFormerJobLeavers(BigInteger.valueOf(pensionIncomeAtExit));
			q25gDataBean.setQ25gPensionFromFormerJobStayers(BigInteger.valueOf(pensionIncomeAtAnnualAssesment));
			
			
			int privatedisabilityIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +" and  privatedisability ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int privatedisabilityIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +" and  privatedisability ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int privatedisabilityIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and privatedisability ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gPrivateDisabilityInsuranceAtEntry(BigInteger.valueOf(privatedisabilityIncomeAtEntry));
			q25gDataBean.setQ25gPrivateDisabilityInsuranceLeavers(BigInteger.valueOf(privatedisabilityIncomeAtExit));
			q25gDataBean.setQ25gPrivateDisabilityInsuranceStayers(BigInteger.valueOf(privatedisabilityIncomeAtAnnualAssesment));
			
			int socsecretirementIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +"  and socsecretirement ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int socsecretirementIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +" and  socsecretirement ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int socsecretirementIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and socsecretirement ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gRetirementAtEntry(BigInteger.valueOf(socsecretirementIncomeAtEntry));
			q25gDataBean.setQ25gRetirementLeavers(BigInteger.valueOf(socsecretirementIncomeAtExit));
			q25gDataBean.setQ25gRetirementStayers(BigInteger.valueOf(socsecretirementIncomeAtAnnualAssesment));
			
			int ssdiIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +" and  ssdi ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int ssdiIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +" and  ssdi ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int ssdiIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +" and  ssdi ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gSSDIAtEntry(BigInteger.valueOf(ssdiIncomeAtEntry));
			q25gDataBean.setQ25gSSDILeavers(BigInteger.valueOf(ssdiIncomeAtExit));
			q25gDataBean.setQ25gSSDIStayers(BigInteger.valueOf(ssdiIncomeAtAnnualAssesment));
		
			int ssiIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +"  and ssi ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int ssiIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +"  and ssi ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int ssiIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and ssi ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gSSIAtEntry(BigInteger.valueOf(ssiIncomeAtEntry));
			q25gDataBean.setQ25gSSILeavers(BigInteger.valueOf(ssiIncomeAtExit));
			q25gDataBean.setQ25gSSIStayers(BigInteger.valueOf(ssiIncomeAtAnnualAssesment));
			
			int tanfIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +" and  tanf ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int tanfIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +"  and  tanf ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int tanfIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and tanf ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gTANFAtEntry(BigInteger.valueOf(tanfIncomeAtEntry));
			q25gDataBean.setQ25gTANFLeavers(BigInteger.valueOf(tanfIncomeAtExit));
			q25gDataBean.setQ25gTANFStayers(BigInteger.valueOf(tanfIncomeAtAnnualAssesment));
			
			int unemploymentIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +" and  unemployment ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int unemploymentIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +"  and unemployment ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int unemploymentIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +"  and unemployment ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gUnemploymentInsuranceAtEntry(BigInteger.valueOf(unemploymentIncomeAtEntry));
			q25gDataBean.setQ25gUnemploymentInsuranceLeavers(BigInteger.valueOf(unemploymentIncomeAtExit));
			q25gDataBean.setQ25gUnemploymentInsuranceStayers(BigInteger.valueOf(unemploymentIncomeAtAnnualAssesment));
			
			int vadisabilitynonserviceIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +" and  vadisabilitynonservice ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int vadisabilitynonserviceIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +" and  vadisabilitynonservice ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int vadisabilitynonserviceIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +" and  vadisabilitynonservice ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gVANonServiceConnectedDisabilityAtEntry(BigInteger.valueOf(vadisabilitynonserviceIncomeAtEntry));
			q25gDataBean.setQ25gVANonServiceConnectedDisabilityLeavers(BigInteger.valueOf(vadisabilitynonserviceIncomeAtExit));
			q25gDataBean.setQ25gVANonServiceConnectedDisabilityStayers(BigInteger.valueOf(vadisabilitynonserviceIncomeAtAnnualAssesment));
			
			
			int vadisabilityserviceIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +"  and vadisabilityservice ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int vadisabilityserviceIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +" and  vadisabilityservice ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int vadisabilityserviceIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +" and  vadisabilityservice ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gVAServiceConnectedDisabilityAtEntry(BigInteger.valueOf(vadisabilityserviceIncomeAtEntry));
			q25gDataBean.setQ25gVAServiceConnectedDisabilityLeavers(BigInteger.valueOf(vadisabilityserviceIncomeAtExit));
			q25gDataBean.setQ25gVAServiceConnectedDisabilityStayers(BigInteger.valueOf(vadisabilityserviceIncomeAtAnnualAssesment));
			
			int workerscompIncomeAtEntry = getIncomeCnt(data.getSchema(), exitQuery +" and  workerscomp ='1' ", DataCollectionStage.ENTRY.getCode(),data);
			int workerscompIncomeAtExit = getIncomeCnt(data.getSchema(), exitQuery +" and  workerscomp ='1' ", DataCollectionStage.EXIT.getCode(),data);
			int workerscompIncomeAtAnnualAssesment = getIncomeCnt(data.getSchema(), exitQuery +" and  workerscomp ='1' ", DataCollectionStage.ANNUAL_ASSESMENT.getCode(),data);
			
			q25gDataBean.setQ25gWorkersCompensationAtEntry(BigInteger.valueOf(workerscompIncomeAtEntry));
			q25gDataBean.setQ25gWorkersCompensationLeavers(BigInteger.valueOf(workerscompIncomeAtExit));
			q25gDataBean.setQ25gWorkersCompensationStayers(BigInteger.valueOf(workerscompIncomeAtAnnualAssesment));
			
			
			String adultsIncomeQuery = " select distinct(e.dedup_client_id)  from %s.incomeandsources i, %s.enrollment e,%s.client c where  e.client_id = c.id and i.datacollectionstage=:datacollectionstage and  e.id=i.enrollmentid "+ 
					" and i.information_date >= e.entrydate and i.information_date >= :startDate and i.information_date <= :endDate  and e.ageatentry >= 18 and i.incomefromanysource in ('1','2') by e.dedup_client_id ";
			data.setQueryDataCollectionStage(DataCollectionStage.EXIT.getCode());
			List<String> enrollmentsAtEnrty = getClients(data.getSchema(), adultsIncomeQuery,data);
			data.setQueryDataCollectionStage(DataCollectionStage.EXIT.getCode());
			List<String> enrollmentsAtExit = getClients(data.getSchema(), adultsIncomeQuery, data);
			data.setQueryDataCollectionStage(DataCollectionStage.ANNUAL_ASSESMENT.getCode());
			List<String> enrollmentsAtAnnualAssesment = getClients(data.getSchema(), adultsIncomeQuery,data);
		
			q25gDataBean.setQ25gAdultsWithIncomeInfoAtEntryAtEntry(BigInteger.valueOf(0));
			q25gDataBean.setQ25gAdultsWithIncomeInfoAtEntryLeavers(BigInteger.valueOf(0));
			q25gDataBean.setQ25gAdultsWithIncomeInfoAtEntryStayers(BigInteger.valueOf(0));
			
			if(CollectionUtils.isNotEmpty(enrollmentsAtExit) && CollectionUtils.isNotEmpty(enrollmentsAtEnrty)) {
				int cnt =0;
				for(String dedupClient : enrollmentsAtExit) {
					if(enrollmentsAtEnrty.contains(dedupClient)) {
						cnt++;
					}
				}
				q25gDataBean.setQ25gAdultsWithIncomeInfoAtEntryLeavers(BigInteger.valueOf(cnt));
			}
			if(CollectionUtils.isNotEmpty(enrollmentsAtAnnualAssesment) && CollectionUtils.isNotEmpty(enrollmentsAtEnrty)) {
				int cnt =0;
				for(String dedupClient : enrollmentsAtAnnualAssesment) {
					if(enrollmentsAtEnrty.contains(dedupClient)) {
						cnt++;
					}
				}
				q25gDataBean.setQ25gAdultsWithIncomeInfoAtEntryStayers(BigInteger.valueOf(cnt));
			}
			
		} catch (Exception e) {
			logger.error("Error in Q17BeanMaker:" + e);
		}
			}
			return Arrays.asList(q25gDataBean);
		}
		
		public static List<String> getClients(String schema,String query,ReportData data) {
			ResultSet resultSet = null;
			List<String> enrollments = new ArrayList<>();
			Statement statement = null;
			Connection connection = null;
			try {
				connection = ImpalaConnection.getConnection();
				statement = connection.createStatement();
				resultSet = statement.executeQuery(formatQuery(query,schema,data));
				
			 while(resultSet.next()) {
				 enrollments.add(resultSet.getString(1));
		     }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
						//connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return enrollments;
		}
		
		
		public static int getIncomeCnt(String schema,String query,String datacollectionStage,ReportData data) {
			ResultSet resultSet = null;
			Statement statement = null;
			Connection connection = null;
			int count =0;
			try {
				connection = ImpalaConnection.getConnection();
				statement = connection.createStatement();
				data.setQueryDataCollectionStage(datacollectionStage);
				String newQuery = buildQueryFromDataCollectionStage(datacollectionStage, query, data);
				resultSet = statement.executeQuery(formatQuery(newQuery,schema,data));
				
			 while(resultSet.next()) {
				 count = resultSet.getInt(1);
		     }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
						//connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return count;
		}
		
		
		public static int getIncomeCntForAnnualAssesment(String schema,String query,String datacollectionStage,ReportData data) {
			ResultSet resultSet = null;
			Statement statement = null;
			Connection connection = null;
			int count =0;
			try {
				connection = ImpalaConnection.getConnection();
				statement = connection.createStatement();
				data.setQueryDataCollectionStage(datacollectionStage);
				resultSet = statement.executeQuery(formatQuery(query,schema,data));
				List<ClientModel> clients = data.getVeterans();
				if(CollectionUtils.isEmpty(clients)) {
					return count;
				}
				 if(CollectionUtils.isNotEmpty(clients)) {
					 StringBuilder enrollmentBuilder = new StringBuilder(" and e.dedup_client_id in  ( ");
					 int index = 0;
					 for(ClientModel client : clients) {
						 enrollmentBuilder.append("'"+client.getDedupClientId()+"'");
						 if(index != clients.size()) {
							 enrollmentBuilder.append(",");
						 }
					 }
					 enrollmentBuilder.deleteCharAt(enrollmentBuilder.length() -1);
					 enrollmentBuilder.append(" ) ");
					 query = query + enrollmentBuilder.toString();
				 }
			 while(resultSet.next()) {
				 count = resultSet.getInt(1);
		     }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				if (statement != null) {
					try {
						statement.close();
						//connection.close();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			return count;
		}

	}
