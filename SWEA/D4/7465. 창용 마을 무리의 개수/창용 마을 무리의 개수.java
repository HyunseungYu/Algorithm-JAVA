import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static Scanner sc;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int t = 1; t <= T; t++)
		{
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static int N, M;
	static int[] roots;

	static int solution() throws Exception {
		N = sc.nextInt();
		M = sc.nextInt();

		roots = new int[N + 1];
		for (int i = 1; i <= N; i++)
			roots[i] = i;

		for (int i = 0; i < M; i++) {
			int left = sc.nextInt();
			int right = sc.nextInt();

			union(left, right);
		}

		int count = 0;
		for (int i = 1; i <= N; i++) {
			if(find(i) == i)
				count++;
		}

		return count;
	}

	static void union(int left, int right) {
		int leftRoot = find(left);
		int rightRoot = find(right);

		if(leftRoot == rightRoot)
			return;

		roots[rightRoot] = leftRoot;

	}

	static int find(int x) {
		if(roots[x] == x)
			return x;

		return find(roots[x]);
	}
}
