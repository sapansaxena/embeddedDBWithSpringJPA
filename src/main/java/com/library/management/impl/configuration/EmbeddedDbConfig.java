package com.library.management.impl.configuration;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;



@Configuration
public class EmbeddedDbConfig {

	
	 @Bean
	    public DataSource dataSource(){
	        return new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.HSQL)
	                .addScript("sql/schema.sql")
	                .addScript("sql/testdata/create_author.sql")
	                .addScript("sql/testdata/create_member.sql")
	                .addScript("sql/testdata/create_book.sql")
	                .addScript("sql/testdata/create_book_author.sql")
	                .addScript("sql/testdata/create_book_copy.sql")
	                .addScript("sql/testdata/create_member_copy.sql")
	                .build();
	        	
	 }
	 
	 @Bean
	    public NamedParameterJdbcTemplate createTemplate(){
	        return new NamedParameterJdbcTemplate( dataSource() );
	    }

	    @Bean
	    public JdbcTemplate createJdbcTemplate(){
	        return new JdbcTemplate( dataSource() );
	    }
}
