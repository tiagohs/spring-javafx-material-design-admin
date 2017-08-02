package com.tiagohs.util;

import java.util.ArrayList;
import java.util.List;

import com.tiagohs.model.Sale;
import com.tiagohs.model.dto.SalesReportDTO;

public class EntityReportFactory {
	
	public static List<SalesReportDTO> createSales(List<Sale> sales) {
		List<SalesReportDTO> salesReport = new ArrayList<>();
		
		for (Sale sale : sales) {
			salesReport.add(createSale(sale));
		}
		
		return salesReport;
	}
	
	public static SalesReportDTO createSale(Sale sale) {
		SalesReportDTO salesReportDTO = new SalesReportDTO();
		
		salesReportDTO.setCode(sale.getSaleCode());
		salesReportDTO.setIssueDate(sale.getIssueDate() == null ? "---" : sale.getIssueDate());
		salesReportDTO.setShipmentDate(sale.getShipmentDate() == null ? "---" : sale.getShipmentDate());
		salesReportDTO.setTotalUnits(String.valueOf(sale.getTotalUnits()));
		salesReportDTO.setTotal(String.format("%.2f", sale.getTotal()));
		
		return salesReportDTO;
	}
}
