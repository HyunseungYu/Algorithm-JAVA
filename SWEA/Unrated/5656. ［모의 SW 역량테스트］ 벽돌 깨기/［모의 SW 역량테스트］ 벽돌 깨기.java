import java.util.Arrays;
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

	static int N, W, H;
	static int[][] field;
	static int min;

	static long solve() throws Exception {
		N = sc.nextInt();
		W = sc.nextInt();
		H = sc.nextInt();

		field = new int[H][W];
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				field[i][j] = sc.nextInt();
			}
		}

		min = Integer.MAX_VALUE;
		for (int x = 0; x < W; x++) {
			int[][] newField = makeNewField(field);
			dfs(0, x, newField);
		}

//		drop(2, field);
//		gravitate(field);
//		drop(2, field);
//		gravitate(field);
//		drop(6, field);
//		gravitate(field);
//		int count = countField(field);

		return min;
	}

	static void dfs(int depth, int x, int[][] field) {
		if(depth == N) {
			int sum = countField(field);
			if(sum < min)
				min = Math.min(min, sum);
			return;
		}

		// i번째에 떨어뜨리자
		drop(x, field);

		// 중력 작용
		gravitate(field);

		// 그리고 또 for문으로 다음 부수기
		for (int j = 0; j < W; j++) {
			int[][] newField = makeNewField(field);
			dfs(depth + 1, j, newField);
		}
	}

	static int countField(int[][] field) {
		int count = 0;
		for (int i = 0; i < H; i++) {
			for (int j = 0; j < W; j++) {
				if(field[i][j] != 0)
					count++;
			}
		}

		return count;
	}

	static void gravitate(int[][] field) {
		for (int x = 0; x < W; x++) {
			int pointer = H - 1;
			for (int i = H - 1; 0 <= i; i--) {
				if(field[i][x] != 0) {
					int temp = field[i][x];
					field[i][x] = 0;
					field[pointer--][x] = temp;
				}
			}
		}
	}

	static void drop(int x, int[][] field) {
		int crashI = findFirstI(x, field); // 부술 줄의 첫번째 i 찾기
		if(H <= crashI) // 부술 게 없음
			return;

		crashCascade(crashI, x, field);
	}

	static final int[] di = {-1, 1, 0, 0};
	static final int[] dj = {0, 0, -1, 1};
	static void crashCascade(int i, int j, int[][] field) {
		int range = field[i][j];
		field[i][j] = 0;

		for (int r = 0; r < range; r++) {
			for (int k = 0; k < 4; k++) {
				int ni = i + di[k] * r;
				int nj = j + dj[k] * r;

				if(!isOk(ni, nj) || field[ni][nj] == 0)
					continue;

				crashCascade(ni, nj, field);
			}
		}
	}

	static boolean isOk(int i, int j) {
		if(i < 0 || H <= i || j < 0 || W <= j)
			return false;

		return true;
	}

	static int findFirstI(int x, int[][] field) {
		int i = 0;
		for(; i < H; i++)
			if(field[i][x] != 0)
				break;

		return i;
	}

	static int[][] makeNewField(int[][] field) {
		int[][] newField = new int[H][W];
		for (int i = 0; i < H; i++) {
			newField[i] = Arrays.copyOf(field[i], W);
		}

		return newField;
	}

}