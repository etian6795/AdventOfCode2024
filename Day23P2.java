import java.io.*;
import java.util.*;

public class Day23P2 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day23Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        HashSet<String> nodes = new HashSet<>();
        HashMap<String, HashSet<String>> e = new HashMap<>();
        while((line = br.readLine()) != null) {
            String[] temp = line.split("-");
            String a = temp[0];
            String b = temp[1];
            nodes.add(a);
            nodes.add(b);
            if(!e.containsKey(a)) e.put(a, new HashSet<>());
            e.get(a).add(b);
            if(!e.containsKey(b)) e.put(b, new HashSet<>());
            e.get(b).add(a);
        }

        HashSet<String> ans = bronKerbosch(nodes, e);
        ArrayList<String> res = new ArrayList<>();
        for(String s : ans) res.add(s);
        Collections.sort(res);
        StringBuilder sb = new StringBuilder();
        for(String s : res) sb.append(s).append(',');
        out.println(sb.toString());
        
        out.close();
    }

    public static HashSet<String> bronKerbosch(HashSet<String> nodes, HashMap<String, HashSet<String>> edges) {
        HashSet<String> R = new HashSet<>(); // Current clique being constructed
        HashSet<String> P = new HashSet<>(nodes); // Candidates that can be added to R
        HashSet<String> X = new HashSet<>(); // Candidates that should be excluded

        HashSet<String> largestClique = new HashSet<>();
        bronKerboschHelper(R, P, X, edges, largestClique);

        return largestClique;
    }

    public static void bronKerboschHelper(HashSet<String> R, HashSet<String> P, HashSet<String> X, HashMap<String, HashSet<String>> edges, HashSet<String> largestClique) {
        // Base case: If P and X are both empty, R is a maximal clique
        if(P.isEmpty() && X.isEmpty()) {
            if(R.size() > largestClique.size()) {
                largestClique.clear();
                largestClique.addAll(R);
            }
            return;
        }

        // Iterate over a copy of P to avoid modification issues
        HashSet<String> PCopy = new HashSet<>(P);

        for(String node : PCopy) {
            // Add node to the current clique
            R.add(node);

            // Compute the new sets P\cap N(node) and X\cap N(node)
            HashSet<String> newP = new HashSet<>();
            HashSet<String> newX = new HashSet<>();

            if(edges.containsKey(node)) {
                for(String neighbor : edges.get(node)) {
                    if(P.contains(neighbor)) {
                        newP.add(neighbor);
                    }
                    if(X.contains(neighbor)) {
                        newX.add(neighbor);
                    }
                }
            }

            // Recursive call
            bronKerboschHelper(R, newP, newX, edges, largestClique);

            // Backtrack
            R.remove(node);
            P.remove(node);
            X.add(node);
        }
    }
}
