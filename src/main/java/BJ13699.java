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

public class BJ13699 {

	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static StringBuilder sb = new StringBuilder();
	private static StringTokenizer st;
	
	static int N;
	static long[] save;
	
	public static void main(String[] args) throws Exception {
		N = Integer.parseInt(br.readLine());
		save = new long[36];
		save[0] = 1;

		System.out.println(t(N));
	}
	
	static long t(int N) {
		if(N == 0)
			return 1;
		
		if(save[N] != 0)
			return save[N];
		
		long sum = 0;
		for(int i=0; i<N; i++) {
			sum += t(i) * t(N-i-1);
		}
		
		save[N] = sum;
		
		return sum;
	}

}
