import java.io.*;
import java.math.BigInteger;
import java.util.*;

public class Day11P2 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day11Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        BigInteger ans = BigInteger.ZERO;
        HashMap<BigInteger, BigInteger> map = new HashMap<>();
        while((line = br.readLine()) != null) {
            long[] temp = AOCInterface.readLineAsLongs(line);
            for(long x : temp) {
                map.put(BigInteger.valueOf(x), BigInteger.ONE);
            }
        }

        for(int i = 0; i < 75; i++) {
            HashMap<BigInteger, BigInteger> next = new HashMap<>();
            for(BigInteger x : map.keySet()) {
                int len = x.toString().length();
                BigInteger mult = map.get(x);
                if(x.equals(BigInteger.ZERO)) {
                    next.put(BigInteger.ONE, next.getOrDefault(BigInteger.ONE, BigInteger.ZERO).add(mult));
                } else if(len % 2 == 0) {
                    BigInteger left = new BigInteger(x.toString().substring(0, len / 2));
                    BigInteger right = new BigInteger(x.toString().substring(len / 2));

                    next.put(left, next.getOrDefault(left, BigInteger.ZERO).add(mult));
                    next.put(right, next.getOrDefault(right, BigInteger.ZERO).add(mult));
                } else {
                    BigInteger multiplied = x.multiply(BigInteger.valueOf(2024));
                    next.put(multiplied, next.getOrDefault(multiplied, BigInteger.ZERO).add(mult));
                }
            }
            map = next;
        }
        
        for(BigInteger x : map.values()) {
            ans = ans.add(x);
        }
        
        out.println(ans);
        out.close();
    }
}
