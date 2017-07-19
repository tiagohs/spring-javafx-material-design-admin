package com.tiagohs.util;

import java.io.IOException;
import java.io.InputStream;

import com.tiagohs.MainApplication;
import com.tiagohs.controller.BaseController;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class WindowsUtils {
	
	public static final String BASE_APPLICATION_CSS_PATH = MainApplication.class.getResource("/css/application.css").toExternalForm();
	public static final String ICON_APP_PATH = MainApplication.class.getResource("/images/icon.png").toExternalForm();
	
	public static void replaceFxmlOnWindow(Pane root, String path) throws Exception {
        root.getChildren().clear();
        root.getChildren().add(loadFxml(path).load());
	}
	
	public static Stage openNewWindow(String fxmlPath, String title) throws Exception {
		
		Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        
        return openNewWindow(stage, fxmlPath, title);
	}
	
	public static Stage openNewWindow(Stage stage, String fxmlPath, String title) throws Exception {
		
        stage.getIcons().add(new Image(WindowsUtils.ICON_APP_PATH));
        stage.setTitle(title);
        stage.setResizable(false);
        
        FXMLLoader loader = loadFxml(fxmlPath);
		Scene scene = new Scene(loader.load());
        scene.getStylesheets().add(BASE_APPLICATION_CSS_PATH);
        stage.setScene(scene);
        stage.show();
        
        BaseController baseController = loader.getController();
        baseController.init(stage);
        
        return stage;
	}
	
	public static FXMLLoader loadFxml(String url) {
		  
		 try (InputStream fxmlStream = WindowsUtils.class.getResourceAsStream(url)) {
			 FXMLLoader loader = new FXMLLoader();
			 loader.setLocation(WindowsUtils.class.getResource(url));
			 loader.setControllerFactory( clazz -> { return MainApplication.springContext.getBean(clazz);});
			   
			 return loader;
		 } catch (IOException ioException) {
			 throw new RuntimeException(ioException);
		 }
	 }
}
