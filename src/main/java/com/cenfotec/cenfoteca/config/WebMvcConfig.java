package com.cenfotec.cenfoteca.config;

import javax.persistence.EntityManagerFactory;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate4.support.OpenSessionInViewInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/*
 * This class is used to configure resource for the app
 */
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter {
	
	@Autowired private EntityManagerFactory entityManagerFactory;
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
            registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
            registry.addResourceHandler("/assets/**").addResourceLocations("/assets/");
    }
	
	 @Bean
	 public ViewResolver getViewResolver(){
		 InternalResourceViewResolver resolver = new InternalResourceViewResolver();
		 resolver.setPrefix("/WEB-INF/views/");
		 resolver.setSuffix(".jsp");
		 return resolver;
	    }
	 
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		 OpenSessionInViewInterceptor osivi = new OpenSessionInViewInterceptor();
		 osivi.setSessionFactory(entityManagerFactory.unwrap(SessionFactory.class));
		 registry.addWebRequestInterceptor(osivi);
	 }
}
