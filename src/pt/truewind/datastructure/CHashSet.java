package pt.truewind.datastructure;

import interfaces.ISet;

public class CHashSet<T> implements ISet<T> {

	private CHashTable<T, T> table;
	
	public CHashSet() {
		table = new CHashTable<>();
	}
	
	@Override
	public boolean contains(T element) {
		return table.containsKey(element);
	}

	@Override
	public void remove(T element) {
		table.remove(element);
	}

	@Override
	public int size() {
		return table.size();
	}

	@Override
	public void clear() {
		table.clear();
	}

	@Override
	public void add(T element) {
		table.put(element, element);
	}

	@Override
	public void union(ISet<T> set) {
		for (T e : set) {
			if(!contains(e)) {
				add(e);
			}
		}
	}

	@Override
	public void intersection(ISet<T> set) {
		for(T e: set) {
			if(!contains(e)) {
				remove(e);
			}
		}
	}

	@Override
	public void difference(ISet<T> set) {
		for(T e: set) {
			if(contains(e)) {
				remove(e);
			}
			else {
				add(e);
			}
		}
	}

}
