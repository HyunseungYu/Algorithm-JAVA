import java.io.*;
import java.util.*;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++) {
			long solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N;
	static long M;
	static long[] candies;
	static long bagCnt;

	static long solution() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Long.parseLong(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		candies = new long[N];
		for (int i = 0; i < N; i++)
			candies[i] = Long.parseLong(st.nextToken());


		long l = 0;
		long r = Long.MAX_VALUE - 1;
		while (l <= r) {
			long mid = (l + r) / 2;

			long bagCnt = getBagCount(mid);
			if(bagCnt < M) {
				r = mid - 1;
			} else if (M <= bagCnt) {
				l = mid + 1;
			} else {

			}
		}

		if(r < 0)
			return 0;

		return r;
	}

	static long getBagCount(long bagCnt) {
		if(bagCnt == 0)
			return 0;

		long sum = 0;

		for (int i = 0; i < N; i++) {
			sum += candies[i] / bagCnt;
		}

		return sum;
	}
}
