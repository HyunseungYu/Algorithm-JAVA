import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();
	static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
//		br = new BufferedReader(new InputStreamReader(System.in));

//		Scanner sc = new Scanner(System.in);
//		int T = Integer.parseInt(br.readLine());
		int T = sc.nextInt();

		for(int t = 1; t <= T; t++) {
			int solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
//		br.close();
	}


	static int solution() throws Exception {
//		int N = Integer.parseInt(br.readLine());
//		Scanner sc = new Scanner(System.in);
		int N = sc.nextInt();
		int[] heights = new int[N];

//		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i <N; i++)
			heights[i] = sc.nextInt();

		int count = 0;
		int up = 0;
		int down = 0;
		for (int i = 1; i < N; i++) {
			int left = heights[i-1];
			int current = heights[i];

			// 상승이면
			if(left < current) {
				if(0 < down) {
					count += down * up;
					up = 0;
					down = 0;
				}

				up++;
			} else { // 하강이면
				down++;
			}
		}

		count += up * down;

		return count;
	}
}
