// 삼성 2024 상반기 오전 1번 고대 문명 유적 탐사
import java.util.*;
import java.io.*;

public class SolutionAncientRelic{
    static int K;
    static int M;
    static int[][] originalArea;
    static int[] wall;
    static int[][] tmpArea;
    static int[][] finalArea;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, -1, 0, 1};
    static int[] relicX;
    static int[] relicY;
    static int sumVal;

    public static void 

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= 5 || c >= 5) return true;
        return false;
    }

    public static void findRelic(){}

    public static void turn90(int r, int c){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tmpArea[i][j] = originalArea[i][j];
            }
        }

        tmpArea[r - 1][c - 1] = originalArea[r + 1][c - 1];
        tmpArea[r - 1][c] = originalArea[r][c - 1];
        tmpArea[r - 1][c + 1] = originalArea[r - 1][c - 1];
        tmpArea[r][c + 1] = originalArea[r - 1][c];
        tmpArea[r + 1][c + 1] = originalArea[r - 1][c + 1];
        tmpArea[r + 1][c] = originalArea[r][c + 1];
        tmpArea[r + 1][c - 1] = originalArea[r + 1][c + 1];
        tmpArea[r][c - 1] = originalArea[r + 1][c];

        findRelic();
    }

    public static void turn180(int r, int c){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tmpArea[i][j] = originalArea[i][j];
            }
        }

        tmpArea[r - 1][c - 1] = originalArea[r + 1][c + 1];
        tmpArea[r - 1][c] = originalArea[r + 1][c];
        tmpArea[r - 1][c + 1] = originalArea[r + 1][c - 1];
        tmpArea[r][c + 1] = originalArea[r][c - 1];
        tmpArea[r + 1][c + 1] = originalArea[r - 1][c - 1];
        tmpArea[r + 1][c] = originalArea[r - 1][c];
        tmpArea[r + 1][c - 1] = originalArea[r - 1][c + 1];
        tmpArea[r][c - 1] = originalArea[r][c + 1];

        findRelic();
    }

    public static void turn270(int r, int c){
        for(int i = 0; i < 5; i++){
            for(int j = 0; j < 5; j++){
                tmpArea[i][j] = originalArea[i][j];
            }
        }

        tmpArea[r - 1][c - 1] = originalArea[r - 1][c + 1];
        tmpArea[r - 1][c] = originalArea[r][c + 1];
        tmpArea[r - 1][c + 1] = originalArea[r + 1][c + 1];
        tmpArea[r][c + 1] = originalArea[r + 1][c];
        tmpArea[r + 1][c + 1] = originalArea[r + 1][c - 1];
        tmpArea[r + 1][c] = originalArea[r][c - 1];
        tmpArea[r + 1][c - 1] = originalArea[r - 1][c - 1];
        tmpArea[r][c - 1] = originalArea[r - 1][c];

        findRelic();
    }

    public static void expedition(){
        for(int i = 1; i < 4; i++){
            for(j = 1; j < 4; j++){ //[i][j] = center of 3x3
                turn90(i, j);
                turn180(i, j);
                turn270(i, j);
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());
        K = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        originalArea = new int[5][5];
        wall = new int[M];

        for(int i = 0; i < 5; i++){
            st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < 5; j++){
                originalArea[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < K; i++){
            sumVal = 0;
            expedition();
            sb.append(sumVal + " ");
        }
        System.out.println(sb.toString());
    }
}