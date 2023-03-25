package com.app.config;

import org.modelmapper.ModelMapper;
import org.springframework.boot.actuate.trace.http.HttpTraceRepository;
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// TODO: Auto-generated Javadoc
/**
 * The Class BeanConfiguration.
 */
@Configuration
public class BeanConfiguration {


    /**
     * Model mapper.
     *
     * @return the model mapper
     */
    @Bean
    ModelMapper modelMapper() {
        return new ModelMapper();
    }
    
    
    /**
     * Gets the http trace.
     *
     * @return the http trace
     */
    @Bean
    HttpTraceRepository getHttpTrace() {
    	return new InMemoryHttpTraceRepository();
    			
    }
}
