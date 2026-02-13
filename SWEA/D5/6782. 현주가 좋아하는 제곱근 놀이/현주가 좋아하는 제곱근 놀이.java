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
			long solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static long N, count;

	static long solution() throws Exception {
		count = 0;
		N = Long.parseLong(br.readLine());

		while(N != 2) {
			// 다음 제곱근 찾기
			long nextRoot = 다음제곱근찾기(N);

			// 다음 제곱근 사이의 거리 구하기
			// 다음 제곱근과 N이 같은 경우 -> 현재 제곱근이라는 의미이니 루트
			// 0 < diff 경우 -> 제곱근까지 diff만큼 +1 해야 하니 -> N += diff
			long diff = nextRoot - N;
			if(diff == 0) {
				N = (long) Math.sqrt(N);
				count++;
			} else {
				N += diff;
				count += diff;
			}
		}

		return count;
	}

	static long 다음제곱근찾기(long n) {
		if(제곱근이냐(n))
			return n;

		long sqrt = (long) Math.sqrt(N);
		return (sqrt+1) * (sqrt + 1);
	}

	static boolean 제곱근이냐(long n) {
		long sqrt = (long) Math.sqrt(N);

		if(sqrt * sqrt == n)
			return true;

		return false;
	}
}
