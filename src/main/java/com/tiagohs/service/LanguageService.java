package com.tiagohs.service;

import com.tiagohs.model.Language;

import javafx.concurrent.Service;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

public interface LanguageService extends IBaseService<Language> {
	
	Service<Language> changeDefaultLanguage(Language newDafaultLanguage, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
	
	Service<Language> findDefaultLanguage(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
	Language findDefaultLanguage();
	
	Service<Void> cleanLanguageDefault(EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
	
	Service<Void> setLanguageAsDefault(String language, String country, EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart);
}
