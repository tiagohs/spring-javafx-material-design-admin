package com.tiagohs.controller;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Locale;
import java.util.ResourceBundle;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.tiagohs.util.WindowsUtils;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.PrintPageFormat;

@Controller
@Scope("prototype")
public class ReportViewerController extends BaseController {

	public static final String JASPER_PRINT = "jasper_print.key";

	public static final String PATH_FXML = "/fxml/report_viewer.fxml";
	public static final String REPORT_VIEWER_TITLE_KEY = "report_viewer.title";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;

	public static final int REPORT_DPI_RESOLUTION = 72;

	@FXML
	private ScrollPane containerScrollPane;
	
	@FXML
	private VBox pageContentVBox;

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
	private Label pageLabel;

	@FXML
	private JFXComboBox<String> zoomComboBox;

	protected DropShadow dropShadow = new DropShadow();

	private final float MIN_ZOOM = 0.5f;
	private final float MAX_ZOOM = 10f;
	private final int SPACING = 25;
	private final int zooms[] = { 50, 75, 100, 125, 150, 175, 200, 250, 400, 800 };

	private float zoom = 0;
	private float realZoom = 0;
	private DecimalFormat zoomDecimalFormat = new DecimalFormat("#.##");
	private int pageIndex;

	private JasperPrint jasperPrint;
	private double screenResolution = REPORT_DPI_RESOLUTION;
	private JasperReportsContext jasperReportsContext;
	private ResourceBundle resourceBundle;

	@Override
	public <T> void init(Stage stage, HashMap<String, T> parameters) {
		super.init(stage, parameters);
		
		this.jasperReportsContext = DefaultJasperReportsContext.getInstance();
		this.resourceBundle = ResourceBundle.getBundle("net/sf/jasperreports/view/viewer", Locale.getDefault());

		try {
			checkParameters(parameters);
			configureButtons();
			configureContainers();
			confireZooms();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	protected void onClose() {
		
	}
	
	private <T> void checkParameters(HashMap<String, T> parameters) throws Exception {

		if (null != parameters) {
			this.jasperPrint = (JasperPrint) parameters.get(JASPER_PRINT);
		} else {
			throw new Exception("Objeto JasperPrint não encontrado.");
		}
	}
	
	private void configureButtons() {

		configureButton(saveButton, "Save Image", e -> onSave());
		configureButton(printerButton, resourceBundle.getString("print"), e -> onBtnPrint());
		configureButton(firstPageButton, resourceBundle.getString("first.page"), e -> onBtnFirstPressed());
		configureButton(previousPageButton, resourceBundle.getString("previous.page"), e -> onBtnPreviousPressed());
		configureButton(nextPageButton, resourceBundle.getString("next.page"), e -> onBtnNextPressed());
		configureButton(lastPageButton, resourceBundle.getString("last.page"), e -> onBtnLastPressed());
		configureButton(moreZoomButton, resourceBundle.getString("zoom.in"), e -> onBtnZoomIn());
		configureButton(lessZoomButton, resourceBundle.getString("zoom.out"), e -> onBtnZoomOut());
		configureButton(fitPageButton, resourceBundle.getString("fit.page"), e -> onFitPagePressed());
		
		configureComboBox();
	}

	private void configureContainers() {

		pageContentVBox.setSpacing(SPACING);
		pageContentVBox.setPadding(new Insets(SPACING));
		pageContentVBox.setAlignment(Pos.CENTER);

		containerScrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		containerScrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
		containerScrollPane.setContent(pageContentVBox);
		containerScrollPane.viewportBoundsProperty()
				.addListener((ObservableValue<? extends Bounds> arg0, Bounds arg1, Bounds arg2) -> onFitScrollPane(arg2));
		containerScrollPane.vvalueProperty()
				.addListener((observable, oldValue, newValue) -> onScroll(observable, oldValue, newValue));
		
		dropShadow.setColor(Color.GREY);
	    dropShadow.setOffsetX(2);
	    dropShadow.setOffsetY(2);
	}
	
	private void confireZooms() {
		setZoomRatio(1);
	}
	
	protected void setZoomRatio(float newZoom) {
	    if (newZoom > 0) {
	    	zoomComboBox.getEditor().setText(zoomDecimalFormat.format(newZoom * 100) + "%");

	      if (zoom != newZoom) {
	        zoom = newZoom;
	        realZoom = (float) (zoom * screenResolution / REPORT_DPI_RESOLUTION);
	        refresh();
	      }
	    }

	    moreZoomButton.setDisable(zoom >= (float) zooms[zooms.length - 1] / 100);
	    lessZoomButton.setDisable(zoom <= (float) zooms[0] / 100);
	  }

	private void configureButton(JFXButton button, String tip, EventHandler<ActionEvent> onAction) {
		button.setTooltip(new Tooltip(tip));
		button.setOnAction(onAction);
		button.setFocusTraversable(false);
	}
	
	private void configureComboBox() {
		zoomComboBox.setEditable(true);
		zoomComboBox.setTooltip(new Tooltip(resourceBundle.getString("zoom.ratio")));
	    for (int zoom : zooms)
	    	zoomComboBox.getItems().add(zoom + "%");
	    zoomComboBox.getSelectionModel().select(2);
	    zoomComboBox.setFocusTraversable(false);
	    zoomComboBox.setOnAction(e -> onComboBoxZoomChanged());
	    zoomComboBox.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
	      if (e.getCode() == KeyCode.ENTER)
	        onComboBoxZoomChanged();
	    });
	}
	
	private void onScroll(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
		int pagesNo = jasperPrint.getPages().size();
		double pageInterval = 1d / pagesNo;
		double low = pageIndex * pageInterval;
		double hi = low + pageInterval;
		int tmpPageIndex = pageIndex;

		if (hi < newValue.doubleValue() && pageIndex < pagesNo) { 
			while (hi < newValue.doubleValue()) {
				tmpPageIndex++;
				hi += pageInterval;
			}

			setPageIndex(tmpPageIndex);
		} else if (low > newValue.doubleValue() && pageIndex > 0) { 
			while (low > newValue.doubleValue()) {
				tmpPageIndex--;
				low -= pageInterval;
			}

			setPageIndex(tmpPageIndex);
		}
		
	}
	
	private void onFitScrollPane(Bounds arg2) {
		containerScrollPane.setFitToWidth(pageContentVBox.prefWidth(-1) < arg2.getWidth());
		containerScrollPane.setFitToHeight(pageContentVBox.prefHeight(-1) < arg2.getHeight());
	}
	
	private void onSave() {
		Stage stage = new Stage();

		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Save Report");

		File file = fileChooser.showSaveDialog(stage);

		if (file != null) {
			try {
				JasperExportManager.exportReportToPdfFile(jasperPrint, file.getAbsolutePath());
			} catch (Exception ex) {
				System.out.println(ex.getMessage());
			}
		}
	}

	private void onBtnPrint() {
		Task<Boolean> task = new Task<Boolean>() {
			@Override
			protected Boolean call() throws Exception {
				try {
					printerButton.setDisable(true);
					stage.getScene().setCursor(Cursor.WAIT);
					return JasperPrintManager.getInstance(jasperReportsContext).print(jasperPrint, true);
				} catch (JRException ex) {
					ex.printStackTrace();
				} finally {
					stage.getScene().setCursor(Cursor.DEFAULT);
					printerButton.setDisable(false);
				}

				return false;
			}
		};

		task.run();
	}

	private void onFitPagePressed() {
		PrintPageFormat pageFormat = jasperPrint.getPageFormat(pageIndex);
		float heightRatio = ((float) containerScrollPane.getViewportBounds().getHeight() - 2 * SPACING)
				/ pageFormat.getPageHeight();
		float widthRatio = ((float) containerScrollPane.getViewportBounds().getWidth() - 2 * SPACING)
				/ pageFormat.getPageWidth();
		zoomComboBox.getSelectionModel().clearSelection(); // Has to be before
															// setRealZoomRatio()
															// call because
															// clearSelection()
															// will clear
															// cmbZoom text box
		setRealZoomRatio(heightRatio < widthRatio ? heightRatio : widthRatio);
	}

	private void onBtnFirstPressed() {
		setPage(0);
	}

	private void onBtnPreviousPressed() {
		setPage(pageIndex - 1);
	}

	private void onBtnNextPressed() {
		setPage(pageIndex + 1);
	}

	private void onBtnLastPressed() {
		setPage(jasperPrint.getPages().size() - 1);
	}

	private void setRealZoomRatio(float newZoom) {
		if (newZoom > 0 && realZoom != newZoom) {
			zoom = (float) (newZoom * REPORT_DPI_RESOLUTION / screenResolution);
			realZoom = newZoom;

			zoomComboBox.getEditor().setText(zoomDecimalFormat.format(zoom * 100) + "%");
			refresh();
		}

		moreZoomButton.setDisable(zoom >= (float) zooms[zooms.length - 1] / 100);
		lessZoomButton.setDisable(zoom <= (float) zooms[0] / 100);
	}

	private void onBtnZoomIn() {

		int index = Arrays.binarySearch(zooms, getZoomRatio());

		if (index < 0) {
			zoomComboBox.getSelectionModel().select(zoomDecimalFormat.format(zooms[-index - 1]) + "%");
		} else if (index < zoomComboBox.getItems().size() - 1) {
			zoomComboBox.getSelectionModel().select(zoomDecimalFormat.format(zooms[index + 1]) + "%");
		}
	}

	private void onBtnZoomOut() {
		int index = Arrays.binarySearch(zooms, getZoomRatio());

		if (index > 0) {
			zoomComboBox.getSelectionModel().select(zoomDecimalFormat.format(zooms[index - 1]) + "%");
		} else if (index < -1) {
			zoomComboBox.getSelectionModel().select(zoomDecimalFormat.format(zooms[-index - 2]) + "%");
		}
	}

	protected void refresh() {
		pageContentVBox.getChildren().clear();

		for (int pageIndex = 0; pageIndex < jasperPrint.getPages().size(); pageIndex++) {
			try {
				java.awt.image.BufferedImage awtImage = (BufferedImage) JasperPrintManager
						.getInstance(jasperReportsContext).printToImage(jasperPrint, pageIndex, realZoom);
				Image image = SwingFXUtils.toFXImage(awtImage, null);

				ImageView iv = new ImageView(image);
				iv.setEffect(dropShadow);
				setPageIndex(0);
				pageContentVBox.getChildren().add(iv);
			} catch (JRException ex) {
				ex.printStackTrace();
			}
		}
	}

	protected void setPageIndex(int index) {
		int pagesNo = jasperPrint.getPages().size();

		firstPageButton.setDisable(index == 0);
		previousPageButton.setDisable(index == 0);
		lastPageButton.setDisable(index == pagesNo - 1);
		nextPageButton.setDisable(index == pagesNo - 1);
		pageLabel.setText(String.format("%d / %d", index + 1, pagesNo));
		pageIndex = index;
	}

	private void setPage(int index) {
		double contentsHeight = pageContentVBox.getBoundsInLocal().getHeight();
		double viewportHeight = containerScrollPane.getViewportBounds().getHeight();
		ImageView iv = (ImageView) pageContentVBox.getChildren().get(index);
		containerScrollPane.setVvalue(iv.getBoundsInParent().getMinY() / (contentsHeight - viewportHeight));
		setPageIndex(index);
	}

	protected int getZoomRatio() {
		try {
			return zoomDecimalFormat.parse(zoomComboBox.getEditor().getText()).intValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return (int) zoom;
	}
	
	private void onComboBoxZoomChanged() {
	    try {
	      if (zoomComboBox.getValue() != null) {
	        float newZoom = zoomDecimalFormat.parse(zoomComboBox.getValue()).floatValue() / 100f;

	        if (newZoom < MIN_ZOOM)
	          newZoom = MIN_ZOOM;

	        if (newZoom > MAX_ZOOM)
	          newZoom = MAX_ZOOM;

	        setZoomRatio(newZoom);
	      }
	    } catch(ParseException e) {
	      e.printStackTrace();
	    }
	  }

}
