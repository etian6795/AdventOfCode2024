import java.io.*;
import java.util.*;

public class Day23P1 implements AOCInterface {
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
        ArrayList<String> nodeList = new ArrayList<>();
        for(String s : nodes) nodeList.add(s);
        int n = nodeList.size();
        HashSet<String> ans = new HashSet<>();
        for(int i = 0; i < n; i++) {
            if(nodeList.get(i).charAt(0) == 't') {
                for(int j = 0; j < n; j++) {
                    for(int k = 0; k < n; k++) {
                        String a = nodeList.get(i);
                        String b = nodeList.get(j);
                        String c = nodeList.get(k);
                        if(e.get(a).contains(b) && e.get(a).contains(c) && e.get(b).contains(c)) {
                            String[] temp = new String[]{a, b, c};
                            Arrays.sort(temp);
                            ans.add(temp[0] + temp[1] + temp[2]);
                        }
                    }
                }
            }
        }
        out.println(ans.size());
        out.close();
    }
}
