import java.io.*;
import java.util.*;

public class Day24P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day24Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        HashMap<String, Boolean> map = new HashMap<>();
        String line;
        while(!(line = br.readLine()).equals("")) {
            String[] temp = line.split(" ");
            map.put(temp[0].substring(0, 3), temp[1].equals("1"));
        }
        HashMap<String, ArrayList<String>> e = new HashMap<>();
        while((line = br.readLine()) != null) {
            String[] temp = line.split(" ");
            e.put(temp[4], new ArrayList<>());
            e.get(temp[4]).add(temp[0]);
            e.get(temp[4]).add(temp[2]);
            e.get(temp[4]).add(temp[1]);
        }

        boolean can = false;
        while(!can) {
            can = true;
            for(String s : e.keySet()) {
                String a = e.get(s).get(0);
                String b = e.get(s).get(1);
                String op = e.get(s).get(2);
                if(map.containsKey(a) && map.containsKey(b) && !map.containsKey(s)) {
                    can = false;
                    if(op.equals("AND")) {
                        map.put(s, map.get(a) & map.get(b));
                    } else if(op.equals("OR")) {
                        map.put(s, map.get(a) | map.get(b));
                    } else {
                        map.put(s, map.get(a) ^ map.get(b));
                    }
                }
            }
        }
        TreeMap<String, Boolean> res = new TreeMap<>((a, b) -> (b.compareTo(a)));
        for(String s : map.keySet()) {
            if(s.charAt(0) == 'z') {
                res.put(s, map.get(s));
            }
        }
        StringBuilder ans = new StringBuilder();
        for(boolean b : res.values()) {
            ans.append((b ? 1 : 0));
        }
        out.println(Long.parseLong(ans.toString(), 2));
        out.close();
    }
}
