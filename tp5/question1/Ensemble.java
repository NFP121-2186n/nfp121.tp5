package question1;

import java.util.*;

public class Ensemble<T> extends AbstractSet<T> {

	protected java.util.Vector<T> table = new java.util.Vector<T>();

	public int size() {
		return table.size();
	}

	public Iterator<T> iterator() {
		return table.iterator();
	}

	public boolean add(T t) {
		// à compléter pour la question1-1
		if(this.table.contains(t))
		return false;
		
		return table.add(t);
	}

	public Ensemble<T> union(Ensemble<? extends T> e) {
		Ensemble union = new Ensemble();
		union.addAll(table);
		union.addAll(e);
		return union;
	}

	public Ensemble<T> inter(Ensemble<? extends T> e) {
		// à compléter pour la question1-2
		Ensemble union = union(e);
		Ensemble inter = union;
		inter.retainAll(e);
		inter.retainAll(table);
		return union;
	}

	public Ensemble<T> diff(Ensemble<? extends T> e) {
		Ensemble diff = union(e);
		diff.removeAll(e);
		return diff;
	}

	Ensemble<T> diffSym(Ensemble<? extends T> e) {
		Ensemble diffSym = union(e);
		diffSym.removeAll(inter(e));
		return diffSym;
		
	}
	
}
