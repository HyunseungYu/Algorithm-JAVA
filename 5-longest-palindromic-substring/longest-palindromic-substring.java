class Solution {
    public String longestPalindrome(String s) {
        int length = s.length();

        String max = "";

        for(int i=0; i<length; i++) {
            int left = i - 1;
            int right = i + 1;

            while(0 <= left && right < length) {
                if(s.charAt(left) != s.charAt(right))
                    break;

                left--;
                right++;
            }

            int size = right - left - 1;
            if(max.length() < size) {
                max = s.substring(left+1, right);
            }

            left = i - 1;
            right = i;

            while(0 <= left && right < length) {
                if(s.charAt(left) != s.charAt(right))
                    break;

                left--;
                right++;
            }

            size = right - left - 1;
            if(max.length() < size) {
                max = s.substring(left+1, right);
            }
        }

        return max;
    }
}