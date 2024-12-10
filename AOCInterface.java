import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public interface AOCInterface {
    public static long[] readLineAsLongs(String s) {
        ArrayList<Long> arr = new ArrayList<>();
        
        Pattern pattern = Pattern.compile("-?\\d+");
        Matcher matcher = pattern.matcher(s);
        while (matcher.find()) {
            arr.add(Long.valueOf(matcher.group()));
        }

        long[] res = new long[arr.size()];
        for(int i = 0; i < res.length; i++) {
            res[i] = arr.get(i);
        }
        return res;
    }

    public static char[][] readInputAsGrid(String file) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(file));
        ArrayList<char[]> arr = new ArrayList<>();
        String line;
        while((line = br.readLine()) != null) {
            arr.add(line.toCharArray());
        }
        char[][] res = new char[arr.size()][arr.get(0).length];
        for(int i = 0; i < res.length; i++) {
            for(int j = 0; j < res[0].length; j++) {
                res[i][j] = arr.get(i)[j];
            }
        }
        return res;
    }

    public static String[] readLineSplitBySpaces(String line) {
        return line.split("\\s+");
    }
}