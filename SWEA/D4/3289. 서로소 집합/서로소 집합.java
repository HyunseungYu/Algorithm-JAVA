import java.util.Scanner;
import java.util.StringTokenizer;

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
			String solution = solve();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static int n, m;
	static int[] parent;

	static String solve() throws Exception {
		n = sc.nextInt();
		m = sc.nextInt();

		parent = new int[n+1];
		for (int i = 1; i <= n; i++)
			parent[i] = i;

		StringBuilder result = new StringBuilder();
		for (int i = 0; i < m; i++) {
			int command = sc.nextInt();
			int a = sc.nextInt();
			int b = sc.nextInt();

			if(command == 1) {
				result.append(isSame(a, b));
				continue;
			}

			union(a, b);
		}

		return result.toString();
	}

	static int isSame(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);

		if(parentA == parentB)
			return 1;

		return 0;
	}

	static void union(int a, int b) {
		int parentA = find(a);
		int parentB = find(b);

		if(parentA == parentB)
			return;

		parent[parentB] = parentA;
	}

	static int find(int x) {
		if(parent[x] == x)
			return x;

		return parent[x] = find(parent[x]);
	}

}