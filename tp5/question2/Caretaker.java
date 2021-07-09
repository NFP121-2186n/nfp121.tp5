package question2;
import java.util.*;
public class Caretaker {
	// Where all mementos are saved
	
	Stack<Memento> saved = new Stack<Memento>();

	// Adds memento to the ArrayList
	public boolean hasMemento() {
		return !saved.isEmpty();
	}
	public void addMemento(Memento m) { saved.push(m); }
   
	// Gets the memento requested from the ArrayList
	
	public Memento getMemento() { 		
		Memento mem = saved.pop();
		return mem;
		}
}
