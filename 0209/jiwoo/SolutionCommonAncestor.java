import java.util.*;
import java.io.*;

public class Solution{
    static BufferedReader br;
    static StringTokenizer st;
    static int V;
    static int E;
    static int V1;
    static int V2;
    static int[] parent;
    static int[][] child;
    static int cnt;
    static int commonIdx;

    public static int nextInt() throws Exception{
        if(st == null || !st.hasMoreTokens()){
            st = new StringTokenizer(br.readLine());
        }
        return Integer.parseInt(st.nextToken());
    }

    public static void dfs(int idx){
        cnt++;
        if(child[idx][0] == 0){
            return;
        }else{
            if(child[idx][1] != 0){
                dfs(child[idx][1]);
            }
            dfs(child[idx][0]);
        }
    }

    public static void main(String[] args) throws Exception{
        br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine().trim());

        for(int test_case = 1; test_case <= T; test_case++){
            st = new StringTokenizer(br.readLine().trim());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            V1 = Integer.parseInt(st.nextToken());
            V2 = Integer.parseInt(st.nextToken());

            parent = new int[10001];
            child = new int[10001][2];

            boolean[] visited = new boolean[V + 1];

            cnt = 0;
            commonIdx = 0;
            
            for(int i = 0; i < E; i++){
                int parentVal = nextInt();
                int childVal = nextInt();

                parent[childVal] = parentVal;
                if(child[parentVal][0] == 0){
                    child[parentVal][0] = childVal;
                }else{
                    child[parentVal][1] = childVal;
                }
            }

            int x1 = V1;
            visited[x1] = true;
            
            while(parent[x1] != 0){
                x1 = parent[x1];
                visited[x1] = true;
            }

            int x2 = V2;
            while(x2 != 0){
                if(visited[x2]){
                    commonIdx = x2;
                    break;
                }
                x2 = parent[x2];
            }

            dfs(commonIdx);

            System.out.println("#" + test_case + " " + commonIdx + " " + cnt);
        }
    }
}