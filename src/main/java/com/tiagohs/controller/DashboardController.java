package com.tiagohs.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.tiagohs.service.EmployeeService;
import com.tiagohs.service.SaleService;
import com.tiagohs.service.UserService;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import javafx.stage.Stage;

@Controller
public class DashboardController extends BaseController {
	
	public static final String PATH_FXML = "/fxml/dashboard.fxml";
	public static final String TITLE = "Dashboard - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	@FXML
	private LineChart<String, Number> salesChart;

	@FXML
	private Label usersLabel;
	
	@FXML
	private Label salesLabel;
	
	@FXML
	private Label employeesLabel;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private SaleService saleService;
	
	@Autowired
	private EmployeeService employeeService;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		configureSalesChart();
		configureLabels();
	}

	@Override
	protected void onClose() {
		userService.onClose();
		saleService.onClose();
		employeeService.onClose();
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void configureSalesChart() {
		SimpleDateFormat month = new SimpleDateFormat("MMM-yyyy");
		
		salesChart.getXAxis().setLabel("Month");
        
		XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Sales");
        
        List<Calendar> allMonths = new ArrayList<>();
        
		try {
			allMonths = getLast12Months();
		} catch (ParseException e1) {
		}
        
        for (Calendar calendar : allMonths) {
        	series.getData().add(new XYChart.Data(month.format(calendar.getTime()), saleService.getTotalSalesByMonth(calendar)));
        }
        
        salesChart.getData().add(series);
	}
	
	private List<Calendar> getLast12Months() throws ParseException {
		List<Calendar> allDates = new ArrayList<>();
		
		Calendar cal = Calendar.getInstance();
		allDates.add(Calendar.getInstance());
		
		for (int i = 1; i <= 12; i++) {
			Calendar c = Calendar.getInstance();
		    cal.add(Calendar.MONTH, -1);
		    c.setTime(cal.getTime());
		    allDates.add(c);
		}
		
		return reverse(allDates);
	}
	
	public List<Calendar> reverse(List<Calendar> list) {
	    for(int i = 0, j = list.size() - 1; i < j; i++) {
	        list.add(i, list.remove(j));
	    }
	    
	    return list;
	}
	
	private void configureLabels() {
		
		userService.getTotalUsers(e -> {
			configureLabel(usersLabel, (Long) e.getSource().getValue());
		}, null);
		
		saleService.getTotalSales(e -> {
			configureLabel(salesLabel, (Long) e.getSource().getValue());		
		}, null);
		
		employeeService.getTotalEmployees(e -> {
			configureLabel(employeesLabel, (Long) e.getSource().getValue());
		}, null);
	}
	
	private void configureLabel(Label label, long value) {
		WindowsUtils.setTextInLabel(label, String.valueOf(value));
	}
	
}
