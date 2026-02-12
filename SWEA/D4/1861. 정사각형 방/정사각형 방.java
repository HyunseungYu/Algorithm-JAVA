import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			String solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N;
	static int[][] rooms;
	static int max, maxRoom;

	static String solution() throws Exception {
		max = 0;
		maxRoom = Integer.MAX_VALUE;

		N = Integer.parseInt(br.readLine());
		rooms = new int[N][N];

		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				rooms[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				dfs(1, i, j, rooms[i][j]);
			}
		}

		return maxRoom + " " + max;
	}

	static void dfs(int depth, int i, int j, int from) {
		if(!isAvailable(i, j))
			return;

		if(max < depth) {
			max = depth;
			maxRoom = from;
		}

		if(max == depth) {
			maxRoom = Math.min(maxRoom ,from);
		}

		if(isAvailable(i-1, j) && rooms[i-1][j] == rooms[i][j] + 1)
			dfs(depth+1, i-1, j, from);

		if(isAvailable(i+1, j) && rooms[i+1][j] == rooms[i][j] + 1)
			dfs(depth+1, i+1, j, from);

		if(isAvailable(i, j-1) && rooms[i][j-1] == rooms[i][j] + 1)
			dfs(depth+1, i, j-1, from);

		if(isAvailable(i, j+1) && rooms[i][j+1] == rooms[i][j] + 1)
			dfs(depth+1, i, j+1, from);

	}

	static boolean isAvailable(int i, int j) {
		if(i < 0 || N <= i || j < 0 || N <= j)
			return false;

		return true;
	}
}
