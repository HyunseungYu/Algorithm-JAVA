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

		for(int t = 1; t <= T; t++)
		{
			int solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N, M;
	static int[][] banned;
	static boolean[] visited;
	static int count;

	static int solution() throws Exception {
		count = 0;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		visited = new boolean[N];

		banned = new int[M][2];
		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int left = Integer.parseInt(st.nextToken()) - 1;
			int right = Integer.parseInt(st.nextToken()) - 1;

			banned[i] = new int[]{left, right};
		}

		subset(0);

		return count;
	}

	static void subset(int depth) {
		if(depth == N) {
			if(isOkRecipe())
				count++;

			return;
		}

		visited[depth] = false;
		subset(depth+1);

		visited[depth] = true;
		subset(depth+1);
	}

	static boolean isOkRecipe(){
		for (int i = 0; i < M; i++) {
			int left = banned[i][0];
			int right = banned[i][1];

			if(visited[left] && visited[right])
				return false;
		}

		return true;
	}
}
