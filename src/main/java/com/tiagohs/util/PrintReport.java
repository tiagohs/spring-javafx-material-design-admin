package com.tiagohs.util;

import java.awt.HeadlessException;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.swing.JRViewer;

public class PrintReport extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public PrintReport() throws HeadlessException {
		
	}
	
	public void showReport() {
		
		try {
			JasperReport jasperReport = JasperCompileManager.compileReport(PrintReport.class.getResource("/reports/sales_template.jrxml").toExternalForm());
			JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<String, Object>(), new JREmptyDataSource());
			JRViewer jrViewer = new JRViewer(jasperPrint);
			
		} catch (Exception e) {
			
		}
		
	}
}
