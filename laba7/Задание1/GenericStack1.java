public class GenericStack1<E> {
    private static final int DEFAULT_SIZE = 10;
    private E[] arr;
    private int size;
    
    @SuppressWarnings("unchecked")
    public GenericStack1() {
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
        if (size == arr.length) {
            E[] newArr = (E[]) new Object[arr.length * 2];
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
        arr[size] = null;
        return o;
    }
    
    public boolean isEmpty() {
        return size == 0;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < size; i++) {
            if (i > 0) sb.append(", ");
            sb.append(arr[i]);
        }
        sb.append("]");
        return sb.toString();
    }
}