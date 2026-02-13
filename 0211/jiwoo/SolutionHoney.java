import java.util.*;
import java.io.*;

public class SolutionHoney{
    static int[][] hive;
    static int result;
    static int[][] profit;
    
    static int N;
    static int M;
    static int C;

    public static void honey(int r, int c, int idx, int sqSum, int sum){
        if(sum > C){
            return;
        }
        if(idx == M){
            profit[r][c] = Math.max(sqSum, profit[r][c]);
            return;
        }

        int curr = hive[r][c + idx];

        //X choose current cell
        honey(r, c, idx + 1, sqSum, sum);

        //choose current cell
        honey(r, c, idx + 1, sqSum + curr * curr, sum + curr);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());

            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            result = 0;
            int profitSum;

            profit = new int[N][N - M + 1];
            hive = new int[N][N];

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    hive[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int r = 0; r < N; r++){
                for(int c = 0; c < N - M + 1; c++){
                    honey(r, c, 0, 0, 0);
                }
            }

            //when picking from the same row
            for(int r = 0; r < N; r++){
                for(int c1 = 0; c1 < N - M + 1; c1++){
                    for(int c2 = c1 + M; c2 < N - M + 1; c2++){
                        profitSum = profit[r][c1] + profit[r][c2];
                        result = Math.max(result, profitSum);
                    }
                }
            }

            //when picking from different row
            for(int r1 = 0; r1 < N; r1++){
                for(int r2 = r1 + 1; r2 < N; r2++){
                    for(int c1 = 0; c1 < N - M + 1; c1++){
                        for(int c2 = 0; c2 < N - M + 1; c2++){
                            profitSum = profit[r1][c1] + profit[r2][c2];
                            result = Math.max(result, profitSum);
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }
}