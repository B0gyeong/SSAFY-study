import java.io.*;
import java.util.*;
/**
 * sel을 새로 만들지 않고 매개변수로 백트래킹
 * */
public class B차이를최대로1 {

    static int N;
    static int[] arr;
    static int max;
    static boolean[] visited;
    static int[] sel;

    public static void dfs(int idx, int prev, int cur) {
        if(idx==N) {
            max = Math.max(max, cur);
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                if(idx==0) {
                    dfs(idx+1, arr[i], 0);
                } else {
                    int nextPrev = arr[i];
                    int nextCur = cur + Math.abs(prev-arr[i]);
                    dfs(idx+1, nextPrev, nextCur);
                }
                visited[i] = false;
            }
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0; i<N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        max = Integer.MIN_VALUE;
        visited = new boolean[N];
        sel = new int[N];
        dfs(0, 0, 0);
        System.out.println(max);
    }
}