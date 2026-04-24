import java.io.*;
import java.net.*;

public class TextReceiver {
    private static final int DEFAULT_PORT = 9999;

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;

        System.out.println("📡 Сервер запущен. Ожидание подключения на порту " + port + "...");
        
        try (ServerSocket serverSocket = new ServerSocket(port);
             Socket clientSocket = serverSocket.accept();
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(clientSocket.getInputStream()))) {

            System.out.println("✓ Клиент подключён: " + clientSocket.getInetAddress());
            
            String received = in.readLine();
            if (received != null) {
                System.out.println("\n📥 Полученный текст (только английские буквы):");
                System.out.println("   \"" + received + "\"");
            }
            
        } catch (IOException e) {
            System.err.println("✗ Ошибка: " + e.getMessage());
        }
    }
}