package com.tiagohs.controller;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.ResourceBundle;

import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.tiagohs.util.WindowsUtils;

import javafx.fxml.FXML;
import javafx.scene.control.ScrollPane;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReportsContext;

@Controller
public class ReportViewerController implements BaseController {
	
	public static final String JASPER_PRINT = "jasper_print.key";
	
	
	public static final String PATH_FXML = "/fxml/report_viewer.fxml";
	public static final String TITLE = "Report - Inventory Management";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	public static final int REPORT_DPI_RESOLUTION = 72;
	
	@FXML
	private ScrollPane containerScrollPane;
	
	@FXML
	private JFXButton saveButton;
	
	@FXML
	private JFXButton printerButton;
	
	@FXML
	private JFXButton fitPageButton;
	
	@FXML
	private JFXButton firstPageButton;
	
	@FXML
	private JFXButton previousPageButton;
	
	@FXML
	private JFXButton nextPageButton;
	
	@FXML
	private JFXButton lastPageButton;
	
	@FXML
	private JFXButton moreZoomButton;
	
	@FXML
	private JFXButton lessZoomButton;
	
	@FXML
	private JFXTextField pageTextField;
	
	@FXML
	private JFXComboBox<String> zoomComboBox;
	
	protected final float MIN_ZOOM = 0.5f;
	protected final float MAX_ZOOM = 10f;
	protected final int zooms[] = {50, 75, 100, 125, 150, 175, 200, 250, 400, 800};
	
	private float zoom = 0;
	private float realZoom = 0;
	private DecimalFormat zoomDecimalFormat = new DecimalFormat("#.##");
	
	private JasperPrint jasperPrint;
	private double screenResolution = REPORT_DPI_RESOLUTION;
	private JasperReportsContext jasperReportsContext;
	private ResourceBundle resourceBundle;
	
	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		this.jasperReportsContext = DefaultJasperReportsContext.getInstance();
		
		try {
			checkParameters(parameters);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private <T> void checkParameters(HashMap<String, T> parameters) throws Exception {
		
		if (null != parameters) {
			this.jasperPrint = (JasperPrint) parameters.get(JASPER_PRINT);
		} else {
			throw new Exception("Objeto JasperPrint não encontrado.");
		}
	}
	
}
