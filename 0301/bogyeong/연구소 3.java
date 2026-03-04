import java.util.*;

public class Main {
	static int N, M, minTime, emptySpaces;
	static int [] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, -1, 1};
	static int[][] map;
	static List<Node> virusList;
	static Node [] activeVirus;
	static class Node {
        int r, c, time;
        Node(int r, int c, int time) {
            this.r = r;
            this.c = c;
            this.time = time;
        }
    }
	
    public static void main(String args[]) throws Exception {
        Scanner sc = new Scanner(System.in);
		N = sc.nextInt();
		M = sc.nextInt();
		map = new int[N][N];
		activeVirus = new Node[M];
		emptySpaces = 0;
		minTime = Integer.MAX_VALUE;
		
		virusList = new ArrayList<>();
		
		for (int r = 0; r < N; r++) {
            for (int c = 0; c < N; c++) {
                map[r][c] = sc.nextInt();
                if (map[r][c] == 2) virusList.add(new Node(r, c, 0));
                if (map[r][c] == 0) emptySpaces++;
            }
        }
		
		// 빈칸이 하나도 없다면 퍼뜨릴 필요 없이 0초
        if (emptySpaces == 0) {
            System.out.println(0);
            return;
        }
        
		combine(0, 0);
		
		System.out.println(minTime == Integer.MAX_VALUE ? -1 : minTime);
	}

	private static void combine(int cnt, int start) {
		if (cnt == M) {
            bfs(emptySpaces);
            return;
        }
		
		for (int i = start; i < virusList.size(); i++) {
            activeVirus[cnt] = virusList.get(i);
            combine(cnt + 1, i + 1);
        }
	}

	private static void bfs(int remains) {
		Queue<Node> q = new LinkedList<>();
		boolean[][] visited = new boolean[N][N];
		
		for (Node v : activeVirus) {
			visited[v.r][v.c] = true;
            q.add(v);
        }
		
		int time = 0;
		while (!q.isEmpty()) {
            Node curr = q.poll();

            for (int i = 0; i < 4; i++) {
                int nr = curr.r + dr[i];
                int nc = curr.c + dc[i];

                if (nr < 0 || nc < 0 || nr >= N || nc >= N || visited[nr][nc] || map[nr][nc] == 1) continue;

                if (map[nr][nc] == 0) {
                    remains--;
                }

                visited[nr][nc] = true;
                q.add(new Node(nr, nc, curr.time + 1));

                if (remains == 0) {
                    minTime = Math.min(minTime, curr.time + 1);
                    return;
                }
            }
        }
	}
}