package DataStructure.priorityqueue;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class PQTest {

	@Test
	void test() {
		BinaryHeapPriorityQueue pq = new BinaryHeapPriorityQueue();
		pq.offer(3);
		pq.offer(23);
		pq.offer(10);
		pq.offer(2);
	}
	
	@Test
	void offerIncreaseSize() {
		BinaryHeapPriorityQueue pq = new BinaryHeapPriorityQueue();
		pq.offer(10);
		assertFalse(pq.isEmpty());
		assertEquals(1,  pq.size());
		
		pq.offer(5);
		assertEquals(2,  pq.size());
	}
	
	@Test
	void peekDoesNotDecreaseSize() {
		BinaryHeapPriorityQueue pq = new BinaryHeapPriorityQueue();
		pq.offer(10);
		pq.offer(3);
		pq.offer(7);
		
		assertEquals(3, pq.size());
		
		assertEquals(10, pq.peek());
		assertEquals(10, pq.peek());
		pq.peek();
		
		assertEquals(3, pq.size());
	}
	
	@Test
	void pollRemovesTop() {
		BinaryHeapPriorityQueue pq = new BinaryHeapPriorityQueue();
		pq.offer(10);
		pq.offer(3);
		pq.offer(7);
		
		assertEquals(10, pq.poll());
		assertEquals(2, pq.size());
		
		
		assertEquals(7, pq.peek());
		
	}
	
	@Test
	void pollReturnSortedOrder() {
		BinaryHeapPriorityQueue pq = new BinaryHeapPriorityQueue();
		int[] values = {5, 1, 9,  2, 7, 3};
		for(int v:values) pq.offer(v);
		System.out.println(Arrays.toString(pq.heap));
		
		assertEquals(9, pq.poll());
		System.out.println(Arrays.toString(pq.heap));
		
		assertEquals(7, pq.poll());
		System.out.println(Arrays.toString(pq.heap));
		
		assertEquals(5, pq.poll());
		System.out.println(Arrays.toString(pq.heap));
		
		assertEquals(3, pq.poll());
		System.out.println(Arrays.toString(pq.heap));
		
		assertEquals(2, pq.poll());
		System.out.println(Arrays.toString(pq.heap));
		
		assertEquals(1, pq.poll());
		System.out.println(Arrays.toString(pq.heap));		
	}
	
	@Test
	void pollReturnSortedOrder2() {
		BinaryHeapPriorityQueue pq = new BinaryHeapPriorityQueue();
		int[] values = {5, 1, 9, 2, 2, 7, 3};
		for(int v:values) pq.offer(v);
//		for(int v:values) pq.offer(v);
//		for(int v:values) pq.offer(v);
//		for(int v:values) pq.offer(v);
//		for(int v:values) pq.offer(v);
		System.out.println(Arrays.toString(pq.heap));
		
		while(!pq.isEmpty()) {
			pq.poll();
			System.out.println(Arrays.toString(pq.heap));
		}
		
	}

}
