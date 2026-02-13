import java.util.*;
import java.io.*;

class Core{
	int r;
	int c;
	public Core(int r, int c) {
		this.r = r;
		this.c = c;
	}
}

public class SolutionProcessor {
	static int[][] processor;
	static boolean[][] visited;
	static int N;
	static int minLength;
	static int maxCnt;
	static ArrayList<Core> cores = new ArrayList<Core>();
	static int[] dr = {-1, 1, 0, 0};
	static int[] dc = {0, 0, 1, -1};
	
	public static boolean isWall(int r, int c) {
		if(r < 0 || r > N || c < 0 || c > N) {
			return true;
		}else return false;
	}
	
	public static int makeLine(int r, int c, int dr, int dc, int len) {
		if(visited[r][c]) {
			return len;
		}
		return makeLine(r + dr, c + dc, dr, dc, len + 1);
	}
	
	public static void connect(int r, int c, int sum, int cnt) {
		if(isWall(r, c)) {
			minLength = Math.min(sum, minLength);
			return;
		}
		if(visited[r][c]) {
			return;
		}
		
		visited[r][c] = true;
		sum++;
		cnt++;
		for(int i = 0; i < 4; i++) {
			//connect()
		}
	}
	
	public static void main(String[] args) throws Exception{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine().trim());
		
		for(int test_case = 1; test_case <= T; test_case++) {
			N = Integer.parseInt(br.readLine().trim());
			processor = new int[N][N];
			visited = new boolean[N][N];
			maxCnt = 0;
			minLength = Integer.MAX_VALUE;
			StringTokenizer st;
			for(int i = 0; i < N; i++) {
				st = new StringTokenizer(br.readLine().trim());
				for(int j = 0; j < N; j++) {
					processor[i][j] = Integer.parseInt(st.nextToken());
					visited[i][j] = true;
					Core c = new Core(i, j);
					cores.add(c);
				}
			}
			
			Iterator<Core> iter = cores.iterator();
			while(iter.hasNext()) {
				Core curr = iter.next();
				connect(curr.r, curr.c, 0, 0);
			}
		}
	}
}
