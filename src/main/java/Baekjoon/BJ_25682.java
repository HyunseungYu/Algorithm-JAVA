package Baekjoon;
import java.io.*;
import java.util.*;

public class BJ_25682 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, M, K;
	static boolean[][] board;
	static int minCount;
	static boolean isStartColorWhite;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		K = Integer.parseInt(st.nextToken());
		minCount = Integer.MAX_VALUE;
		
		board = new boolean[N+1][M+1];
		
		for(int i=1; i<N+1; i++) {
			char[] line = br.readLine().toCharArray();
			for(int j=1; j<M+1; j++) {
				board[i][j] = (line[j-1] == 'W') ? true : false;
			}
		}
		
		System.out.println(Math.min(chess(true),chess(false)));
	}
	
	static int chess(boolean startColor) {
		boolean color = startColor;
		
		int[][] sumBoard = new int[N+1][M+1];
		int value;
		int min = Integer.MAX_VALUE;
		
		for(int i=1; i<N+1; i++) {
			
			for(int j=1; j<M+1; j++) {
				if((i+j) % 2 == 0)
					value = board[i][j] != color? 1 : 0;
				else
					value = board[i][j] == color ? 1 : 0;
				
				sumBoard[i][j] = sumBoard[i-1][j] + sumBoard[i][j-1] - sumBoard[i-1][j-1] + value;
			}
		}

		for(int i=K; i<N+1; i++) {
			for(int j=K; j<M+1; j++) {
				int count = sumBoard[i][j] - sumBoard[i-K][j] - sumBoard[i][j-K] + sumBoard[i-K][j-K];
				min = Math.min(min, count);
			}
		}
		
		return min;
		
		
		
	}
}
