
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class B1987_알파벳 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};
    static int R;
    static int C;
    static char[][] arr;
    static boolean[] used;
    static int max;
    public static void dfs(int r, int c, int dist) {
        // 매번 dfs할때마다 갱신
        // 더이상 갈수 없을때까지
        max = Math.max(max, dist);

        for(int d=0; d<4; d++) {
            int nr = r + dr[d];
            int nc = c + dc[d];
            if(nr<0||nr>=R||nc<0||nc>=C) continue;
            if(used[arr[nr][nc]-'A']) continue;
            used[arr[nr][nc]-'A'] = true;
            dfs(nr,nc,dist+1);
            used[arr[nr][nc]-'A'] = false;
        }
    }
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken()); // 세로 (행)
        C = Integer.parseInt(st.nextToken()); // 가로 (열)
        arr = new char[R][C];
        for(int i=0; i<R; i++) {
            String line = br.readLine();
            for(int j=0; j<C; j++) {
                arr[i][j] = line.charAt(j);
            }
        }
        max = 1;
        used = new boolean[26];
        int start = arr[0][0] - 'A'; // 0~25
        used[start] = true;
        dfs(0,0,1);
        System.out.println(max);
    }
}