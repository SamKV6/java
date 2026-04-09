public class main1 {
    public static void main(String[] args) {
        GenericStack1<Integer> stack = new GenericStack1<>();
        
        System.out.println("push(1)");
        stack.push(1);
        System.out.println("Результат: " + stack);
        
        System.out.println("\npush(2)");
        stack.push(2);
        System.out.println("Результат: " + stack);
        
        System.out.println("\npush(3)");
        stack.push(3);
        System.out.println("Результат: " + stack);
        
        System.out.println("\ngetSize()");
        System.out.println("Результат: " + stack.getSize());
        
        System.out.println("\npeek()");
        System.out.println("Результат: " + stack.peek());
        
        System.out.println("\npop()");
        System.out.println("Результат: " + stack.pop());
        System.out.println("Стек после pop(): " + stack);
        
        System.out.println("\nisEmpty()");
        System.out.println("Результат: " + stack.isEmpty());
        
        System.out.println("\npop()");
        System.out.println("Результат: " + stack.pop());
        System.out.println("Стек после pop(): " + stack);
        
        System.out.println("\npop()");
        System.out.println("Результат: " + stack.pop());
        System.out.println("Стек после pop(): " + stack);
        
        System.out.println("\nisEmpty()");
        System.out.println("Результат: " + stack.isEmpty());
        
        System.out.println("\nПроверка расширения массива:");
        GenericStack1<Integer> stack2 = new GenericStack1<>();
        for (int i = 1; i <= 15; i++) {
            stack2.push(i);
        }
        System.out.println("push() 15 элементов");
        System.out.println("Результат: размер стека = " + stack2.getSize());
    }
}