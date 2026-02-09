package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution_d4_7733_치즈도둑_서울_8반_유현승 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++)
		{
			int solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N;
	static int[][] cheese;

	static int maxChunk;

	static int solution() throws Exception {
		maxChunk = 1;

		N = Integer.parseInt(br.readLine());

		cheese = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				cheese[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 1; i < 100; i++) {
			eat(i);
			int chunk = getChunk();
			maxChunk = Math.max(maxChunk, chunk);

		}

		return maxChunk;
	}

	static void eat(int day) {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cheese[i][j] == day)
					cheese[i][j] = 0;
			}
		}
	}

	static final int[] dI = {-1, 1, 0, 0};
	static final int[] dJ = {0, 0, -1, 1};

	static int getChunk() {
		int chunk = 0;

		boolean[][] visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(cheese[i][j] == 0)
					visited[i][j] = true;
				else
					visited[i][j] = false;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(visited[i][j])
					continue;

				Deque<int[]> queue = new ArrayDeque<>();
				queue.offer(new int[]{i, j});
				while (!queue.isEmpty()) {
					int[] poll = queue.poll();
					int _i = poll[0];
					int _j = poll[1];

					if(visited[_i][_j])
						continue;

					visited[_i][_j] = true;

					for (int k = 0; k < 4; k++) {
						int nI = _i + dI[k];
						int nJ = _j + dJ[k];

						if(nI < 0 || N <= nI || nJ < 0 || N <= nJ)
							continue;

						queue.offer(new int[]{nI, nJ});
					}

				}

				chunk++;
			}
		}

		return chunk;
	}

	static void bfs(int i, int j) {

	}
}
