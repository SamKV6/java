public class SystemTypesTest {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ НА СИСТЕМНЫХ ТИПАХ ===\n");
        
        testWithIntegers();
        testWithStrings();
        testWithDates();
        testWithBigInteger();
        testWithBoundaryCases();
    }
    
    private static void testWithIntegers() {
        System.out.println("--- Тест с числами Integer ---");
        Integer[] numbers = {15, 7, 22, 3, 11, 9, 42, 8};
        
        System.out.print("Исходный массив: ");
        printArray(numbers);
        
        Comparable[] result = ArrayUtils.maxAndMin(numbers);
        
        System.out.println("Результат [max, min]: [" + result[0] + ", " + result[1] + "]");
        System.out.println("Максимум: " + result[0] + ", Минимум: " + result[1]);
        System.out.println();
    }
    
    private static void testWithStrings() {
        System.out.println("--- Тест со строками String ---");
        String[] strings = {"банан", "яблоко", "вишня", "дыня", "апельсин", "груша"};
        
        System.out.print("Исходный массив: ");
        printArray(strings);
        
        Comparable[] result = ArrayUtils.maxAndMin(strings);
        
        System.out.println("Результат [max, min]: [" + result[0] + ", " + result[1] + "]");
        System.out.println("Максимум (лексикографически): " + result[0]);
        System.out.println("Минимум (лексикографически): " + result[1]);
        System.out.println();
    }
    
    private static void testWithDates() {
        System.out.println("--- Тест с датами Date ---");
        java.util.Date[] dates = {
            new java.util.Date(2023, 5, 15),  // Июнь 15, 2023
            new java.util.Date(2023, 0, 10),  // Январь 10, 2023
            new java.util.Date(2024, 2, 20),  // Март 20, 2024
            new java.util.Date(2022, 11, 5)   // Декабрь 5, 2022
        };
        
        System.out.println("Исходные даты:");
        for (java.util.Date d : dates) {
            System.out.println("  " + d);
        }
        
        Comparable[] result = ArrayUtils.maxAndMin(dates);
        
        System.out.println("Самая поздняя дата (max): " + result[0]);
        System.out.println("Самая ранняя дата (min): " + result[1]);
        System.out.println();
    }
    
    private static void testWithBigInteger() {
        System.out.println("--- Тест с BigInteger ---");
        java.math.BigInteger[] bigNumbers = {
            new java.math.BigInteger("2323231092923992"),
            new java.math.BigInteger("432232323239292"),
            new java.math.BigInteger("54623239292"),
            new java.math.BigInteger("9999999999999999")
        };
        
        System.out.print("Исходный массив: ");
        printArray(bigNumbers);
        
        Comparable[] result = ArrayUtils.maxAndMin(bigNumbers);
        
        System.out.println("Результат [max, min]: [" + result[0] + ", " + result[1] + "]");
        System.out.println();
    }
    
    private static void testWithBoundaryCases() {
        System.out.println("--- Тест граничных случаев ---");
        
        // Тест с одним элементом
        try {
            Integer[] singleElement = {42};
            ArrayUtils.maxAndMin(singleElement);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при одном элементе: " + e.getMessage());
        }
        
        // Тест с null массивом
        try {
            ArrayUtils.maxAndMin(null);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при null массиве: " + e.getMessage());
        }
        
        // Тест с null элементом
        try {
            Integer[] arrayWithNull = {10, 20, null, 30};
            ArrayUtils.maxAndMin(arrayWithNull);
        } catch (IllegalArgumentException e) {
            System.out.println("Ошибка при null элементе: " + e.getMessage());
        }
        
        System.out.println();
    }
    
    private static void printArray(Object[] array) {
        System.out.print("[");
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i]);
            if (i < array.length - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }
}