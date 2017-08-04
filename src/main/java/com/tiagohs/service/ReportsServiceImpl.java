package com.tiagohs.service;

import java.util.HashMap;
import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@org.springframework.stereotype.Service("reportsService")
public class ReportsServiceImpl implements ReportsService {
	
	public <D> Service<JasperPrint> createJasperPrint(String reportTemplatePath, List<D> data) {
		
		return new Service<JasperPrint>() {

			@Override
			protected Task<JasperPrint> createTask() {
				return new Task<JasperPrint>() {

					@Override
					protected JasperPrint call() throws Exception {
						JasperPrint jasperPrint = null;
						
						try {
							JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream(reportTemplatePath));
							jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JRBeanCollectionDataSource(data));
						} catch (Exception e) {
							e.printStackTrace();
						}
						
						return jasperPrint;
					}
				};
			}
			
		};
	}
	
}
