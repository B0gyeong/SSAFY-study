import java.util.*;
import java.io.*;

class Node{
    int r;
    int c;
    int dist;

    public Node(int r, int c, int d){
        this.r = r;
        this.c = c;
        dist = d;
    }
}

public class SolutionBabyShark {
    static int N;
    static int sharkSize;
    static int sharkR;
    static int sharkC;
    static int sec;
    static int eatCnt;
    static int[][] arr;
    static boolean[][] visited;
    static int[] dr = {-1, 0, 1, 0}; //상 좌 하 우
    static int[] dc = {0, -1, 0, 1};

    public static boolean canMove(int r, int c){
        if(r < 0 || c < 0 || c >= N || r >= N ) return false;
        if(arr[r][c] > sharkSize && arr[r][c] != 9) return false;
        return true;
    }

    public static boolean bfs(){ //true: 먹을 물고기 찾고 이동 성공. false = 더 이상 먹을 물고기 없음
        int minDist = Integer.MAX_VALUE;
        int bestR = -1;
        int bestC = -1;
        Queue<Node> q = new LinkedList<>();
        visited = new boolean[N][N];

        q.add(new Node(sharkR, sharkC, 0));
        visited[sharkR][sharkC] = true;

        while(!q.isEmpty()){
            Node cur = q.poll();
            if(cur.dist > minDist) break;

            for(int d = 0; d < 4; d++){
                int nr = cur.r + dr[d];
                int nc = cur.c + dc[d];

                if(!canMove(nr, nc) || visited[nr][nc]) continue;

                visited[nr][nc] = true;

                int nextDist = cur.dist + 1;

                q.add(new Node(nr, nc, nextDist));

                if(arr[nr][nc] > 0 && arr[nr][nc] < sharkSize){
                    if(nextDist < minDist ||
                        (nextDist == minDist && nr < bestR) ||
                        (nextDist == minDist && nr == bestR && nc < bestC)
                    ){
                        minDist = nextDist;
                        bestR = nr;
                        bestC = nc;
                    }
                }
            }
        }

        if(minDist == Integer.MAX_VALUE) return false;

        sec += minDist;
        arr[sharkR][sharkC] = 0;
        sharkR = bestR;
        sharkC = bestC;
        arr[bestR][bestC] = 0;

        return true;
    }

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        sharkSize = 2;
        sec = 0;
        arr = new int[N][N];
        eatCnt = 0;

        for(int i = 0; i < N; i++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            for(int j = 0; j < N; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
                if(arr[i][j] == 9){
                    sharkR = i;
                    sharkC = j;
                }
            }
        }

        while(bfs()){
            eatCnt++;
            if(eatCnt == sharkSize){
                sharkSize++;
                eatCnt = 0;
            }
        }

        System.out.println(sec);
    }
}