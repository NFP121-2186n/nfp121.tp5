package question3;

import java.util.Set;
import java.util.AbstractCollection;
import java.util.HashSet;

public class HashSetFactory<E> implements Factory<AbstractCollection<E>>{
 
	@Override
	public AbstractCollection<E> create() {
		// TODO Auto-generated method stub
		return new HashSet<E>();
	}

}