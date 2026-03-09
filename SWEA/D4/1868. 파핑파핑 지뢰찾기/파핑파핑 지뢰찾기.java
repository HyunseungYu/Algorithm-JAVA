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

	static int N;
	static char[][] field;
	static int[][] mines;
	static boolean[][] visited;

	static int solution() throws Exception {
		N = sc.nextInt();
		field = new char[N][N];
		mines = new int[N][N];

		for (int i = 0; i < N; i++) {
			char[] line = sc.next().toCharArray();
			for (int j = 0; j < N; j++) {
				field[i][j] = line[j];
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				int count = getCount(i, j);
				mines[i][j] = count;
			}
		}

		int count = 0;
		visited = new boolean[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(field[i][j] == '*')
					visited[i][j] = true;
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j] && mines[i][j] == 0) {
					count++;
					dfs(i, j);
				}
			}
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(!visited[i][j] && mines[i][j] != 0)
					count++;
			}
		}

		return count;
	}

	static void dfs(int i, int j) {
		visited[i][j] = true;

		if(mines[i][j] != 0)
			return;

		for (int k = 0; k < 8; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];

			if(!isOk(ni, nj) || visited[ni][nj])
				continue;

			dfs(ni, nj);
		}
	}

	static final int[] di = {-1, -1, -1, 0, 0, 1, 1, 1};
	static final int[] dj = {-1, 0, 1, -1, 1, -1, 0, 1};

	static int getCount(int i, int j) {
		int count = 0;
		for (int k = 0; k < 8; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];

			if(!isOk(ni, nj))
				continue;

			if(field[ni][nj] == '*')
				count++;
		}

		return count;
	}

	static boolean isOk(int i, int j) {
		if(i < 0 || N <= i || j < 0 || N <= j)
			return false;

		return true;
	}
}
