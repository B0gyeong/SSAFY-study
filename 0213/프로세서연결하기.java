import java.util.*;
public class 프로세서연결하기 {
	static int N;
	static int coreNum;
	static int minLen;
	static int maxCore;
	static int [] dr = {-1, 1, 0, 0};
	static int [] dc = {0, 0, -1, 1};
	static boolean [] coreSelectBool;
	static int [][] matrix;
	static List<Node> coreList;
	static List<Node> selectCore;
	static class Node{
		int r, c;
		Node(int r, int c){
			this.r = r; this.c = c;
		}
	}
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for(int tc=1; tc<=T ; tc++) {
        	N = sc.nextInt();
        	matrix = new int [N][N];
        	
        	coreNum = 0;
        	coreList = new ArrayList<>();
        	for(int r=0; r<N; r++) {
        		for(int c=0; c<N; c++) {
        			matrix[r][c] = sc.nextInt();
        			if(matrix[r][c] == 1) {
        				coreNum++;
        				coreList.add(new Node(r,c));
        			}
        		}
        	}
        	coreSelectBool = new boolean [coreNum];
        	
        	minLen = Integer.MAX_VALUE;
        	maxCore = Integer.MIN_VALUE;
        	
        	dfs(0, 0, 0);
        	System.out.println("#"+tc+" "+minLen);
        }
    }

    static void dfs(int idx, int connected, int len) {
        if (connected + (coreList.size() - idx) < maxCore) return;

        if (idx == coreList.size()) {
            if (connected > maxCore) {
                maxCore = connected;
                minLen = len;
            } else if (connected == maxCore) {
                minLen = Math.min(minLen, len);
            }
            return;
        }

        Node curr = coreList.get(idx);

        if (curr.r == 0 || curr.c == 0 || curr.r == N-1 || curr.c == N-1) {
            dfs(idx + 1, connected + 1, len);
            return;
        }

        for (int d = 0; d < 4; d++) {
            if (canConnect(curr, d)) {
                int wireLen = connect(curr, d);
                dfs(idx + 1, connected + 1, len + wireLen);
                disconnect(curr, d);
            }
        }

        dfs(idx + 1, connected, len);
    }

	private static boolean canConnect(Node curr, int d) {
		int nr = curr.r + dr[d];
		int nc = curr.c + dc[d];
		
		while(true) {
			if(isWall(nr,nc)) return true;
			if(matrix[nr][nc] != 0) return false;
			nr = nr + dr[d];
			nc = nc + dc[d];
		}

	}

	private static int connect(Node curr, int d) {
		int nr = curr.r + dr[d];
		int nc = curr.c + dc[d];
		int cnt = 0;
		
		while(true) {
			if(isWall(nr,nc)) break;
			matrix[nr][nc] = 2;
			cnt++;
			nr = nr + dr[d];
			nc = nc + dc[d];
		}
		
		return cnt;
	}

	private static void disconnect(Node curr, int d) {
		int nr = curr.r + dr[d];
		int nc = curr.c + dc[d];
		
		while(true) {
			if(isWall(nr,nc)) break;
			matrix[nr][nc] = 0;
			nr = nr + dr[d];
			nc = nc + dc[d];
		}
		
	}
	
	private static boolean isWall(int nr, int nc) {
		return nr<0 || nr>=N || nc<0 || nc>=N;
	}
}

