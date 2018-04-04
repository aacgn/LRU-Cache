package pt.truewind.datastructure;

import java.util.Iterator;

import interfaces.ICollection;
import interfaces.IMap;
import interfaces.ISet;

public class CHashTable<K, V> implements IMap<K, V>{
	
	public static class Node<K, V>{
		K key;
		V value;
		Node<K, V> next;
		Node(K k, V v){
			key = k;
			value = v;
		}
	}
	
	private Node<K, V>[] table;
	private int size;
	private int loadFactor = 5;
	
	@SuppressWarnings("unchecked")
	public CHashTable() {
		table = new Node[10];
		size = 0;
	}
	
	@SuppressWarnings("unchecked")
	public CHashTable(int capacity) {
		table = new Node[capacity];
	}
	
	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		for (int i = 0; i < table.length; i++) {
			table[i] = null;
		}
	}

	@Override
	public void put(K key, V value) {
		if(size/table.length > loadFactor) {
			@SuppressWarnings("unchecked")
			Node<K, V>[] tb = new Node[(table.length)*2];
			for (int i = 0; i < table.length; i++) {
				Node<K, V> a = table[i];
				while(a != null) {
					insert(tb, a.key, a.value);
					a = a.next;
				}
			table = tb;
			}
		}
		
		if(insert(table, key, value)) {
			size++;
		}
		
	}

	private boolean insert(Node<K, V>[] table, K key, V value) {
		Node<K, V> n = new Node<>(key, value);
		int hashCode = key.hashCode();
		int indexHash = Math.abs(hashCode) % table.length;
		Node<K, V> a = table[indexHash];
		if(a != null) {
			Node<K, V> p = a;
			while(a != null) {
				if((a.key).equals(key)) {
					a.value = value;
					return false;
				}
				p = a;
				a = a.next;
			}
			p.next = n;
		}
		else {
			table[indexHash] = n;
		}
		return true;
	}

	@Override
	public V get(K key) {
		int hashCode = key.hashCode();
		int indexHash = Math.abs(hashCode) % table.length;
		if(table[indexHash] != null) {
			Node<K, V> a = table[indexHash];
			while(a != null) {
				if(a.key.equals(key)) {
					return a.value;
				}
				a = a.next;
			}
		}
		return null;
	}

	@Override
	public boolean containsKey(K key) {
		int hashCode = key.hashCode();
		int indexHash = Math.abs(hashCode) % table.length;
		if(table[indexHash] != null) {
			Node<K, V> a = table[indexHash];
			while(a != null) {
				if(a.key.equals(key)) {
					return true;
				}
				a = a.next;
			}
		}
		return false;
	}

	@Override
	public void remove(K key) {
		int hashCode = key.hashCode();
		int indexHash = Math.abs(hashCode) % table.length;
		if(table[indexHash] != null) {
			Node<K, V> a = table[indexHash];
			Node<K, V> p = a;
			while(a.key != null) {
				if(a.key.equals(key)) {
					if(p.equals(a)) {
						if(a.next != null) {
							table[indexHash] = a.next;
						}
						else {
							table[indexHash] = null;							
						}
						size--;
						return;
					}
					p.next = a.next;
					size--;
					return;
				}
				p = a;
				a = a.next;
			}
		}
		
	}
	
	public Iterator<K> iterator() {
		return new CHashTableIterator();
	}
	
	private class CHashTableIterator implements Iterator<K>{
		
		private int index;
		private Node<K, V> n;
		
		public CHashTableIterator(){
			index = 0;
			n = table[index];
			
			while(n == null && index < table.length) {
				index++;
				if(index < table.length) {
					n = table[index];					
				}
			}
		}
		
		@Override
		public boolean hasNext() {
			if(index < table.length) {
				return true;
			}
			return false;
		}

		@Override
		public K next() {
			K e = n.key;
			n = n.next;
			while(n == null && index < table.length) {
				index++;
				if(index < table.length) {
					n = table[index];					
				}
			}
			return e;
		}
		
	}

	@Override
	public ISet<K> keySet() {
		CHashSet<K> set = new CHashSet<>();
		for (int i = 0; i < table.length; i++) {
			Node<K, V> a = table[i];
			while(a != null) {
				set.add(a.key);
				a = a.next;
			}
		}
		return set;
	}

	@Override
	public ICollection<V> values() {
		CArrayList<V> v = new CArrayList<>();
		for (int i = 0; i < table.length; i++) {
			Node<K, V> a = table[i];
			while(a != null) {
				v.add(a.value);
				a = a.next;
			}
		}
		return v;
	}

}
