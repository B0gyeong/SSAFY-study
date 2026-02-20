import java.util.*;

public class Main {
	static int N;
	static int M;
	static int d;
	static int cnt;
	static int [] dr = {-1, 0, 1, 0};
	static int [] dc = {0, 1, 0, -1};
	static int [][] matrix;
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	M = sc.nextInt();
    	int str = sc.nextInt();
    	int stc = sc.nextInt();
    	d = sc.nextInt();
    	
    	matrix = new int [N][M];
    	for(int r=0; r<N; r++) {
    		for(int c=0; c<M; c++) {
    			matrix[r][c] = sc.nextInt();
    		}
    	}
    	
    	cnt = 0;
    	solve(str, stc);
    	System.out.println(cnt);
	}
    
	private static void solve(int r, int c) {
		// 1. 현재 위치가 청소되지 않은 경우(0), 현재 위치를 청소(2)한다.
		if(matrix[r][c] == 0) {
			matrix[r][c] = 2;
			cnt++;
		} 
		
		// 2. 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 탐색
		for(int i=0; i<4; i++) {
			// 왼쪽 방향으로 회전: (d+3)%4는 북(0) -> 서(3) -> 남(2) -> 동(1) 순서
			d = (d+3)%4;
			int nr = r + dr[d];
      int nc = c + dc[d];
      
			// 지도 범위 안이고, 청소되지 않은 빈칸(0)이 있다면 해당 방향으로 이동
			if(!isWall(nr,nc) && matrix[nr][nc]==0) {
				solve(nr,nc);
				return; // 한 방향이라도 이동했다면 더 이상 회전하지 않고 이동한 곳에서 다시 시작
			} 
		}
		
		// 3. 네 방향 모두 청소가 이미 되어있거나 벽인 경우
    // 바라보는 방향을 유지한 채로 한 칸 후진 시도
		int backD = (d + 2) % 4;  // 반대 방향 계산
			int br = r + dr[backD];
			int bc = c + dc[backD];

			if (!isWall(br, bc) && matrix[br][bc] != 1) {
					solve(br, bc);
			}
	}

	private static boolean isWall(int nr, int nc) {
		return nr<0 || nr>=N || nc<0 || nc>=M;
	}
}