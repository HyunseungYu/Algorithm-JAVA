package DataStructure.set;

import java.util.Arrays;

public class HashSet implements Set {
	
	private final static int DEFAULT_CAPACITY = 1 << 4;
	private final static float LOAD_FACTOR = 0.75f;
	
	public Node[] table;
	public int size;
	
	public HashSet() {
		table = new Node[DEFAULT_CAPACITY];
		size = 0;
	}
	
	private int hashInt(int key) {
		int h = Integer.hashCode(key);
		h = h ^ (h >>> 16);
	
		return h;
	}

	@Override
	public boolean add(int key) {
		return _add(hashInt(key), key);
	}
	
	private boolean _add(int hash, int key) {
		int bucket = hash & (table.length - 1);
		
		if(table[bucket] == null) {
			table[bucket] = new Node(hash, key, null);
			size++;
			return true;
		}
		
		Node cur = table[bucket];
		Node prev = null;
		
		while(cur != null) {
			// 만약 해시와 키가 같은 노드가 있다면 return false
			if(cur.hash == hash && cur.key == key)
				return false;
			
			prev = cur;
			cur = cur.next;
		}
		
		// 마지막까지 가서 노드 삽입
		prev.next = new Node(hash, key, null);
		size++;
		
		// 사이즤가 로드 팩터를 넘겼으면 resize
		if((int) (LOAD_FACTOR * table.length) <= size)
			resize();
		
		return true;
	}
	
	private void resize() {
		int newCapacity = table.length << 1;
		Node[] newTable = new Node[newCapacity];
		
		for(int i=0; i<table.length; i++) {
			Node cur = table[i];
			
			while(cur != null) {
				int bucket = cur.hash & (newCapacity - 1);
				
				Node next = cur.next;
				
				cur.next = newTable[bucket];
				newTable[bucket] = cur;
				
				cur = next;
			}
			
			table[i] = null;
		}
		
		table = newTable;
	}

	@Override
	public boolean remove(int key) {
		return _remove(hashInt(key), key);
	}
	
	private boolean _remove(int hash, int key) {
		int bucket = hash & (table.length - 1);
		

		if(table[bucket] == null) 
			return false;

		Node cur = table[bucket];
		Node prev = null;
		while(cur != null) {
			// 노드 삭제
			if(cur.hash == hash && cur.key == key) {
				// 헤드 노드일 경우
				if(prev == null) {
					table[bucket] = cur.next;
				} else { // 중간 노드일 경우
					prev.next = cur.next;
				}
				
				size--;
				return true;
			}
			
			prev = cur;
			cur = cur.next;
		}

		return false;
	}

	@Override
	public boolean contains(int key) {
		int hash = hashInt(key);
		int bucket = hash & (table.length - 1);
		
		Node cur = table[bucket];
		
		while(cur != null) {
			if(cur.hash == hash && cur.key == key)
				return true;
			
			cur = cur.next;
		}
		
		return false;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public void clear() {
		Arrays.fill(table, null);
		size = 0;
	}


}
