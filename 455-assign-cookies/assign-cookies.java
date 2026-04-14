class Solution {
    public int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);

        int gi = 0;
        int si = 0;
        int count = 0;
        while(gi < g.length && si < s.length) {
            if(g[gi] <= s[si]) {
                count++;
                gi++;
                si++;
            } else {
                si++;
            }
        }

        return count;
        
    }
}