import java.util.*;

public class Main {
	static int N, M;
	static int [] dr = {0, 0, -1, -1, -1, 0, 1, 1, 1};
	static int [] dc = {0, -1, -1, 0, 1, 1, 1, 0, -1};
	static int [][] rains;
	static class Move {
		int d, s;
		Move (int d, int s) {
			this.d = d; this.s = s;
		}
	}
	static class Node {
		int r, c;
		Node (int r, int c) {
			this.r = r; this.c = c;
		}
	}
    public static void main(String[] args){
    	Scanner sc = new Scanner(System.in);
    	N = sc.nextInt();
    	M = sc.nextInt();
    	rains = new int [N+1][N+1];
    	
    	
    	for(int r=1; r<=N; r++) {
    		for(int c=1; c<=N; c++) {
    			rains[r][c] = sc.nextInt();
    		}
    	}
    	
    	List <Move> moves = new ArrayList<>();
    	for(int i=0; i<M; i++) {
			int d = sc.nextInt();
			int s = sc.nextInt();
			moves.add(new Move(d, s));
		}
    	
    	Queue <Node> q = new LinkedList<>();
    	q.add(new Node(N-1, 1));
    	q.add(new Node(N-1, 2));
    	q.add(new Node(N, 1));
    	q.add(new Node(N, 2));
    	
    	for(int i=0; i<M; i++) {
    		Move move = moves.get(i);
    		boolean [][] visited = new boolean [N+1][N+1];
    		while(!q.isEmpty()) {
    			Node curr = q.poll();
    			Node next =  moveF(move, curr);
    			
    			visited[next.r][next.c] = true;
    			rains[next.r][next.c]++;

    		}
    		
    		for(int r=1; r<=N; r++) {
        		for(int c=1; c<=N; c++) {
        			if(visited[r][c]) {
        				for(int d=2; d<=8; d+=2) {
        					int nr = r + dr[d];
        					int nc = c + dc[d];
        					if(nr<1 || nr>N || nc<1 || nc>N) continue;
        					if(rains[nr][nc]>0) rains[r][c]++;
        				}
        			}
        		}
        	}
    		
    		
    		for(int r=1; r<=N; r++) {
        		for(int c=1; c<=N; c++) {
        			if(rains[r][c]>=2 && !visited[r][c]) {
        				q.add(new Node(r,c));
        				rains[r][c]-=2;
        			}
        		}
        	}
    	}
    	
    	int sum = 0;
    	for(int r=1; r<=N; r++) {
    		for(int c=1; c<=N; c++) {
    			if(rains[r][c]> 0) sum+=rains[r][c];
    		}
    	}
    	System.out.println(sum);
    	
	}
	private static Node moveF(Move move, Node curr) {
		int nr = curr.r + dr[move.d]*move.s;
		int nc = curr.c + dc[move.d]*move.s;
		
		if(nr>N) nr = nr%N;
		if(nc>N) nc = nc%N;
		if(nr < 1) {
			while(nr<1) nr += N;
		}
		if(nc < 1) {
			while(nc<1) nc += N;
		}
		
		return new Node(nr, nc);
	}
}