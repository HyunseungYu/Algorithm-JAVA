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

		for (int t = 1; t <= T; t++) {
			long solution = solve();
			sb.append("#").append(t).append(" ").append(solution).append("\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static int N, M;
	static int[][] field;
	static List<int[]> homes;

	static long solve() throws Exception {
		N = sc.nextInt();
		M = sc.nextInt();

		field = new int[N][N];
		homes = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				field[i][j] = sc.nextInt();
				if(field[i][j] == 1) {
					homes.add(new int[] {i, j});
				}
			}
		}

		int maxHomeCount = Integer.MIN_VALUE;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				for (int k = 1; k <= 2 * N; k++) {
					int cost = k * k + (k-1) * (k-1);
					int homeCount = getHomeInRange(i, j, k);

					if(cost <= homeCount * M)
						maxHomeCount = Math.max(maxHomeCount, homeCount);
				}
			}
		}

		return maxHomeCount;
	}

	static int getHomeInRange(int i, int j, int k) {
		int count = 0;

		for(int[] home : homes) {
			int distance = Math.abs(home[0] - i) + Math.abs(home[1] - j);
			if(distance < k)
				count++;
		}

		return count;
	}

	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};


	static boolean isOk(int i, int j) {
		if(i < 0 || N<=i || j < 0 || N<=j)
			return false;

		return true;
	}
}