import java.util.ArrayList;
import java.util.List;
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
	static long[][] cord;
	static long[][] costs;

	static long solve() throws Exception {
		N = sc.nextInt();

		cord = new long[N][2];
		for (int i = 0; i < N; i++) {
			long x = sc.nextLong();
			cord[i] = new long[] {x, 0};
		}
		for (int i = 0; i < N; i++) {
			long y = sc.nextLong();
			cord[i][1] = y;
		}
		double ratio = sc.nextDouble();


		// 노드끼리 거리(코스트) 계산
		List<long[]>[] G = new List[N];
		for (int i = 0; i < N; i++) {
			G[i] = new ArrayList<>();
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < N; j++) {
				if(i == j)
					continue;

				G[i].add(new long[] {j, getDistance(cord[i], cord[j])});
			}
		}


		boolean[] visited = new boolean[N];
		long[] P = new long[N];
		for (int i = 0; i < N; i++)
			P[i] = Long.MAX_VALUE;
		P[0] = 0;

		long mst = 0;
		int count = 0;
		for (int i = 0; i < N; i++) {
			long min = Long.MAX_VALUE;
			int minVertex = -1;

			for (int j = 0; j < N; j++) {
				if(!visited[j] && P[j] < min) {
					min = P[j];
					minVertex = j;
				}
			}

			visited[minVertex] = true;
			mst += min;

			for (long[] v : G[minVertex]) {
				if(!visited[(int) v[0]] && v[1] < P[(int) v[0]]) {
					P[(int) v[0]] = v[1];
				}
			}

		}

		return (long) (Math.round(mst * ratio));
	}

	static long getDistance(long[] p1, long[] p2) {
		long p1X = p1[0];
		long p1Y = p1[1];

		long p2X = p2[0];
		long p2Y = p2[1];

		return (p1X - p2X) * (p1X - p2X) + (p1Y - p2Y) * (p1Y - p2Y);
	}


}