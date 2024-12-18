import java.io.*;
import java.util.*;

public class Day18P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day18Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        String line;
        ArrayList<int[]> bad = new ArrayList<>();
        while((line = br.readLine()) != null) {
            int[] temp = AOCInterface.readLineAsInts(line);
            bad.add(temp);
        }

        int n = 7; // 71
        boolean[][] grid = new boolean[n][n];
        for(boolean[] x : grid) Arrays.fill(x, true);
        for(int i = 0; i < 12 /*1024*/; i++) {
            grid[bad.get(i)[0]][bad.get(i)[1]] = false;
        }

        int d = 0;
        Queue<int[]> q = new LinkedList<>();
        int[] dir = new int[]{0, 1, 0, -1, 0};
        boolean[][] seen = new boolean[n][n];
        q.add(new int[]{0, 0});
        seen[0][0] = true;

        while(!q.isEmpty()) {
            int size = q.size();
            while(size-->0) {
                int[] curr = q.remove();
                if(curr[0] == n-1 && curr[1] == n-1) {
                    out.println(d);
                    out.close();
                    return;
                }
                for(int i = 0; i < 4; i++) {
                    int nr = curr[0] + dir[i];
                    int nc = curr[1] + dir[i + 1];
                    if(nr >= 0 && nr < n && nc >= 0 && nc < n && !seen[nr][nc] && grid[nr][nc]) {
                        seen[nr][nc] = true;
                        q.add(new int[]{nr, nc});
                    }
                }
            }
            d++;
        }
        
        out.close();
    }
}
