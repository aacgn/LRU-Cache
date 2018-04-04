package pt.truewind.datastructure;

import java.util.Iterator;

import interfaces.IList;

public class CArrayList<T> implements IList<T> {

	public static class Node<T>{
		T element;
		Node<T> next;
		Node(T e){
			element = e;
		}
	}

	private Node<T> head;
	private Node<T> tail;
	private int size;

	public CArrayList() {
		this.head = null;
		this.tail = null;
		this.size = 0;
	}


	@Override
	public boolean contains(T element) {
		Node<T> n = head;

		while(n != null) {
			if(n.element.equals(element))
				return true;
			n = n.next;
		}

		return false;

	}

	@Override
	public void add(T element) {
		Node<T> n = new Node<>(element);
		if(head == null) {
			head = n;
			tail = n;
		}
		else {
			tail.next = n;
			tail = n;
		}
		size++;
	}

	@Override
	public void remove(T element) {
		Node<T> n = head;
		Node<T> a = null;
		while(n.element != element) {
			a = n;
			n = n.next;
		}

		if(n==head) {
			head = head.next;
			if(head == null) {
				tail = null;
			}
		}

		else {
			a.next = n.next;
			if(a.next == null) {
				tail = a;
			}
		}
		size--;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		head = null;
		tail = null;
		size = 0;
	}

	public Iterator<T> iterator() {
		return new CLinkedListIterator();
	}

	private class CLinkedListIterator implements Iterator<T>{

		private Node<T> n = head;

		@Override
		public boolean hasNext() {
			if(n != null) {
				return true;
			}
			return false;
		}

		@Override
		public T next() {
			T e = n.element;
			n = n.next;
			return e;
		}

	}

	@Override
	public void add(T element, int index) {
		if((index >= size && index != 0) || index < 0) {
			throw new IllegalArgumentException("Invalid Index");
		}

		else {
			int count = 0;
			Node<T> nw = new Node<>(element);
			Node<T> n = head;
			Node<T> a = null;
			
			if(index == 0) {
				nw.next = head;
				if(head == null) {
					tail = nw;
				}
				head = nw;
				size++;
				return;
			}

			while(count != index) {
				a = n;
				n = n.next;
				count++;
			}
			
			nw.next = n;
			a.next = nw;
			size++;
		}

	}

	@Override
	public T elementAt(int index) {
		if(index >= size || index < 0) {
			throw new IllegalArgumentException("Invalid Index");
		}

		else {
			int count = 0;
			Node<T> n = head;
			while(count != index) {
				n = n.next;
				count++;
			}
			return n.element;
		}
	}

	@Override
	public void replace(int index, T element) {
		if(index >= size || index < 0) {
			throw new IllegalArgumentException("Invalid Index");
		}

		else {
			int count = 0;
			Node<T> n = head;
			while(count != index) {
				n = n.next;
				count++;
			}
			n.element = element; 
		}

	}

	@Override
	public void remove(int index) {
		if(index >= size || index < 0) {
			throw new IllegalArgumentException("Invalid Index");
		}

		else {
			int count = 0;
			Node<T> n = head;

			if(index == 0) {
				if(head == null) {
					return;
				}
				head = head.next;
				if(head == null) {
					tail = null;
				}
			}

			else {
				while(count != index-1) {
					n = n.next;
					count++;
				}

				n.next = (n.next).next;
				if(n.next == null) {
					tail = n;
				}
			}
			size--;
		}
	}


}
