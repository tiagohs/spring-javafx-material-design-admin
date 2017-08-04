package com.tiagohs.service;

import java.util.List;

import javafx.concurrent.Service;
import net.sf.jasperreports.engine.JasperPrint;

public interface ReportsService {
	
	<D> Service<JasperPrint> createJasperPrint(String reportTemplatePath, List<D> data);
}
