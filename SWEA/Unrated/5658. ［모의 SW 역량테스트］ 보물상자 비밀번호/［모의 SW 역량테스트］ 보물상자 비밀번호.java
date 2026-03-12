import java.util.*;

public class Solution {

	static Scanner sc;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//      System.setIn(new FileInputStream("res/input.txt"));
		sc = new Scanner(System.in);

		int T = sc.nextInt();

		for(int t = 1; t <= T; t++)
		{
			long solution = solve();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static int N, K;

	static long solve() throws Exception {
		N = sc.nextInt();
		K = sc.nextInt();

		sc.nextLine();
		char[] crpyto = sc.nextLine().toCharArray();

		char[] all = new char[N * N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				all[i * N + j] = crpyto[j];
			}
		}

		int madi = N / 4; // N이 12인 경우, N = 3
		int left = 0, right = madi - 1;
		Set<String> comb = new HashSet<>();
		while (right < N * N) {
			String c = makeC(all, left, right);
			comb.add(c);
			left++;
			right++;
		}

		List<String> list = new ArrayList<>(comb);
		Collections.sort(list, Comparator.reverseOrder());
		int integer = toInt(list.get(K - 1));

		return integer;
	}

	static int toInt(String crypto) {
		int result = 0;
		char[] c = crypto.toCharArray();
		for (int i = 0; i < c.length; i++) {
			result *= 16;
			if('0' <= c[i] && c[i] <= '9')
				result += c[i] - '0';
			else
				result += c[i] - 'A' + 10;
		}

		return result;
	}

	static String makeC(char[] all, int left, int right) {
		StringBuilder result = new StringBuilder();

		for (int i=left; i<=right; i++)
			result.append(all[i]);

		return result.toString();
	}

}