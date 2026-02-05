package SWEA;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution_d4_8275_햄스터_서울_8반_유현승 {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

		int T = Integer.parseInt(br.readLine());

		for(int t = 1; t <= T; t++)
		{
			String solve = solution();
			sb.append("#" + t + " " + solve + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

	static int N, X, M;
	static int[] l, r, s;
	static int[] cases; // 우리에 햄스터 수를 저장하는 경우의 수
	static int[] answer;
	static int maxHamasters;


	static String solution() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		X = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		maxHamasters = Integer.MIN_VALUE;

		l = new int[M];
		r = new int[M];
		s = new int[M];

		for (int i = 0; i < M; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			l[i] = Integer.parseInt(st.nextToken()) - 1;
			r[i] = Integer.parseInt(st.nextToken()) - 1;
			s[i] = Integer.parseInt(st.nextToken());
		}

		answer = null;
		cases = new int[N];
		comb(0);

		if(answer == null)
			return "-1";

		StringBuilder ret = new StringBuilder();
		for (int i = 0; i < N; i++)
			ret.append(answer[i] + " ");

		return ret.toString().trim();
	}

	static void comb(int depth) {
		if(depth == N) {
			check();
			return;
		}

		for(int i=0; i<=X; i++) {
			cases[depth] = i;
			comb(depth+1);
		}

	}

	/**
	 * l, r, s를 만족하는지 체크 후 최댓값 저장
	 */
	static void check() {
		for (int i = 0; i < M; i++) {
			int left = l[i];
			int right = r[i];
			int count = s[i];

			int hamster = getHamsterRange(left, right);

			// s를 만족하지 못한 경우 return
			if(hamster != count)
				return;
		}

		int hamsters = getHamster();

		// 조건을 만족하는 조합의 햄스터 수 비교해서 더 큰 경우의 수로 교환
		if(maxHamasters < hamsters) {
			answer = Arrays.copyOf(cases, cases.length);
			maxHamasters = hamsters;
		}

	}

	/**
	 * left부터 right까지 햄스터 수 구하기
	 */
	static int getHamsterRange(int left, int right) {
		int sum = 0;

		for(int i=left; i<=right; i++) {
			sum += cases[i];
		}

		return sum;
	}

	/**
	 * 전체 햄스터 수 구하기
	 */
	static int getHamster() {
		int sum = 0;
		for (int i = 0; i < N; i++) {
			sum += cases[i];
		}
		return sum;
	}

}
