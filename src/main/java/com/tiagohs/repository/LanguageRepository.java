package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tiagohs.model.Language;

@Repository("languageRepository")
public interface LanguageRepository extends JpaRepository<Language, Long> {
	
}
