package com.tiagohs.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Language;
import com.tiagohs.repository.LanguageRepository;

import javafx.concurrent.Task;
import javafx.concurrent.WorkerStateEvent;
import javafx.event.EventHandler;

@Service("languageService")
public class LanguageServiceImpl extends BaseCrudService<Language, JpaRepository<Language,Long>> implements LanguageService {
	
	private LanguageRepository languageRepository;
	
	public LanguageServiceImpl(LanguageRepository repository) {
		super(repository);
		
		this.languageRepository = repository;
	}

	public LanguageRepository getLanguageRepository() {
		return languageRepository;
	}

	public void setLanguageRepository(LanguageRepository languageRepository) {
		this.languageRepository = languageRepository;
	}

	@Override
	public javafx.concurrent.Service<Language> findDefaultLanguage(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Language>() {
			protected Language call() throws Exception {
				return findDefaultLanguage();
			};
		}, onSucess, beforeStart);
	}
	
	@Override
	public Language findDefaultLanguage() {
		return languageRepository.findDefaultLanguage();
	}
	
	@Override
	public javafx.concurrent.Service<Void> cleanLanguageDefault(EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Void>() {
			protected Void call() throws Exception {
				languageRepository.cleanLanguageDefault();
				
				return null;
			};
		}, onSucess, beforeStart);
	}

	@Override
	public javafx.concurrent.Service<Void> setLanguageAsDefault(String language, String country,
			EventHandler<WorkerStateEvent> onSucess, EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Void>() {
			protected Void call() throws Exception {
				languageRepository.setLanguageAsDefault(language, country);
				
				return null;
			};
		}, onSucess, beforeStart);
	}

	@Override
	public javafx.concurrent.Service<Language> changeDefaultLanguage(Language newDafaultLanguage, EventHandler<WorkerStateEvent> onSucess,
			EventHandler<WorkerStateEvent> beforeStart) {
		return createService(new Task<Language>() {
			protected Language call() throws Exception {
				
				languageRepository.cleanLanguageDefault();
				languageRepository.setLanguageAsDefault(newDafaultLanguage.getLanguageCode(), newDafaultLanguage.getCountryCode());
				
				return null;
			};
		}, onSucess, beforeStart);
	}


}
