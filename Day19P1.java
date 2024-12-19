import java.io.*;

public class Day19P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day19Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        line = br.readLine();
        String[] arr = line.split(", ");
        br.readLine();

        long ans = 0;
        while((line = br.readLine()) != null) {
            ans += rec(line, line.length(), arr, 0) ? 1 : 0;
        }
        
        out.println(ans);
        out.close();
    }

    public static boolean rec(String s, int n, String[] arr, int idx) {
        if(idx == n) return true;
        for(String x : arr) {
            if(x.equals(s.substring(idx, Math.min(n, idx + x.length())))) {
                if(rec(s, n, arr, idx + x.length())) {
                    return true;
                }
            }
        }
        return false;
    }
}
