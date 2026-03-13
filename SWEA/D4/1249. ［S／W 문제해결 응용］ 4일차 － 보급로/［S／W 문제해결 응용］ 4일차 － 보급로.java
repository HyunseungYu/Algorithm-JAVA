import java.util.PriorityQueue;
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

		PriorityQueue<int[]> q = new PriorityQueue<>((a1, a2) -> Integer.compare(a1[2], a2[2]));
		q.offer(new int[] {0, 0, 0});
		boolean[][] visited = new boolean[N][N];
		visited[0][0] = true;

		while (!q.isEmpty()) {
			int[] poll = q.poll();
			int i = poll[0];
			int j = poll[1];
			int currentCost = poll[2];

			for (int k = 0; k < 4; k++) {
				int ni = i + di[k];
				int nj = j + dj[k];
				if(!isOk(ni, nj))
					continue;

				if(visited[ni][nj])
					continue;

				q.offer(new int[] {ni, nj, currentCost + cost[ni][nj]});
				visited[ni][nj] = true;
				if(ni == N - 1 && nj == N -1) {
					return currentCost;
				}
			}


		}




		return 1;
	}

	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};

	static boolean isOk(int i, int j) {
		if(i < 0 || N <= i || j < 0 || N <= j)
			return false;

		return true;
	}
}