import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static Scanner sc;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("res/input.txt"));
		sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int t = 1; t <= T; t++)
		{
			long solution = solve();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static int N;
	static int[][] cost;
	static int[][] dp;
	static int min;

	static long solve() throws Exception {
		N = sc.nextInt();
		cost = new int[N][N];

		sc.nextLine();
		for (int i = 0; i < N; i++) {
			char[] line = sc.nextLine().toCharArray();
			for (int j = 0; j < N; j++) {
				cost[i][j] = line[j] - '0';
			}
		}

		dp = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dp[i][j] = Integer.MAX_VALUE;
			}
		}
		dp[0][0] = 0;

		min = Integer.MAX_VALUE;
		dfs(0, 0, 0);

		return dp[N-1][N-1];
	}

	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};
	static void dfs(int i, int j, int sum) {
//		if(i == N-1 && j == N-1) {
//			min = sum;
//			return;
//		}

		for (int k = 0; k < 4; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];
			if(!isOk(ni, nj))
				continue;

			if(sum + cost[ni][nj] < dp[ni][nj]) {
				int nextCost = dp[i][j] + cost[ni][nj];
				dp[ni][nj] = nextCost;
				dfs(ni, nj, dp[i][j] + cost[ni][nj]);
			}

		}
	}

	static boolean isOk(int i, int j) {
		if(i < 0 || N <= i || j < 0 || N <= j)
			return false;

		return true;
	}
}