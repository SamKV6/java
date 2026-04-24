import java.io.*;
import java.net.*;
import java.util.Scanner;

public class ChatClient {
    private static final String DEFAULT_HOST = "localhost";
    private static final int DEFAULT_PORT = 8888;

    public static void main(String[] args) {
        String host = args.length > 0 ? args[0] : DEFAULT_HOST;
        int port = args.length > 1 ? Integer.parseInt(args[1]) : DEFAULT_PORT;
        
        try (Socket socket = new Socket(host, port);
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             Scanner scanner = new Scanner(System.in)) {
            
            System.out.println("💬 Подключено к чату: " + host + ":" + port);
            System.out.println("   Введите ваш никнейм:");
            
            // Отправляем никнейм серверу
            String nickname = scanner.nextLine();
            out.println(nickname.isEmpty() ? "Аноним" : nickname);
            
            System.out.println("✅ Вы в чате! Пишите сообщения.");
            System.out.println("   Введите @exit для выхода.\n");
            
            // Поток для приёма сообщений от сервера
            Thread receiver = new Thread(() -> {
                try {
                    String msg;
                    while ((msg = in.readLine()) != null) {
                        System.out.println(msg);
                    }
                } catch (IOException ignored) {}
            });
            receiver.setDaemon(true);
            receiver.start();
            
            // Основной цикл: отправка сообщений
            while (scanner.hasNextLine()) {
                String message = scanner.nextLine();
                out.println(message);
                if ("@exit".equalsIgnoreCase(message)) break;
            }
            
        } catch (UnknownHostException e) {
            System.err.println("✗ Неверный адрес сервера: " + host);
        } catch (IOException e) {
            System.err.println("✗ Ошибка подключения: " + e.getMessage());
        }
    }
}
