package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_1952_수영장_서울_8반_유현승 {

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
	static int[] pay;

	static int solution() throws Exception {
		minFee = 0;
		tickets = new int[4];
		calendar = new int[12];
		pay = new int[12];

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 4; i++)
			tickets[i] = Integer.parseInt(st.nextToken());

		st = new StringTokenizer(br.readLine(), " ");
		for (int i = 0; i < 12; i++)
			calendar[i] = Integer.parseInt(st.nextToken());

		minFee = tickets[3];
		choose(0);

		return minFee;
	}

	static void choose(int month) {
		if(12 <= month) {
			calc();
			return;
		}

		// 1일 이용권 구매
		pay[month] = 0;
		choose(month + 1);

		// 1달 이용권 구매
		pay[month] = 1;
		choose(month + 1);

		// 3달 이용권 구매
		for (int i = 0; i < 3 && month + i < 12; i++) {
			pay[month + i] = 2;
		}
		choose(month + 3);


	}

	static void calc() {
		int dailyPassFee = 0;
		int monthlyPassFee = 0;
		int monthly3Pass = 0;

		for (int i = 0; i < 12; i++) {
			if(pay[i] == 0) {
				dailyPassFee += tickets[0] * calendar[i];
			} else if(pay[i] == 1)
				monthlyPassFee += tickets[1];
			else
				monthly3Pass++;
		}

		monthly3Pass = (int) Math.ceil(monthly3Pass/ (double) 3);

		int totalFee = dailyPassFee + monthlyPassFee + monthly3Pass * tickets[2];
		minFee = Math.min(minFee, totalFee);
	}

}
