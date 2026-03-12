import java.util.ArrayList;

public class MyStack implements Cloneable {
    private ArrayList<Object> list = new ArrayList<>();
    
    public boolean isEmpty() {
        return list.isEmpty();
    }
    
    public int getSize() {
        return list.size();
    }
    
    public Object peek() {
        return list.get(getSize() - 1);
    }
    
    public Object pop() {
        Object o = list.get(getSize() - 1);
        list.remove(getSize() - 1);
        return o;
    }
    
    public void push(Object o) {
        list.add(o);
    }
    
    @Override
    public Object clone() {
        try {
            // Сначала делаем поверхностную копию (копируются примитивные поля и ссылки)
            MyStack clonedStack = (MyStack) super.clone();
            
            // Теперь делаем глубокую копию ArrayList'а
            // Клонируем ArrayList и присваиваем новому стеку
            clonedStack.list = (ArrayList<Object>) this.list.clone();
            
            return clonedStack;
        } catch (CloneNotSupportedException ex) {
            // Этот блок никогда не выполнится, так как мы реализуем Cloneable
            return null;
        }
    }
    
    @Override
    public String toString() {
        return "стек: " + list.toString();
    }
    
    // Для демонстрации, что списки независимы
    public ArrayList<Object> getList() {
        return list;
    }
}