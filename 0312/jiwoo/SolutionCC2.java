import java.util.*;
import java.io.*;

public class SolutionCC2 {
    static int N;
    static int[][] relation;
    static int[][] shortestDist;
    static int minCC;
    static int[] cc;

    public static void findShortest(){
        for(int k = 0; k < N; k++){
            for(int a = 0; a < N; a++){
                for(int b = 0; b < N; b++){
                    shortestDist[a][b] = Math.min(shortestDist[a][b], shortestDist[a][k] + shortestDist[k][b]);
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            N = Integer.parseInt(st.nextToken());
            shortestDist = new int[N][N];
            relation = new int[N][N];
            minCC = 1000000;
            cc = new int[N];

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    relation[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    if(i == j) continue;
                    if(relation[i][j] == 1) {
                        shortestDist[i][j] = 1;
                    }else{
                        shortestDist[i][j] = 1000000;
                    }
                }
            }

            findShortest();

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    cc[i] += shortestDist[i][j];
                }
            }

            for(int i = 0; i < N; i++){
                minCC = Math.min(minCC, cc[i]);
            }
            
            System.out.println("#" + test_case + " " + minCC);
        }
    }
}
