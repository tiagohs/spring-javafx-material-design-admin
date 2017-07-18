package com.tiagohs.util;

import com.tiagohs.MainApplication;

import io.datafx.controller.flow.Flow;
import io.datafx.controller.flow.FlowException;
import io.datafx.controller.flow.FlowHandler;
import io.datafx.controller.flow.container.AnimatedFlowContainer;
import io.datafx.controller.flow.container.ContainerAnimations;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WindowsUtils {
	
	public static final String BASE_APPLICATION_CSS_PATH = MainApplication.class.getResource("/css/application.css").toExternalForm();
	public static final String ICON_APP_PATH = MainApplication.class.getResource("/images/icon.png").toExternalForm();
	
	public static void replaceFxmlOnWindow(Pane root, Class<?> controller) throws FlowException {
		Flow innerFlow = new Flow(controller);
		FlowHandler flowHandler = innerFlow.createHandler();
        
        root.getChildren().clear();
        root.getChildren().add(flowHandler.start(new AnimatedFlowContainer(Duration.seconds(2), ContainerAnimations.FADE)));
	}
	
	public static Stage openNewWindow(Class<?> controller) throws FlowException {
		Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.getIcons().add(new Image(WindowsUtils.ICON_APP_PATH));
        stage.setResizable(false);
        
		Scene scene = new Scene(new Flow(controller).start());
        scene.getStylesheets().add(BASE_APPLICATION_CSS_PATH);
        stage.setScene(scene);
        stage.show();
        
        return stage;
	}
}
