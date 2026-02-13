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


		choose(0);

		return min;
	}

	static void choose(int index) {
		if(index == N) {
			check();
			return;
		}


		check[index] = false;
		choose(index+1);

		check[index] = true;
		choose(index+1);
	}

	static void check() {
		// 먼저 N/2 씩 있는지 확인
		int trueCnt = 0;
		int falseCnt = 0;

		for (int i = 0; i < N; i++) {
			if(check[i])
				trueCnt++;
		}

		// N/2 : N/2 조합이 아니면 탈락
		if(trueCnt != N/2)
			return;

		// 시너지 계산
		int aSynergy = 0;
		int bSynergy = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				if(check[i] && check[j]) {
					aSynergy += synergy[i][j];
//					aSynergy += synergy[j][i];
					continue;
				}

				if(!check[i] && !check[j]) {
					bSynergy += synergy[i][j];
//					bSynergy += synergy[j][i];
					continue;
				}
			}
		}

		// 최솟값 비교
		int abs = Math.abs(aSynergy - bSynergy);
		min = Math.min(min, abs);
	}
}
