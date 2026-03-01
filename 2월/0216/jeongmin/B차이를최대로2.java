import java.io.*;
import java.util.*;
/**
 * 매번 sel을 업데이트 하면서 cur 값 전달
 * */
public class B차이를최대로2 {

    static int N;
    static int[] arr;
    static int max;
    static boolean[] visited;
    static int[] sel;

    public static void dfs(int idx, int cur) {
        if(idx==N) {
            max = Math.max(max, cur);
            return;
        }

        for(int i=0; i<N; i++) {
            if(!visited[i]) {
                visited[i] = true;
                sel[idx] = arr[i];
                int next = 0;
                if (sel.length > 1)
                    for (int j = 0; j < idx; j++) {
                        next += Math.abs(sel[j] - sel[j + 1]);
                    }
                dfs(idx + 1, next);
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
        dfs(0, 0);
        System.out.println(max);
    }
}