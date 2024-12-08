import java.io.*;
import java.util.*;

public class Day8P1 implements AOCInterface {
    public static BufferedReader br;
    public static PrintWriter out = new PrintWriter(System.out);
    public static String file = "Day8Input.txt";

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new FileReader(file));

        long ans = 0;
        char[][] grid = AOCInterface.readInputAsGrid(file);
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] anti = new boolean[m][n];
        HashMap<Character, ArrayList<int[]>> ant = new HashMap<>();
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(grid[i][j] != '.') {
                    if(!ant.containsKey(grid[i][j])) ant.put(grid[i][j], new ArrayList<>());
                    ant.get(grid[i][j]).add(new int[]{i, j});
                }
            }
        }
        for(ArrayList<int[]> qwe : ant.values()) {
            int z = qwe.size();
            for(int i = 0; i < z; i++) {
                for(int j = i+1; j < z; j++) {
                    int dx = qwe.get(i)[0] - qwe.get(j)[0];
                    int dy = qwe.get(i)[1] - qwe.get(j)[1];
                    int nr = qwe.get(i)[0] + dx;
                    int nc = qwe.get(i)[1] + dy;
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n) anti[nr][nc] = true;
                    nr = qwe.get(j)[0] - dx;
                    nc = qwe.get(j)[1] - dy;
                    if(nr >= 0 && nr < m && nc >= 0 && nc < n) anti[nr][nc] = true;
                }
            }
        }
        for(boolean[] x : anti) {
            for(boolean y : x) {
                if(y) ans++;
            }
        }
        out.println(ans);
        out.close();
    }
}
