package com.tiagohs.service;

import java.util.HashMap;
import java.util.List;

import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@org.springframework.stereotype.Service("reportsService")
public class ReportsServiceImpl extends BaseService implements ReportsService {
	
	public <D> Service<JasperPrint> createJasperPrint(String reportTemplatePath, List<D> data, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		
		return createService(new Task<JasperPrint>() {
			protected JasperPrint call() throws Exception {
				JasperPrint jasperPrint = null;
				
				try {
					System.out.println(data.size());
					JasperReport jasperReport = JasperCompileManager.compileReport(this.getClass().getResourceAsStream(reportTemplatePath));
					jasperPrint = JasperFillManager.fillReport(jasperReport, new HashMap<>(), new JRBeanCollectionDataSource(data));
				} catch (Exception e) {
					e.printStackTrace();
				}
				
				return jasperPrint;
			};
		}, onSucess, beforeStart);
		
	}

}
