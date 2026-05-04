import java.io.*;
import java.util.*;

public class main {
    public static void main(String[] args) throws IOException {

        StringComparator longer = (a, b) -> a.length() >= b.length() ? a : b;

        File inputFile = new File("strings.txt");
        try (PrintWriter writer = new PrintWriter(inputFile)) {
            writer.println("кот");
            writer.println("слон");
            writer.println("бегемот");
            writer.println("лев");
            writer.println("тигр");
            writer.println("крокодил");
        }

        String longestLine = "";

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                longestLine = longer.compare(longestLine, line);
            }
        }

        System.out.println("Самая длинная строка в файле: \"" + longestLine + "\"");
    }
}
