package question2;

import java.util.*;

public class Memento {
	
    // The article stored in memento Object
    private List<String> liste;
    Map<String, Integer> occurences;
    // Save a w article String to the memento Object

    public Memento(List<String> liste, Map<String, Integer> occurences) {
    	this.liste = liste; 
    	this.occurences = occurences;
    }
    // Return the value stored in article

    public List<String> getSavedList() { 
    	return liste;  	
    }
    public Map<String, Integer> getSavedMap() { 
    	return occurences;  	
    }
}
