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

		for(int t = 1; t <= T; t++)
		{
			int solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N, L;
	static int[] scores;
	static int[] calories;
	static int max = 0;

	static int solution() throws Exception {
		max = 0;

		st= new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());

		scores = new int[N];
		calories = new int[N];
		for (int i = 0; i < N; i++) {
			st= new StringTokenizer(br.readLine(), " ");

			scores[i] = Integer.parseInt(st.nextToken());
			calories[i] = Integer.parseInt(st.nextToken());
		}

		for (int i = 1; i <= N; i++) {
			int[] comb = new int[i];
			combination(comb, 0, 0, new boolean[N], i);
		}

		return max;
	}

	static void combination(int[] comb, int depth, int start, boolean[] visited, int R) {
		if (depth == R) {
			// 계산
			int score = calc(comb);
			max = Math.max(max, score);
			return;
		}

		for (int i = start; i < N; i++) {
			if(visited[i])
				continue;

			visited[i] = true;
			comb[depth] = i;
			combination(comb, depth+1, i, visited, R);
			visited[i] = false;
		}
	}

	static boolean isDuplicate(int[] comb, int count, int i) {
		for (int j = 0; j < count; j++) {
			if(comb[j] == i)
				return true;
		}

		return false;
	}

	static int calc(int[] comb) {
		int sumScores = 0;
		int sumCalories = 0;

		for (int i = 0; i < comb.length; i++) {
			sumScores += scores[comb[i]];
			sumCalories += calories[comb[i]];
		}

		if(sumCalories <= L)
			return sumScores;

		return 0;
	}
}
