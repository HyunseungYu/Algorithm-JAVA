package DataStructure.priorityqueue;

public interface PriorityQueue {
	void offer(int value);
	int peek();
	int poll();
	int size();
	boolean isEmpty();

}
