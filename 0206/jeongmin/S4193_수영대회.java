import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S4193_수영대회 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Node{
        int r;
        int c;
        int t;
        public Node(int r, int c, int t) {
            this.r = r;
            this.c = c;
            this.t = t;
        }

    }

    public static int bfs(int[][] map, int sr, int sc, int er, int ec, int N) {
        Queue<Node> q = new ArrayDeque<>();
        boolean[][][] visited = new boolean[N][N][3];

        q.offer(new Node(sr,sc,0));
        visited[sr][sc][0] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int t = cur.t;

            if(r==er&&c==ec) {
                return cur.t;
            }

            int nt = t+1;

            if(!visited[r][c][nt%3]) {
                q.offer(new Node(r,c,nt));
                visited[r][c][nt%3] = true;
            }

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr<0||nr>=N||nc<0||nc>=N) continue;
                if(visited[nr][nc][nt%3]) continue;
                if(map[nr][nc]==1) continue;
                if(map[nr][nc]==2 && t%3!=2) continue;

                q.offer(new Node(nr,nc,nt));
                visited[nr][nc][nt%3] = true;


            }
        }
        return -1;
    }

    public static void main(String[] args) throws Exception{


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());

        for(int t=1; t<=T; t++) {
            int N = Integer.parseInt(br.readLine());
            int[][] map = new int[N][N];


            // map 입력
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            st = new StringTokenizer(br.readLine());
            int sr = Integer.parseInt(st.nextToken());
            int sc = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int er = Integer.parseInt(st.nextToken());
            int ec = Integer.parseInt(st.nextToken());

            int answer = 0;
            // 최단거리를 찾으면서 거리 리턴하기
            answer = bfs(map, sr, sc, er, ec, N);
            System.out.println("#"+t+" "+answer);
        }
    }

}