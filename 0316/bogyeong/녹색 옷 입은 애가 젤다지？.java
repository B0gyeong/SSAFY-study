import java.io.*;
import java.util.*;

public class Main {
	static int N;
	static int [] dr = {-1,1,0,0};
	static int [] dc = {0,0,-1,1};
	static int [][] map, minDistMap;
	static boolean [][] visited;
	static class Node implements Comparable<Node>{
		int r, c, cost;
		Node(int r, int c, int cost){
			this.r = r;
			this.c = c;
			this.cost = cost;
		}
		@Override
		public int compareTo(Node o) {
			return this.cost - o.cost;
		}
	}
	public static void main(String args[]) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int cnt = 0;
		
        while(true) {
        	N = Integer.parseInt(br.readLine());
        	if(N == 0) break;
        	cnt++;
        	
        	map = new int [N][N];
        	for(int i=0; i<N; i++) {
        		StringTokenizer st = new StringTokenizer(br.readLine());
        		for(int j=0; j<N; j++) {
        			map[i][j] = Integer.parseInt(st.nextToken());
        		}
        	}
        	
        	minDistMap = new int [N][N];
        	for(int i=0; i<N; i++) Arrays.fill(minDistMap[i], Integer.MAX_VALUE);
        	visited = new boolean [N][N];
        	Dijkstra();
        	System.out.println("Problem "+cnt+": "+minDistMap[N-1][N-1]);
        	
        }
		
	}
	private static void Dijkstra() {
		PriorityQueue<Node> pq = new PriorityQueue<>();
		
		minDistMap[0][0] = map[0][0];
		pq.add(new Node(0, 0, minDistMap[0][0]));

		while(!pq.isEmpty()) {
			Node curr = pq.poll();
			int r = curr.r;
			int c = curr.c;
			
			if(visited[r][c]) continue;
			visited[r][c] = true;
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr<0||nr>=N||nc<0||nc>=N) continue;
				
				if(minDistMap[r][c]+map[nr][nc] < minDistMap[nr][nc]) {
					minDistMap[nr][nc] = minDistMap[r][c]+map[nr][nc];
					pq.add(new Node(nr,nc,minDistMap[nr][nc]));
				}
					
			}
		}
		
		
		
	}
}
