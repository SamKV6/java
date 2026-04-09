import java.util.ArrayList;

public class main2 {
    public static void main(String[] args) {
        GenericStack<String> stack = new GenericStack<>();
        
        stack.push("A");
        stack.push("B");
        stack.push("C");
        
        System.out.println("Исходный стек: " + stack);
        
        ArrayList<String> list = new ArrayList<>();
        list.add("B");
        list.add("D");
        list.add("A");
        list.add("E");
        list.add("C");
        
        System.out.println("\naddAllWithoutDuplicates(" + list + ")");
        stack.addAllWithoutDuplicates(list);
        System.out.println("Результат: " + stack);
        
        System.out.println("\ncontains(\"A\")");
        System.out.println("Результат: " + stack.contains("A"));
        
        System.out.println("\ncontains(\"F\")");
        System.out.println("Результат: " + stack.contains("F"));
        
        System.out.println("\ngetSize()");
        System.out.println("Результат: " + stack.getSize());
        
        System.out.println("\npeek()");
        System.out.println("Результат: " + stack.peek());
        
        System.out.println("\npop()");
        System.out.println("Результат: " + stack.pop());
        System.out.println("Стек после pop(): " + stack);
        
        System.out.println("\nisEmpty()");
        System.out.println("Результат: " + stack.isEmpty());
        
        System.out.println("\nПроверка с числами:");
        GenericStack<Integer> intStack = new GenericStack<>();
        intStack.push(1);
        intStack.push(2);
        intStack.push(3);
        
        System.out.println("Исходный стек: " + intStack);
        
        ArrayList<Integer> numbers = new ArrayList<>();
        numbers.add(2);
        numbers.add(4);
        numbers.add(1);
        numbers.add(5);
        
        System.out.println("\naddAllWithoutDuplicates(" + numbers + ")");
        intStack.addAllWithoutDuplicates(numbers);
        System.out.println("Результат: " + intStack);
    }
}