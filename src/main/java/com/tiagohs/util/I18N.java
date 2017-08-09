package com.tiagohs.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N {
	
	private Locale defaultLocale;
	private ResourceBundle bundle;
	
	public I18N() {
		this.updateResourceBundle ( updateLocale("pt", "BR") );
	}
	
	public I18N(Locale locale) {
		this.updateResourceBundle ( updateLocale(locale) );
	}
	
	public Locale updateLocale(Locale locale) {
		this.defaultLocale = locale;
		
		return defaultLocale;
	}
	
	public Locale updateLocale(String language, String country) {
		return updateLocale(new Locale(language, country));
	}
	
	public ResourceBundle updateResourceBundle(ResourceBundle bundle) {
		this.bundle = bundle;
		
		return bundle;
	}
	
	public String getString(String key) {
		return bundle.getString(key);
	}
	
	public ResourceBundle updateResourceBundle(Locale locale) {
		return this.updateResourceBundle( ResourceBundle.getBundle("i18n/i18n", locale) );
	}

	public Locale getDefaultLocale() {
		return defaultLocale;
	}

	public void setDefaultLocale(Locale defaultLocale) {
		this.defaultLocale = defaultLocale;
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	
}
