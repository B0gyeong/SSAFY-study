import java.util.*;
import java.io.*;

class Node{
    int to;
    Node next;

    public Node(int to, Node next){
        this.to = to;
        this.next = next;
    }
}

public class SolutionContact{
    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;
        
        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            int len = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            Node[] nodes = new Node[101];
            Node[] adj;

            for(int i = 0; i < (len / 2); i++){
                st = new StringTokenizer(br.readLine().trim());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                adj[from] = new Node(to, adj[from]);
            }

            boolean[] visited = new boolean[101];
            Queue<Integer> q = new LinkedList<>();
            q.add(nodes[start]);
            visited[start] = true;

            int last = start;

            int result = -1;

            while(!q.isEmpty()){
                Node curr = q.poll();
                for(Node e = adj[v]; e != null; e = e.next){
                    if(!visited[e]){
                        visited[e] = true;
                        q.add(nodes[e]);
                        last = curr.to;
                        if(result < last){
                            result = last;
                        }
                    }
                }
            }

            System.out.println("#" + test_case + " " + result);
        }
    }
}