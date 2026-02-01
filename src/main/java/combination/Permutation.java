package combination;

import java.util.Arrays;

public class Permutation {
	
	static int[] arr;
	static boolean[] visited;
	static int N;
	static int R;

	public static void main(String[] args) {
		arr = new int[]{1, 2, 3, 4};
		visited = new boolean[arr.length];
		N = arr.length;
		R = 2; // 두 개만 선택
		
		perm(new int[R], 0);

	}
	
	static void perm(int[] p, int count) {
		if(count == 2) {
			System.out.println(Arrays.toString(p));
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i])
				continue;
			
			visited[i] = true;
			p[count] = arr[i];
			perm(p, count+1);
			visited[i] = false;
		}
	}

}
