import java.util.ArrayDeque;
import java.util.Deque;
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
			String solution = solve();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static int N, M;
	static int[][] field;
	static int startI, startJ, endI, endJ;

	static String solve() throws Exception {
		N = sc.nextInt();
		M = sc.nextInt();
		field = new int[N][M];
		Deque<int[]> poisons = new ArrayDeque<>();

		// 입력
		sc.nextLine();
		for (int i = 0; i < N; i++) {
			char[] line = sc.nextLine().toCharArray();
			for (int j = 0; j < M; j++) {
//				field[i][j] = line[j];
				if(line[j] == 'D') {
					field[i][j] = -2;
					endI = i;
					endJ = j;
				} else if(line[j] == '*') {
					poisons.add(new int[] {i, j});
					field[i][j] = -1;
				} else if(line[j] == 'S') {
					field[i][j] = 0;
					startI = i;
					startJ = j;
				} else if(line[j] == 'X'){
					field[i][j] = -3;
				} else {
					field[i][j] = -4;
				}
			}
		}

		Deque<int[]> q = new ArrayDeque<>();
		q.offer(new int[] {startI, startJ, 0});

		Deque<int[]> nextQ = new ArrayDeque<>();
		Deque<int[]> nextPoisons = new ArrayDeque<>();

		int e = 0;
		while (e++ <= N * M) {
			nextQ.clear();

			while(!q.isEmpty()) {
				int[] poll = q.poll();
				int i = poll[0];
				int j = poll[1];
				int depth = poll[2];

				if(isEnd(i, j)) {
					return String.valueOf(depth);
				}

				if(isPoision(i, j))
					continue;

				for (int k = 0; k < 4; k++) {
					int ni = i + di[k];
					int nj = j + dj[k];

					if(!isOk(ni ,nj))
						continue;

					if(isPoision(ni, nj))
						continue;

					if(isRock(ni, nj))
						continue;

					if(0 <= field[ni][nj] && field[ni][nj] <= depth + 1 )
						continue;

					field[ni][nj] = depth + 1;
					nextQ.offer(new int[] {ni, nj, depth + 1});
				}

			}
			q = new ArrayDeque<>(nextQ);

			nextPoisons.clear();
			while (!poisons.isEmpty()) {
				int[] poll = poisons.poll();
				int i = poll[0];
				int j = poll[1];

				for (int k = 0; k < 4; k++) {
					int ni = i + di[k];
					int nj = j + dj[k];

					if(!isOk(ni ,nj))
						continue;

					if(isRock(ni ,nj))
						continue;

					if(isEnd(ni, nj))
						continue;

					if(field[ni][nj] == -1)
						continue;

					field[ni][nj] = -1;
					nextPoisons.add(new int[] {ni, nj});
				}
			}
			poisons = new ArrayDeque<>(nextPoisons);
		}

		return "GAME OVER";
	}

	static boolean isEnd(int i, int j) {
		if(i==endI && j == endJ)
			return true;

		return false;
	}

	static boolean isPoision(int i, int j) {
		if(field[i][j] == -1)
			return true;

		return false;
	}

	static boolean isRock(int i, int j) {
		if(field[i][j] == -3)
			return true;

		return false;
	}

	static boolean isOk(int i, int j) {
		if(i < 0 || N <= i || j < 0 || M <= j)
			return false;

		return true;
	}

	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};
}