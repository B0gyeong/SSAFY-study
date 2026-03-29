import java.util.*;
import java.io.*;

class Node implements Comparable<Node>{
    int r;
    int c;
    int cost;

    public Node(int r, int c, int cost){
        this.r = r;
        this.c = c;
        this.cost = cost;
    }

    public int compareTo(Node o){
        return this.cost - o.cost;
    }
}

public class SolutionJelda{
    static int N;
    static int[][] map;
    static int[][] dist;
    static final int[] dr = {-1, 1, 0, 0};
    static final int[] dc = {0, 0, -1, 1};

    public static boolean isWall(int r, int c){
        if(r < 0 || c < 0 || r >= N || c >= N) return true;
        return false;
    }

    public static void dijkstra(){
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        pq.offer(new Node(0,0,0));

        while(!pq.isEmpty()){
            Node curr = pq.poll();
            if(curr.cost > dist[curr.r][curr.c]) continue;

            for(int i = 0; i < 4; i++){
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];
                if(!isWall(nr, nc)){
                    int newCost = curr.cost + map[nr][nc];
                    if(newCost < dist[nr][nc]){
                        dist[nr][nc] = newCost;
                        Node next = new Node(nr, nc, newCost);
                        pq.offer(next);
                    }
                }
            }
        }
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        int tc = 1;
        while(N > 0){
            map = new int[N][N];
            dist = new int[N][N];

            for(int i = 0; i < N; i++){
                StringTokenizer st = new StringTokenizer(br.readLine().trim());
                for(int j = 0; j < N; j++){
                    map[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            for(int i = 0; i < N; i++){
                for(int j = 0; j < N; j++){
                    dist[i][j] = Integer.MAX_VALUE;
                }
            }
            dist[0][0] = 0;

            dijkstra();

            System.out.println("Problem " + tc + ": " + dist[N-1][N-1]);
            tc++;
        }
        
    }
}