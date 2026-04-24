import java.io.*;
import java.net.*;
import java.util.*;
import java.util.concurrent.*;


public class ChatServer {
    private static final int DEFAULT_PORT = 8888;
    
    // Потокобезопасный список клиентов
    private static final CopyOnWriteArrayList<ClientHandler> clients = new CopyOnWriteArrayList<>();
    private static final ExecutorService pool = Executors.newCachedThreadPool();

    public static void main(String[] args) {
        int port = args.length > 0 ? Integer.parseInt(args[0]) : DEFAULT_PORT;
        
        System.out.println("💬 Чат-сервер запущен на порту " + port);
        
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler handler = new ClientHandler(clientSocket);
                clients.add(handler);
                pool.execute(handler);
                System.out.println("✓ Подключился клиент: " + clientSocket.getRemoteSocketAddress());
            }
        } catch (IOException e) {
            System.err.println("✗ Ошибка сервера: " + e.getMessage());
        }
    }
    
    // Внутренний класс для обработки клиента
    private static class ClientHandler implements Runnable {
        private final Socket socket;
        private PrintWriter out;
        private BufferedReader in;
        private String nickname;
        
        public ClientHandler(Socket socket) {
            this.socket = socket;
        }
        
        @Override
        public void run() {
            try {
                in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                out = new PrintWriter(socket.getOutputStream(), true);
                
                // Читаем никнейм при подключении
                nickname = in.readLine();
                if (nickname == null || nickname.isEmpty()) nickname = "Аноним";
                
                // Уведомляем всех о новом участнике
                broadcast("🔔 [" + nickname + "] присоединился к чату");
                
                // Основной цикл: читаем сообщения и рассылаем
                String message;
                while ((message = in.readLine()) != null) {
                    if ("@exit".equalsIgnoreCase(message)) break;
                    broadcast("[" + nickname + "]: " + message);
                }
                
            } catch (IOException e) {
                System.out.println("✗ Клиент отключился: " + nickname);
            } finally {
                // Убираем клиента и уведомляем остальных
                clients.remove(this);
                broadcast("🔌 [" + nickname + "] покинул чат");
                closeSilently();
            }
        }
        
        // Отправка сообщения всем клиентам
        private void broadcast(String message) {
            for (ClientHandler client : clients) {
                if (client.out != null) {
                    client.out.println(message);
                }
            }
        }
        
        private void closeSilently() {
            try { socket.close(); } catch (IOException ignored) {}
        }
    }
}