class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Arrays.sort(nums);

        int n = nums.length;

        Set<List<Integer>> answer = new HashSet<>();
        for(int i=0; i<n; i++) {
            if(0 < i && nums[i] == nums[i-1])
                continue;

            for(int j=i+1; j<n; j++) {
                if(i + 1 < j && nums[j] == nums[j-1])
                    continue; 
                    
                int l = j+1;
                int r = n-1;

                while(l < r) {
                    long sum = (long) nums[i] + nums[j] + nums[l] + nums[r];
                    if(sum == target) {
                        answer.add(Arrays.asList(nums[i], nums[j], nums[l], nums[r]));
                        l++;
                        r--;


                    } else if(sum < target) {
                        l++;
                    } else {
                        r--;
                    }
                }
            }
        }

        return new ArrayList<>(answer);
    }
}