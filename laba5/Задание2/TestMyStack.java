public class TestMyStack {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ СТЕКА С CLONEABLE ===\n");
        
        // Создаем исходный стек и добавляем элементы
        MyStack originalStack = new MyStack();
        originalStack.push("Первый элемент");
        originalStack.push(42);
        originalStack.push(3.14);
        originalStack.push("Последний элемент");
        
        System.out.println("Исходный стек:");
        System.out.println(originalStack);
        System.out.println("Размер: " + originalStack.getSize());
        System.out.println("Верхний элемент (peek): " + originalStack.peek());
        
        // Клонируем стек
        MyStack clonedStack = (MyStack) originalStack.clone();
        
        System.out.println("\n--- После клонирования ---");
        System.out.println("Оригинал: " + originalStack);
        System.out.println("Клон: " + clonedStack);
        
        // Проверяем, что это разные объекты
        System.out.println("\n--- Проверка независимости ---");
        System.out.println("originalStack == clonedStack? " + 
                          (originalStack == clonedStack)); // Должно быть false
        
        // Изменяем оригинал
        originalStack.push("Новый элемент в оригинале");
        originalStack.pop(); // Удаляем последний
        
        System.out.println("\nПосле изменений в оригинале:");
        System.out.println("Оригинал: " + originalStack);
        System.out.println("Клон: " + clonedStack);
        
        // Изменяем клон
        clonedStack.push("Элемент только в клоне");
        
        System.out.println("\nПосле изменений в клоне:");
        System.out.println("Оригинал: " + originalStack);
        System.out.println("Клон: " + clonedStack);
        
        // Демонстрируем, что ArrayList'ы независимы
        System.out.println("\n--- Проверка независимости ArrayList'ов ---");
        System.out.println("originalStack.getList() == clonedStack.getList()? " +
                          (originalStack.getList() == clonedStack.getList())); // Должно быть false
        
        // Тест с разными типами данных
        System.out.println("\n=== ТЕСТ С РАЗНЫМИ ТИПАМИ ДАННЫХ ===");
        MyStack mixedStack = new MyStack();
        mixedStack.push("Строка");
        mixedStack.push(123);
        mixedStack.push(45.67);
        mixedStack.push(new Student("Тестовый студент", 4.8, 20));
        
        MyStack mixedClone = (MyStack) mixedStack.clone();
        
        System.out.println("Оригинал mixedStack: " + mixedStack);
        System.out.println("Клон mixedClone: " + mixedClone);
        
        // Изменяем оригинал
        mixedStack.pop();
        
        System.out.println("\nПосле удаления элемента из оригинала:");
        System.out.println("Оригинал: " + mixedStack);
        System.out.println("Клон: " + mixedClone);
    }
}