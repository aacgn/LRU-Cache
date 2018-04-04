package pt.truewind.sort;

import java.util.Comparator;

import pt.truewind.datastructure.CPriorityQueueHeap;

public class HeapSortComparator {
	
	static class AscedingOrder implements Comparator<Integer>{
		public int compare(Integer a, Integer b) {
			return a - b;
		}
	}
	
	public static <T> void heapSort(T[] v, Comparator<T> comp) {
		CPriorityQueueHeap<T> q = new CPriorityQueueHeap<>(comp);
		
		for (int i = 0; i < v.length; i++) {
			q.enqueue(v[i]);
		}
		
		for (int i = 0; i < v.length; i++) {
			T e = q.head();
			q.poll();
			v[i] = e;
		}
	}
}
