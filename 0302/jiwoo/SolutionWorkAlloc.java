import java.util.*;
import java.io.*;

public class SolutionWorkAlloc {
    static int N;
    static double[][] work;
    static boolean[] done;
    static double best;

    public static void check(int depth, double per){
        if(per <= best){
            return;
        }
        if(depth == N){
            best = Math.max(best, per);
            return;
        }

        for(int i = 0; i < N; i++){
            if(!done[i]){
                done[i] = true;
                check(depth + 1, per * work[depth][i] / 100);
                done[i] = false;
            }
        }
    }


    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            work = new double[N][N];
            done = new boolean[N];
            best = 0;

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    work[i][j] = Double.parseDouble(st.nextToken());
                }
            }

            check(0, 1);
            String formatted = String.format("#%d %.6f", test_case, best * 100);
            System.out.println(formatted);
        }
    }
}