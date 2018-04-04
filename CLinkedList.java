import java.util.Iterator;

import interfaces.IList;

public class CLinkedList<T> implements IList<T>{
	private static class Node <T> {
		T element;
		Node<T> next;
		Node(T e) {
			element = e;
		}
	}
	private Node<T> head;
	private Node<T> tail;
	private int size;

	public CLinkedList() {
		head = null;
		tail = null;
		size = 0;
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
	public void remove(T element) {
		// TODO Auto-generated method stub
		
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

	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void add(T element, int index) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public T elementAt(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void replace(int index, T element) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void remove(int index) {
		// TODO Auto-generated method stub
		
	}

}
