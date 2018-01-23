package com.tiagohs.util;

import java.util.Locale;
import java.util.ResourceBundle;

public class I18N {
	
	public static Locale PORTUGUESE_BRAZILLIAN = new Locale("pt", "BR");
	public static Locale ENGLISH = new Locale("en", "US");
	
	private Locale defaultLocale;
	private ResourceBundle bundle;
	
	public I18N() {
		this.updateResourceBundle ( updateLocale(PORTUGUESE_BRAZILLIAN) );
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

	public void updateDefaultLocale(Locale defaultLocale) {
		this.updateResourceBundle ( updateLocale(defaultLocale) );
	}

	public ResourceBundle getBundle() {
		return bundle;
	}

	public void setBundle(ResourceBundle bundle) {
		this.bundle = bundle;
	}
	
	
}
