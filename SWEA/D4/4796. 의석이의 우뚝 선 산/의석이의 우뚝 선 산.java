import java.io.BufferedReader;
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

		for (int i = 1; i < N-1; i++) {
			int left = heights[i-1];
			int current = heights[i];
			int right = heights[i+1];

			if(!(left < current && right < current))
				continue;

			// 이제부터 여기는 피크인 지점이니 좌우로 펼치면서 left 몇 개인지, right 몇 개인지 찾기
			int leftCnt = 0;
			int rightCnt = 0;

			int l = i-1;
			while(0 <= l) {
				if(heights[l] > heights[l+1])
					break;

				l--;
			}
			l++;
			leftCnt = i - l;

			int r = i+1;
			while(r < N) {
				if(heights[r-1] < heights[r])
					break;

				r++;
			}
			r--;
			rightCnt = r - i;

//			System.out.println("l = " + l);
//			System.out.println("leftCnt = " + leftCnt);
//			System.out.println("r = " + r);
//			System.out.println("rightCnt = " + rightCnt);

			count += rightCnt * leftCnt;
		}

		return count;
	}
}
