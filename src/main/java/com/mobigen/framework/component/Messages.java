package com.mobigen.framework.component;

import java.util.Locale;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Component;

@Component
public class Messages {
	@Autowired
	private MessageSource messageSource;
	private MessageSourceAccessor accessor;
	
	@PostConstruct
	private void init() {
		accessor = new MessageSourceAccessor(messageSource, Locale.getDefault());
	}
	
	public String get(String code) {
		String msg = "";
		try {
			msg = accessor.getMessage(code);
		} catch (Exception e) {
			msg = null;
		}
		
		return msg;
	}
	
	public String get(String code, Object[] args) {
		String msg = "";
		try {
			msg = accessor.getMessage(code, args);
		} catch (Exception e) {
			msg = null;
		}
		
		return msg;
	}
}
