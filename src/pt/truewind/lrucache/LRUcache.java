package pt.truewind.lrucache;

import java.util.Comparator;

import pt.truewind.datastructure.CHashTable;
import pt.truewind.sort.HeapSortComparator;

public class LRUcache<T, K>{
	
	/** Data Object: Store date and result of the Input.
	 * */
	
	public class Data {
		int date;
		K result;
		public Data(int d, K e) {
			date = d;
			result = e;
		}
	}
	
	/** Parameters: HashTable, system date, system size, system capacity.
	 * */
	
	CHashTable<T, Data> table;
	int LRUdate;
	int LRUsize;
	int LRUcapacity;
	
	public LRUcache(int capacity) {
		this.LRUcapacity = capacity;
		table = new CHashTable<>();
		LRUdate = 0;
		LRUsize = 0;
	}

	/** Store Method: Stores the input values in HashTable and removes the last accessed elements if the storage size is exceeded.
	 * */
	@SuppressWarnings("unchecked")
	public void store(T input,K result) {
			
		if(table.containsKey(input)) {
				table.put(input, new Data(LRUdate, result));
			}
			
			else {
				LRUsize++;
				if(LRUsize > LRUcapacity) {
					table.put(input, new Data(LRUdate, result));
					remove((T) listEntries()[0]);
				}
				
				else {					
					table.put(input, new Data(LRUdate, result));
				}
									
			}
		
		LRUdate++;
		}

	/** Remove Method: Auxiliary function for removal.
	 * */
	private void remove(T input) {
		table.remove(input);
		LRUsize--;
	}
	
	/** Retrieve Method: Search and return in the HashTable the desired element.
	 * */
	
	public K retrieve (T input){
		Data data = table.get(input);
		if(data != null) {
			return data.result;
		}
		return null;
	}
	
	/** Clear Method: Clears HashTable and resets all attributes.
	 * */
	
	public void clear() {
		table.clear();
		LRUsize = 0;
		LRUdate = 0;
	}
	
	/** listEntries Method: Returns an array of keys in descending order.
	 * */
	@SuppressWarnings({ "unchecked", "static-access" })
	public Object[] listEntries() {
		class DescendingOrder implements Comparator<Object>{
			public int compare(Object a, Object b) {
				Data c = table.get((T) a);
				Data d = table.get((T) b);
				return c.date - d.date;
			}
		}
		
		int i = 0;
		
		Object[] aux = new Object[LRUsize];
		
		for(T input : table) {
			aux[i] = input;
			i++;
		}
		
		HeapSortComparator hsc = new HeapSortComparator();
		
		hsc.heapSort(aux, new DescendingOrder());
		
		return aux;
	}
	
	/** Clear Older Than Method: Removes all elements stored before that date.
	 * */

	@SuppressWarnings("unchecked")
	public void clearOlderThan(int date) {
		
		Object[] aux = listEntries();
		for (int i = 0; i < aux.length; i++) {
			if(table.get((T) aux[i]).date < date) {
				table.remove((T) aux[i]);
				LRUsize--;
			}
		}
		
	}
	
	/** Set Capacity Method: Sets the storage capacity.
	 * */
	
	@SuppressWarnings("unchecked")
	public void setCapacity(int size) {
		if(LRUcapacity > size) {
			clearOlderThan(table.get((T) listEntries()[size]).date);
		}
		else {
			LRUcapacity = size;
		}
	}

}
