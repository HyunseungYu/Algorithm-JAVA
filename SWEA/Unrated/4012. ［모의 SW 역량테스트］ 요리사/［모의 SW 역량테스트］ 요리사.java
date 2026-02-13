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

	static int N;
	static int[][] synergy;
	static int min;
	static boolean[] check;

	static int solution() throws Exception {
		min = Integer.MAX_VALUE;
		N = Integer.parseInt(br.readLine());

		check = new boolean[N];
		synergy = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// 시너지 합치기 그냥~ 시너지 계산 빠르게 하려고~~
		for (int i = 0; i < N-1; i++) {
			for (int j = i+1; j < N; j++) {
				synergy[i][j] += synergy[j][i];
			}
		}


		choose(0, 0, 0);

		return min;
	}

	static void choose(int index, int aCnt, int bCnt) {
		if(index == N) {
			check(aCnt, bCnt);
			return;
		}

		if(aCnt != N/2) {
			check[index] = false;
			choose(index + 1, aCnt + 1, bCnt);
		}

		if(bCnt != N/2) {
			check[index] = true;
			choose(index + 1, aCnt, bCnt + 1);
		}
	}

	static void check(int aCnt, int bCnt) {
		// 먼저 N/2 씩 있는지 확인
		if(aCnt != bCnt)
			return;

		// 시너지 계산
		int aSynergy = 0;
		int bSynergy = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				if(!check[i] && !check[j]) {
					aSynergy += synergy[i][j];
				}

				if(check[i] && check[j]) {
					bSynergy += synergy[i][j];
				}
			}
		}

		// 최솟값 비교
		int abs = Math.abs(aSynergy - bSynergy);
		min = Math.min(min, abs);
	}
}
