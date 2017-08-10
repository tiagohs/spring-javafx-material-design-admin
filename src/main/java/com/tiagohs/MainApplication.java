package com.tiagohs;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.tiagohs.controller.LoginController;
import com.tiagohs.controller.RootController;
import com.tiagohs.model.Language;
import com.tiagohs.model.User;
import com.tiagohs.service.LanguageService;
import com.tiagohs.service.UserService;
import com.tiagohs.util.I18N;
import com.tiagohs.util.WindowsUtils;

import javafx.application.Application;
import javafx.application.HostServices;
import javafx.stage.Stage;

@SpringBootApplication
public class MainApplication extends Application  {
	
	
	
	public static ConfigurableApplicationContext springContext;
	public static I18N i18n;
	public static HostServices hostServices;
	
	private UserService userService;
	private LanguageService languageService;
	
    @Override
    public void init() throws Exception {
    	springContext = SpringApplication.run(MainApplication.class);
    	userService = springContext.getBean(UserService.class);
    	languageService = springContext.getBean(LanguageService.class);
    	
    	initI18N();
    }
    
    private void initI18N() {
    	Language languageDefault = languageService.findDefaultLanguage();
    	
    	if (languageDefault != null) {
    		i18n = new I18N(new Locale(languageDefault.getLanguageCode(), languageDefault.getCountryCode()));
    	} else {
    		i18n = new I18N(I18N.PORTUGUESE_BRAZILLIAN);
    	}
    	
    	
    }
   
    @Override
	public void start(Stage primaryStage) throws Exception {
    	hostServices = this.getHostServices();
    	
    	userService.finUserSignIn(e -> {
    		try {
    			User user = (User) e.getSource().getValue();
        		
        		if (user == null) {
        			WindowsUtils.openNewWindow(primaryStage, LoginController.PATH_FXML, i18n.getString(LoginController.LOGIN_TITLE_KEY), LoginController.PATH_ICON, null);
        		} else {
        			WindowsUtils.openNewWindow(primaryStage, RootController.PATH_FXML, i18n.getString(RootController.ROOT_TITLE_KEY), RootController.PATH_ICON, null);
        		}
    		} catch(Exception ex) {
    			ex.printStackTrace();
    		}
    		
    	}, null);
    	
	}
    
	public static void main(String[] args) {
		launch(args);
	}
	
}
