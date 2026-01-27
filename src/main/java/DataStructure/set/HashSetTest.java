package DataStructure.set;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class HashSetTest {

	@Test
    void addAndContains_basic() {
        HashSet set = new HashSet();
        assertTrue(set.isEmpty());
        assertEquals(0, set.size());

        assertTrue(set.add(10));
        assertTrue(set.contains(10));
        assertEquals(1, set.size());
        assertFalse(set.isEmpty());

        assertTrue(set.add(20));
        assertTrue(set.contains(20));
        assertEquals(2, set.size());
    }
	
	@Test
	@DisplayName("중복 add는 false여야 하고 size는 증가하면 안 됨")
    void add_duplicate() {
        HashSet set = new HashSet();
        
        assertTrue(set.add(7));
        assertFalse(set.add(7)); // duplicate
        assertTrue(set.contains(7));
        assertEquals(1, set.size());
	}
	
	@Test
	@DisplayName("remove 성공 시 true, 실패 시 false + contains/size 반영")
	void remove_basic() {
		HashSet set = new HashSet();
		set.add(1);
		set.add(2);
		set.add(3);
		
		assertTrue(set.remove(2));
		assertFalse(set.contains(2));
		assertEquals(2, set.size());
		
		assertFalse(set.remove(999));
		assertEquals(2, set.size());
	}
	
	@Test
	@DisplayName("remove: 버킷 head 제거/ 중간 제거 케이스")
	void remove_headAndMiddle() {
		HashSet set = new HashSet();
		set.add(1);
		set.add(2);
		set.add(3);
		
		int[] values = {16, 32, 48, 64, 80};
		for(int v : values) assertTrue(set.add(v));
		
		assertTrue(set.remove(16)); // 헤드 제거
		assertFalse(set.contains(16));
		
		assertTrue(set.remove(48)); // 미들 제거
		assertFalse(set.contains(48));
		
		assertEquals(6, set.size());	
	}
	
	@Test
	@DisplayName("clear는 모든 데이터 제거 + size=0 + contains false")
	void clear_basic() { 
		HashSet set = new HashSet();
		set.add(1);
		set.add(2);
		set.add(3);

		assertEquals(3, set.size());
		
		set.clear();
		assertEquals(0, set.size());
		assertTrue(set.isEmpty());
		assertFalse(set.contains(1));
		assertFalse(set.contains(2));
		assertFalse(set.contains(3));
	}
	
    @Test
    @DisplayName("엣지 케이스: 0, 음수, Integer.MIN_VALUE")
    void edgeCases_numbers() {
        HashSet set = new HashSet();

        assertTrue(set.add(0));
        assertTrue(set.add(-1));
        assertTrue(set.add(Integer.MIN_VALUE));

        assertTrue(set.contains(0));
        assertTrue(set.contains(-1));
        assertTrue(set.contains(Integer.MIN_VALUE));

        assertTrue(set.remove(-1));
        assertFalse(set.contains(-1));
        assertEquals(2, set.size());
    }
    
    @Test
    @DisplayName("리사이즈 발생 + 리사이즈 후에도 모든 원소 유지")
    void resize_shouldHappen_andPreserveAllElements() throws Exception {
        HashSet set = new HashSet();

        int initialCapacity = set.table.length;

        // 기본 용량 16, load factor 0.75면 threshold=12 정도
        // 충분히 많이 넣어서 resize 유도
        int n = 200;
        for (int i = 0; i < n; i++) {
            assertTrue(set.add(i));
        }

        int afterCapacity = set.table.length;
        assertTrue(afterCapacity > initialCapacity, "resize가 발생해야 합니다.");

        // 리사이즈 후에도 모든 값이 조회되어야 함
        for (int i = 0; i < n; i++) {
            assertTrue(set.contains(i), "리사이즈 후에도 " + i + "가 남아있어야 합니다.");
        }
        assertEquals(n, set.size());
    }

}
