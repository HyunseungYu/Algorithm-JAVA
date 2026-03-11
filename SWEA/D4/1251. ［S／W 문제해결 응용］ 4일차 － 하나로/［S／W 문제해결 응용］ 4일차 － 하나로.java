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
	static long[][] costs;

	static long solve() throws Exception {
		N = sc.nextInt();

		cords = new int[N][N];
		for (int i = 0; i < N; i++)
			cords[i] = new int[] {sc.nextInt(), 0};

		for (int i = 0; i < N; i++)
			cords[i][1] = sc.nextInt();

		double ratio = sc.nextDouble();

		costs = new long[N][N];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				costs[i][j] = getDistance(cords[i], cords[j]);
			}
		}

		boolean[] visited = new boolean[N];
		long[] P = new long[N];
		for (int i = 0; i < N; i++)
			P[i] = Long.MAX_VALUE;

		long mst = 0;
		P[0] = 0;
		for (int i = 0; i < N; i++) {
			long min = Long.MAX_VALUE;
			long minVertex = -1;
			for (int j = 0; j < N; j++) {
				if(!visited[j] && P[j] < min) {
					min = P[j];
					minVertex = j;
				}
			}

			visited[(int) minVertex] = true;
			mst += min;

			for (int j = 0; j < N; j++) {
				if(minVertex == j)
					continue;

				long cost = costs[(int) minVertex][j];
				if(!visited[j] && cost < P[j]) {
					P[j] = cost;
				}
			}
		}

		return Math.round(mst * ratio);
	}

	static long getDistance(int[] cord1, int[] cord2) {
		long width = cord1[0] - cord2[0];
		long height = cord1[1] - cord2[1];

		return width * width + height * height;
	}

}