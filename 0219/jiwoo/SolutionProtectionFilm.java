import java.util.*;
import java.io.*;

public class SolutionProtectionFilm{
    static int D;
    static int W;
    static int K;
    static int[][] film;
    static boolean[] protect;
    static int result;

    public static void combination(int depth, int changed, int[][] test){
        if(depth == D){ //n = D
            if(inspect(test)){
                result = Math.min(result, changed);
            }
            
            return;
        }
        if(changed >= result){
            return;
        }

        int[] backup = new int[W];
        for(int c = 0; c < W; c++){
            backup[c] = test[depth][c];
        }
        
        combination(depth + 1, changed, test);

        for(int c = 0; c < W; c++){
            test[depth][c] = 0;
        }
        combination(depth + 1, changed + 1, test);

        for(int c = 0; c < W; c++){
            test[depth][c] = 1;
        }
        combination(depth + 1, changed + 1, test);

        for(int c = 0; c < W; c++){
            test[depth][c] = backup[c];
        }
    }

    public static boolean inspect(int[][] test){
        int above;
        int cnt;

        if(K == 1) return true;

        for(int c = 0; c < W; c++){
            above = test[0][c];
            cnt = 1;
            protect[c] = false;
            for(int r = 1; r < D; r++){
                if(above == test[r][c]){
                    cnt++;
                }else{
                    cnt = 1;
                }
                if(cnt >= K){
                    protect[c] = true;
                    break;
                }
                above = test[r][c];
            }
        }

        for(int i = 0; i < W; i++){
            if(protect[i] == false){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            D = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            result = D;

            film = new int[D][W];
            protect = new boolean[W];

            for(int i = 0; i < D; i++){
                st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < W; j++){
                    film[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if(inspect(film)){
                System.out.println("#" + test_case + " 0"); //pass w/ X change
                continue;
            }

            combination(0, 0, film);

            System.out.println("#" + test_case + " " + result);
        }
    }
}