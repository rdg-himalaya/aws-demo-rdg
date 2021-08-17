package com.aws.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aws.demo.controller.ContosoServiceImpl;

@SpringBootApplication
@RestController
public class AwsDemoRdgApplication {

	private static final Logger logger = LogManager.getLogger(AwsDemoRdgApplication.class);
	@Autowired public ContosoServiceImpl appService;	

	/**
     * Find the distance between landmarks via given route 
     * 
     * @param landmarkInputs
     *           the landmarks
     * @return The distance between landmarks via given route
     */  
  
	@RequestMapping(method = RequestMethod.GET, value = "/pathdistance/{landmarkInputs}")
	public String getPathDistance(@PathVariable String landmarkInputs) {
		logger.info("Inside getPathDistance method");
		String response ="" ;
		try
		{
			response = appService.countPathDistance(landmarkInputs);
		}
		catch (Exception e) {
			logger.error("Error inside getPathDistance");
			e.printStackTrace();
		}
		return response;

	}	  

  
  /**
   * Get the number of routes between given starting and ending points with maximum of 'N' stops.
   * 
   * @param startLandmark
   *           starting point or landmark
   * @param endLandmark
   *           end point or end landmark
   * @param maxStop
   *           max number of stops while reaching end landmark from start landmark
   * @return The number of possible routes
   */
	@RequestMapping(method = RequestMethod.GET, value = "/routecount/{startLandmark}/{endLandmark}/{maxStop}")
	public String getLandmarkRoutes(@PathVariable char startLandmark,@PathVariable char endLandmark,@PathVariable int maxStop)
			throws Exception {
		logger.info("Inside getLandmarkRoutes method");
		String response = "" ;
		try
		{
			response =  appService.getRouteCount(Character.toUpperCase(startLandmark), Character.toUpperCase(endLandmark), maxStop);
		}
		catch (Exception e) {
			logger.error("Error inside getPathDistance");
			e.printStackTrace();
		}
		return response;

	}

	
	public static void main(String[] args) {
		SpringApplication.run(AwsDemoRdgApplication.class, args);
	}

}
