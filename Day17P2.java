import java.io.*;

public class Day17P2 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day17Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        while(!br.readLine().equals("")) {
            
        }

        long[] arr = AOCInterface.readLineAsLongs(br.readLine());
        int n = arr.length;
        out.println(rec(0, arr, n-1));
        
        out.close();
    }

    public static long rec(long A, long[] arr, int i) {
        long res = Long.MAX_VALUE;
        for(long j = 0; j < 8; j++) {
            if(can(A * 8 + j, arr, i)) {
                if(i == 0) {
                    res = Math.min(res, A * 8 + j);
                } else {
                    long temp = rec(A * 8 + j, arr, i - 1);
                    if(temp != -1) {
                        res = Math.min(res, temp);
                    }
                }
            }
        }
        
        return res;
    }

    public static boolean can(long A, long[] arr, int i) {
        int idx = 0;
        int n = arr.length;
        long[] reg = new long[]{A, 0, 0};
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
                return arr[i] == combo(arr[idx+1], reg) % 8;
            } else if(arr[idx] == 6) {
                reg[1] = reg[0] / ((long)Math.pow(2, combo(arr[idx+1], reg)));
                idx += 2;
            } else if(arr[idx] == 7) {
                reg[2] = reg[0] / ((long)Math.pow(2, combo(arr[idx+1], reg)));
                idx += 2;
            }
        }
        return true;
    }

    public static long combo(long x, long[] reg) {
        if(x <= 3) return x;
        return reg[(int)x-4];
    }
}
