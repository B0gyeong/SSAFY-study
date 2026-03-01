import java.io.*;
import java.util.*;

public class S1238_Contact {

    static class Node {
        int num;
        int dist;
        public Node(int num, int dist) {
            this.num = num;
            this.dist = dist;
        }
    }

    public static int bfs(List<List<Node>> graph, boolean[] visited, int s) {
        Queue<Node> q = new ArrayDeque<>();

        q.add(new Node(s, 0));
        visited[s] = true;

        int maxDist = 0; // 가장 긴 거리
        int maxNum = s; // 가장 큰 번호

        while(!q.isEmpty()) {
            Node cur = q.poll();
            int num = cur.num;

            if(cur.dist>maxDist) {
                maxDist = cur.dist;
                maxNum = cur.num;
                if(cur.num>maxNum) {
                    maxNum = cur.num;
                }
            } else if(cur.dist==maxDist) {
                if(cur.num>maxNum) {
                    maxNum = cur.num;
                }
            }

            for(Node next:graph.get(num)) {
                if(visited[next.num]) continue;
                q.add(new Node(next.num, cur.dist+1));
                visited[next.num] = true;
            }

        }

        return maxNum;
    }

    public static void main(String args[]) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for(int t=1; t<=10; t++) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // 입력받는 데이터 길이
            int S = Integer.parseInt(st.nextToken()); // 시작점
            List<List<Node>> graph = new ArrayList<>();
            boolean[] visited = new boolean[101];

            for(int i=0; i<101; i++) {
                graph.add(new ArrayList<>());
            }

            st = new StringTokenizer(br.readLine());
            for(int i=0; i<N/2; i++) {
                // 쌍을 입력 받음
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());

                graph.get(from).add(new Node(to, 0));
            }

            //마지막에 방문한 원소 중 가장 큰 번호 출력
            int Answer = 0;
            Answer = bfs(graph, visited, S);

            System.out.println("#"+t+" "+Answer);
        }


    }
}
