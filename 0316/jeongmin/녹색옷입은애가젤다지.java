import java.io.*;
import java.util.*;

public class 녹색옷입은애가젤다지 {


    static int N;
    static int[][] grid;
    static int[][] dist;

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Node implements Comparable<Node> {
        int r;
        int c;
        int x; // dist
        public Node(int r, int c, int x) {
            this.r=r;
            this.c=c;
            this.x=x;
        }

        @Override
        public int compareTo(Node o) {
            return this.x-o.x;
        }
    }

    public static boolean isWall(int r, int c) {
        return (r<0 || r>=N || c<0 || c>=N);
    }

    public static void dijkstra(int sr, int sc) {
        dist[sr][sc] = grid[sr][sc];
        PriorityQueue<Node> pq = new PriorityQueue<>();

        pq.add(new Node(sr,sc,grid[sr][sc]));

        while(!pq.isEmpty()) {
            Node cur = pq.poll();

            int r = cur.r;
            int c = cur.c;

            if(cur.x > dist[r][c]) continue;

            if(r==N-1 && c==N-1) {
                return;
            }

            for(int d=0; d<4; d++) {
                int nr = r+dr[d];
                int nc = c+dc[d];

                if(isWall(nr,nc)) continue;

                int newCost = dist[r][c] + grid[nr][nc];
                // 이미 방문된 곳이면 패스
                if(newCost >= dist[nr][nc]) continue;

                dist[nr][nc] = newCost;
                pq.add(new Node(nr,nc,newCost));
            }

        }
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int caseNum = 1;

        while(true) {

            N = Integer.parseInt(br.readLine());

            if(N==0) break;

            grid = new int[N][N];
            dist = new int[N][N];

            StringTokenizer st;
            for(int i=0; i<N; i++) {
                st = new StringTokenizer(br.readLine());
                for(int j=0; j<N; j++) {
                    grid[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i=0; i<N; i++) {
                for(int j=0; j<N; j++) {
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }

            dijkstra(0,0);

            System.out.println("Problem " + caseNum + ": " + dist[N-1][N-1]);
            caseNum++;
        }

    }
}