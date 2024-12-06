import java.io.*;
import java.util.*;

public class Day6P2 {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader("Day6Input.txt"));

        String line;
        long ans = 0;
        ArrayList<char[]> grid = new ArrayList<>();
        int r = 0;
        int c = 0;
        while((line = br.readLine()) != null) {
            char[] arr = line.toCharArray();
            grid.add(line.toCharArray());
            for(int i = 0; i < arr.length; i++) {
                if(arr[i] == '^') {
                    r = grid.size()-1;
                    c = i;
                }
            }
        }
        int m = grid.size();
        int n = grid.get(0).length;
        int tempr = r;
        int tempc = c;
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid.get(i)[j] == '.') {
                    r = tempr;
                    c = tempc;
                    grid.get(i)[j] = '#';
                    boolean[][][] seen = new boolean[m][n][4];
                    int dir = 0;
                    int[][] dirs = new int[][]{{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
                    while(r >= 0 && r < m && c >= 0 && c < n) {
                        if(grid.get(r)[c] == '#') {
                            r -= dirs[dir%4][0];
                            c -= dirs[dir%4][1];
                            dir++;
                        } else {
                            if(seen[r][c][dir%4]) {
                                ans++;
                                break;
                            }
                            seen[r][c][dir%4] = true;
                            r += dirs[dir%4][0];
                            c += dirs[dir%4][1];
                        }
                    }
                    grid.get(i)[j] = '.';
                }
            }
        }
        
        out.println(ans);
        out.close();
    }
}
