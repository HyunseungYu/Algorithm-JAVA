package DataStructure.priorityqueue;

import java.lang.reflect.Array;
import java.util.Arrays;

//import java.util.PriorityQueue;

public class BinaryHeapPriorityQueue implements PriorityQueue{
	private static final int DEFAULT_INITIAL_CAPACITY = 11;
	
	public int[] heap;
	private int size;

	public BinaryHeapPriorityQueue() {
		heap = new int[DEFAULT_INITIAL_CAPACITY];
		size = 0;
	}

	@Override
	public void offer(int value) {		
		heap[size] = value;
		siftUp(size, value);
		size++;
	}
	
	private void siftUp(int index, int value) {
		while(0 < index) {
			int parent = (index - 1) / 2;
			
			if(value <= heap[parent])
				break;
			
			heap[index] = heap[parent];
			index = parent;
		}
		
		heap[index] = value;
	}

	@Override
	public int peek() {
		if(size == 0)
			return -1;
		
		return heap[0];
	}

	@Override
	public int poll() {
		if(size == 0)
			return -1;
		
		int result = heap[0];
		int last = heap[--size];
		heap[0] = last;
		heap[size] = 0;
		
		if(0 < size)
			siftDown(0, last);
		
		return result;
	}
	
	private void siftDown(int index, int value) {
		while(true) {
			int left = index * 2 + 1;
			
			if(size <= left)
				break;
			
			int right = left + 1;
			
			int child = (right < size && heap[right] != 0 && heap[right] > heap[left]) ? right : left;
			
			if(value >= heap[child])
				break;
			
			heap[index] = heap[child];
			index = child;
		}
		heap[index] = value;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return size;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return size == 0;
	}

	
}
