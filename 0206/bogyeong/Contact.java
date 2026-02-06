import java.util.*;

public class Contact {
	static int N;
	static int[][] sPool;
	static int fir;
	static int fic;
	static int[] dr = { -1, 1, 0, 0 };
	static int[] dc = { 0, 0, -1, 1 };

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		for (int tc = 1; tc <= 10; tc++) {
			int L = sc.nextInt();
			int s = sc.nextInt();
			int[][] g = new int[101][101];
			boolean[] visited = new boolean[101];

			for (int i = 0; i < L / 2; i++) {
				int from = sc.nextInt();
				int to = sc.nextInt();
				g[from][to] = 1;
			}

			Queue<Integer> queue = new LinkedList<>();
			visited[s] = true;
			queue.add(s);
			ArrayList<Integer> finalAnswer = new ArrayList<>();
			while (!queue.isEmpty()) {
				int len = queue.size();
				ArrayList<Integer> currentLevelNodes = new ArrayList<>();
				for (int j = 0; j < len; j++) {
					int cur = queue.poll();
					for (int i = 1; i <= 100; i++) {
						if (g[cur][i] == 1 && !visited[i]) {
							queue.add(i);
							visited[i] = true;
							currentLevelNodes.add(i);
						}
					}
				}
				if (!currentLevelNodes.isEmpty()) {
					finalAnswer = currentLevelNodes;
				}
			}

			Collections.sort(finalAnswer);
			System.out.println("#" + tc + " " + finalAnswer.get(finalAnswer.size() - 1));
		}
	}
}
