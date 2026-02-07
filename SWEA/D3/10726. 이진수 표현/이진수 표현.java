import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Solution {

    static BufferedReader br;
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws Exception {
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

    static int N, M;
    static Deque<Boolean> stack;

    static String solution() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        stack = new ArrayDeque<>();

        while (M != 0) {
            boolean bit = (M & 1) == 1;
            stack.offer(bit);

            M = M >> 1;
        }

        for (int i = 0; i < N; i++) {
            if(stack.isEmpty())
                return "OFF";

            if(!stack.poll())
                return "OFF";
        }

        return "ON";
    }


}
