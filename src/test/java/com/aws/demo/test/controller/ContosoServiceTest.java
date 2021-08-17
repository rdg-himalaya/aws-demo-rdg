package com.aws.demo.test.controller;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.aws.demo.controller.ContosoServiceImpl;
import com.aws.demo.util.DataLoad;


@ExtendWith(SpringExtension.class)
@SpringBootTest
class ContosoServiceTest {

	@Autowired private ContosoServiceImpl appService;
	@Autowired public DataLoad loadDataService;
	@Test
	void testLandmarkDistance_01() { // unit test to find the path distance
		
		// Scenario 1 : Happy Path , Distance for Landmarks A-> B -> C
		String output01 = appService.countPathDistance("ABC");
		assertEquals("12",output01 );
		
	} 
	
	@Test
	void testLandmarkDistance_02() { // unit test to find the path distance
		
		// Scenario 1 : Happy Path , Distance for Landmarks A-> B -> C
		String output02 = appService.countPathDistance("ABC");
		assertNotEquals("-777",output02 );
		
	}
	
	@Test
	void testLandmarkDistance_03() { // unit test to find the path distance
		
		// Scenario 1 : Happy Path , Distance for Landmarks A-> B -> C
		String output03 = appService.countPathDistance("AbC");
		assertEquals("Path not found.",output03 );
		
	}

	
	@Test
	void testLandmarkRoute_02() {

		// Scenario 2 : Happy Path , where input chars are in lower case , application will handle this scenario 
		String output02 = appService.getRouteCount('a', 'd', 8);
		assertEquals("4",output02 );
		
	}
	
	@Test
	void testLandmarkRoute_03() {
		
		// Scenario 1 : Happy Path , Distance for Landmarks A and E with max 2 stops
		String output01 = appService.getRouteCount('A', 'E', 2);
		assertEquals("5",output01 );
		
	}
	
	@Test
	void testLandmarkRoute_04() {
		
		// Scenario 2 : When inputs are wrong and landmark datas are not available 
		String output04 = appService.getRouteCount('E', 'e', 4);
		assertEquals("Start and End landmark can not be same !",output04 );
		
	}
	
	@Test
	void testLandmarkRoute_05() {
		
		// Scenario 2 : When inputs are wrong and landmark datas are not available 
		String output05 = appService.getRouteCount('R', 'e', 4);
		assertEquals("Path not found.",output05 );
		
	}
	
}

