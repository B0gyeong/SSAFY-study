import java.util.*;

public class 빙산 {
    static int [] dr = {-1, 1, 0, 0};
    static int [] dc = {0, 0, -1, 1};
    static int N;
    static int M;
    static int [][] matrix;
    static class Node{
        int r, c;
        Node(int r, int c){
            this.r = r; this.c = c;
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        M = sc.nextInt();
        matrix = new int [N][M];
        for(int r=0; r<N; r++) {
            for(int c=0; c<M; c++) {
                matrix[r][c] = sc.nextInt();
            }
        }
        
        int cnt = 0;
        while(true) {
        	int landCount = countLand();
        	if(landCount > 1) {
        		break;
        	}
        	if (landCount == 0) {      // 다 녹을 때까지 분리 안 되면 0 출력
        		cnt = 0;
                break;
            }
            Melt();
            cnt++;
        }
        System.out.println(cnt);
    }

	private static void Melt() {
	    int [][] tempMatrix = new int [N][M];
	    
	    for(int r=0; r<N; r++) {
	        for(int c=0; c<M; c++) {
	            if(matrix[r][c] == 0) {
	                tempMatrix[r][c] = 0;
	            } else {
	                int meltN = 0;
	                for(int d=0; d<4; d++) {
	                    int nr = r + dr[d];
	                    int nc = c + dc[d];
	                    if(isBeach(nr,nc)) {
	                        meltN++;
	                    }
	                }
	                tempMatrix[r][c] = Math.max(0, matrix[r][c]-meltN);
	            } 
	        }
	    }
	    matrix = tempMatrix;
	}

	private static int countLand() {
		int land = 0;
		boolean [][] visited = new boolean [N][M];
	    for(int r=0; r<N; r++) {
	    	for(int c=0; c<M; c++) {
	    		if(matrix[r][c] > 0 && !visited[r][c]) {
	    			Queue<Node> q = new LinkedList<>();
	    			q.add(new Node(r,c));
	    			visited[r][c] = true;
	    			while (!q.isEmpty()) {
	    				Node curr = q.poll();
	    				for(int d=0; d<4; d++) {
	    					int nr = curr.r+dr[d];
	    					int nc = curr.c+dc[d];
	    					if(0 <= nr && nr< N && 0 <= nc && nc< M && matrix[nr][nc] > 0 && !visited[nr][nc]) {
	    						q.add(new Node(nr,nc));
	    						visited[nr][nc] = true;
	    					}
	    				}
	    			}
	    			land++;
	    		}
	    	}
	    }
	    return land;
	}

	private static boolean isBeach(int nr, int nc) {
	    return 0 <= nr && nr< N && 0 <= nc && nc< M && matrix[nr][nc] == 0;
	}
}