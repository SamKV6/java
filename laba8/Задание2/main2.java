class ContainerException extends Exception {
    public ContainerException(String message) {
        super(message);
    }
}

class ContainerFullException extends ContainerException {
    public ContainerFullException() {
        super("Контейнер заполнен.");
    }
}

class ContainerEmptyException extends ContainerException {
    public ContainerEmptyException() {
        super("Контейнер пуст.");
    }
}

class SearchException extends ContainerException {
    public SearchException(String reason) {
        super("Ошибка поиска: " + reason);
    }
}

class IntStack {
    private static final int MAX_SIZE = 10;
    private int[] data;
    private int count;

    public IntStack() {
        data = new int[MAX_SIZE];
        count = 0;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public boolean isFull() {
        return count >= MAX_SIZE;
    }

    public void push(int value) throws ContainerFullException {
        if (isFull()) {
            throw new ContainerFullException();
        }
        data[count++] = value;
    }

    public int pop() throws ContainerEmptyException {
        if (isEmpty()) {
            throw new ContainerEmptyException();
        }
        return data[--count];
    }

    public int search(int value) throws ContainerException {
        if (isEmpty()) {
            throw new ContainerEmptyException();
        }
        int foundIndex = -1;
        int foundCount = 0;
        for (int i = 0; i < count; i++) {
            if (data[i] == value) {
                foundCount++;
                foundIndex = i;
            }
        }
        if (foundCount > 1) {
            throw new SearchException("Найдено более одного элемента.");
        }
        return foundIndex;
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < count; i++) {
            System.out.print(data[i] + (i < count - 1 ? ", " : ""));
        }
        System.out.print("]");
    }
}

public class main2 {
    public static void main(String[] args) {
        IntStack stack = new IntStack();

        System.out.println("=== Метод: push() / pop() ===");
        try {
            stack.push(5);
            stack.push(10);
            stack.push(3);
            System.out.print("Состояние: ");
            stack.print();
            System.out.println(" | Удалено: " + stack.pop());
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: push() (Переполнение) ===");
        try {
            for (int i = 0; i < 10; i++) {
                stack.push(i);
            }
            stack.push(99);
            System.out.println("Успешно");
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: pop() (Пустой контейнер) ===");
        try {
            while (!stack.isEmpty()) {
                stack.pop();
            }
            stack.pop();
            System.out.println("Успешно");
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: search() (Успешный поиск) ===");
        try {
            stack.push(9);
            stack.push(1);
            stack.push(5);
            System.out.print("Контейнер: ");
            stack.print();
            int index = stack.search(1);
            System.out.println(" | Индекс: " + index);
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: search() (Элемент не найден) ===");
        try {
            int index = stack.search(99);
            if (index == -1) {
                System.out.println("Результат: Элемент не найден (-1)");
            } else {
                System.out.println("Результат: Индекс " + index);
            }
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: search() (Дубликаты) ===");
        try {
            stack.push(7);
            stack.push(7);
            System.out.print("Контейнер: ");
            stack.print();
            stack.search(7);
            System.out.println("Успешно");
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }
}