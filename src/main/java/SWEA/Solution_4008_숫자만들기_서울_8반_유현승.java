package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution_4008_숫자만들기_서울_8반_유현승 {

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

	static int N;
	static int[] operatorCnt, numbers;
	static int[] operators;
	static int[] arr;
	static int max, min;

	static int solution() throws Exception {
		max = Integer.MIN_VALUE;
		min = Integer.MAX_VALUE;

		N = Integer.parseInt(br.readLine());

		st = new StringTokenizer(br.readLine(), " ");
		operatorCnt = new int[4];
		for (int i = 0; i < 4; i++)
			operatorCnt[i] = Integer.parseInt(st.nextToken());


		st = new StringTokenizer(br.readLine(), " ");
		numbers = new int[N];
		for (int i = 0; i < N; i++)
			numbers[i] = Integer.parseInt(st.nextToken());

		operators = new int[N-1];
		permOp(0);

		return max - min;
	}

	static void perm(int cnt) {
		if(cnt == N) {
			permOp(0);
			return;
		}

		for (int i = 0; i < N; i++) {

			if(isDuplicate(cnt, i))
				continue;

			arr[cnt] = i;
			perm(cnt+1);
		}
	}

	static boolean isDuplicate(int cnt, int i) {
		for (int j = 0; j < cnt; j++) {
			if(arr[j] == i)
				return true;
		}

		return false;
	}

	static void permOp(int depth) {
		if(depth == N-1) {
			int total = calc();
			max = Math.max(max, total);
			min = Math.min(min, total);
			return;
		}

		for (int i = 0; i < 4; i++) {
			if(operatorCnt[i] == 0)
				continue;

			operators[depth] = i;
			operatorCnt[i]--;
			permOp(depth + 1);
			operatorCnt[i]++;
		}
	}

	static int calc(){
		int left = numbers[0];
		for (int i = 1; i <N; i++) {
			int right = numbers[i];
			int operator = operators[i-1];

			switch (operator) {
				case 0: // +
					left += right;
					break;
				case 1: // -
					left -= right;
					break;
				case 2: // *
					left *= right;
					break;
				case 3: // /
					left /= right;
					break;
			}

		}

		return left;
	}

}
