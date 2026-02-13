import java.util.*;
import java.io.*;

class Top{
	int r;
	int c;
	
	public Top(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class SolutionMountainPath {
	static int N;
	static int K;
	static int[][] map;
	static int highest;
	static ArrayList<Top> top;
	static boolean[][] visited;
	static int maxLength;
	
	public static boolean isWall(int r, int c) {
		if(r < 0 || r >= N || c < 0 || c >= N) {
			return true;
		}
		return false;
	}
	
	public static void findPath(int r, int c, boolean paved, int cnt) {
		if(visited[r][c] == true) {
			return;
		}
		int top;
		int bottom;
		int left;
		int right;
		int curr = map[r][c];
		visited[r][c] = true;
		maxLength = Math.max(cnt, maxLength);
		
		if(!isWall(r - 1, c)) {
			bottom = map[r - 1][c];
			if(bottom < curr) {
				findPath(r - 1, c, paved, cnt + 1);
			}else {
				if(!paved) {
					if(bottom - K < curr) {
						map[r-1][c] = curr - 1;
						findPath(r - 1, c, true, cnt + 1);
						map[r-1][c] = bottom;
					}
				}
			}
		}
		if(!isWall(r + 1, c)) {
			top = map[r + 1][c];
			if(top < curr) {
				findPath(r + 1, c, paved, cnt + 1);
			}else {
				if(!paved) {
					if(top - K < curr) {
						map[r+1][c] = curr - 1;
						findPath(r + 1, c, true, cnt + 1);
						map[r+1][c] = top;
					}
				}
			}
		}
		if(!isWall(r, c - 1)) {
			left = map[r][c - 1];
			if(left < curr) {
				findPath(r, c - 1, paved, cnt + 1);
			}else {
				if(!paved) {
					if(left - K < curr) {
						map[r][c-1] = curr - 1;
						findPath(r, c - 1, true, cnt + 1);
						map[r][c-1] = left;
					}
				}
			}
		}
		if(!isWall(r, c + 1)) {
			right = map[r][c + 1];
			if(right < curr) {
				findPath(r, c + 1, paved, cnt + 1);
			}else {
				if(!paved) {
					if(right - K < curr) {
						map[r][c+1] = curr - 1;
						findPath(r, c + 1, true, cnt + 1);
						map[r][c+1] = right;
					}
				}
			}
		}
		
		visited[r][c] = false;
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			StringTokenizer st = new StringTokenizer(br.readLine().trim());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			highest = 0;
			top = new ArrayList<Top>();
			visited = new boolean[N][N];
			maxLength = 0;
			
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 0; j < N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
					highest = Math.max(highest, map[i][j]);
				}
			}
			
			for(int i = 0; i < N; i++) {
				for(int j = 0; j < N; j++) {
					if(map[i][j] == highest) {
						Top t = new Top(i, j);
						top.add(t);
					}
				}
			}
			
			Iterator<Top> iter = top.iterator();
			Top t;
			
			while(iter.hasNext()) {
				t = iter.next();
				findPath(t.r, t.c, false, 1);
			}
			
			System.out.println("#" + test_case + " " + maxLength);
		}
	}
}