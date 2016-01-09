package com.cenfotec.cenfoteca.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/*
 * This Class works as main controller to intercept requests other then auth
 */
@Controller
public class SwaggerController {
	// Configure Logging
	private static final Logger log = Logger.getLogger(SwaggerController.class);
	
	/*
	 * This function is for home page
	 */	
	@RequestMapping(value= {"/swagger"},method = RequestMethod.GET)
    public ModelAndView root() {
    	
		log.info("Getting swagger root view");    	
    	
		ModelAndView mv = new ModelAndView("swagger/home");
		log.info("Root view is ok!");
		return mv;
    }
	
}
