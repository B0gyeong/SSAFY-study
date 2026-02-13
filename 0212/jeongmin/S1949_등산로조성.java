
import java.io.*;
import java.util.*;


public class S1949_등산로조성 {
	
	static int[] dr = {-1,1,0,0};
	static int[] dc = {0,0,-1,1};
	static int N;
	static int K;
	static int map[][];
	static int hmax;
	static boolean[][] visited;
	static boolean isCut;
	static int path;
	
	public static void dfs(int r, int c, int curPath) {
		
		// 다음 지점은? 
		for(int d=0; d<4; d++) {
			int nr = r + dr[d];
			int nc = c + dc[d];
			
			// 범위 밖, 방문하지 않은 곳 패스
			if(nr<0||nr>=N||nc<0||nc>=N) continue;
			if(visited[nr][nc]) continue;
			
			// 낮은지형인 경우 
			if(map[nr][nc]<map[r][c]) {
				visited[nr][nc] = true;
				curPath += 1;
				dfs(nr, nc, curPath);
				curPath -= 1;
				visited[nr][nc] = false;
			} else { // 낮은지형이 아닌경우
				// K만큼 깎은 상태가 아니고 깎았을때 현재 자리보다 낮아지면 이동 가능
				// 깎았다면 K만큼 깎고 이동후 K만큼 복구하자
				// 얼마나 깎을건지?( K는 1~5 ) 다해보자
				for(int k=1; k<=K; k++) {
					if(!isCut&&map[nr][nc]-k<map[r][c]) {
						map[nr][nc] -= k;
						isCut = true;
						visited[nr][nc] = true;
						curPath += 1;
						dfs(nr, nc, curPath);
						curPath -= 1;
						map[nr][nc] += k;
						isCut = false;
						visited[nr][nc] = false;
					}
				}
			}
			
		}
		// 더이상 못가면 ? -> 누적한 curPath 을 max로 업데이트하자 ! 
		path = Math.max(path,curPath);
		return;
	}
	
	public static void main(String args[]) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		for(int t=1; t<=T; t++) {
			
			st = new StringTokenizer(br.readLine());
			N = Integer.parseInt(st.nextToken());
			K = Integer.parseInt(st.nextToken()); // 최대 공사 가능 
			map = new int[N][N];
			visited = new boolean[N][N];
			isCut = false;
			path = 0;
			
			for(int i=0; i<N; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					map[i][j] = Integer.parseInt(st.nextToken());
				}
			}
			
			hmax = 0;
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					hmax = Math.max(hmax, map[i][j]);
				}
			}
			
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					if(map[i][j]==hmax) {
						visited[i][j] = true;
						dfs(i, j, 1);
						visited[i][j] = false;
					}
				}
			}
			
			System.out.println("#"+t+" "+path);
		}
	}

}





