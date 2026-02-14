import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static BufferedReader br;
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
		br = new BufferedReader(new InputStreamReader(System.in));

//		int T = Integer.parseInt(br.readLine());
		int T = 10;

		for(int t = 1; t <= T; t++) {
			String solution = solution();
			sb.append("#" + t + " " + solution + "\n");
		}

		System.out.println(sb.toString());
		br.close();
	}

    static int N;
    static char[] c;
    static StringBuilder str;

	static String solution() throws Exception {
        str = new StringBuilder();

        N = Integer.parseInt(br.readLine());
        c = new char[N+1];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine(), " ");
            st.nextToken();
            c[i+1] = st.nextToken().charAt(0);

            if(st.countTokens() == 2) {
                st.nextToken();
                st.nextToken();
            }
        }

        dfs(1);

        return str.toString();
	}

    static void dfs(int current) {
        if(N < current)
            return;

        int left = current * 2;
        int right = current * 2 + 1;

        dfs(left);
        str.append(c[current]);
        dfs(right);
    }
}
