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
			int solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N, count;
	static boolean[][] board;
	static boolean[] garo;
	static boolean[] sero;

	static int solution() throws Exception {
		count = 0;

		N = Integer.parseInt(br.readLine());

		garo = new boolean[N];
		sero = new boolean[N];

		board = new boolean[N][N];

		choose(0);

		return count;
	}

	static final int[] dI = {-1, -1, 1, 1};
	static final int[] dJ = {-1, +1, -1, 1};

	static void choose(int depth) {
		if(depth == N) {
			count++;
			return;
		}

		for (int i = 0; i < N; i++) {
			// 여기서 상하좌우, 대각선까지 검사하면 좋을 것 같은디
			// 일단 가로 검사 -> depth 겹치는 게 있나
			boolean isGaroDuplicate = false;
			for (int j = 0; j < N; j++) {
				if(board[depth][j])
					isGaroDuplicate = true;
			}

			// 그리고 세로 검사 -> i가 같고, dpeth만 다르게 겹치는 게 있나
			boolean isSeroDuplicate = false;
			for (int j = 0; j < N; j++) {
				if(board[j][i])
					isSeroDuplicate = true;
			}

			// 그리고 대각선 검사 -> i가 같고, dpeth만 다르게 겹치는 게 있나
			boolean isDiagonalDuplicate = false;
			for (int j = 0; j < N; j++) {
				for (int k = 0; k < 4; k++) {
					int nI = depth + dI[k] * j;
					int nJ = i + dJ[k] * j;

					if(!isAvailable(nI, nJ))
						continue;

					if(board[nI][nJ])
						isDiagonalDuplicate = true;
				}
			}

			// 하나라도 겹치면(가로, 세로) continue
			if(isGaroDuplicate || isSeroDuplicate || isDiagonalDuplicate)
				continue;

			// [depth][i]에 퀸을 뒀으니까 상하좌우, 대각선에 뒀다는 걸 알리자 -> 1. 이걸 근데 둘 때마다 검사하는 것. 2. 행/열에 둘 수 있는지 검증하는 배열을 하나 두는 것?
			board[depth][i] = true;

			choose(depth+1);

			board[depth][i] = false;
		}

	}

	static boolean isAvailable(int i, int j) {
		if(i < 0 || N<=i || j < 0 || N<=j)
			return false;

		return true;
	}
}
