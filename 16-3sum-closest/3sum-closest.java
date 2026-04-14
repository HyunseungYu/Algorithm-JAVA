class Solution {
    public int threeSumClosest(int[] nums, int target) {
        Arrays.sort(nums);
        int n = nums.length;

        int smallest = nums[0] + nums[1] + nums[2];

        if(smallest == target || n == 3)
            return smallest;

        if(target < smallest)
            return smallest;

        int biggest = nums[n-3] + nums[n-2] + nums[n-1];
        if(biggest < target)
            return biggest;

        int closest = smallest;

        // 이중 for문 투 포인터
        for(int i=0; i<n-2; i++) {
            int l = i + 1;
            int r = n - 1;
            int sum = 0;

            while(l < r) {
                sum = nums[i] + nums[l] + nums[r];
                if(sum < target) {
                    l++;
                } else if(target < sum) {
                    r--;
                } else {
                    return target;
                }

                if(Math.abs(sum - target) < Math.abs(closest - target)) {
                    closest = sum;
                }
            }

            // int diff = target - sum;
            // if(Math.abs(sum - target) < Math.abs(closest - target)) {
            //     closest = sum;
            // }

            System.out.println("closest = " + closest + ", sum = " + sum);


        }

        return closest;
    }
}