package Baekjoon;
import java.io.*;
import java.util.*;

public class BJ_2567_색종이2_서울_8반_유현승 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static boolean[][] board;
	static boolean[][] visited;
	static int count = 0;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		board = new boolean[102][102];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			
			paste(x, y);
		}
		
		
		visited = new boolean[101][101];
		for(int i=1; i<=100; i++) {
			for(int j=1; j<=100; j++) {
				if(!board[i][j])
					continue;
				
				if(!visited[i][j])
					dfs(i, j);
			}
		}
		
		System.out.println(count);
	}
	
	
	static void dfs(int i, int j) {
		if(!isAvailable(i, j))
			return;
		
		if(visited[i][j])
			return;
		
		if(!board[i][j])
			return;
		
		visited[i][j] = true;
		
		if(isLeftWall(i, j))
			count++;
		
		if(isRightWall(i, j))
			count++;

		if(isUpWall(i, j))
			count++;

		if(isDownWall(i, j))
			count++;
		
		dfs(i-1, j);
		dfs(i+1, j);
		dfs(i, j-1);
		dfs(i, j+1);
	}
	
	static boolean isLeftWall(int i, int j) {
		if(!board[i][j-1])
			return true;
		
		return false;
	}
	
	static boolean isRightWall(int i, int j) {
		if(!board[i][j+1])
			return true;
		
		return false;
	}
	
	static boolean isUpWall(int i, int j) {
		if(!board[i-1][j])
			return true;
		
		return false;
	}
	
	static boolean isDownWall(int i, int j) {
		if(!board[i+1][j])
			return true;
		
		return false;
	}
	
	static boolean isAvailable(int i, int j) {
		if(i < 1 || 101 <= i || j < 1 || 101 <= j)
			return false;
		
		return true;
	}
	
	static void paste(int x, int y) {
		for(int i=y; i<y+10; i++) {
			for(int j=x; j<x+10; j++) {
				board[i][j] = true;
			}
		}
	}
}
