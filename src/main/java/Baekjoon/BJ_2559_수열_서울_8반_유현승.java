package Baekjoon;
import java.io.*;
import java.util.*;

class BJ_2559_수열_서울_8반_유현승 {

	private static BufferedReader br;
	private static StringTokenizer st;
	private static StringBuilder sb = new StringBuilder(); 
	
	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));
		
//		int T = Integer.parseInt(br.readLine());
		
		for(int t=1; t<=1; t++) {
			int solve = solution();
//			sb.append("#" + t + " " + solve + "\n");
			sb.append(solve);
		}
		System.out.println(sb);
		
		br.close();
	}

	static int N, K;
	static int[] temperatures;
	static int window;
	static int maxTemperature;
	
	private static int solution() throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		
		st = new StringTokenizer(br.readLine(), " ");
		
		temperatures = new int[N];
		for(int i=0; i<N; i++) {
			temperatures[i] = Integer.parseInt(st.nextToken());
		}
		
		// 윈도우 초깃값
		window = 0;
		for(int i=0; i<K; i++) 
			window += temperatures[i];

		// 최댓값 찾기
		maxTemperature = window; // 최댓값 초기 = 윈도우
		for(int i=0; i<N-K; i++) {
			window = window - temperatures[i] + temperatures[i + K];
			maxTemperature = Math.max(maxTemperature, window);
		}
		
		return maxTemperature;
	}
	
	

}
