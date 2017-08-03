package com.tiagohs.service;

import java.util.HashMap;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;

public class ReportsService extends Service<JasperPrint> {
	
	@Override
	protected Task<JasperPrint> createTask() {
		return new Task<JasperPrint>() {

			@Override
			protected JasperPrint call() throws Exception {
				JasperPrint jasperPrint = null;
				
				try {
					JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream("/reports/sales_template.jrxml"));
					jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JREmptyDataSource());
				} catch (Exception e) {
					return null;
				}
				
				return jasperPrint;
			}
		};
	}
	
}
