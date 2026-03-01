
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

public class S1226_미로 {

    static int[] dr = {-1,1,0,0};
    static int[] dc = {0,0,-1,1};

    static class Node {
        int r;
        int c;
        public Node(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public static int find(int[][] map, int sr, int sc) {
        // 16*16 map
        Queue<Node> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[16][16];

        q.offer(new Node(sr, sc));
        visited[sr][sc] = true;

        while(!q.isEmpty()) {
            Node cur = q.poll();
            int r = cur.r;
            int c = cur.c;

            if(map[r][c]==3) return 1;

            for(int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if(nr<0||nr>=16||nc<0||nc>=16) continue;
                if(map[nr][nc]==1) continue;
                if(visited[nr][nc]) continue;

                q.offer(new Node(nr, nc));
                visited[nr][nc] = true;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws Exception{

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        for(int t=1; t<=10; t++) {
            int T = Integer.parseInt(br.readLine());
            int[][] map = new int[16][16];

            // map 입력 받기
            for(int i=0; i<16; i++) {
                String line = br.readLine();
                for(int j=0; j<16; j++) {
                    map[i][j] = line.charAt(j) - '0';
                }
            }

            int answer = 0;

            // map[i][j] == 2 에서 출발
            for(int i=0; i<16; i++) {
                for(int j=0; j<16; j++) {
                    if(map[i][j]==2) {
                        answer = find(map, i, j); // find가 (i,j)에서 출발했을때 도착 가능하면 1 아니면 0 반환
                    }
                }
            }

            System.out.println("#"+T+" "+answer);
        }

    }

}
