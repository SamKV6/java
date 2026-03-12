public class CopyTest {
    public static void main(String[] args) {
        System.out.println("=== ДЕМОНСТРАЦИЯ ГЛУБОКОГО КОПИРОВАНИЯ ===\n");
        
        // Создаем стек с объектами
        MyStack stack1 = new MyStack();
        stack1.push("Один");
        stack1.push("Два");
        stack1.push("Три");
        
        System.out.println("stack1 (оригинал): " + stack1);
        
        // Клонируем
        MyStack stack2 = (MyStack) stack1.clone();
        System.out.println("stack2 (клон): " + stack2);
        
        // Проверяем, что это разные объекты
        System.out.println("\nstack1 == stack2? " + (stack1 == stack2));
        
        // Модифицируем stack1
        stack1.pop(); // удаляем "Три"
        stack1.push("Четыре");
        
        System.out.println("\nПосле модификации stack1:");
        System.out.println("stack1: " + stack1);
        System.out.println("stack2: " + stack2);
        
        // Модифицируем stack2
        stack2.push("Пять");
        
        System.out.println("\nПосле модификации stack2:");
        System.out.println("stack1: " + stack1);
        System.out.println("stack2: " + stack2);
        
        // Демонстрируем, что внутренние ArrayList'ы разные
        System.out.println("\nВнутренние ArrayList'ы:");
        System.out.println("stack1.list == stack2.list? " + 
                          (stack1.getList() == stack2.getList()));
        
        // Проверяем, что можем изменять один ArrayList независимо от другого
        stack1.getList().clear();
        
        System.out.println("\nПосле очистки stack1:");
        System.out.println("stack1: " + stack1);
        System.out.println("stack2: " + stack2);
    }
}