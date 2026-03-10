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
	static int startX, startY, homeX, homeY;
	static int[][] cord;
	static int min;
	static boolean[] visited;

	static long solve() throws Exception {
		N = sc.nextInt();
		startX = sc.nextInt();
		startY = sc.nextInt();
		homeX = sc.nextInt();
		homeY = sc.nextInt();

		cord = new int[N][2];
		for (int i = 0; i < N; i++) {
			cord[i] = new int[] {sc.nextInt(), sc.nextInt()};
		}

		min = Integer.MAX_VALUE;
		visited = new boolean[N];
		dfs(0, startX, startY, 0);

//		graph = new int[N * N][3];
//		for (int i = 0; i < N; i++) {
//			for (int j = 0; j < N; j++) {
//				graph[i * N + j][0] = i;
//				graph[i * N + j][1] = j;
//				graph[i * N + j][2] = getDistance(cord[i], cord[j]);
//			}
//		}
//
//		Arrays.sort(graph, (e1, e2) -> Integer.compare(e1[2], e2[2]));
//		int[] parent = new int[N];
//		for (int i = 0; i < N; i++)
//			parent[i] = i;


		return min;
	}

    private static void dfs(int depth, int beforeX, int beforeY, int sum) {
		if(depth == N) {
			sum += getDistance(new int[]{beforeX, beforeY}, new int[] {homeX, homeY});
			min = Math.min(min, sum);
			return;
		}
		if(min < sum) {
			return;
		}

		for (int i = 0; i < N; i++) {
			if(visited[i])
				continue;

			visited[i] = true;
			dfs(depth + 1, cord[i][0], cord[i][1], sum + getDistance(cord[i], new int[]{beforeX, beforeY}));
			visited[i] = false;
		}
    }

	static int getDistance(int[] p1, int[] p2) {
		return Math.abs(p1[0] - p2[0]) + Math.abs(p1[1] - p2[1]);
	}
}