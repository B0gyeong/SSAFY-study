import java.util.*;
import java.io.*;

public class SolutionConnectIsland { //swea 하나로
    static int N;
    static double E;
    static long[] islandsX;
    static long[] islandsY;
    static boolean[] islandVisited;
    static long[] minL2s;
    static long minL2;
    static double minCost;
    static long result;

    public static long calculateL2(long x1, long y1, long x2, long y2){
        return (x1 - x2)*(x1 - x2) + (y1 - y2)*(y1 - y2);
    }

    public static void prim(){
        minL2s[0] = 0;
        for(int i = 1; i < N; i++){
            minL2s[i] = Long.MAX_VALUE;
        }

        for(int i = 0; i < N; i++){
            int idx = -1;
            for(int j = 0; j < N; j++){
                if(islandVisited[j]) continue;
                if(idx == -1){
                    idx = j;
                }else{
                    if(minL2s[idx] > minL2s[j]) idx = j;
                }
            }
            
            islandVisited[idx] = true;
            minL2 += minL2s[idx];
            
            for(int j = 0; j < N; j++){
                minL2s[j] = Math.min(minL2s[j], calculateL2(islandsX[idx], islandsY[idx], islandsX[j], islandsY[j]));
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine().trim());
        
        for(int test_case = 1; test_case <= T; test_case++){
            N = Integer.parseInt(br.readLine().trim());
            islandsX = new long[N];
            islandsY = new long[N];
            islandVisited = new boolean[N];
            minL2s = new long[N];
            minL2 = 0;

            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < N; i++){
                islandsX[i] = Long.parseLong(st.nextToken());
            }
            st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < N; i++){
                islandsY[i] = Long.parseLong(st.nextToken());
            }
            E = Double.parseDouble(br.readLine().trim());

            prim();
            minCost = minL2 * E;
            result = Math.round(minCost);
            System.out.println("#" + test_case + " " + result);
        }
    }
}
