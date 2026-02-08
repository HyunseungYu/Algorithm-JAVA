package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_d3_5215_햄버거다이어트_서울_8반_유현승 {

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

	static int N, L, max;
	static int[] scores;
	static int[] calories;

	static int solution() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		L = Integer.parseInt(st.nextToken());
		scores = new int[N];
		calories = new int[N];

		max = 0;
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			scores[i] = Integer.parseInt(st.nextToken());
			calories[i] = Integer.parseInt(st.nextToken());
		}

		dfs(0, 0, 0);

		return max;
	}

	static void dfs(int index, int sumScore, int sumCalorie) {
		// 가지치기, 칼로리가 L을 넘었는지
		if(L < sumCalorie)
			return;

		if(index == N) {
			max = Math.max(max, sumScore);
			return;
		}
		max = Math.max(max, sumScore);

		dfs(index + 1, sumScore, sumCalorie);
		dfs(index + 1, sumScore + scores[index], sumCalorie + calories[index]);
	}




}
