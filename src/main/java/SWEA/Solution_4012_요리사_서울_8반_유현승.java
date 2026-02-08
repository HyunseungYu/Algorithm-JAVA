package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4012_요리사_서울_8반_유현승 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++)
		{
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N;
	static int[][] synergy;
	static boolean[] isFoodA;
	static int min;

	static int solution() throws Exception {
		N = Integer.parseInt(br.readLine());
		min = Integer.MAX_VALUE;
		synergy = new int[N][N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			for (int j = 0; j < N; j++) {
				synergy[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		// N을 2개로 나누기. {1, 2, 3, 4} -> {1, 2} / {3, 4}
		isFoodA = new boolean[N];
		comb(0, 0);

		return min;
	}

	static void comb(int start, int count) {
		if(count == N/2) {
			check();
			return;
		}

		for (int i = start+1; i < N; i++) {
			if(isFoodA[i])
				continue;

			isFoodA[i] = true;
			comb(i, count + 1);
			isFoodA[i] = false;
		}

	}

	static void check() {
		int sumA = 0;
		int sumB = 0;

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j)
					continue;

				if(isFoodA[i] && isFoodA[j])
					sumA += synergy[i][j];
				else if(!isFoodA[i] && !isFoodA[j])
					sumB += synergy[i][j];
			}
		}

		min = Math.min(min, Math.abs(sumA - sumB));
	}


}
