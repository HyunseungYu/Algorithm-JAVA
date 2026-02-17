

import java.io.*;
import java.util.*;

public class Solution {

    static StringBuilder sb = new StringBuilder();
    static Scanner sc;
    static BufferedReader br;

    public static void main(String[] args) throws Exception {
//		System.setIn(new FileInputStream("res/input.txt"));
        sc = new Scanner(System.in);
//        br = new BufferedReader(new InputStreamReader(System.in));

//        int T = Integer.parseInt(br.readLine());
        int T = sc.nextInt();

        for(int t = 1; t <= T; t++) {
            int solution = solve();
            sb.append("#" + t + " " + solution + "\n");
        }

        System.out.println(sb.toString());
    }



    static int solve() throws Exception {
        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] snacks = new int[N];
        for (int i = 0; i < N; i++)
            snacks[i] = sc.nextInt();

        int sum = 0;
        int max = -1;

        Arrays.sort(snacks);
        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                sum = snacks[i] + snacks[j];
                if(M < sum)
                    break;

                max = Math.max(max, sum);
            }
        }

        return max;
    }



}
