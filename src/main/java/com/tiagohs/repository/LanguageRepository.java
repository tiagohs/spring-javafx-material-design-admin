package com.tiagohs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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
	@Query("UPDATE Language SET isDefault = 'true' WHERE language_code = :language_code AND country_code = :country_code")
	void setLanguageAsDefault(@Param("language_code") String language, @Param("country_code") String country);
}
