import java.io.*;
import java.util.*;

public class Day5P2 {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("Day5Input.txt"));

        String line;
        long ans = 0;
        HashMap<Integer, HashSet<Integer>> map = new HashMap<>();
        while(!(line = br.readLine()).equals("")) {
            String[] s = line.split("\\|");
            int a = Integer.parseInt(s[0]);
            int b = Integer.parseInt(s[1]);
            if(!map.containsKey(a)) map.put(a, new HashSet<>());
            map.get(a).add(b);
        }

        while((line = br.readLine()) != null) {
            String[] s = line.split(",");
            if(!can(s, map)) {
                HashSet<Integer> seen = new HashSet<>();
                HashSet<Integer> in = new HashSet<>();
                for(String ss : s) in.add(Integer.valueOf(ss));
                ArrayList<Integer> topoSort = new ArrayList<>();
                for(String ss : s) {
                    if(!seen.contains(Integer.valueOf(ss))) {
                        dfs(seen, map, Integer.parseInt(ss), topoSort, in);
                    }
                }
                out.println(topoSort);
                ans += Long.valueOf(topoSort.get(s.length/2));
            }
        }
        out.println(ans);
        out.close();
    }

    public static void dfs(HashSet<Integer> seen, HashMap<Integer, HashSet<Integer>> map, int curr, ArrayList<Integer> topoSort, HashSet<Integer> in) {
        seen.add(curr);
        if(map.containsKey(curr)) {
            for(int next : map.get(curr)) {
                if(!in.contains(next)) continue;
                if(!seen.contains(next)) {
                    dfs(seen, map, next, topoSort, in);
                }
            }
        }
        topoSort.add(curr);
    }

    public static boolean can(String[] arr, HashMap<Integer, HashSet<Integer>> map) {
        int n = arr.length;
        for(int i = 0; i < n; i++) {
            for(int j = i+1; j < n; j++) {
                int a = Integer.parseInt(arr[i]);
                int b = Integer.parseInt(arr[j]);
                if(map.containsKey(b) && map.get(b).contains(a)) return false;
            }
        }
        return true;
    }
}
