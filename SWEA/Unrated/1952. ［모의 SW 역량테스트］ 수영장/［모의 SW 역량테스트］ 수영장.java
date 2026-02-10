
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
			int solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int[] tickets;
	static int[] calendar;
	static int minFee;

	static int solution() throws Exception {
		minFee = 0;
		tickets = new int[4];
		calendar = new int[12];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++)
			tickets[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 12; i++)
			calendar[i] = Integer.parseInt(st.nextToken());

		minFee = tickets[3];
		choose(0, 0);

		return minFee;
	}

	static void choose(int month, int cost) {
		if(12 <= month) {
			minFee = Math.min(minFee, cost);
			return;
		}

		// 1일 이용권 구매
		choose(month + 1, cost + tickets[0] * calendar[month]);

		// 1달 이용권 구매
		choose(month + 1, cost + tickets[1]);

		// 3달 이용권 구매
		choose(month + 3, cost+tickets[2]);
	}

}
