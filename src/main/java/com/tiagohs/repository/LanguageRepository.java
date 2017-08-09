package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tiagohs.model.Language;

@Repository("languageRepository")
public interface LanguageRepository extends JpaRepository<Language, Long> {
	
	@Query("SELECT l FROM Language l WHERE l.isDefault = 'true'")
	Language findDefaultLanguage();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE Language SET isDefault = 'false' WHERE isDefault = 'true'")
	void cleanLanguageDefault();
	
	@Modifying(clearAutomatically = true)
	@Transactional
	@Query("UPDATE Language SET isDefault = 'true' WHERE language = :language AND country = :country")
	void setLanguageAsDefault(String language, String country);
}
