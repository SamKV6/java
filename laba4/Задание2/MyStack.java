import java.util.ArrayList;

public class MyStack {
    // Изменяем тип с Object на Comparable
    private ArrayList<Comparable> list = new ArrayList<>();
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int getSize() {
        return list.size();
    }
    
    public Comparable peek() {
        return list.get(getSize() - 1);
    }
    
    public Comparable pop() {
        Comparable o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }
    
    public void push(Comparable o) {
        list.add(o);
    }
    
    // НОВЫЙ МЕТОД СОРТИРОВКИ
    public void sort() {
        for (int i = 0; i < list.size() - 1; i++) {
            for (int j = i + 1; j < list.size(); j++) {
                // Используем compareTo для сравнения
                if (list.get(i).compareTo(list.get(j)) > 0) {
                    // Меняем местами
                    Comparable temp = list.get(i);
                    list.set(i, list.get(j));
                    list.set(j, temp);
                }
            }
        }
    }
    
    @Override
    public String toString() {
        return "стек: " + list.toString();
    }
}