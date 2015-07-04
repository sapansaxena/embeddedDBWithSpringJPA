package com.library.management.api.converter;

public interface IConverterFactory {

	public Object convertBeans(Object source, Object target);

	public Object convertBeans(Object source, Object target, String... ignoreProperties);
}
