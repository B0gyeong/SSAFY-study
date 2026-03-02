import java.util.*;
import java.io.*;

public class SolutionSwimmingPool{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int[] prices = new int[4]; // daily, monthly, 3 months, annually
            int[] plan = new int[12]; // month plan
            int[] dp = new int[13]; // min cost from january ~ i-th month
            dp[0] = 0;
            int daily; //daily
            int om; //one month
            int tm; //three months

            for(int i = 0; i < 4; i++){
                prices[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < 12; i++){
                plan[i] = Integer.parseInt(st.nextToken());
            }

            int min = prices[3]; //just regard annual plan is the min amount initially.

            for(int i = 1; i <= 12; i++){
                daily = dp[i - 1] + prices[0] * plan[i - 1];
                om = dp[i - 1] + prices[1];
                dp[i] = Math.min(daily, om);
                if(i >= 3){
                    tm = dp[i - 3] + prices[2];
                    dp[i] = Math.min(dp[i], tm);
                }
            }

            min = Math.min(min, dp[12]);

            System.out.println("#" + test_case + " " + min);
        }
    }
}