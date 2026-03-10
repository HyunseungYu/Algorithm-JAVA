import java.util.ArrayList;
import java.util.List;
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

	static int N, K, M;
	static List<Bug> bugs;
	static Cell[][] board;

	static final int[] di = {0, -1, 1, 0, 0};
	static final int[] dj = {0, 0, 0, -1, 1};

	static long solve() throws Exception {
		N = sc.nextInt();
		M = sc.nextInt();
		K = sc.nextInt();

		board = new Cell[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				board[i][j] = new Cell(i, j);
			}
		}

		bugs = new ArrayList<>();
		for (int k = 0; k < K; k++) {
			int i = sc.nextInt();
			int j = sc.nextInt();
			int count = sc.nextInt();
			int direction = sc.nextInt();
			Bug bug = new Bug(k, i, j, count, direction);

			bugs.add(bug);
			board[i][j].bugsIndex.add(k);
		}

		// M번 이동
		for (int m = 0; m < M; m++) {
			for (int k = 0; k < K; k++) {
				move(k);
			}

			for (int i = 0; i < N; i++) {
				for (int j = 0; j < N; j++) {
					if(2 <= board[i][j].bugsIndex.size()) {
						int sum = 0;
						int maxIndex = board[i][j].bugsIndex.get(0);
						for (Integer bugIndex : board[i][j].bugsIndex) {
							sum += bugs.get(bugIndex).count;

							if(bugs.get(maxIndex).count < bugs.get(bugIndex).count)
								maxIndex = bugIndex;
						}

						bugs.get(maxIndex).count = sum;


						for (Integer bugIndex : board[i][j].bugsIndex) {
							if(maxIndex == bugIndex)
								continue;

							bugs.get(bugIndex).count = 0;
//							board[i][j].bugsIndex.remove(Integer.valueOf(bugIndex));
						}

					}
				}
			}
		}

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (Integer bugsIndex : board[i][j].bugsIndex) {
					sum += bugs.get(bugsIndex).count;
				}

			}
		}

		return sum;
	}

	static void move(int k) {
		Bug bug = bugs.get(k);
		int i = bug.i;
		int j = bug.j;
		int direction = bug.direction;

		int ni = i + di[direction];
		int nj = j + dj[direction];

		bug.i = ni;
		bug.j = nj;

		board[i][j].bugsIndex.remove(Integer.valueOf(k));
		board[ni][nj].bugsIndex.add(k);

		if(isRound(ni, nj)) {
			bug.count /= 2;
			if(direction == 1)
				bug.direction = 2;
			else if(direction == 2)
				bug.direction = 1;
			else if(direction == 3)
				bug.direction = 4;
			else if(direction == 4)
				bug.direction = 3;
		}
	}

	static boolean isRound(int i, int j) {
		if(i==0 || i == N-1 || j == 0 || j == N-1)
			return true;

		return false;
	}

	static class Bug {
		int no;
		int i, j, count, direction;

		public Bug(int no, int i, int j, int count, int direction) {
			this.no = no;
			this.i = i;
			this.j = j;
			this.count = count;
			this.direction = direction;
		}
	}

	static class Cell {
		int i, j;
		List<Integer> bugsIndex;

		public Cell(int i, int j) {
			this.i = i;
			this.j = j;
			this.bugsIndex = new ArrayList<>();
		}
	}

}