import java.io.*;
import java.util.*;

public class Day18P2 implements AOCInterface {
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

        int[] dir = new int[]{0, 1, 0, -1, 0};
        
        for(int j = 0; j < bad.size(); j++) {
            Queue<int[]> q = new LinkedList<>();
            boolean[][] seen = new boolean[n][n];
            q.add(new int[]{0, 0});
            seen[0][0] = true;
            grid[bad.get(j)[0]][bad.get(j)[1]] = false;
            
            boolean can = false;
            while(!q.isEmpty()) {
                int[] curr = q.remove();
                if(curr[0] == n-1 && curr[1] == n-1) {
                    can = true;
                    break;
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
            if(!can) {
                out.println(bad.get(j)[0]+","+bad.get(j)[1]);
                break;
            }
        }
        
        out.close();
    }
}
