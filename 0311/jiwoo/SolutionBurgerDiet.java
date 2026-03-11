import java.util.*;
import java.io.*;

public class SolutionBurgerDiet {
    static int N;
    static int L;
    static int[][] ingredients; //[0]: score; [1]: cal
    static int maxScore;
    static int[] dp; //max Score for cal


    public static void calculate(int depth, int accSc, int accCal){
        if(accCal > L) return;
        if(depth == N){
            dp[accCal] = Math.max(dp[accCal], accSc);
            return;
        }
        
        //include
        calculate(depth + 1, accSc + ingredients[depth][0], accCal + ingredients[depth][1]);

        //exclude
        calculate(depth + 1, accSc, accCal);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            maxScore = 0;

            ingredients = new int[N][2];
            dp = new int[L + 1];
            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine().trim());
                ingredients[i][0] = Integer.parseInt(st.nextToken());
                ingredients[i][1] = Integer.parseInt(st.nextToken());
            }

            calculate(0, 0, 0);

            for(int i = 0; i < L + 1; i++){
                maxScore = Math.max(dp[i], maxScore);
            }

            System.out.println("#" + test_case + " " + maxScore);
        }
    }
}
