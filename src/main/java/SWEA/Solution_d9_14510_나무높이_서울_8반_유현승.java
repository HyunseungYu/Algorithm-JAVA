package SWEA;

import java.io.*;
import java.util.*;

public class Solution_d9_14510_나무높이_서울_8반_유현승 {

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
	static int[] heights, left;
	static int max, count, day;
	static int need1, need2;

	static int solution() throws Exception {
		count = 0;
		day = 0;
		N = Integer.parseInt(br.readLine());

		heights = new int[N];
		left = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		max = 0;
		for (int i =0; i < N; i++) {
			heights[i] = Integer.parseInt(st.nextToken());
			max = Math.max(max, heights[i]);
		}

		need1 = 0;
		need2 = 0;
		int sumDef = 0;

		for (int i = 0; i < N; i++) {
			int d = max - heights[i];

			need1 += d & 1;
			need2 += d / 2;
			sumDef += d;
		}

		if(sumDef == 0)
			return 0;

		int day = 0;
		while(day <= N * max) {
			if(canFinish(day))
				return day;

			day++;
		}

		return day;
	}

	static boolean canFinish(int day) {
		int odd = (day + 1) / 2; // +1이 가능한 횟수
		int even = (day) / 2; // +2이 가능한 횟수


		if(even < need2) {
			int extra = need2 - even;
			odd -= extra * 2;
		}

		if(need1 <= odd)
			return true;

		return false;
	}
}
