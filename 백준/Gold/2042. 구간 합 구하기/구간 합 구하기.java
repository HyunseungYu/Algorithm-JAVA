import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static Scanner sc;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static class SegmentTree {
		long tree[];
		int treeSize;

		public SegmentTree(long[] nums) {
			int arrSize = nums.length;
			int h = (int) Math.ceil(Math.log(arrSize) / Math.log(2));
			this.treeSize = (int) Math.pow(2, h+1);

			tree = new long[treeSize];

			init(nums, 1, 0, arrSize - 1);
		}

		public long init(long[] arr, int node, int start, int end) {
			if(start == end)
				return tree[node] = arr[start];

			int mid = (start + end) / 2;

			return tree[node] = init(arr, node * 2, start, mid) + init(arr, node * 2 + 1, mid + 1, end);
		}

		public void update(int node, int start, int end, int idx, long diff) {
			if(idx < start || end < idx)
				return;

			tree[node] += diff;

			if(start != end) {
				int mid = (start + end) / 2;
				update(node * 2, start, mid, idx, diff);
				update(node * 2 + 1, mid + 1, end, idx, diff);
			}
		}

		public long sum(int node, int start, int end, int left, int right) {
			// 범위를 벗어나게 되는 경우 더할 필요 없음
			if(end < left || right < start)
				return 0;

			// 범위 내 완전히 포함 시에는 더 내려가지 않고 바로 리턴
			if(left <= start && end <= right)
				return tree[node];

			int mid = (start + end) / 2;
			return sum(node * 2, start, mid, left, right) + sum(node * 2 + 1, mid + 1, end, left ,right);
		}
	}

//	static long[] nums;

	public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("res/input.txt"));
		sc = new Scanner(System.in);

		int N = sc.nextInt();
		int M = sc.nextInt();
		int K = sc.nextInt();

		long[] nums = new long[N];
		for (int i = 0; i < N; i++) {
			nums[i] = sc.nextLong();
		}

		SegmentTree tree = new SegmentTree(nums);

		for (int i = 0; i < M + K; i++) {
			int a = sc.nextInt();
			long b = sc.nextLong();
			long c = sc.nextLong();

			if(a == 1) {
				int idx = (int) b - 1;
				long newValue = c;
				long diff = newValue - nums[idx];
				tree.update(1, 0, N-1, idx, diff);
				nums[idx] = newValue;
			} else {
				int left = (int) b - 1;
				int right = (int) c - 1;
				long sum = tree.sum(1, 0, N - 1, left, right);
				sb.append(sum + "\n");
			}
		}

		System.out.println(sb.toString());
		sc.close();
	}

}