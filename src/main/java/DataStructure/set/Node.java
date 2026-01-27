package DataStructure.set;

public class Node {
	final int hash;
	final int key;
	Node next;
	
	public Node(int hash, int key, Node next) {
		this.hash = hash;
		this.key = key;
		this.next = next;
	}
}
