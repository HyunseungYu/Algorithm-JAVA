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
	static int[][] edges;
	static int[] parent;

	static long solve() throws Exception {
		V = sc.nextInt();
		E = sc.nextInt();

		edges = new int[E][3];
		for (int i = 0; i < E; i++) {
			edges[i] = new int[] {sc.nextInt(), sc.nextInt(), sc.nextInt()};
		}

		Arrays.sort(edges, (e1, e2) -> Integer.compare(e1[2], e2[2]));

		parent = new int[V+1];
		for (int i = 1; i <= V; i++) {
			parent[i] = i;
		}

		int count = 0;
		long mst = 0;
		for (int i = 0; i < E; i++) {
			if(count == V - 1)
				break;

			int parentS = findSet(edges[i][0]);
			int parentE = findSet(edges[i][1]);
			if(parentS == parentE)
				continue;

			parent[parentE] = parentS;
			mst += edges[i][2];
			count++;
		}

		return mst;
	}

	static int findSet(int x) {
		if(parent[x] == x)
			return x;

		return parent[x] = findSet(parent[x]);
	}

}