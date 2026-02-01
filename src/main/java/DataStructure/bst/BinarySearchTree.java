package DataStructure.bst;

public class BinarySearchTree {
	
	public Node root;
	public int size;
	
	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	public boolean add(int value) {
		Node cur = root;
		
		if(cur == null) {
			root = new Node(value);
			size++;
			return true;
		}
		
		Node parent = null;
		
		do {
			parent = cur;
			
			if(value < cur.value)
				cur = cur.left;
			else if (cur.value < value)
				cur = cur.right;
			else
				return false;
			
		} while(cur != null);
		
		Node newNode = new Node(value, parent);
		if(value < parent.value)
			parent.left = newNode;
		else
			parent.right = newNode;
		
		size++;
		return true;
	}
	
	public boolean remove(int value) {
		if(root == null)
			return false;
		
		Node cur = root;
		Node parent = null;
		boolean hasLeft = false;
		
		do {
			parent = cur;
			
			if(cur.value == value) 
				break;
			
			if(value < cur.value) {
				hasLeft = true;
				cur = cur.left;	
			}
			else {
				hasLeft = false;
				cur = cur.right;
			}
		} while (cur != null);
		
		if(cur == null)
			return false;
		
		// 루트 노드임
		if(parent == null) {
			deleteNode(cur);
			size--;
			return true;
		}
		
		if(hasLeft) {
			parent.left = deleteNode(cur);
			if(parent.left != null)
				parent.left.parent = parent;
		} else {
			parent.right = deleteNode(cur);
			if(parent.right != null)
				parent.right.parent = parent;
		}
		
		size--;
		return true;
	}
	
	private Node deleteNode(Node node) {
		if(node == null)
			return null;
		
		// 자식 노드가 없는 경우
		if(node.left == null && node.right == null) {
			node = null;
			return null;
		}
		
		// 양쪽의 자식 노드가 모두 있을 경우
		if(node.left != null && node.right != null) {
			// 대체 노드 찾기
			Node replacement = getSuccessorAndUnlink(node);
			
			// 삭제된 노드에 대체 노드 값 대체
			node.value = replacement.value;
		} else if(node.left != null) { // 왼쪽 노드만 있을 경우
			if(node == root) {
				node = node.left;
				root = node;
				root.parent = null;
			} else {
				node = node.left;
			}
		} else { // 오른쪽 노드만 존재할 경우
			if(node == root) {
				node = node.right;
				root = node;
				root.parent = null;
			} else {
				node = node.right;
			}
		}
		
		return node;
	}
	
	private Node getSuccessorAndUnlink(Node node) {
		Node parent = node; // 초기 부모 노드는 자신
		Node cur = node.right; // 초기 오른쪽 노드
		
		if(cur.left == null) {
			parent.right = cur.right;
			if(parent.right != null)
				parent.right.parent = parent;
			
			cur.right = null;
			return cur;
		}
		
		while(cur.left != null) {
			parent = cur;
			cur = cur.left;
		}
		
		parent.right = cur.right;
		if(parent.left != null) {
			parent.left.parent = parent;
		}
		
		cur.right = null;
		return cur;
	}

	public int size() {
		return size;
	}
	
	public boolean isEmpty() {
		return size == 0;
	}
	
	public boolean contains(int value) {
		Node cur = root;
		
		while(cur != null) {
			if(cur.value == value)
				return true;
			
			if(value < cur.value)
				cur = cur.left;
			else
				cur = cur.right;
		}
		
		return false;
	}
	
	public void clear() {
		root = null;
		size = 0;
	}
}
