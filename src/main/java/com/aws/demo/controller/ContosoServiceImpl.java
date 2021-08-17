package com.aws.demo.controller;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RestController;
import com.aws.demo.util.DataLoad;

@RestController
@Service
public class ContosoServiceImpl implements ContosoService{

	private static final Logger logger = LogManager.getLogger(ContosoServiceImpl.class);
	int pathCount ;
	static HashMap<Character, HashMap<Character, Integer>> engineMap = new HashMap<Character, HashMap<Character, Integer>>();
	static ArrayList<Character>[] nodeList ;
	static HashMap<Character, Boolean> visit;
	@Autowired public DataLoad loadDataService;
	@Value("${my.list.of.strings}")  // fetch input values from properties file while loading the project
	private List<String> myList;

	//@Override
	public String countPathDistance(String landmarks) {
		// TODO Auto-generated method stub

		engineMap = loadDataService.updateMap();
		return findDistance(landmarks);
	}

	// this method is to take the landmark details as input and calculate the path distance
	private String findDistance(String input)
	{
		boolean path = true;
		char [] nodes = input.toCharArray(); // convert landmark input in character array
		int length = 0;

		for (int i=0;i< nodes.length-1;i++) { // iterate through landmark array and calculate the distance

			int j = i+1;
			if(engineMap.containsKey(nodes[i]))
			{
				HashMap<Character, Integer> tempMap= engineMap.get(nodes[i]);
				if(tempMap.containsKey(nodes[j]))
					length = length +tempMap.get(nodes[j]);
				else path = false;
			}
			else path = false;

		}
		if(path) return Integer.toString(length);
		else return "Path not found.";

	}

	@Override
	public String getRouteCount(char a, char b , int maxStop) {

		// when start and end landmark is same
		if(Character.toUpperCase(a) == Character.toUpperCase(b))
			return "Start and End landmark can not be same !";
		// when start or end landmark is incorrect 
		engineMap = loadDataService.updateMap();
		if(!engineMap.containsKey(Character.toUpperCase(a)) || !engineMap.containsKey(Character.toUpperCase(b)))
			return "Path not found." ;

		return findAllRoutes(Character.toUpperCase(a), Character.toUpperCase(b), maxStop);
	} 


	public String findAllRoutes(char s, char d , int maxStop)
	{
		pathCount = 0;

		HashMap<Character, Boolean> trackVisitedNode = new HashMap<Character, Boolean>();
		Iterator <Character> it = engineMap.keySet().iterator();  
		while(it.hasNext())  
		{   char key = it.next();
		trackVisitedNode.put(key, false);
		}
		ArrayList<Character> pathDetails = new ArrayList<>();

		// add source to path Array
		pathDetails.add(s);

		// Call recursive method
		calAllRoutes(s, d, trackVisitedNode, pathDetails, maxStop);
		return String.valueOf(pathCount);
	}

	// A recursive function to calculate and print all paths from source and destinations
	// isVisited[] to keeps track of all visited nodes in current path
	// finalPathList<> is to store the routes from source and destinations
	private void calAllRoutes(Character u, Character d,HashMap<Character, Boolean> isVisited,
			List<Character> finalPathList , int maxStop)
	{
		if (u.equals(d)) { // when source and destinations is same , path found for given inputs
			logger.info(finalPathList); // print the path 
			
			if(finalPathList.size() <= maxStop+2 ) // check if the total number of nodes between start and end landmark is less or equal to max stop
				pathCount++; 
			return;
		}

		// Mark current node as visited

		isVisited.put(Character.toUpperCase(u), true);

		// Get all adjacent to current node and iterate over those which is not visited yet 
		HashMap<Character, Integer> temp = engineMap.get(u);
		for (Entry<Character, Integer> entry : temp.entrySet()) {
			if (!isVisited.get(entry.getKey())) {
				
				finalPathList.add(entry.getKey());
				calAllRoutes(entry.getKey(), d, isVisited, finalPathList ,maxStop);

				// remove current node from final path list
				finalPathList.remove(entry.getKey());
			}
		}

		// Update the current node in visited node list
		isVisited.put(u, false);
	}

}
