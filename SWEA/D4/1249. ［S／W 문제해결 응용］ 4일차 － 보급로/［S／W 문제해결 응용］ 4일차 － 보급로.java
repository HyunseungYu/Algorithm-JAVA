import java.io.FileInputStream;
import java.util.ArrayDeque;
import java.util.Queue;
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

		for(int t = 1; t <= T; t++) {
			int solution = solve();
			System.out.println("#" + t + " " + solution);
		}

		sc.close();
	}

	static int N;
	static int[][] field;
	static int[][] dp;
	static int endI, endJ;
	static int min;

	static int solve() throws Exception {
		N = sc.nextInt();
		field = new int[N][N];
		dp = new int[N][N];

		for (int i = 0; i < N; i++) {
			char[] s = sc.next().toCharArray();
			for (int j = 0; j < N; j++) {
				field[i][j] = s[j] - '0';
				dp[i][j] = -1;
			}
		}


		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {0, 0});
		dp[0][0] = 0;

		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int i = poll[0];
			int j = poll[1];
			int curTime = dp[i][j];

			for (int k = 0; k < 4; k++) {
				int ni = i + di[k];
				int nj = j + dj[k];

				if(!isOk(ni, nj))
					continue;

				if(dp[ni][nj] == -1 || (dp[ni][nj] > curTime + field[ni][nj])) {
					dp[ni][nj] = curTime + field[ni][nj];
					q.offer(new int[]{ni, nj});
				}
			}


		}

		return dp[N-1][N-1];
	}

	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};

	static void dfs(int i, int j, int cost) {
		if(!isOk(i, j))
			return;

		if(i == endI && j == endJ) {
			min = Math.min(min, cost);
			return;
		}

		for (int k = 0; k < 4; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];

			dfs(ni, nj, cost + field[i][j]);
		}
	}

	static boolean isOk(int i, int j) {
		if(i < 0 || N<=i || j < 0 || N <= j)
			return false;

		return true;
	}
}
