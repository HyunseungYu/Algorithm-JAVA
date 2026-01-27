package DataStructure.set;

public interface Set {
	boolean add(int key);
	boolean remove(int key);
	boolean contains(int key);
	boolean isEmpty();
	int size();
	void clear();
}
