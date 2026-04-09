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

class SortException extends ContainerException {
    public SortException(String reason) {
        super("Ошибка сортировки: " + reason);
    }
}

// Класс-контейнер
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

    // Вариант 1: Сортировка
    public void sort() throws ContainerException {
        if (isEmpty()) {
            throw new SortException("Контейнер пуст.");
        }

        boolean allEqual = true;
        for (int i = 1; i < count; i++) {
            if (data[i] != data[0]) {
                allEqual = false;
                break;
            }
        }
        if (allEqual) {
            throw new SortException("Все элементы равны.");
        }

        // Сортировка пузырьком
        for (int i = 0; i < count - 1; i++) {
            for (int j = 0; j < count - i - 1; j++) {
                if (data[j] > data[j + 1]) {
                    int temp = data[j];
                    data[j] = data[j + 1];
                    data[j + 1] = temp;
                }
            }
        }
    }

    public void print() {
        System.out.print("[");
        for (int i = 0; i < count; i++) {
            System.out.print(data[i] + (i < count - 1 ? ", " : ""));
        }
        System.out.print("]");
    }
}


public class main1 {
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
            for (int i = 0; i < 10; i++) stack.push(i);
            stack.push(99);
            System.out.println("Успешно");
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: pop() (Пустой контейнер) ===");
        try {
            while (!stack.isEmpty()) stack.pop();
            stack.pop();
            System.out.println("Успешно");
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: sort() ===");
        try {
            stack.push(9); stack.push(1); stack.push(5);
            System.out.print("До: "); stack.print();
            stack.sort();
            System.out.print(" | После: "); stack.print();
            System.out.println();
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: sort() (Пустой контейнер) ===");
        try {
            while (!stack.isEmpty()) stack.pop();
            stack.sort();
            System.out.println("Успешно");
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }

        System.out.println("\n=== Метод: sort() (Все элементы равны) ===");
        try {
            stack.push(7); stack.push(7); stack.push(7);
            stack.sort();
            System.out.println("Успешно");
        } catch (ContainerException ex) {
            System.out.println("Ошибка: " + ex.getMessage());
        }
    }
}