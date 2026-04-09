import java.util.ArrayList;

public class GenericStack<E> {
    private static final int DEFAULT_SIZE = 10;
    private E[] arr;
    private int size;
    
    @SuppressWarnings("unchecked")
    public GenericStack() {
        arr = (E[]) new Object[DEFAULT_SIZE];
        size = 0;
    }
    
    public int getSize() {
        return size;
    }
    
    public E peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст");
        }
        return arr[size - 1];
    }
    
    @SuppressWarnings("unchecked")
    public void push(E o) {
        // Проверяем, заполнен ли массив
        if (size == arr.length) {
            // Создаем новый массив в 2 раза больше
            E[] newArr = (E[]) new Object[arr.length * 2];
            // Копируем элементы из старого массива в новый
            System.arraycopy(arr, 0, newArr, 0, arr.length);
            arr = newArr;
        }
        arr[size++] = o;
    }
    
    public E pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Стек пуст");
        }
        E o = arr[--size];
        arr[size] = null; // Помогаем сборщику мусора
        return o;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    /**
     * Добавляет элементы из коллекции в стек, исключая дубликаты
     * @param collection коллекция для добавления
     */
    public void addAllWithoutDuplicates(ArrayList<E> collection) {
        for (E element : collection) {
            // Проверяем, содержится ли элемент в стеке
            if (!contains(element)) {
                push(element);
            }
        }
    }
    
    /**
     * Проверяет, содержится ли элемент в стеке
     * @param element искомый элемент
     * @return true если элемент найден, false в противном случае
     */
    public boolean contains(E element) {
        for (int i = 0; i < size; i++) {
            if (arr[i] == null && element == null) {
                return true;
            }
            if (arr[i] != null && arr[i].equals(element)) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) {
                sb.append(", ");
            }
            sb.append(arr[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}