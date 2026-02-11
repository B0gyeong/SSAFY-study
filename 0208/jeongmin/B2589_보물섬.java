
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class B2589_보물섬 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Node {
        int r;
        int c;
        int dist;
        public Node(int r, int c, int dist) {
            this.r = r;
            this.c = c;
            this.dist = dist;
        }
    }

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];

        // map 입력
        for(int i=0; i<N; i++) {
            String line = br.readLine();
            for(int j=0; j<M; j++) {
                char tmp = line.charAt(j); // W or L
                if(tmp=='W') {
                    map[i][j] = 1;
                } else if(tmp=='L') {
                    map[i][j] = 0;
                }
            }
        }

        int max = 0;
        // 보물은 서로 간에 최단거리로 이동하는데 있어 가장 긴 시간이 걸리는 육지 두곳에 있음
        // 육지(L) 바다(W) - 육지로만 이동가능
        for(int i=0; i<N; i++) {
            for(int j=0; j<M; j++) {
                if(map[i][j]==0) {
                    int cur = findMaxDist(map, i, j);
                    if(cur>max) max=cur;
                }
            }
        }

        System.out.println(max);

    }

    public static int findMaxDist(int[][] map, int sr, int sc) {
        int N = map.length;
        int M = map[0].length;
        boolean[][] visited = new boolean[N][M];
        Queue<Node> q = new ArrayDeque<>();

        q.offer(new Node(sr,sc,0));
        visited[sr][sc] = true;

        int max = 0;
        while(!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;
            int dist = cur.dist;

            max = Math.max(max, dist);

            for(int d=0; d<4; d++) {
                int nr = r + dr[d];
                int nc = c + dc[d];

                if(nr<0||nr>=N||nc<0||nc>=M) continue;
                if(visited[nr][nc]) continue;
                if(map[nr][nc]==1) continue;

                q.offer(new Node(nr,nc,dist+1));
                visited[nr][nc] = true;
            }
        }

        return max;
    }

}