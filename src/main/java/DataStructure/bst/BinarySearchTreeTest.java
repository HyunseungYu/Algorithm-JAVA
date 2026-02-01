package DataStructure.bst;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class BinarySearchTreeTest {

	@Test
	@DisplayName("add: 단건 삽입")
	void add_single() {
		BinarySearchTree bst = new BinarySearchTree();
		assertTrue(bst.add(10));
		assertEquals(1, bst.size);
		assertTrue(bst.contains(10));
	}
	
	@Test
	@DisplayName("add: 여러 개 삽입 후 contains 검증")
	void add_multiple_contains() {
		BinarySearchTree bst = new BinarySearchTree();
		assertTrue(bst.add(10));
		assertTrue(bst.add(5));
		assertTrue(bst.add(15));
		assertTrue(bst.add(3));
		assertTrue(bst.add(7));
		
		assertTrue(bst.contains(10));
		assertTrue(bst.contains(5));
		assertTrue(bst.contains(15));
		assertTrue(bst.contains(3));
		assertTrue(bst.contains(7));
		assertFalse(bst.contains(999));
		assertEquals(5, bst.size);
	}
	
	@Test
	@DisplayName("add: 중복 삽입은 false, size 변화 없음")
	void add_duplicate() {
		BinarySearchTree bst = new BinarySearchTree();
		assertTrue(bst.add(10));
		assertFalse(bst.add(10));
		assertEquals(1, bst.size());
	}
	
	@Test
	@DisplayName("remove: 리프 노드 삭제")
	void remove_leaf() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.add(10);
		bst.add(5);
		bst.add(15);
		
		assertTrue(bst.remove(5));
		assertFalse(bst.contains(5));
		assertTrue(bst.contains(10));
		assertTrue(bst.contains(15));
		assertEquals(2, bst.size());
	}
	

	@Test
	@DisplayName("remove: 자식 1개 노드 삭제")
	void remove_oneChild() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.add(10);
		bst.add(5);
		bst.add(3);
		
		assertTrue(bst.remove(5));
		assertFalse(bst.contains(5));
		assertTrue(bst.contains(10));
		assertTrue(bst.contains(3));
		assertEquals(2, bst.size());
	}
	
	@Test
	@DisplayName("remove: 자식 2개 노드 삭제(successor 사용)")
	void remove_twoChildren() {
		BinarySearchTree bst = new BinarySearchTree();
		bst.add(10);
		bst.add(5);
		bst.add(15);
		bst.add(12);
		bst.add(18);
		
		assertTrue(bst.remove(10));
		assertFalse(bst.contains(10));
		assertTrue(bst.contains(5));
		assertTrue(bst.contains(12));
		assertTrue(bst.contains(15));
		assertTrue(bst.contains(8));
		assertEquals(4, bst.size());
	}
	
    @Test
    @DisplayName("remove: 존재하지 않는 값 삭제는 false")
    void remove_notFound() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(10);
        bst.add(5);

        assertFalse(bst.remove(999));
        assertEquals(2, bst.size());
    }
    
    @Test
    @DisplayName("clear: 초기화 후 empty/size 확인")
    void clear_test() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.add(10);
        bst.add(5);
        bst.clear();

        assertTrue(bst.isEmpty());
        assertEquals(0, bst.size());
        assertFalse(bst.contains(10));
    }

}
