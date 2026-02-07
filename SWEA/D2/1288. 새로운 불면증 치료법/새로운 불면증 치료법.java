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
    static boolean[] visited;

    static int solution() throws Exception {
        st = new StringTokenizer(br.readLine(), " ");
        N = Integer.parseInt(st.nextToken());
        visited = new boolean[10];
        count = 0;

        current = N;
        while(true) {
            // 0~9까지 다 돌았다면

            visit(current);

            if(allVisited())
                return current;
            
            current += N;
            count++;
        }

    }

    static void visit(int current) {
        while(0 < current) {
            int left = current % 10;
            visited[left] = true;
            current /= 10;
        }
    }

    static boolean allVisited() {
        for (int i = 0; i < 10; i++) {
            if(!visited[i])
                return false;
        }

        return true;
    }

}
