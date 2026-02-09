package SWEA;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1767_프로세서연결하기_서울_8반_유현승 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
		System.setIn(new FileInputStream("res/input.txt"));
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
	static int[][] field;
	static int[][] cores;
	static int coreCount;
	static int maxCore, minWireCount;

	static final int[] dI = {-1, 1, 0, 0};
	static final int[] dJ = {0,0, -1, 1};

	static int solution() throws Exception {
		coreCount = 0;
		cores = new int[12][2];
		maxCore = 0;
		minWireCount = 0;

		N = Integer.parseInt(br.readLine());

		field = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				if(st.nextToken().charAt(0) == '1') {
					field[i][j] = 1;
					if(i==0 || i==N-1 || j==0 || j==N-1)
						continue;

					cores[coreCount++] = new int[]{i, j};
				}  else {
					field[i][j] = 0;
				}
			}
		}

		choose(0, 0, 0);

		return minWireCount;
	}

	static void choose(int depth, int coreCnt, int wireCnt) {
		if(depth == coreCount) {
			if(maxCore < coreCnt) {
				maxCore = coreCnt;
				minWireCount = wireCnt;
			} else if(maxCore == coreCnt) {
				minWireCount = Math.min(minWireCount, wireCnt);
			}
			return;
		}

		int i = cores[depth][0];
		int j = cores[depth][1];

		for (int k = 0; k < 4; k++) {
			int count = 0, nI = i, nJ = j;

			while (true) {
				nI += dI[k];
				nJ += dJ[k];

				if(nI < 0 || N<=nI || nJ<0 || N<=nJ)
					break;

				if (field[nI][nJ] == 1) {
					count = 0;
					break;
				}

				count++;
			}

			int fill_i = i;
			int fill_j = j;

			for (int l = 0; l < count; l++) {
				fill_i += dI[k];
				fill_j += dJ[k];
				field[fill_i][fill_j] = 1;
			}

			if(count == 0)
				choose(depth+1, coreCnt, wireCnt);
			else {
				choose(depth+1, coreCnt + 1, wireCnt + count);

				fill_i = i;
				fill_j = j;

				for (int l = 0; l < count; l++) {
					fill_i += dI[k];
					fill_j += dJ[k];
					field[fill_i][fill_j] = 0;
				}
			}
		}
	}
}
