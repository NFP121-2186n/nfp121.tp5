package question2;

import java.util.*;
import java.util.Map.Entry;

public class Memento {
	
    // The article stored in memento Object
    private List<String> liste;
    Map<String, Integer> occurences;
    // Save a w article String to the memento Object

    public Memento(List<String> liste, Map<String, Integer> occurences) {
    	List<String> oldList= new ArrayList<String>();
    	Map<String, Integer> oldMap= new HashMap<String, Integer>();
    	for (Entry<String, Integer> set : occurences.entrySet()) {
		    if(!occurences.containsKey(set.getKey())) {
		    	oldMap.put(set.getKey(), set.getValue());
		    }
		    
		}
    	
    	for(int i=0; i < liste.size(); i++)
    		oldList.add(liste.get(i));
    	
    	
    	
    	this.liste = oldList; 
    	this.occurences = oldMap;
    }
    // Return the value stored in article

    public List<String> getSavedList() { 
    	return liste;  	
    }
    public Map<String, Integer> getSavedMap() { 
    	return occurences;  	
    }
}
