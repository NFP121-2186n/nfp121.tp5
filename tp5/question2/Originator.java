package question2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Originator {
	private List<String> liste;
	Map<String, Integer> occurences;

	// Sets the value for the article

	public void set(List<String> newList, Map<String, Integer> newOccurences) {
		this.liste = newList;
		this.occurences = newOccurences;
	}

	// Creates a new Memento with a new article

	public Memento storeInMemento() {
		return new Memento(liste, occurences);
	}

	// Gets the article currently stored in memento

	public List<String> restoreListFromMemento(Memento memento) {
		liste = memento.getSavedList();
		return liste;

	}
	
	public Map<String, Integer> restoreMapFromMemento(Memento memento) {
		occurences = memento.getSavedMap();
		return occurences;
		
	}
	

}
