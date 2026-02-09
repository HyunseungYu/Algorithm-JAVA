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
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N, M;
	static int[] snacks;
	static int maxSum;

	static int solution() throws Exception {
		maxSum = 0;

		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());

		snacks = new int[N];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < N; i++)
			snacks[i] = Integer.parseInt(st.nextToken());

		int sum = 0;
		for (int i = 0; i < N; i++) {
			for (int j = i+1; j < N; j++) {
				sum = snacks[i] + snacks[j];
				if(M < sum)
					continue;

				maxSum = Math.max(maxSum, sum);
			}
		}

		return (maxSum != 0) ? maxSum : -1;
	}


}
