package Baekjoon;
import java.io.*;
import java.util.*;

public class BJ_2531 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N, d, k, c;
	static int[] sushis;
	static int[] sushi;
	static int maxEat = 0;
	static int eat;
	
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		
		N = Integer.parseInt(st.nextToken());
		d = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());
		c = Integer.parseInt(st.nextToken());
		
		sushis = new int[N];
		for(int i=0; i<N; i++)
			sushis[i] = Integer.parseInt(br.readLine());

		sushi = new int[d+1];
		for(int i=0; i<k; i++) {
			int type = sushis[i];
			if(sushi[type] == 0)
				eat++;
			
			sushi[type]++;
		}
		for(int i=0; i<N; i++) {
			
			int add = sushis[(i+k) % N];
			if(sushi[add] == 0)
				eat++;
			sushi[add]++;
			
			int remove = sushis[i];
			sushi[remove]--;
			if(sushi[remove] == 0)
				eat--;
			
			if(sushi[c] == 0)
				maxEat = Math.max(maxEat, eat+1);
			else
				maxEat = Math.max(maxEat, eat);
		}
		
		System.out.println(maxEat);
		
	}
}
