import java.util.Arrays;
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
			long solution = solve();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		sc.close();
	}

	static int N;
	static int[][] cords;
	static long[][] E;
	static long[] parent;

	static long solve() throws Exception {
		N = sc.nextInt();

		cords = new int[N][2];
		for (int i = 0; i < N; i++)
			cords[i] = new int[] {sc.nextInt(), 0};

		for (int i = 0; i < N; i++)
			cords[i][1] = sc.nextInt();

		double ratio = sc.nextDouble();


		E = new long[N * N][3];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				long cost = getDistance(cords[i], cords[j]);
				E[i * N + j] = new long[] {i, j, cost};
			}
		}

		Arrays.sort(E, (e1, e2) -> Long.compare(e1[2], e2[2]));

		parent = new long[N];
		for (int i = 0; i < N; i++) {
			parent[i] = i;
		}

		long mst = 0;
		for (int i = 0; i < N * N; i++) {
			long s = E[i][0];
			long e = E[i][1];
			long cost = E[i][2];

			long parentS = findSet(parent, s);
			long parentE = findSet(parent, e);

			if(parentS == parentE)
				continue;

			mst += cost;
			parent[(int) parentE] = parentS;
		}

		return Math.round(mst * ratio);
	}

	static long findSet(long[] parent, long x) {
		if(parent[(int) x] == x)
			return x;

		return parent[(int) x] = findSet(parent, parent[(int) x]);
	}

	static long getDistance(int[] cord1, int[] cord2) {
		long width = cord1[0] - cord2[0];
		long height = cord1[1] - cord2[1];

		return width * width + height * height;
	}

}