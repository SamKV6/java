import java.io.*;
import java.util.*;

public class mainv2 {
    public static void main(String[] args) throws IOException {

        NumberChecker divisibleBy13 = n -> n % 13 == 0;

        File inputFile = new File("numbers.txt");
        try (PrintWriter writer = new PrintWriter(inputFile)) {
            int[] testNumbers = {1, 13, 26, 7, 39, 100, 52, 3, 65, 42, 78, 5};
            for (int num : testNumbers) {
                writer.println(num);
            }
        }

        System.out.println("Входной файл создан: numbers.txt");

        File outputFile = new File("numbers_filtered.txt");

        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(outputFile)
        ) {
            String line;
            int removedCount = 0;

            while ((line = reader.readLine()) != null) {
                int number = Integer.parseInt(line.trim());

                if (divisibleBy13.check(number)) {
    
                    System.out.println("Удалено (делится на 13): " + number);
                    removedCount++;
                } else {
                    
                    writer.println(number);
                }
            }

            System.out.println("\nУдалено чисел: " + removedCount);
            System.out.println("Результат сохранён в: numbers_filtered.txt");
        }

        
        System.out.println("\nСодержимое выходного файла:");
        try (BufferedReader reader = new BufferedReader(new FileReader(outputFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}
