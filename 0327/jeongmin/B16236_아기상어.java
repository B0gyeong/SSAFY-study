import java.io.*;
import java.util.*;

public class B16236_아기상어 {

	static int N;
	static int[][] grid;
	static Shark baby;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	static class Shark {
		int r;
		int c;
		int size;
		int eat;
		
		public Shark(int r, int c, int size, int eat) {
			this.r = r;
			this.c = c;
			this.size = size;
			this.eat = eat;
		}
	}
	
	static class Fish implements Comparable<Fish>{
		int r;
		int c;
		int dist;
		
		public Fish(int r, int c, int dist) {
			this.r = r;
			this.c = c;
			this.dist = dist;
		}

		@Override
		public int compareTo(Fish o) {
			// TODO Auto-generated method stub
			return this.dist - o.dist;
		}
	}
	
	public static int bfs(int er, int ec) {
		
		boolean[][] visited = new boolean[N][N];
		Queue<Fish> q = new ArrayDeque<>();
		q.add(new Fish(baby.r, baby.c, 0));
		visited[baby.r][baby.c] = true;
		
		while(!q.isEmpty()) {
			Fish cur = q.poll();
			int r = cur.r;
			int c = cur.c;
			int dist = cur.dist;
			
			if(r==er && c==ec) {
				return dist;
			}
			
			for(int d=0; d<4; d++) {
				int nr = r + dr[d];
				int nc = c + dc[d];
				
				if(nr<0||nr>=N||nc<0||nc>=N) continue;
				if(visited[nr][nc]) continue;
				if(grid[nr][nc] <= baby.size) {
					q.offer(new Fish(nr,nc,dist+1));
					visited[nr][nc] = true;
				}
			}
			
		}
		return -1;
		
		
	}
	
	public static void main(String[] args)throws Exception {

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		N = Integer.parseInt(br.readLine());
		grid = new int[N][N];
		
		StringTokenizer st;
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				grid[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		
		int babyR=-1, babyC=-1;
		// 아기상어 위치 저장 
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				if(grid[i][j] == 9) {
					babyR = i;
					babyC = j;
					// 아기 상어 위치를 0으로 
					grid[i][j] = 0;
				}
				
			}
		}
		baby = new Shark(babyR, babyC, 2, 0);
		
		// 먹을수 있는 물고기 
		PriorityQueue<Fish> pq = new PriorityQueue<>();
		int time = 0;
		
		while(true) {
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(grid[i][j] !=0 && grid[i][j] < baby.size) {
						// 1. 도달 가능하지 않은 경우 -1이 리턴됨 이 경우는 넣으면 안됨 
						int dist = bfs(i,j);
						if(dist != -1) {
							pq.add(new Fish(i, j, dist));
						}
					}
				}
			}
			
			// 더이상 먹을 물고기가 없다면 
			if(pq.isEmpty()) break;
			
			// 먹을 물고기
			Fish eat = pq.poll();
			
			baby.eat++;
			baby.r = eat.r;
			baby.c = eat.c;
			time += eat.dist;
			
			// 2. 먹은 물고기 처리!!
			grid[eat.r][eat.c] = 0;
			
			
			if(baby.eat == baby.size) {
				baby.size++;
				// 3. 커진 뒤 먹은 횟수는 다시 0으로
				baby.eat = 0;
			}
			
			while(!pq.isEmpty()) {
				pq.poll();
			}
			
		}
		
		System.out.println(time);
		
	}
}
