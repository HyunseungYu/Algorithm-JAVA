import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Main {

	static Scanner sc;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();


	static boolean[][] block;
	static int N;
	static int[][] dp;

	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};
	public static void main(String[] args) throws Exception {
		sc = new Scanner(System.in);

		N = sc.nextInt();
		block = new boolean[N+1][N+1];

		sc.nextLine();
		for (int i = 1; i <= N; i++) {
			char[] line = sc.nextLine().toCharArray();
			for (int j = 1; j <= N; j++) {
				block[i][j] = (line[j-1] == '0') ? true : false;
			}
		}

		Queue<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {1, 1, 0}); // i, j, count
		dp = new int[N+1][N+1];
		dp[1][1] = 0;
		for (int i = 1; i <= N; i++) {
			for (int j = 1; j <= N; j++) {
				dp[i][j] = -1;
			}
		}

		int p = 0;
		while(!q.isEmpty()) {
			p++;
			int[] poll = q.poll();
			int i = poll[0];
			int j = poll[1];
			int count = poll[2];

			int current = (block[i][j]) ? 1 : 0;
			int nextCost = count + current;

			for (int k = 0; k < 4; k++) {
				int ni = i + di[k];
				int nj = j + dj[k];

				if(!isOk(ni, nj))
					continue;

				if(dp[ni][nj] == -1) {
					dp[ni][nj] = nextCost;
					q.offer(new int[]{ni, nj, nextCost});
					continue;
				}

				if(nextCost < dp[ni][nj]) {
					dp[ni][nj] = nextCost;
					q.offer(new int[]{ni, nj, nextCost});
				}
			}
		}

		System.out.println(dp[N][N]);
//		System.out.println("p = " + p);
		sc.close();
	}

	static boolean isOk(int i, int j) {
		if(i <= 0 || N < i || j <= 0 || N < j)
			return false;

		return true;
	}

}