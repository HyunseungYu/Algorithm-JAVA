import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
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

	static int[][] field;
	static Set<String> combinations;

	static long solve() throws Exception {
		field = new int[4][4];
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				field[i][j] = sc.nextInt();
			}
		}

		combinations = new HashSet<>();
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				move(0, i, j, new char[7]);
			}
		}

		return combinations.size();
	}

	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};
	static void move(int depth, int i, int j, char[] combination) {
		if(depth == 7) {
			combinations.add(String.valueOf(combination));
			return;
		}

//		if(!isOk(i, j))
//			return;

		combination[depth] = (char)('0' + field[i][j]);

		for (int k = 0; k < 4; k++) {
			int ni = i + di[k];
			int nj = j + dj[k];

			if(!isOk(ni ,nj))
				continue;

			move(depth + 1, ni, nj, combination);
		}

	}

	static boolean isOk(int i, int j) {
		if(i < 0 || 4 <= i || j < 0 || 4 <= j)
			return false;

		return true;
	}
}