import java.util.*;
import java.io.*;

public class SolutionRecover {
    static int N;
    static int[][] map;
    static int[][] dist;
    static int minTime;
    static int[] dr = {1, -1, 0, 0};
    static int[] dc = {0, 0, -1, 1};

    static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }

    static void dijkstra(){
        
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            map = new int[N][N];
            dist = new int[N][N];

            for(int i = 0; i < N; i++){
                String line = br.readLine().trim();
                for(int j = 0; j < N; j++){
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            dist[0][0] = 0;

            dijkstra();

            minTime = dist[N - 1][N - 1];
            System.out.println("#" + test_case + " " + minTime);
        }
    }
}
