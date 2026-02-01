import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;
import java.util.TreeSet;

public class BJ24108 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;

	static int N, M;
	static int[][] list;
	static int[] eatCount;
	static int[] sushi;
	
	public static void main(String[] args) throws Exception {
		st = new StringTokenizer(br.readLine(), " ");
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		
		eatCount = new int[N];
		list = new int[N][];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine(), " ");
			int k = Integer.parseInt(st.nextToken());
			list[i] = new int[k];
			
			for(int j=0; j<k; j++) {
				int num = Integer.parseInt(st.nextToken());
				list[i][j] = num;
			}
			
			Arrays.sort(list[i]);
		}
		
		st = new StringTokenizer(br.readLine(), " ");
		sushi = new int[200_001];
		for(int i=0; i<M; i++) {
			int num = Integer.parseInt(st.nextToken());
			sushi[num]++;
		}

		
		for(int i=0; i<N; i++) {
			int[] personWant = list[i];
			
			for(int j=0; j<personWant.length; j++) {
				int want = personWant[j];

				if(0 < sushi[want]) {
					eatCount[i]++;
					sushi[want]--;
				}
			}
		}
		
		for(int i=0; i<N; i++)
			sb.append(eatCount[i] + " ");
		
		System.out.println(sb.toString().trim());
		
		
	}

}
