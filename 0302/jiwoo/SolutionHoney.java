import java.io.*;
import java.util.*;


public class SolutionHoney {
    static int N;
    static int M;
    static int C;
    static int[][] hive;
    static int maxAmt;
    static int result;
    static int[][] mem;

    public static void dfs(int n, int cnt, int sum, int i, int j){
        if(cnt > C) return;
        if(n == M){
            maxAmt = Math.max(maxAmt, sum);
            return;
        }

        //include
        dfs(n + 1, cnt + hive[i][j + n], sum + hive[i][j+n] * hive[i][j+n], i, j);

        //exclude
        dfs(n + 1, cnt, sum, i, j);
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
            hive = new int[N][N];
            mem = new int[N][N];
            result = 0;

            for(int i = 0; i < N; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    hive[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N - M + 1; j++){
                    maxAmt = 0;
                    dfs(0, 0, 0, i, j);
                    mem[i][j] = maxAmt;
                }
            }

            for(int i1 = 0; i1 < N; i1++){
                for(int j1 = 0; j1 < N - M + 1; j1++){
                    for(int i2 = i1; i2 < N; i2++){
                        int sj = 0;
                        if(i1 == i2) sj = j1 + M;
                        for(int j2 = sj; j2 < N - M + 1; j2++){
                            result = Math.max(result, mem[i1][j1] + mem[i2][j2]);
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }
}
