import java.util.*;

public class main {
    public static void main(String[] args) {
        System.out.println("================================================");
        System.out.println("ЛАБОРАТОРНАЯ РАБОТА: КЛАСС FRACTION");
        System.out.println("================================================");
        
        // Часть 1: Проверка основных возможностей класса Fraction
        System.out.println("\n--- ЧАСТЬ 1: ОСНОВНЫЕ ВОЗМОЖНОСТИ FRACTION ---");
        testBasicFeatures();
        
        // Часть 2: Проверка естественной сортировки (Comparable)
        System.out.println("\n--- ЧАСТЬ 2: ЕСТЕСТВЕННАЯ СОРТИРОВКА (Comparable) ---");
        testComparable();
        
        // Часть 3: Проверка всех четырех компараторов
        System.out.println("\n--- ЧАСТЬ 3: ЧЕТЫРЕ КОМПАРАТОРА ---");
        testAllComparators();
        
        // Часть 4: Проверка приоритетной очереди (дополнительное задание)
        System.out.println("\n--- ЧАСТЬ 4: ДОПОЛНИТЕЛЬНОЕ ЗАДАНИЕ ---");
        System.out.println("Условие: При наличии в приоритетной очереди элементов с одинаковым приоритетом");
        System.out.println("они должны извлекаться в порядке поступления (FIFO для равных элементов)");
        testPriorityQueue();
    }
    
    // Тест 1: Базовые возможности класса Fraction
    private static void testBasicFeatures() {
        System.out.println("1.1 Создание дробей и автоматическое сокращение:");
        
        Fraction f1 = new Fraction(3, 4);
        Fraction f2 = new Fraction(-1, 2);
        Fraction f3 = new Fraction(6, 8);  // должна сократиться до 3/4
        Fraction f4 = new Fraction(2, -4);  // должна стать -1/2
        Fraction f5 = new Fraction(0, 5);   // ноль
        Fraction f6 = new Fraction(-3, -4); // должно стать 3/4
        
        System.out.println("  new Fraction(3, 4) = " + f1 + " (значение: " + f1.doubleValue() + ")");
        System.out.println("  new Fraction(-1, 2) = " + f2 + " (значение: " + f2.doubleValue() + ")");
        System.out.println("  new Fraction(6, 8) = " + f3 + " (должна сократиться до 3/4)");
        System.out.println("  new Fraction(2, -4) = " + f4 + " (должна стать -1/2)");
        System.out.println("  new Fraction(0, 5) = " + f5);
        System.out.println("  new Fraction(-3, -4) = " + f6 + " (должно стать 3/4)");
        
        System.out.println("\n1.2 Проверка equals() и hashCode():");
        System.out.println("  f1 (3/4) equals f3 (6/8): " + f1.equals(f3));
        System.out.println("  f2 (-1/2) equals f4 (2/-4): " + f2.equals(f4));
        System.out.println("  f1.equals(f2): " + f1.equals(f2));
        System.out.println("  f1.hashCode() = " + f1.hashCode());
        System.out.println("  f3.hashCode() = " + f3.hashCode() + " (должен совпадать с f1)");
        
        System.out.println("\n1.3 Абсолютные значения:");
        System.out.println("  |" + f1 + "| = " + f1.absDoubleValue());
        System.out.println("  |" + f2 + "| = " + f2.absDoubleValue());
        
        System.out.println("\n1.4 Геттеры:");
        System.out.println("  f1 числитель: " + f1.getNumerator() + ", знаменатель: " + f1.getDenominator());
        
        System.out.println("\n1.5 Проверка исключения:");
        try {
            Fraction bad = new Fraction(1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("  Исключение при делении на ноль: " + e.getMessage());
        }
    }
    
    // Тест 2: Естественная сортировка через Comparable
    private static void testComparable() {
        List<Fraction> fractions = new ArrayList<>(Arrays.asList(
            new Fraction(3, 4),
            new Fraction(-1, 2),
            new Fraction(5, 6),
            new Fraction(-2, 3),
            new Fraction(1, 3),
            new Fraction(-3, 4),
            new Fraction(2, 5),
            new Fraction(0, 1)
        ));

        System.out.println("Исходный список:");
        printFractionsWithValues(fractions);

        Collections.sort(fractions);
        System.out.println("\nПосле сортировки по возрастанию (Comparable):");
        printFractionsWithValues(fractions);
        
        // Проверка порядка
        System.out.println("\nПроверка порядка (от меньшего к большему):");
        for (int i = 0; i < fractions.size() - 1; i++) {
            Fraction current = fractions.get(i);
            Fraction next = fractions.get(i + 1);
            System.out.println("  " + current + " (" + current.doubleValue() + ") <= " 
                             + next + " (" + next.doubleValue() + "): " 
                             + (current.compareTo(next) <= 0));
        }
    }
    
    // Тест 3: Все четыре компаратора
    private static void testAllComparators() {
        List<Fraction> fractions = new ArrayList<>(Arrays.asList(
            new Fraction(3, 4),
            new Fraction(-1, 2),
            new Fraction(5, 6),
            new Fraction(-2, 3),
            new Fraction(1, 3),
            new Fraction(-3, 4),
            new Fraction(2, 5)
        ));

        System.out.println("Исходный список для тестирования компараторов:");
        printFractionsWithValues(fractions);

        // Тест 3.1: По возрастанию
        List<Fraction> listAsc = new ArrayList<>(fractions);
        Collections.sort(listAsc, new FractionComparator(SortType.ASCENDING));
        System.out.println("\n3.1 СОРТИРОВКА ПО ВОЗРАСТАНИЮ:");
        printFractionsWithValues(listAsc);

        // Тест 3.2: По убыванию
        List<Fraction> listDesc = new ArrayList<>(fractions);
        Collections.sort(listDesc, new FractionComparator(SortType.DESCENDING));
        System.out.println("\n3.2 СОРТИРОВКА ПО УБЫВАНИЮ:");
        printFractionsWithValues(listDesc);

        // Тест 3.3: По возрастанию абсолютных значений
        List<Fraction> listAbsAsc = new ArrayList<>(fractions);
        Collections.sort(listAbsAsc, new FractionComparator(SortType.ABS_ASCENDING));
        System.out.println("\n3.3 СОРТИРОВКА ПО ВОЗРАСТАНИЮ АБСОЛЮТНЫХ ЗНАЧЕНИЙ:");
        printFractionsWithAbs(listAbsAsc);

        // Тест 3.4: По убыванию абсолютных значений
        List<Fraction> listAbsDesc = new ArrayList<>(fractions);
        Collections.sort(listAbsDesc, new FractionComparator(SortType.ABS_DESCENDING));
        System.out.println("\n3.4 СОРТИРОВКА ПО УБЫВАНИЮ АБСОЛЮТНЫХ ЗНАЧЕНИЙ:");
        printFractionsWithAbs(listAbsDesc);
    }
    
    // Тест 4: Приоритетная очередь с сохранением порядка (дополнительное задание)
    private static void testPriorityQueue() {
        // Сбрасываем счетчик
        PrioritizedFraction.resetCounter();
        
        System.out.println("\n4.1 Тест с сортировкой ПО ВОЗРАСТАНИЮ:");
        testPriorityQueueWithType(SortType.ASCENDING);
        
        System.out.println("\n4.2 Тест с сортировкой ПО УБЫВАНИЮ:");
        testPriorityQueueWithType(SortType.DESCENDING);
        
        System.out.println("\n4.3 Демонстрация важности сохранения порядка:");
        demonstrateStability();
    }
    
    private static void testPriorityQueueWithType(SortType sortType) {
        PriorityQueue<PrioritizedFraction> queue = new PriorityQueue<>(
            new StableFractionComparator(sortType)
        );

        String typeName = sortType == SortType.ASCENDING ? "ВОЗРАСТАНИЮ" : "УБЫВАНИЮ";
        System.out.println("  Очередь с приоритетом по " + typeName);
        System.out.println("  (одинаковые дроби должны извлекаться в порядке добавления)");
        
        // Добавляем дроби с одинаковыми значениями
        System.out.println("\n  Порядок добавления:");
        PrioritizedFraction[] fractions = {
            new PrioritizedFraction(new Fraction(1, 2)),   // seq: 0
            new PrioritizedFraction(new Fraction(2, 4)),   // seq: 1 (равна 1/2)
            new PrioritizedFraction(new Fraction(3, 4)),   // seq: 2
            new PrioritizedFraction(new Fraction(2, 4)),   // seq: 3 (равна 1/2)
            new PrioritizedFraction(new Fraction(1, 4)),   // seq: 4
            new PrioritizedFraction(new Fraction(1, 2)),   // seq: 5 (равна 1/2)
            new PrioritizedFraction(new Fraction(3, 4)),   // seq: 6 (равна 3/4)
        };

        for (PrioritizedFraction pf : fractions) {
            queue.offer(pf);
            System.out.println("    Добавлен: " + pf);
        }

        System.out.println("\n  Порядок извлечения:");
        int order = 1;
        while (!queue.isEmpty()) {
            PrioritizedFraction pf = queue.poll();
            System.out.println("    " + order++ + ". " + pf);
        }
    }
    
    private static void demonstrateStability() {
        System.out.println("  Сравнение обычной PriorityQueue и нашей стабильной версии:");
        
        // Обычная PriorityQueue (без учета порядка)
        PriorityQueue<PrioritizedFraction> unstableQueue = new PriorityQueue<>(
            (pf1, pf2) -> pf1.getFraction().compareTo(pf2.getFraction())
        );
        
        // Наша стабильная очередь
        PriorityQueue<PrioritizedFraction> stableQueue = new PriorityQueue<>(
            new StableFractionComparator(SortType.ASCENDING)
        );
        
        PrioritizedFraction.resetCounter();
        
        // Добавляем одинаковые дроби
        PrioritizedFraction f1 = new PrioritizedFraction(new Fraction(1, 2));
        PrioritizedFraction f2 = new PrioritizedFraction(new Fraction(1, 2));
        PrioritizedFraction f3 = new PrioritizedFraction(new Fraction(1, 2));
        
        unstableQueue.offer(f1);
        unstableQueue.offer(f2);
        unstableQueue.offer(f3);
        
        stableQueue.offer(f1);
        stableQueue.offer(f2);
        stableQueue.offer(f3);
        
        System.out.println("    Добавлены три одинаковые дроби 1/2 с номерами 0, 1, 2");
        System.out.println("    Обычная очередь (порядок может быть любым):");
        System.out.print("      ");
        while (!unstableQueue.isEmpty()) {
            System.out.print(unstableQueue.poll() + " ");
        }
        System.out.println();
        
        System.out.println("    Стабильная очередь (сохраняет порядок 0,1,2):");
        System.out.print("      ");
        while (!stableQueue.isEmpty()) {
            System.out.print(stableQueue.poll() + " ");
        }
        System.out.println();
    }
    
    // Вспомогательные методы для печати
    private static void printFractionsWithValues(List<Fraction> fractions) {
        for (Fraction f : fractions) {
            System.out.printf("  %s = %.3f%n", f, f.doubleValue());
        }
    }

    private static void printFractionsWithAbs(List<Fraction> fractions) {
        for (Fraction f : fractions) {
            System.out.printf("  |%s| = %.3f (исходное: %.3f)%n", 
                f, f.absDoubleValue(), f.doubleValue());
        }
    }
}