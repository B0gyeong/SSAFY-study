import java.io.*;
import java.util.*;


public class Solution {
	
	// 0:우 1:상 2:좌 3:하
	static int[] dx = {1,0,-1,0};
	static int[] dy = {0,-1,0,1};
	
	static boolean[][] visited = new boolean[101][101];
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			int x = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int d = Integer.parseInt(st.nextToken());
			int g = Integer.parseInt(st.nextToken());
			
			List<Integer> directions = new ArrayList<>();
			directions.add(d);
			
			visited[x][y] = true;
			x += dx[d];
			y += dy[d];
			visited[x][y] = true;
			
			while(g-->0) {
				
				int size = directions.size();
				
				// 끝점부터 s
				for(int j = size-1; j>=0; j--) {
					int prevD = directions.get(j);
					int nextD = (prevD + 1) % 4;
					
					x += dx[nextD];
					y += dy[nextD];
					visited[x][y] = true;
					
					directions.add(nextD);
					
				}
				
				
			}
		}
		
		int count = 0;
		for(int i=0; i<100; i++) {
			for(int j=0; j<100; j++) {
				if(visited[i][j] && visited[i+1][j] && visited[i][j+1] && visited[i+1][j+1] ) {
					count++;
				}
			}
		}
		
		System.out.println(count);
		
	}
}
