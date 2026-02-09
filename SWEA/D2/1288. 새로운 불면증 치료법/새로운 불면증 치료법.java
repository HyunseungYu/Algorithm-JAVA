import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
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
            int solve = solution();
            sb.append("#" + t + " " + solve + "\n");
        }

        System.out.println(sb.toString());
        br.close();
    }

    static int N, count;
    static int current;
    static int visited;
    static final int total = (1 << 10) - 1;

    static int solution() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        count = 1;
        current = N;
        visited = 0;

        while(true) {
            visit(current);

            if(allVisited())
                return current;

            current += N;
            count++;
        }

    }

    static void visit(int current) {
        char[] line = String.valueOf(current).toCharArray();
        for (char c : line) {
            int num = c - '0';
            visited |= (1 << num);
        }
    }

    static boolean allVisited() {
        if(visited == total)
            return true;

        return false;
    }

}
