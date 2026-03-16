import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static Scanner sc;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static final long MOD = 1234567891L;

	public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("res/input.txt"));
		sc = new Scanner(System.in);

		int T = sc.nextInt();

		for (int t = 1; t <= T; t++) {
			long solution = solve();
			sb.append("#").append(t).append(" ").append(solution).append("\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static long solve() throws Exception {
		int N = sc.nextInt();
		int R = sc.nextInt();

		if (R == 0 || R == N) return 1;

		long upValue = 1;
		for (int i = 0; i < R; i++) {
			upValue = (upValue * (N - i)) % MOD;
		}

		long downValue = 1;
		for (int i = 1; i <= R; i++) {
			downValue = (downValue * i) % MOD;
		}

		return (upValue * pow(downValue, MOD - 2)) % MOD;
	}

	static long pow(long base, long exp) {
		long result = 1;
		base %= MOD;

		while (exp > 0) {
			if ((exp & 1) == 1) {
				result = (result * base) % MOD;
			}
			base = (base * base) % MOD;
			exp >>= 1;
		}

		return result;
	}
}