import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class StringListManager {
    private final List<String> stringList = new ArrayList<>();
    private volatile boolean running = true;
    
    // Синхронизированный метод для добавления строки
    public synchronized void addString(String str) {
        // Разрезаем строку длиннее 80 символов
        while (str.length() > 80) {
            String part = str.substring(0, 80);
            stringList.add(part);
            str = str.substring(80);
        }
        if (!str.isEmpty()) {
            stringList.add(str);
        }
    }
    
    // Синхронизированный метод для сортировки списка (пузырьковая сортировка)
    public synchronized void sortList() {
        if (stringList.isEmpty()) {
            return;
        }
        
        System.out.println("\n[Сортировка] Начинаю сортировку списка...");
        
        // Пузырьковая сортировка
        int n = stringList.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (stringList.get(j).compareTo(stringList.get(j + 1)) > 0) {
                    String temp = stringList.get(j);
                    stringList.set(j, stringList.get(j + 1));
                    stringList.set(j + 1, temp);
                }
            }
        }
        
        System.out.println("[Сортировка] Сортировка завершена. Размер списка: " + stringList.size());
        printCurrentState();
    }
    
    // Синхронизированный метод для вывода текущего состояния
    public synchronized void printCurrentState() {
        System.out.println("\n=== Текущее состояние списка ===");
        if (stringList.isEmpty()) {
            System.out.println("Список пуст.");
        } else {
            for (int i = 0; i < stringList.size(); i++) {
                System.out.println((i + 1) + ". " + stringList.get(i));
            }
        }
        System.out.println("================================\n");
    }
    
    // Синхронизированный метод для остановки программы
    public synchronized void stop() {
        running = false;
    }
    
    public boolean isRunning() {
        return running;
    }
}

class SorterThread extends Thread {
    private final StringListManager manager;
    
    public SorterThread(StringListManager manager) {
        this.manager = manager;
    }
    
    @Override
    public void run() {
        while (manager.isRunning()) {
            try {
                Thread.sleep(5000); // Спим 5 секунд
                if (manager.isRunning()) {
                    manager.sortList();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("[Сортировщик] Поток завершил работу.");
    }
}

public class main2 {
    public static void main(String[] args) {
        StringListManager manager = new StringListManager();
        SorterThread sorter = new SorterThread(manager);
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("=== Программа управления списком строк ===");
        System.out.println("Вводите строки (максимум 80 символов, длинные будут разрезаны).");
        System.out.println("Для завершения и вывода результата введите пустую строку.");
        System.out.println("Дочерний поток будет сортировать список каждые 5 секунд.\n");
        
        // Запускаем поток-сортировщик
        sorter.start();
        
        // Основной поток читает ввод пользователя
        while (manager.isRunning()) {
            System.out.print("Введите строку: ");
            String input = scanner.nextLine();
            
            if (input.isEmpty()) {
                System.out.println("\nОбнаружена пустая строка. Завершение программы...");
                manager.stop();
                break;
            }
            
            manager.addString(input);
            System.out.println("Строка добавлена. Текущий размер списка: " + 
                             (manager.isRunning() ? "неизвестно" : "завершение"));
        }
        
        // Ожидаем завершения потока-сортировщика
        try {
            sorter.interrupt();
            sorter.join(2000); // Ждём не более 2 секунд
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        // Выводим финальное состояние списка
        System.out.println("\n=== ФИНАЛЬНЫЙ РЕЗУЛЬТАТ ===");
        manager.printCurrentState();
        
        scanner.close();
        System.out.println("Программа завершена.");
    }
}