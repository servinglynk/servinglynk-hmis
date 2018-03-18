package com.servinglynk.report.business;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.servinglynk.report.bean.Q06dDataBean;
import com.servinglynk.report.bean.ReportData;
import com.servinglynk.report.model.EnrollmentModel;

/***
 * https://www.hudexchange.info/resources/documents/HMIS-Standard-Reporting-Terminology-Glossary.pdf
 * Rules
• This question applies to adults and heads of household active in the date range who started in the project
any time after 10/1/2016, which was when element 3.917 was restructured to improve data entry. Apply an
extra filter of [project start date] >= 10/1/2016 to all records for this question.
• Row 2 reports on adults and heads of household active in Street Outreach, Emergency Shelter or Safe Haven
([Project Type] = 1, 4, 8).
• Row 3 reports on adults and heads of household active in Transitional Housing ([Project Type] = 2).
• Row 4 reports on adults and heads of household active in all Permanent Housing types ([Project Type] = 3, 9,
10, 13).
• Column B: Count the total number of adults and heads of household.
• Column C: Count the number of adults and heads of household entering from institution who are missing
their [Length of stay]:
o [Type of residence] = 15, 6, 7, 24, 4, 5 AND
o [Length of stay in prior living situation] = 8, 9, missing.
• Column D: Count the number of adults and heads of household entering from housing and other situations
who are missing their [Length of stay]:
o [Type of residence] = 14, 23, 21, 3, 22, 19, 25, 20, 26, 12, 13, 2, 8, 9, missing AND
o [Length of stay in prior living situation] = 8, 9, missing.
• Columns E, F and G report only on adults and heads of household expected to have answers in fields
3.917.3, .4, and .5 respectively.
o Row 2 – all active adults and heads of household.
o Rows 3 and 4 – active adults and heads of household where one of the following is true:
▪ [Type of Residence] = 16, 1, 18, 27.
▪ ([Type of residence] = 15, 6, 7, 24, 4, 5) AND ([Length of stay in prior living situation] = 10,
11, 2, or 3) AND ([On the night before did you stay on streets, ES, or SH] = 1).
▪ ([Type of residence] = 14, 23, 21, 3, 22, 19, 25, 20, 26, 12, 13, 2, 8, 9, missing) AND ([Length
of stay in prior living situation] = 10, 11) AND ([On the night before did you stay on streets,
ES, or SH] = 1).
• Column E: Count the number of adults and heads of household with responses 8, 9, or missing in
[approximate date started] (3.917.3).
• Column F: Count the number of adults and heads of household with responses 8, 9, or missing in [number
of times…] (3.917.4).
• Column G: Count the number of adults and heads of household with responses 8, 9, or missing in [number
of months...] (3.917.5).
• Column H: Count the unique number of adults and heads of household missing one or more responses in
columns C through G and divide by the total records in column B.
 * @return
 */
public class Q06dDataBeanMaker {

	public static List<Q06dDataBean> getQ06DataBeanList(ReportData data){
		
		List<EnrollmentModel> enrollments = data.getEnrollments();

		Q06dDataBean q06dDataBean =new Q06dDataBean();
		q06dDataBean.setEsshCountOfTotalRecords(BigInteger.valueOf(0));
		q06dDataBean.setEsshMissingTimeInInstitution(BigInteger.valueOf(0));
		q06dDataBean.setEsshMissingTimeInHousing(BigInteger.valueOf(0));
		q06dDataBean.setEsshApproximateDateStarted(BigInteger.valueOf(0));
		q06dDataBean.setEsshnumberOfTimesDKR(BigInteger.valueOf(0));
		q06dDataBean.setEsshNumberOfMonthsDKR(BigInteger.valueOf(0));
		q06dDataBean.setEsshPercentOfRecords(BigInteger.valueOf(0));
		q06dDataBean.setThCountOfTotalRecords(BigInteger.valueOf(0));
		q06dDataBean.setThMissingTimeInInstitution(BigInteger.valueOf(0));
		q06dDataBean.setThMissingTimeInHousing(BigInteger.valueOf(0));
		q06dDataBean.setThApproximateDateStarted(BigInteger.valueOf(0));
		q06dDataBean.setThnumberOfTimesDKR(BigInteger.valueOf(0));
		q06dDataBean.setThNumberOfMonthsDKR(BigInteger.valueOf(0));
		q06dDataBean.setThPercentOfRecords(BigInteger.valueOf(0));
		q06dDataBean.setPhCountOfTotalRecords(BigInteger.valueOf(0));
		q06dDataBean.setPhMissingTimeInInstitution(BigInteger.valueOf(0));
		q06dDataBean.setPhMissingTimeInHousing(BigInteger.valueOf(0));
		q06dDataBean.setPhApproximateDateStarted(BigInteger.valueOf(0));
		q06dDataBean.setPhnumberOfTimesDKR(BigInteger.valueOf(0));
		q06dDataBean.setPhNumberOfMonthsDKR(BigInteger.valueOf(0));
		q06dDataBean.setPhPercentOfRecords(BigInteger.valueOf(0));
		q06dDataBean.setTotalCountOfTotalRecords(BigInteger.valueOf(0));
		q06dDataBean.setTotalMissingTimeInInstitution(BigInteger.valueOf(0));
		q06dDataBean.setTotalMissingTimeInHousing(BigInteger.valueOf(0));
		q06dDataBean.setTotalApproximateDateStarted(BigInteger.valueOf(0));
		q06dDataBean.setTotalnumberOfTimesDKR(BigInteger.valueOf(0));
		q06dDataBean.setTotalNumberOfMonthsDKR(BigInteger.valueOf(0));
		q06dDataBean.setTotalPercentOfRecords(BigInteger.valueOf(0));	
		
		
		return Arrays.asList(q06dDataBean);
	}

}
