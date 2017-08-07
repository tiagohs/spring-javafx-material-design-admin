package com.tiagohs.controller;

import org.springframework.stereotype.Controller;

import com.tiagohs.util.WindowsUtils;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.concurrent.Worker;
import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

@Controller
public class LoginDialogController extends BaseController {
	
	public static final String PATH_FXML = "/fxml/login_dialog.fxml";
	public static final String TITLE = "Login - Confirmar Informações";
	public static final String PATH_ICON = WindowsUtils.ICON_APP_PATH;
	
	private static String HTML_STRING = //
	           "<html>"//
	                   + "<head> " //
	                   + "  <script language='javascript'> " //
	                   + "     function changeBgColor()  { "//
	                   + "       var color= document.getElementById('color').value; "//
	                   + "       document.body.style.backgroundColor= color; " //
	                   + "     } " //
	                   + "  </script> "//
	                   + "</head> "//
	                   + "<body> "//
	                   + "   <h2>This is Html content</h2> "//
	                   + "   <b>Enter Color:</b> "//
	                   + "   <input id='color' value='yellow' /> "//
	                   + "   <button onclick='changeBgColor();'>Change Bg Color</button> "//
	                   + "</body> "//
	                   + "</html> "//
	   ;
	
	@FXML
	private WebView webView;
	
	@FXML
	private AnchorPane container;
	
	public <T> void init(javafx.stage.Stage stage, java.util.HashMap<String,T> parameters) {
		super.init(stage, parameters);
		System.out.println("indo");
		// Get WebEngine via WebView
		
		WebEngine webEngine = webView.getEngine();
		 
		System.setProperty("http.proxyHost","proxy.inf.bndes.net");
		System.setProperty("http.proxyPort","8080");
		System.setProperty("https.proxyHost","proxy.inf.bndes.net");
		System.setProperty("https.proxyPort","8080");
		
		webEngine.load("https://google.com");
		
		webEngine.getLoadWorker().stateProperty().addListener(
			      new ChangeListener<Worker.State>() {
			      public void changed(ObservableValue ov, Worker.State oldState, Worker.State newState) {                               
			             System.out.println("webEngine result "+ newState.toString());
			      }
			  });
	};
	
	@Override
	protected void onClose() {
		
	}
	
}
