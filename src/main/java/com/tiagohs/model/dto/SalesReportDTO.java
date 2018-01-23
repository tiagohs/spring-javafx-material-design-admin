package com.tiagohs.model.dto;

public class SalesReportDTO {
	
	private String code;
	private String shipmentDate;
	private String issueDate;
	private String client;
	private String totalUnits;
	private String total;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getShipmentDate() {
		return shipmentDate;
	}
	public void setShipmentDate(String shipmentDate) {
		this.shipmentDate = shipmentDate;
	}
	public String getIssueDate() {
		return issueDate;
	}
	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}
	public String getClient() {
		return client;
	}
	public void setClient(String client) {
		this.client = client;
	}
	public String getTotalUnits() {
		return totalUnits;
	}
	public void setTotalUnits(String totalUnits) {
		this.totalUnits = totalUnits;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
}
