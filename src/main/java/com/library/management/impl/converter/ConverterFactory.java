package com.library.management.impl.converter;

import javax.inject.Named;

import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Bean;

import com.library.management.api.converter.IConverterFactory;

@Named
public class ConverterFactory implements IConverterFactory{
	
	
	
	@Bean
	ConverterFactory createConverterFactory(){
		return new ConverterFactory();
	}
	
	@Override
	public Object convertBeans(Object source, Object target){
		
		BeanUtils.copyProperties(source, target);
		
		return target;
	}
	
	@Override
	public Object convertBeans(Object source, Object target, String... ignoreProperties){
		
		BeanUtils.copyProperties(source, target, ignoreProperties);
		
		return target;
	}


}
