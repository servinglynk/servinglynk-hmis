package com.servinglynk.report.engine;


import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

import org.apache.log4j.Logger;

import com.servinglynk.report.bean.HomePageDataBean;
import com.servinglynk.report.business.HomePageDataBeanMaker;

public class Reporter {
	
		private Logger logger = Logger.getLogger(Reporter.class);
		
        @SuppressWarnings("unchecked")
        
    private void exportToPDF() {
        try {                                           
//        	InputStream inputStream = new FileInputStream ("C:/workspace/hudAnnualReport/src/main/resources/HUD_Annual_Report.jrxml");
        	
        	InputStream inputStream = new FileInputStream ("C:/Users/sdolia/Downloads/15-10-2016_HUD_COC_BEAN_with_JRXML/hudAnnualReport/src/main/resources/homePage.jrxml");
            
            /*DataBeanMaker dataBeanMaker = new DataBeanMaker();
            ArrayList<DataBean> dataBeanList = dataBeanMaker.getDataBeanList();*/
        	HomePageDataBeanMaker homePageDataBeanMaker = new HomePageDataBeanMaker();
        	List<HomePageDataBean> dataBeanList = HomePageDataBeanMaker.getHomePageDataList();
           
            JRBeanCollectionDataSource beanColDataSource = new JRBeanCollectionDataSource(dataBeanList);
            
            Map parameters = new HashMap();
    
		    JasperDesign jasperDesign = JRXmlLoader.load(inputStream);
		    JasperReport jasperReport = JasperCompileManager.compileReport(jasperDesign);
		    JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, beanColDataSource);
		    JasperExportManager.exportReportToPdfFile(jasperPrint, "C:/Users/sdolia/Downloads/15-10-2016_HUD_COC_BEAN_with_JRXML/hudAnnualReport/src/main/resources/homePage.pdf"); 
 
        } catch (Exception e) {
            logger.error(e, e);
        }
    }
     
     public static void main(String[] args) {
        Reporter main = new Reporter();
        main.exportToPDF();
    }
	
	
}