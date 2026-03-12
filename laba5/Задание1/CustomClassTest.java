public class CustomClassTest {
    public static void main(String[] args) {
        System.out.println("=== ТЕСТИРОВАНИЕ НА СОБСТВЕННОМ КЛАССЕ PERSON1 ===\n");
        
        testWithPersons();
        testWithSameGrades();
        testEqualsMethod();
        testWithExceptions();
    }
    
    private static void testWithPersons() {
        System.out.println("--- Тест с разными людьми ---");
        
        Person1[] persons = {
            new Person1("Иванов", 4.5, 20),
            new Person1("Петров", 3.8, 19),
            new Person1("Сидорова", 4.9, 21),
            new Person1("Васильева", 4.2, 20),
            new Person1("Козлов", 3.5, 22),
            new Person1("Морозова", 4.8, 19)
        };
        
        System.out.println("Исходный массив людей:");
        for (Person1 p : persons) {
            System.out.println("  " + p);
        }
        
        Comparable[] result = ArrayUtils.maxAndMin(persons);
        
        System.out.println("\nРезультат [max, min]:");
        System.out.println("  Лучший человек (max): " + result[0]);
        System.out.println("  Худший человек (min): " + result[1]);
        System.out.println();
    }
    
    private static void testWithSameGrades() {
        System.out.println("--- Тест с людьми с одинаковыми оценками ---");
        
        Person1[] sameGradePersons = {
            new Person1("Анна", 4.5, 20),
            new Person1("Борис", 4.5, 21),
            new Person1("Виктор", 4.2, 19),
            new Person1("Галина", 4.5, 18)  // самая младшая с оценкой 4.5
        };
        
        System.out.println("Исходный массив:");
        for (Person1 p : sameGradePersons) {
            System.out.println("  " + p);
        }
        
        Comparable[] result = ArrayUtils.maxAndMin(sameGradePersons);
        
        System.out.println("\nРезультат [max, min] (при равных оценках сравнивается возраст):");
        System.out.println("  Лучший человек (max): " + result[0]);
        System.out.println("  Худший человек (min): " + result[1]);
        System.out.println();
    }
    
    private static void testEqualsMethod() {
        System.out.println("--- Тест метода equals ---");
        
        Person1 p1 = new Person1("Иванов", 4.5, 20);
        Person1 p2 = new Person1("Иванов", 4.5, 20);
        Person1 p3 = new Person1("Петров", 4.5, 20);
        
        System.out.println("Человек 1: " + p1);
        System.out.println("Человек 2: " + p2);
        System.out.println("Человек 3: " + p3);
        
        System.out.println("p1.equals(p2): " + p1.equals(p2) + " (должно быть true - одинаковые данные)");
        System.out.println("p1.equals(p3): " + p1.equals(p3) + " (должно быть false - разные имена)");
        System.out.println();
    }
    
    private static void testWithExceptions() {
        System.out.println("--- Тест обработки исключений ---");
        
        // Тест с одним элементом
        try {
            Person1[] singlePerson = {new Person1("Один", 4.0, 20)};
            ArrayUtils.maxAndMin(singlePerson);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Перехвачено исключение для одного элемента: " + e.getMessage());
        }
        
        // Тест с null элементом
        try {
            Person1[] personsWithNull = {
                new Person1("Анна", 4.5, 20),
                null,
                new Person1("Борис", 4.2, 21)
            };
            ArrayUtils.maxAndMin(personsWithNull);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Перехвачено исключение для null элемента: " + e.getMessage());
        }
        
        // Тест с пустым массивом
        try {
            Person1[] emptyArray = {};
            ArrayUtils.maxAndMin(emptyArray);
        } catch (IllegalArgumentException e) {
            System.out.println("✓ Перехвачено исключение для пустого массива: " + e.getMessage());
        }
        
        System.out.println();
    }
}