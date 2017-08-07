package com.tiagohs.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import com.tiagohs.model.Language;
import com.tiagohs.repository.LanguageRepository;

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
	
	

}
