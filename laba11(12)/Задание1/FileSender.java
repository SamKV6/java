import java.io.*;
import java.net.*;

public class FileSender {
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 9999;
    private static final String DEFAULT_FILE = "input.txt";

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : DEFAULT_HOST;
        int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_PORT;
        String filePath = args.length > 2 ? args[2] : DEFAULT_FILE;

        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader fileReader = new BufferedReader(new FileReader(filePath))) {

            System.out.println("✓ Подключено к " + host + ":" + port);
            
            StringBuilder englishOnly = new StringBuilder();
            String line;
            
            while ((line = fileReader.readLine()) != null) {
                for (char c : line.toCharArray()) {
                    // Оставляем только латинские буквы
                    if ((c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z')) {
                        englishOnly.append(c);
                    }
                }
            }
            
            String result = englishOnly.toString();
            out.println(result);
            System.out.println("  Отправлено: \"" + result + "\"");
            System.out.println("  (Исходный текст содержал также русские буквы, они отфильтрованы)");
            
        } catch (UnknownHostException e) {
            System.err.println("✗ Неверный адрес сервера: " + host);
        } catch (IOException e) {
            System.err.println("✗ Ошибка ввода-вывода: " + e.getMessage());
        }
    }
}