import java.util.*;
import java.io.*;

class Graph{
    int vertexNum;
    List<List<Integer>> adj;
    int[] in_degree;

    public Graph(int verNum){
        this.vertexNum = verNum;
        adj = new ArrayList<>();
        for(int i = 0; i < vertexNum; i++){
            adj.add(new LinkedList<>());
        }

        in_degree = new int[vertexNum];
    }

    public void addEdge(int src, int dest){
        adj.get(src).add(dest);
        in_degree[dest]++;
    }
}

public class SolutionWorkOrder {
    static int V;
    static int E;
    static Queue<Integer> queue;
    static List<Integer> topOrder;

    public static void main(String[] args) throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = 10;

        for(int test_case = 1; test_case <= T; test_case++){
            StringTokenizer st = new StringTokenizer(br.readLine().trim());
            V = Integer.parseInt(st.nextToken());
            E = Integer.parseInt(st.nextToken());
            queue = new LinkedList<>();
            topOrder = new ArrayList<>();

            int from;
            int to;
            
            Graph graph = new Graph(V);
            
            st = new StringTokenizer(br.readLine().trim());
            for(int i = 0; i < E; i++){
                from = Integer.parseInt(st.nextToken()) - 1; //array index 맞추기 위해서 1 빼줌
                to = Integer.parseInt(st.nextToken()) - 1;
                graph.addEdge(from, to);
            }

            for(int i = 0; i < graph.in_degree.length; i++){
                if(graph.in_degree[i] == 0){
                    queue.add(i);
                }
            }

            while(!queue.isEmpty()){
                int vertex = queue.poll();
                topOrder.add(vertex);

                for(int adjV : graph.adj.get(vertex)){
                    if(--graph.in_degree[adjV] == 0){
                        queue.add(adjV);
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            sb.append("#" + test_case);

            for(int v : topOrder){
                v = v + 1;
                sb.append(" " + v);
            }

            System.out.println(sb.toString());
        }
    }
}