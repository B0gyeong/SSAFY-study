package samsung01;


import java.util.*;
import java.io.*;

public class S1251_하나로 {
	
	static int N;
	static int[][] island;
	static double[][] adj;
	static double e;
	static double[] key;
	static int[] parent;
	static boolean[] visited;
	
	static class Node implements Comparable<Node>{
		
		int index;
		double key;
		
		public Node(int index, double key) {
			this.index = index;
			this.key = key;
			
		}

		@Override
		public int compareTo(Node o) {
			// TODO Auto-generated method stub
			return Double.compare(this.key, o.key);
		}
		

	}
	
	public static void mst_prim(int r) {
		for(int u=0; u<N; u++) {
			
			key[u] = Double.MAX_VALUE;
			parent[u] = -1;
		}
		
		key[r] = 0;
		PriorityQueue<Node> pq = new PriorityQueue<>();
		visited[r] = true;
		
		for(int u=0; u<N; u++) {
			pq.add(new Node(u, key[u]));
		}
		
		while(!pq.isEmpty()) {
			
			Node cur = pq.poll();
			int index = cur.index;
			visited[index] = true;
			
			for(int v=0; v<N; v++) {
				if(!visited[v] && adj[index][v]< key[v]) {
					parent[v] = index;
					key[v] = adj[index][v];
					pq.add(new Node(v, key[v]));
				}
			}
			
		}
	}
	
	public static void main(String[] args) throws Exception {
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int T = Integer.parseInt(br.readLine());
		StringTokenizer st;
		
		for(int t=1; t<=T; t++) {
			
			N = Integer.parseInt(br.readLine());
			island = new int[2][N]; // 0: x 1: y
			
			for(int i=0; i<2; i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<N; j++) {
					island[i][j] = Integer.parseInt(st.nextToken());
				}
			}
		
			
			e = Double.parseDouble(br.readLine());
			
			adj = new double[N][N];
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					adj[i][j] = Math.abs(Math.pow(island[0][i]-island[0][j], 2)+Math.pow(island[1][i]-island[1][j], 2));
				
					
				}
			}
			
//			System.out.printf("%.0f ", adj[1][0]);
			
			key = new double[N];
			parent = new int[N];
			visited = new boolean[N];
			
			mst_prim(0);
			
			
			double ans = 0;
			
			for(int i=0; i<N; i++) {
//				System.out.printf("  %.0f", key[i]);
				ans += key[i];
			}
			
			ans *= e;
			ans = Math.round(ans);
			System.out.printf("#%d %.0f\n", t, ans);
		}
		
	}
}