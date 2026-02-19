import java.util.*;
import java.io.*;

public class SolutionProtectionFilm{
    static int D;
    static int W;
    static int K;
    static int[][] film;
    static boolean[] protect;
    static boolean[] visited;
    static int result;

    public static void combination(int depth, int changed, int[][] test){
        if(depth == W){ //n = W
            result = Math.min(result, changed);
            return;
        }
        if(changed == W){
            return;
        }
        if(inspect(test)){
            result = Math.min(result, changed);
            return;
        }

        visited[depth] = false;
        combination(depth + 1, changed, test);

        visited[depth] = true;
        for(int r = 0; r < D; r++){
            if(r == depth){
                for(int c = 0; c < W; c++){
                    test[r][c] = 0;
                }
            }else{
                for(int c = 0; c < W; c++){
                test[r][c] = film[r][c];
            }
            }
        }
        combination(depth + 1, changed + 1, test);

        for(int r = 0; r < D; r++){
            if(r == depth){
                for(int c = 0; c < W; c++){
                    test[r][c] = 1;
                }
            }else{
                for(int c = 0; c < W; c++){
                test[r][c] = film[r][c];
            }
            }
        }
        combination(depth + 1, changed + 1, test);

        visited[depth] = false;
    }

    public static boolean inspect(int[][] test){
        boolean ret = true;
        int above;
        int cnt = 0;

        for(int c = 0; c < W; c++){
            above = test[0][c];
            cnt++;
            protect[c] = false;
            for(int r = 1; r < D; r++){
                if(above == test[r][c]){
                    cnt++;
                }else{
                    cnt = 0;
                }
                if(cnt >= 3){
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
            result = W;

            film = new int[D][W];
            protect = new boolean[W];
            visited = new boolean[W];

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