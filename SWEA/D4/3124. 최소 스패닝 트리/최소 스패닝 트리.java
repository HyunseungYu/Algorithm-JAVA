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

	static int V, E;
	static int[] parent;

	static long solve() throws Exception {
		V = sc.nextInt();
		E = sc.nextInt();

		int[][] graph = new int[E][3];
		for (int i = 0; i < E; i++) {
			int start = sc.nextInt();
			int end = sc.nextInt();
			int cost = sc.nextInt();

			graph[i] = new int[] {start, end, cost};
		}

		Arrays.sort(graph, (e1, e2) -> Integer.compare(e1[2], e2[2]));
		long mst = 0;

		parent = new int[V+1];
		for (int i = 1; i <= V; i++)
			parent[i] = i;

		for (int i = 0; i < E; i++) {
			int start = graph[i][0];
			int end = graph[i][1];
			int cost = graph[i][2];

			int rootS = findSet(start);
			int rootE = findSet(end);

			if(rootS == rootE)
				continue;

			// union
			parent[rootE] = rootS;

			mst += cost;
		}

		return mst;
    }

	static int findSet(int x) {
		if(parent[x] == x)
			return x;

		return parent[x] = findSet(parent[x]);
	}

}