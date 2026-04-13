class Solution {
    public int threeSumClosest(int[] nums, int target) {
        int length = nums.length;

        int closest = Integer.MAX_VALUE/2;

        for(int i=0; i<length; i++) {
            for(int j=i+1; j<length; j++) {
                for(int k=j+1; k<length; k++) {
                    int sum = nums[i] + nums[j] + nums[k];

                    int diff = target - sum;
                    int closestDiff = target - closest;

                    if(Math.abs(diff) < Math.abs(closestDiff)) {
                        // System.out.println("sum = " + sum + ", diff = " + diff + ", closestDiff = " + closestDiff);
                        closest = sum;
                    }
                }
            }
        }   

        return closest;
    }
}