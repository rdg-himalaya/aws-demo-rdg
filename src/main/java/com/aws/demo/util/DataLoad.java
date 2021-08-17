package com.aws.demo.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DataLoad {
	
	static char startNode ;
	static char endNode ;
	static StringBuffer distance;
	static HashMap<Character, HashMap<Character, Integer>> engineMap = new HashMap<Character, HashMap<Character, Integer>>();
	static HashMap<Character, Boolean > trackMap = new HashMap<Character, Boolean>();
	
	
	@Value("${my.list.of.strings}")  
	private List<String> inputDataList;
	
	 // adjacency list
    private ArrayList<Character>[] adjList;
    private int v;
    //ArrayList<ArrayList<Character> > nodeList;
    ArrayList<Character>[] nodeList ;
	
	public HashMap<Character, HashMap<Character, Integer>> updateMap()
	{
		if(inputDataList !=null)
		{
			for (String string : inputDataList) {	
				processInput(string);
				HashMap<Character, Integer> tempMap = new HashMap<Character, Integer>();
				if (engineMap.containsKey(startNode))
				{
					tempMap = engineMap.get(startNode);
				}
					tempMap.put(endNode,Integer.parseInt(distance.toString()));
					engineMap.put(startNode,tempMap);
			}
			
		}
		return engineMap;
	}
  
  // this method to convert first to char values as starting and ending node and remaining chars to calculate the distance 
  // Example: BC20 -> B , C , 20 (we need to calculate the distance when input is not in single digit)
  private static void processInput(String inputVal) 
	{	
		distance = new StringBuffer();
		char[] nodes = inputVal.toCharArray();
		startNode = nodes[0];
		endNode = nodes[1];
		for (int i=2;i<inputVal.length();i++)
		{
			distance.append(nodes[i]);
		}
	}
  
  // utility method to initialise
  // adjacency list

 
  
  
  
@SuppressWarnings("unchecked")
//public void processRouteInput()
public ArrayList<Character>[] processRouteInput()
  {
	  updateMap();
	  //nodeList = new ArrayList<ArrayList<Character> >(engineMap.size());
	  nodeList = new ArrayList[engineMap.size()];
	  int i = 0;
	  Iterator <Character> it = engineMap.keySet().iterator();  
	  while(it.hasNext())  
	  {
		  nodeList[i] = new ArrayList<Character>();
		  ArrayList<Character> tempList = new ArrayList<Character>();
		  tempList.addAll(engineMap.get(it.next()).keySet());
		  nodeList[i].addAll(tempList);
		  i++;
	  }
	 return nodeList;
  }  


public void printAllPaths(char s, char d)
{
    boolean[] isVisited = new boolean[v];
    ArrayList<Character> pathList = new ArrayList<>();

    // add source to path[]
    pathList.add(s);

    // Call recursive utility
    printAllPathsUtil(s, d, isVisited, pathList);
}

// A recursive function to print
// all paths from 'u' to 'd'.
// isVisited[] keeps track of
// vertices in current path.
// localPathList<> stores actual
// vertices in the current path
private void printAllPathsUtil(Character u, Character d,
                               boolean[] isVisited,
                               List<Character> localPathList)
{

    if (u.equals(d)) {
        System.out.println(localPathList);
        // if match found then no need to traverse more till depth
        return;
    }

    // Mark the current node
    isVisited[u] = true;

    // Recur for all the vertices
    // adjacent to current vertex
    for (Character i : nodeList[u]) {
        if (!isVisited[i]) {
            // store current node
            // in path[]
            localPathList.add(i);
            printAllPathsUtil(i, d, isVisited, localPathList);

            // remove current node
            // in path[]
            localPathList.remove(i);
        }
    }

    // Mark the current node
    isVisited[u] = false;
}

 /*     
	  if(engineMap.size() > 0)
	  {
		  int i=0;
		  adjList = new ArrayList[engineMap.size()]; 
		  Iterator <Character> it = engineMap.keySet().iterator();  
		  while(it.hasNext())  
		  {
			  adjList[i] = new ArrayList<>();
			  HashMap<Character, Integer> tempMap = new HashMap<Character, Integer>();
			  tempMap = engineMap.get(it.next()); 
			  Character [] arr = (Character[]) tempMap.keySet().toArray();

 			// adjList[i].add
		  }
		  for (int i = 0; i < engineMap.size(); i++) {
	          adjList[i] = new ArrayList<>();
	          adjList[i].addAll(engineMap.keySet(it.next()));

	      }		  
	  }
	  
	  
	  for (int i = 0; i < v; i++) {
          adjList[i] = new ArrayList<>();
      }
  }
   
    // add edge from u to v
  public void addEdge(int u, int v)
  {
      // Add v to u's list.
      adjList[u].add(v);
  }
    // Constructor
  public AwsGraph(int vertices)
  {

      // initialise vertex count
      this.v = vertices;

      // initialise adjacency list
      initAdjList();
  }
   @SuppressWarnings("unchecked")
  private void initAdjList()
  {
      adjList = new ArrayList[v];

      for (int i = 0; i < v; i++) {
          adjList[i] = new ArrayList<>();
      }
  }
 */
 

//add edge from u to v
 
}
