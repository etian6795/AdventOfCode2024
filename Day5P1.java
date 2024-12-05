import java.io.*;
import java.util.*;

public class Day5P1 {
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
            if(can(s, map)) {
                ans += Long.valueOf(s[s.length/2]);
            }
        }
        out.println(ans);
        out.close();
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
