import java.util.*;
 
public class Solution {
	static int N, minSum;
	static boolean [] visited;
	static Node home, com;
	static Node [] customers, selected;
	static class Node {
		int r, c;
		Node (int r, int c) {
			this.r = r; this.c = c;
		}
	}
    public static void main(String[] args) {
    	Scanner sc = new Scanner(System.in);
    	
    	int T = sc.nextInt();
    	for(int tc=1; tc<=T; tc++) {
    		N = sc.nextInt();
    		
    		selected = new Node [N+2];
    		int cr = sc.nextInt();
    		int cc = sc.nextInt();
    		int hr = sc.nextInt();
    		int hc = sc.nextInt();
    		com = new Node(cr, cc);
    		home = new Node(hr, hc);
    		selected[0] = com;
    		selected[N+1] = home;
    		
    		customers = new Node [N];
    		for(int i=0; i<N; i++) {
    			int csr = sc.nextInt();
    			int csc = sc.nextInt();
    			customers[i] = new Node(csr, csc);
    		}
    		
    		minSum = Integer.MAX_VALUE;
    		visited = new boolean [N];
    		perm(0, 0);
    		System.out.println("#"+tc+" "+minSum);
    	}
    }
    
	private static void perm(int idx, int sum) {
		if(sum > minSum) return;
		
		if(idx == N) {
			int finSum = sum+calDist(selected[idx], selected[idx+1]);
			minSum = Math.min(minSum, finSum);
			
			return;
		}
		
		for(int i=0; i<N; i++) {
			if(visited[i]) continue;
			
			visited[i] = true;
			selected[idx+1] = customers[i];
			perm(idx+1, sum + calDist(selected[idx], customers[i]));
			visited[i] = false;
		}
	}

	private static int calDist(Node node, Node node2) {
		int dist = Math.abs(node.r - node2.r) + Math.abs(node.c - node2.c);
		return dist;
	}
}