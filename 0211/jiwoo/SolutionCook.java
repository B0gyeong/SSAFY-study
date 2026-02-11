import java.util.*;
import java.io.*;

public class SolutionCook{
    static int result;
    static int N;
    static int[][] S;
    static boolean[] pick;

    public static void comb(int idx, int cnt){
        if(cnt == N/2){
            int A = 0;
            int B = 0;
            for(int i = 0; i < N; i++){
                for(int j = i + 1; j < N; j++){
                    if(pick[i] && pick[j]){
                        A = A + S[i][j] + S[j][i];
                    }else if(!pick[i] && !pick[j]){
                        B = B + S[i][j] + S[j][i];
                    }
                }
            }
            int curr = Math.abs(A - B);
            result = Math.min(curr, result);
            return;
        }else if(idx == N){
            return;
        }else{
            pick[idx] = true; //pick this item to dish A
            comb(idx + 1, cnt + 1);

            pick[idx] = false; //pick this item to dish B
            comb(idx + 1, cnt);
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            S = new int[N][N];
            pick = new boolean[N];
            result = Integer.MAX_VALUE;

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    S[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            pick[0] = true; //remove half of the duplicate
            comb(1, 1);

            System.out.println("#" + test_case + " " + result);
        }
    }
}