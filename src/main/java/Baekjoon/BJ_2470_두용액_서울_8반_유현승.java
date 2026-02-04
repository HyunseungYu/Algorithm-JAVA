package Baekjoon;
import java.io.*;
import java.util.*;

public class BJ_2470_두용액_서울_8반_유현승 {

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringBuilder sb = new StringBuilder();
	static StringTokenizer st;
	
	static int N;
	static int[] arr;
	static int l, r;
	static int minL, minR;
	static int min;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		
		arr = new int[N];
		
		st = new StringTokenizer(br.readLine(), " ");
		for(int i=0; i<N; i++)
			arr[i] = Integer.parseInt(st.nextToken());
		
		Arrays.sort(arr);
		
		l = 0; 
		r = N-1;
		
		minL = l;
		minR = r;
		min = Integer.MAX_VALUE;
		
		while(l<r) {
			int sum = arr[l] + arr[r];
			
			if(Math.abs(sum) < Math.abs(min)) {
				min = sum;
				minL = l;
				minR = r;
			}
				
			if(sum < 0) {
				l++;
			} else {
				r--;
			}
		}
		
		System.out.println(arr[minL] + " " + arr[minR]);
	}
}
