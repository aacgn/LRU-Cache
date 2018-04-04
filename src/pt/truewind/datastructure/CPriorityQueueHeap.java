package pt.truewind.datastructure;

import java.util.Comparator;

import interfaces.IQueue;

public class CPriorityQueueHeap<T> implements IQueue<T> {
	
	private Object[] elements;
	private int last;
	private Comparator<T> comparator;
	
	public CPriorityQueueHeap(Comparator<T> cp) {
		elements = new Object[100];
		last = 0;
		comparator = cp;
	}
	
	@Override
	public int size() {
		return last;
	}

	@Override
	public void clear() {
		for (int i = 1; i <= last; i++) {
			elements[i] = null;
		}
		last = 0;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void enqueue(T e) {
		last++;
		
		if(last >= elements.length) {
			Object[] aux = new Object[(elements.length)*2];
			for (int i = 0; i < last; i++) {
				aux[i] = elements[i];
			}
			elements = aux;
		}
		
		elements[last] = e;
		int i = last; 
		while(i > 1 && comparator.compare(e, (T) elements[i/2]) < 0) {
			swap(i,i/2);
			i = i/2;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void poll() {
		elements[1] = null;
		swap(last,1);
		last--;
		int i = 1;
		int j = i*2;
		while(j <= last) {
			if(j+1 > last) {
				if(comparator.compare((T) elements[i], (T)elements[j]) > 0){
					swap(i,i*2);
				}
			}
			else if(comparator.compare((T) elements[i], (T)elements[j]) > 0 || comparator.compare((T) elements[i], (T)elements[j+1]) > 0) {
				if(comparator.compare((T) elements[i*2], (T)elements[i*2+1]) < 0) {
					swap(i,i*2);
				}
				else {
					swap(i,i*2+1);
				}
			}
			i = j;
			j = i*2;
		}
		
	}
	
	private void swap(int a, int b) {
		Object aux = elements[b];
		elements[b] = elements[a];
		elements[a] = aux;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T head() {
		return (T) elements[1];
	}

}
