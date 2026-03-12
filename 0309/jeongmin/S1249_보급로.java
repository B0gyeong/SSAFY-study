package samsung01;

import java.util.*;
import java.io.*;

public class S1249_보급로 {
	
	static boolean[][] visited;
	static int[][] dist;
	static int[][] map;
	static int N;
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	
	public static void dijkstra(int sr, int sc) {
		
		visited[sr][sc] = true;
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				dist[i][j] = Integer.MAX_VALUE;
			}
		}
		
		dist[sr][sc]=0;
		dist[sr+1][sc] = map[sr+1][sc];
		dist[sr][sc+1] = map[sr][sc+1];
		
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
			
				int xidx = -1;
				int yidx = -1;
				int min = Integer.MAX_VALUE;
					
				for(int x=0; x<N; x++) {
					for(int y=0; y<N; y++) {
						if(!visited[x][y]&&dist[x][y]<min) {
							min = dist[x][y];
							xidx = x;
							yidx = y;
						}
					}
				}
				
				if(xidx==-1) continue;
				
				visited[xidx][yidx] = true;
				
				
				for(int d=0; d<4; d++) {
					int nextR = xidx+dr[d];
					int nextC = yidx+dc[d];
					
					if(!isWall(nextR,nextC)&&!visited[nextR][nextC]) {
						dist[nextR][nextC] = Math.min(dist[nextR][nextC], dist[xidx][yidx]+map[nextR][nextC]);
					}
				}
				
			}
		}
		
	
	}
	
	public static boolean isWall(int r, int c) {
		return (r<0||r>=N||c<0||c>=N);
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			map = new int[N][N];
			
			for(int i=0; i<N; i++) {
				String line = br.readLine();
				for(int j=0; j<N; j++) {
					map[i][j] = line.charAt(j) - '0';
				}
			}
			
			visited = new boolean[N][N];
			dist = new int[N][N];
			
			dijkstra(0,0);
			
			System.out.printf("#%d %d\n",t ,dist[N-1][N-1]);
		}
		
	}
}
