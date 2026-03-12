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

	static int N, X, count;
	static int[][] field;

	static long solve() throws Exception {
		N = sc.nextInt();
		X = sc.nextInt();

		field = new int[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				field[i][j] = sc.nextInt();
			}
		}

		count = 0;
		// 가로 체크
		for (int i = 0; i < N; i++) {
			int[] row = field[i];
			boolean[] isInstalled = new boolean[N];
			if(leftToRight(row, isInstalled) && rightToLeft(row, isInstalled))
				count++;
		}

		// 세로 체크
		for (int x = 0; x < N; x++) {
			int[] column = getColumn(x);
			boolean[] isInstalled = new boolean[N];
			if(leftToRight(column, isInstalled) && rightToLeft(column, isInstalled))
				count++;
		}

//		4 3 3 3 3 3 3 3 4 4 4 5 5 5 5 5 5 4 4 4
//		int[] row = {4, 3, 3, 3, 3, 3, 3, 3, 4, 4, 4, 5, 5, 5, 5, 5, 5, 4, 4, 4};
//		boolean[] isInstalled = new boolean[N];
//		boolean leftToRight = leftToRight(row, isInstalled);
//		boolean rightToLeft = rightToLeft(row, isInstalled);



		return count;
	}

	static int[] getColumn(int x) {
		int[] column = new int[N];
		for (int i = 0; i < N; i++) {
			column[i] = field[i][x];
		}

		return column;
	}

	// 3 3 2 2 1 1 1 1 2
	static boolean leftToRight(int[] row, boolean[] isInstalled) {
		for (int i = 1; i < N; i++) {
			int left = row[i-1];
			int current = row[i];

			if(left + 1 < current)
				return false;

			if(left + 1 == current) {
				int leftLength = 0;
				for (int l = i - 1; 0 <= l && row[l] == left && !isInstalled[l]; l--) {
					leftLength++;
				}

				if(leftLength < X)
					return false;
				else {
					// isInstalled true로 변경
					for (int j = 0; j < X; j++) {
						isInstalled[i - j - 1] = true;
					}
				}
			}
		}

		return true;
	}

	static boolean rightToLeft(int[] row, boolean[] isInstalled) {
		for (int i = N-2; 0 <= i; i--) {
			int right = row[i+1];
			int current = row[i];

			if(right + 1 < current)
				return false;

			if(right + 1 == current) {
				int rightLength = 0;
				for (int r = i + 1; r < N && row[r] == right && !isInstalled[r]; r++) {
					rightLength++;
				}

				if(rightLength < X)
					return false;
				else {
					// isInstalled true로 변경
					for (int j = 0; j < X; j++) {
						isInstalled[i + j + 1] = true;
					}
				}
			}
		}

		return true;
	}
}