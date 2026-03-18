import java.util.*;
import java.io.*;

public class Group{
    //
}

public class SolutionMintChocoMilk{
    static int N;
    static int T;
    static int[][] F; //신봉음식
    static int[][] B; //신앙심
    static boolean[][] visited;
    static int[] dr = {-1, 1, 0, 0};
    static int[] dc = {0, 0, -1, 1};
    static final int[] order = {7, 6, 5, 3, 1, 2, 4}; //tmc, tc, tm, cm, m, c, t

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }

    public static void grouping(){ //dfs
    }

    public static void morning(){
        for(int i = 0; i < N; i++){
            for(int j = 0; j < N; j++){
                B[i][j] = B[i][j] + 1;
            }
        }
    }

    public static void lunch(){
        group
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        N = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        F = new int[N][N];
        B = new int[N][N];
        visited = new boolean[N][N];

        for(int i = 0; i < N; i++){
            String f = br.readLine().trim();
            for(int j = 0; j < N; j++){
                char init = f.charAt(j);
                if(init == "T"){
                    F[i][j] = 4;
                }else if(init == "C"){
                    F[i][j] = 2;
                }else{
                    F[i][j] = 1;
                }
            }
        }

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < N; j++){
                B[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < T; i++){
            morning();
            lunch();
            dinner();
        }
    }
}