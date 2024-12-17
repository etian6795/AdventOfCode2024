import java.io.*;

public class Day17P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day17Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        long[] reg = new long[3];
        int idx = 0;
        while(!(line = br.readLine()).equals("")) {
            long[] arr = AOCInterface.readLineAsLongs(line);
            reg[idx] = arr[0];
            idx++;
        }

        line = br.readLine();
        long[] arr = AOCInterface.readLineAsLongs(line);
        
        idx = 0;
        int n = arr.length;
        while(idx < n) {
            if(arr[idx] == 0) {
                reg[0] = reg[0] / ((long)Math.pow(2, combo(arr[idx+1], reg)));
                idx += 2;
            } else if(arr[idx] == 1) {
                reg[1] = reg[1] ^ arr[idx+1];
                idx += 2;
            } else if(arr[idx] == 2) {
                reg[1] = combo(arr[idx+1], reg) % 8;
                idx += 2;
            } else if(arr[idx] == 3) {
                if(reg[0] == 0) {
                    idx += 2;
                } else {
                    idx = (int)arr[idx+1];
                }
            } else if(arr[idx] == 4) {
                reg[1] = reg[1] ^ reg[2];
                idx += 2;
            } else if(arr[idx] == 5) {
                out.print((combo(arr[idx+1], reg) % 8) + ",");
                idx += 2;
            } else if(arr[idx] == 6) {
                reg[1] = reg[0] / ((long)Math.pow(2, combo(arr[idx+1], reg)));
                idx += 2;
            } else if(arr[idx] == 7) {
                reg[2] = reg[0] / ((long)Math.pow(2, combo(arr[idx+1], reg)));
                idx += 2;
            }
        }

        out.close();
    }

    public static long combo(long x, long[] reg) {
        if(x <= 3) return x;
        return reg[(int)x-4];
    }
}
