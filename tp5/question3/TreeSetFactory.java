package question3;

import java.util.AbstractCollection;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetFactory<E> implements Factory<AbstractCollection<E>>{
	@Override
	public AbstractCollection<E> create() {
		// TODO Auto-generated method stub
		return new TreeSet<E>();
	}

}
