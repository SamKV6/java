public class main {
    public static void main(String[] args) {
        // Демонстрация с числами
        MyStack stack = new MyStack();
        stack.push(5);
        stack.push(2);
        stack.push(8);
        stack.push(1);
        stack.push(3);
        
        System.out.println("До сортировки: " + stack);
        stack.sort();
        System.out.println("После сортировки: " + stack);
        System.out.println();
        
        // Демонстрация с другим типом - String
        MyStack stringStack = new MyStack();
        stringStack.push("Яблоко");
        stringStack.push("Банан");
        stringStack.push("Апельсин");
        stringStack.push("Груша");
        
        System.out.println("Строки до сортировки: " + stringStack);
        stringStack.sort();
        System.out.println("Строки после сортировки: " + stringStack);
    }
}