package competition.kattis.the_class.lab6;

import java.util.*;

public class erdos2 {

    public static void main(String[] args) {

        // All my data structs...
        Scanner stdin = new Scanner(System.in);
        ArrayList<String[]> lists = new ArrayList<>();
        HashMap<String,Integer> map = new HashMap<>();
        ArrayList<String> nameList = new ArrayList<>();
        ArrayList<String> authorList = new ArrayList<>();
        int id = 0;

        // Keep on reading.
        while (stdin.hasNextLine()) {
            // Parse a line.
            StringTokenizer tok = new StringTokenizer(stdin.nextLine());
            String[] tmp = new String[tok.countTokens()];
            int i = 0;

            // Go through each token, adding unique names to my map.
            while (tok.hasMoreTokens()) {
                String name = tok.nextToken();
                if (i == 0) authorList.add(name);
                tmp[i++] = name;
                if (!map.containsKey(name)) {
                    map.put(name, id++);
                    nameList.add(name);
                }
            }

            // Store each list of authors here.
            lists.add(tmp);
        }

        // Create my graph.
        HashSet[] graph = new HashSet[id];
        for (int i=0; i<graph.length; i++) graph[i] = new HashSet<Integer>();

        // Go through each list.
        for (String[] names : lists) {
            // Just linking each name to index 0.
            for (int j = 1; j < names.length; j++) {
                int id1 = map.get(names[j]);
                int id2 = map.get(names[0]);
                graph[id1].add(id2);
                graph[id2].add(id1);
            }
        }

        // Store bfs distances here.
        int[] dist = new int[nameList.size()];
        Arrays.fill(dist, -1);
        int source = map.get("PAUL_ERDOS");
        LinkedList<Integer> q = new LinkedList<>();
        q.offer(source);
        dist[source] = 0;

        // run bfs
        while (q.size() > 0) {

            int cur = q.pollFirst();

            // Go to all neighbors.
            for (Integer next: (HashSet<Integer>)graph[cur]) {
                if (dist[next] == -1) {
                    dist[next] = dist[cur] + 1;
                    q.offer(next);
                }
            }
        }

        // Just answer each query...
        for (int i=0; i<authorList.size(); i++) {
            if (dist[map.get(authorList.get(i))] == -1)
                System.out.println(authorList.get(i)+" no-connection");
            else
                System.out.println(authorList.get(i)+" "+dist[map.get(authorList.get(i))]);
        }
    }
}
