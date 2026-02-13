
import java.io.*;
import java.util.*;


public class S4014_활주로건설 {
	
	static int[][] map;
	static int[][] mapTran; // 행열 반전 
	static int N; 
	static int X;
	static int ans; // 건설 가능한 경우의 수 
	
	// r번째 행의 ans 세기 
	public static boolean findRow(int r, int[][] map) {
		boolean[] used = new boolean[N]; // 활주로 사용 
		
		for(int c=0; c<N-1; c++) {
			int cur = map[r][c];
			int next = map[r][c+1];
			
			if(cur==next) continue;
			if(Math.abs(cur-next)>1) return false;
			if(next==cur+1) {
				// 오르막
				// 뒤로 X칸이 범위 안인지 검사 
				for(int k=0; k<X; k++) {
					int idx = c-k;
					if(idx<0) return false;
					if(used[idx]) return false; // 경사로로 사용한 칸 
					if(map[r][idx]!=cur) return false;
				}
				for(int k=0; k<X; k++) used[c-k] = true;
				
			} else if(next+1==cur) {
				// 내리막
				// 앞으로 X칸이 범위 안인지 검사
				for(int k=1; k<=X; k++) {
					int idx = c+k;
					if(idx>=N) return false;
					if(used[idx]) return false;
					if(map[r][idx]!=next) return false;
				}
				for(int k=1; k<=X; k++) used[c+k] = true;
			}
		}
		
		return true;
	}
	
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			map = new int[N][N];
			mapTran = new int[N][N];
			X = Integer.parseInt(st.nextToken()); // 경사로 길이 
			ans = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken()); // 구간의 높이 
					mapTran[j][i] = map[i][j];
				}
			}
			
			for(int i=0; i<N; i++) {
				if(findRow(i, map)) ans++;
				if(findRow(i, mapTran)) ans++;
			}
			
			System.out.println("#"+t+" "+ans);
		}

	}
}




