package DataStructure.bst;

public class Node {
	int value;
	
	Node left;
	Node right;
	Node parent;
	
	Node(int value) {
		this(value, null);
	}
	
	Node(int value, Node parent) {
		this.value = value;
		this.parent = parent;
		this.left = null;
		this.right = null;
	}
}
