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
	static int[] dp = new int[1_000_000];

	static int solution() throws Exception {
		N = Integer.parseInt(br.readLine());

		dp[N] = get(N);

		return dp[N];
	}

	static int get(int n) {
		if(n < 10)
			return 0;

		if(dp[n] != 0)
			return dp[n];

		String s = Integer.toString(n);
		int len = s.length();
		int cutCnt = len - 1;

		int best = 0;

		for (int mask = 1; mask < (1 << cutCnt); mask++) {
			int prod = 1;
			int last = 0;

			for (int i = 0; i < cutCnt; i++) {
				if((mask & (1 << i)) != 0) {
					int part = Integer.parseInt(s.substring(last, i+1));
					prod *= part;
					last = i + 1;
				}
			}

			int part = Integer.parseInt(s.substring(last));
			prod *= part;

			best = Math.max(best, 1 + get(prod));
		}

		dp[n] = best;
		return best;
	}

}
