package com.tiagohs.util;

import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.text.ParseException;
import java.util.Arrays;
import java.util.Locale;
import java.util.ResourceBundle;

import com.jfoenix.controls.JFXButton;

import javafx.beans.value.ObservableValue;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingFXUtils;
import javafx.geometry.Bounds;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Separator;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import net.sf.jasperreports.engine.DefaultJasperReportsContext;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReportsContext;
import net.sf.jasperreports.engine.PrintPageFormat;

public class JRPrintPreview extends Stage {
  public static final int REPORT_DPI_RESOLUTION = 72;
  protected final int SPACING = 25;
  protected final float MIN_ZOOM = 0.5f;
  protected final float MAX_ZOOM = 10f;
  protected final int zooms[] = {50, 75, 100, 125, 150, 175, 200, 250, 400, 800};

  protected float zoom = 0;
  protected float realZoom = 0;
  protected DecimalFormat zoomDecimalFormat = new DecimalFormat("#.##");

  protected JasperPrint jasperPrint;
  protected double screenResolution = REPORT_DPI_RESOLUTION;
  protected JasperReportsContext jasperReportsContext;
  protected ResourceBundle resourceBundle;

  protected ToggleButton btnActualSize = new ToggleButton();
  protected ToggleButton btnFitPage = new ToggleButton();
  protected ToggleButton btnFitWidth = new ToggleButton();
  
  protected Button btnSave = new Button();
  protected Button btnFirst = new Button();
  protected Button btnLast = new Button();
  protected Button btnNext = new Button();
  protected Button btnPrevious = new Button();
  protected Button btnPrint = new JFXButton();
  protected Button btnZoomIn = new Button();
  protected Button btnZoomOut = new Button();
  
  protected ComboBox<String> cmbZoom = new ComboBox<>();
  protected Label lblStatus = new Label();
  protected VBox vBoxPage = new VBox();
  protected HBox statusBar = new HBox();
  protected ScrollPane scrollPane = new ScrollPane();
  protected ToolBar toolBar = new ToolBar();
  protected TextField txtGoTo = new TextField();

  protected DropShadow dropShadow = new DropShadow();
  protected int pageIndex;

  public JRPrintPreview(JasperPrint jasperPrint) {
    this(jasperPrint, null);
  }

  public JRPrintPreview(JasperPrint jasperPrint, ResourceBundle resBundle) {
    this(DefaultJasperReportsContext.getInstance(), jasperPrint, resBundle);
  }

  public JRPrintPreview(JasperReportsContext jasperReportsContext, JasperPrint jasperPrint, ResourceBundle resBundle) {
    this.jasperReportsContext = jasperReportsContext;
    this.jasperPrint = jasperPrint;

    initResources(resBundle);
    screenResolution = Screen.getPrimary().getDpi();
    initComponents();
    setZoomRatio(1);

    double width = vBoxPage.getBoundsInParent().getWidth() + 2 * SPACING + 13; // vBoxPage width + 2 * SPACING + vertical scroll bar width
    setWidth(width > 1000 ? 1000 : width);
  }

  protected void initResources(ResourceBundle resBundle) {
    if (resBundle == null)
      this.resourceBundle = ResourceBundle.getBundle("net/sf/jasperreports/view/viewer", Locale.getDefault());
    else
      this.resourceBundle = resBundle;
  }

  private void initComponents() {
    setTitle("Print preview");
    initModality(Modality.APPLICATION_MODAL);

    ToggleGroup group = new ToggleGroup();
    btnActualSize.setToggleGroup(group);
    btnFitPage.setToggleGroup(group);
    btnFitWidth.setToggleGroup(group);
    btnActualSize.setSelected(true);

    setMinHeight(150);
    setMinWidth(500);
    setHeight(480);

    btnPrint.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/print.GIF")))); // NOI18N
    btnPrint.setTooltip(new Tooltip(resourceBundle.getString("print")));
    btnPrint.setPrefSize(23, 23);
    btnPrint.setOnAction(e -> onBtnPrint());
    btnPrint.setFocusTraversable(false);

    btnFirst.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/first.GIF")))); // NOI18N
    btnFirst.setTooltip(new Tooltip(resourceBundle.getString("first.page")));
    btnFirst.setPrefSize(23, 23);
    btnFirst.setOnAction(e -> onBtnFirstPressed());
    btnFirst.setFocusTraversable(false);

    btnPrevious.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/previous.GIF")))); // NOI18N
    btnPrevious.setTooltip(new Tooltip(resourceBundle.getString("previous.page")));
    btnPrevious.setPrefSize(23, 23);
    btnPrevious.setOnAction(e -> onBtnPreviousPressed());
    btnPrevious.setFocusTraversable(false);

    btnNext.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/next.GIF")))); // NOI18N
    btnNext.setTooltip(new Tooltip(resourceBundle.getString("next.page")));
    btnNext.setPrefSize(23, 23);
    btnNext.setOnAction(e -> onBtnNextPressed());
    btnNext.setFocusTraversable(false);

    btnLast.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/last.GIF")))); // NOI18N
    btnLast.setTooltip(new Tooltip(resourceBundle.getString("last.page")));
    btnLast.setPrefSize(23, 23);
    btnLast.setOnAction(e -> onBtnLastPressed());
    btnLast.setFocusTraversable(false);

    txtGoTo.setTooltip(new Tooltip(resourceBundle.getString("go.to.page")));
    txtGoTo.setAlignment(Pos.CENTER);
    txtGoTo.setPrefSize(40, 23);
    txtGoTo.setOnAction(e -> onGoToTextChanged());
    txtGoTo.setFocusTraversable(false);
    
    btnSave.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/save.GIF")))); // NOI18N
    btnSave.setTooltip(new Tooltip("Save Image"));
    btnSave.setPrefSize(23, 23);
    btnSave.setOnAction(e -> onSave());
    btnSave.setFocusTraversable(false);
    
    btnActualSize.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/actualsize.GIF")))); // NOI18N
    btnActualSize.setTooltip(new Tooltip(resourceBundle.getString("actual.size")));
    btnActualSize.setPrefSize(23, 23);
    btnActualSize.setOnAction(e -> {
      if (((ToggleButton) e.getSource()).isSelected())
        onActualSizePressed();
    });
    btnActualSize.setFocusTraversable(false);

    btnFitPage.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/fitpage.GIF")))); // NOI18N
    btnFitPage.setTooltip(new Tooltip(resourceBundle.getString("fit.page")));
    btnFitPage.setPrefSize(23, 23);
    btnFitPage.setOnAction(e -> {
      if (((ToggleButton) e.getSource()).isSelected())
        onFitPagePressed();
    });
    btnFitPage.setFocusTraversable(false);

    btnFitWidth.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/fitwidth.GIF")))); // NOI18N
    btnFitWidth.setTooltip(new Tooltip(resourceBundle.getString("fit.width")));
    btnFitWidth.setPrefSize(23, 23);
    btnFitWidth.setOnAction(e -> {
      if (((ToggleButton) e.getSource()).isSelected())
        onSetFitToWidthPressed();
    });
    btnFitWidth.setFocusTraversable(false);

    toolBar.getItems().addAll( btnSave, btnActualSize, btnFitPage, btnFitWidth, new Separator(),btnZoomIn, btnZoomOut, cmbZoom, new Separator(),
        btnFirst, btnPrevious, txtGoTo, btnNext, btnLast, new Separator(), btnPrint);

    btnZoomIn.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/zoomin.GIF")))); // NOI18N
    btnZoomIn.setTooltip(new Tooltip(resourceBundle.getString("zoom.in")));
    btnZoomIn.setPrefSize(23, 23);
    btnZoomIn.setOnAction(e -> onBtnZoomIn());
    btnZoomIn.setFocusTraversable(false);

    btnZoomOut.setGraphic(new ImageView(new Image(getClass().getResourceAsStream("/net/sf/jasperreports/view/images/zoomout.GIF")))); // NOI18N
    btnZoomOut.setTooltip(new Tooltip(resourceBundle.getString("zoom.out")));
    btnZoomOut.setPrefSize(23, 23);
    btnZoomOut.setOnAction(e -> onBtnZoomOut());
    btnZoomOut.setFocusTraversable(false);

    cmbZoom.setEditable(true);
    cmbZoom.setTooltip(new Tooltip(resourceBundle.getString("zoom.ratio")));
    cmbZoom.setPrefSize(80, 24);
    for (int zoom : zooms)
      cmbZoom.getItems().add(zoom + "%");
    cmbZoom.getSelectionModel().select(2);
    cmbZoom.setFocusTraversable(false);
    cmbZoom.setOnAction(e -> onComboBoxZoomChanged());
    cmbZoom.addEventHandler(KeyEvent.KEY_PRESSED, (KeyEvent e) -> {
      if (e.getCode() == KeyCode.ENTER)
        onComboBoxZoomChanged();
    });

    vBoxPage.setSpacing(SPACING);
    vBoxPage.setPadding(new Insets(SPACING));
    vBoxPage.setAlignment(Pos.CENTER);

    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
    scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.AS_NEEDED);
    scrollPane.setContent(vBoxPage);
    scrollPane.viewportBoundsProperty().addListener((ObservableValue<? extends Bounds> arg0, Bounds arg1, Bounds arg2) -> {
        if (btnFitWidth.isSelected())
          onSetFitToWidthPressed();

        scrollPane.setFitToWidth(vBoxPage.prefWidth(-1) < arg2.getWidth());
        scrollPane.setFitToHeight(vBoxPage.prefHeight(-1) < arg2.getHeight());
      });
    scrollPane.vvalueProperty().addListener((ObservableValue<? extends Number> observable, Number oldValue, Number newValue) -> {
        int pagesNo = jasperPrint.getPages().size();
        double pageInterval = 1d / pagesNo;
        double low = pageIndex * pageInterval;
        double hi = low + pageInterval;
        int tmpPageIndex = pageIndex;

        if (hi < newValue.doubleValue() && pageIndex < pagesNo) { // Increase page index
          while (hi < newValue.doubleValue()) {
            tmpPageIndex++;
            hi += pageInterval;
          }

          setPageIndex(tmpPageIndex);
        } else if (low > newValue.doubleValue() && pageIndex > 0) { // Decrease page index
          while (low > newValue.doubleValue()) {
            tmpPageIndex--;
            low -= pageInterval;
          }

          setPageIndex(tmpPageIndex);
        }
      }
    );
		
    lblStatus.setFont(new Font("Dialog", 10));
    statusBar.setAlignment(Pos.CENTER);
    statusBar.setMaxHeight(24);
    statusBar.getChildren().add(lblStatus);

    VBox root = new VBox();
    VBox.setVgrow(scrollPane, Priority.ALWAYS);
    root.getChildren().addAll(toolBar, scrollPane, statusBar);
    setScene(new Scene(root));

    dropShadow.setColor(Color.GREY);
    dropShadow.setOffsetX(2);
    dropShadow.setOffsetY(2);
  }

  protected void refresh() {
    vBoxPage.getChildren().clear();

    for (int pageIndex = 0; pageIndex < jasperPrint.getPages().size(); pageIndex++) {
      try {
        java.awt.image.BufferedImage awtImage = (BufferedImage) JasperPrintManager.getInstance(jasperReportsContext).printToImage(jasperPrint, pageIndex, realZoom);
        Image image = SwingFXUtils.toFXImage(awtImage, null);

        ImageView iv = new ImageView(image);
        iv.setEffect(dropShadow);
        setPageIndex(0);
        vBoxPage.getChildren().add(iv);
      } catch (JRException ex) {
        ex.printStackTrace();
      }
    }
  }

  protected void setZoomRatio(float newZoom) {
    if (newZoom > 0) {
      cmbZoom.getEditor().setText(zoomDecimalFormat.format(newZoom * 100) + "%");

      if (zoom != newZoom) {
        zoom = newZoom;
        realZoom = (float) (zoom * screenResolution / REPORT_DPI_RESOLUTION);
        refresh();
      }
    }

    btnZoomIn.setDisable(zoom >= (float) zooms[zooms.length - 1] / 100);
    btnZoomOut.setDisable(zoom <= (float) zooms[0] / 100);
  }

  private void setRealZoomRatio(float newZoom) {
    if (newZoom > 0 && realZoom != newZoom) {
      zoom = (float) (newZoom * REPORT_DPI_RESOLUTION / screenResolution);
      realZoom = newZoom;

      cmbZoom.getEditor().setText(zoomDecimalFormat.format(zoom * 100) + "%");
      refresh();
    }

    btnZoomIn.setDisable(zoom >= (float) zooms[zooms.length - 1] / 100);
    btnZoomOut.setDisable(zoom <= (float) zooms[0] / 100);
  }

  protected int getZoomRatio() {
    try {
     return zoomDecimalFormat.parse(cmbZoom.getEditor().getText()).intValue();
    } catch(ParseException e) {
      e.printStackTrace();
    }

    return (int) zoom;
  }

  private void onBtnZoomIn() {
    btnActualSize.setSelected(false);
    btnFitPage.setSelected(false);
    btnFitWidth.setSelected(false);

    int index = Arrays.binarySearch(zooms, getZoomRatio());

    if (index < 0) {
      cmbZoom.getSelectionModel().select(zoomDecimalFormat.format(zooms[-index - 1]) + "%");
    } else if (index < cmbZoom.getItems().size() - 1) {
      cmbZoom.getSelectionModel().select(zoomDecimalFormat.format(zooms[index + 1]) + "%");
    }
  }

  private void onBtnZoomOut() {
    btnActualSize.setSelected(false);
    btnFitPage.setSelected(false);
    btnFitWidth.setSelected(false);

    int index = Arrays.binarySearch(zooms, getZoomRatio());

    if (index > 0) {
      cmbZoom.getSelectionModel().select(zoomDecimalFormat.format(zooms[index - 1]) + "%");
    } else if (index < -1) {
      cmbZoom.getSelectionModel().select(zoomDecimalFormat.format(zooms[-index - 2]) + "%");
    }
  }

  private void onComboBoxZoomChanged() {
    try {
      if (cmbZoom.getValue() != null) { // Can be null when clearSelection() triggers the action
        float newZoom = zoomDecimalFormat.parse(cmbZoom.getValue()).floatValue() / 100f;

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

  private void onGoToTextChanged() {
    try {
      int pageNumber = Integer.parseInt(txtGoTo.getText());

      if (pageNumber > 0 && pageNumber <= jasperPrint.getPages().size())
        setPage(pageNumber - 1);
    } catch(NumberFormatException e) {
      setPageIndex(pageIndex);
    }
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

  private void setPage(int index) {
    double contentsHeight = vBoxPage.getBoundsInLocal().getHeight();
    double viewportHeight = scrollPane.getViewportBounds().getHeight();
    ImageView iv = (ImageView) vBoxPage.getChildren().get(index);
    scrollPane.setVvalue(iv.getBoundsInParent().getMinY() / (contentsHeight - viewportHeight));
    setPageIndex(index);
  }

  protected void setPageIndex(int index) {
    int pagesNo = jasperPrint.getPages().size();

    btnFirst.setDisable(index == 0);
    btnPrevious.setDisable(index == 0);
    btnLast.setDisable(index == pagesNo - 1);
    btnNext.setDisable(index == pagesNo - 1);
    txtGoTo.setDisable(pagesNo == 1);

    txtGoTo.setText(String.valueOf(index + 1));
    lblStatus.setText(MessageFormat.format(resourceBundle.getString("page"), index + 1, pagesNo));
    pageIndex = index;
  }

  private void onSetFitToWidthPressed() {
    cmbZoom.getSelectionModel().clearSelection(); // Has to be before setRealZoomRatio() call because clearSelection() will clear cmbZoom text box
    setRealZoomRatio((float) (scrollPane.getViewportBounds().getWidth() - 2 * SPACING - 2) / jasperPrint.getPageFormat(pageIndex).getPageWidth());
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
  
  private void onActualSizePressed() {
    cmbZoom.getSelectionModel().select(2); // 100% size
  }

  private void onFitPagePressed() {
    PrintPageFormat pageFormat = jasperPrint.getPageFormat(pageIndex);
    float heightRatio = ((float) scrollPane.getViewportBounds().getHeight() - 2 * SPACING) / pageFormat.getPageHeight();
    float widthRatio = ((float) scrollPane.getViewportBounds().getWidth() - 2 * SPACING) / pageFormat.getPageWidth();
    cmbZoom.getSelectionModel().clearSelection(); // Has to be before setRealZoomRatio() call because clearSelection() will clear cmbZoom text box
    setRealZoomRatio(heightRatio < widthRatio ? heightRatio : widthRatio);
  }

  private void onBtnPrint() {
    Task<Boolean> task = new Task<Boolean>() {
      @Override protected Boolean call() throws Exception {
        try {
          btnPrint.setDisable(true);
          getScene().setCursor(Cursor.WAIT);
          return JasperPrintManager.getInstance(jasperReportsContext).print(jasperPrint, true);
        } catch (JRException ex) {
          ex.printStackTrace();
        } finally {
          getScene().setCursor(Cursor.DEFAULT);
          btnPrint.setDisable(false);
        }

        return false;
      }
    };

    task.run();
  }
}
