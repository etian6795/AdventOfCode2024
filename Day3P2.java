import java.io.*;

public class Day3P2 {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("Day3Input.txt"));

        String line;
        long ans = 0;
        boolean can = true;
        while((line = br.readLine()) != null) {
            int n = line.length();
            int idx = 0;
            while(idx < n-3) {
                if(line.substring(idx, idx+4).equals("mul(") && can) {
                    idx += 4;
                    long a = 0;
                    while(Character.isDigit(line.charAt(idx))) {
                        a = a*10 + (line.charAt(idx++)-'0');
                    }
                    if(line.charAt(idx) != ',') continue;
                    idx++;
                    long b = 0;
                    while(Character.isDigit(line.charAt(idx))) {
                        b = b*10 + (line.charAt(idx++)-'0');
                    }
                    if(line.charAt(idx) != ')') continue;
                    ans += a*b;
                } else {
                    if(line.substring(idx, idx+4).equals("do()")) can = true;
                    else if(idx < n-7 && line.substring(idx, idx+7).equals("don't()")) can = false;
                    idx++;
                }
            }
        }
        out.println(ans);
        out.close();
    }
}
