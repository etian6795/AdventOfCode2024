import java.io.*;

public class Day13P2 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day13Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        long ans = 0;
        while((line = br.readLine()) != null) {
            long[] A = AOCInterface.readLineAsLongs(line);
            line = br.readLine();
            long[] B = AOCInterface.readLineAsLongs(line);
            line = br.readLine();
            long[] P = AOCInterface.readLineAsLongs(line);
            P[0] += 10000000000000l;
            P[1] += 10000000000000l;
            br.readLine();
            
            long a = A[0];
            long b = B[0];
            long c = A[1];
            long d = B[1];
            long e = P[0];
            long f = P[1];

            double det = (a*d - b*c);
            long x = (long)(((d) * (e) - (b) * (f)) / det);
            long y = (long)(((a) * (f) - (c) * (e)) / det);
            x = Math.abs(x);
            y = Math.abs(y);
            if(x*a + y*b == e && x*c + y*d == f) {
                ans += 3*x+y;
            }
        }
        
        out.println(ans);
        out.close();
    }
}