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

	static int N, H;
	static int[] heights;
	static int min;

	static int solution() throws Exception {
		min = Integer.MAX_VALUE;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		H = Integer.parseInt(st.nextToken());

		heights = new int[N];
		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			heights[i] = Integer.parseInt(st.nextToken());

		choose(0, 0);

		return min;
	}

	static void choose(int depth, int heightSum) {
		if(depth == N) {
			if(H <= heightSum)
				min = Math.min(min, heightSum - H);
			return;
		}


		// 현재 사람 탑 쌓기 X
		choose(depth + 1, heightSum);

		// 현재 사람 탑 쌓기 X
		choose(depth + 1, heightSum + heights[depth]);
	}
}
