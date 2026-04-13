class Solution {
    int TARGET;
    int CLOSEST;

    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int length = nums.length;

        TARGET = target;
        CLOSEST = nums[0] + nums[1] + nums[2];

        int smallest = nums[0] + nums[1] + nums[2];
        if(smallest == target || length == 3)
            return smallest;

        if(target <= smallest)
            return smallest;

        int largest = nums[length - 3] + nums[length - 2] + nums[length - 1];
        if(largest <= target)
            return largest;


        for(int i = 1; i < length - 1; i++) {
            int l = i - 1;
            int r = i + 1;

            while(0 <= l && r < length) {
                int left = nums[l];
                int right = nums[r];
                
                int sum = nums[i] + left + right;

                if(sum == target)
                    return target;

                // sum이 target보다 작으면 -> r 한 칸 증가
                if(sum < target) {
                    comapre(sum);

                    r++;
                } else { // sum이 target보다 크면 -> l 한 칸 감소
                    comapre(sum);

                    l--;
                }
            }
        }

        return CLOSEST;
    }

    private void comapre(int sum) {
        int diff = TARGET - sum;
        int closestDiff = TARGET - CLOSEST;

        if(Math.abs(diff) < Math.abs(closestDiff)) {
            CLOSEST = sum;
        }
    }
}