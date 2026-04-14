/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode rotateRight(ListNode head, int k) {
        if(head == null || head.next == null)
            return head;

        int n = 0;
        ListNode forLength = head;
        while(forLength != null) {
            n++;
            forLength = forLength.next;
        }

        k = k % n;

        // 먼저 로테이트 한다.
        for(int i=0; i<k; i++) {
            ListNode last = head;
            ListNode beforeLast = null;

            // 1. 마지막 노드로 간다.
            while(last.next != null) {
                beforeLast = last;
                last = last.next;
            }

            // 2. 마지막 노드의 next를 head로 바꾼다. head에 last를 지정한다. 마지막 이전 노드의 next를 null로 바꾼다.
            beforeLast.next = null;
            last.next = head;
            head = last;
        }
        

        // 주기를 구해서 일정 부분만 로테이트 한다.

        // 반환한다.
        return head;
    }
}