public class main1 {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("ЗАПУСК ВСЕХ ТЕСТОВ - ВАРИАНТ 1");
        System.out.println("========================================\n");
        
        // Запускаем тест с системными типами
        SystemTypesTest.main(args);
        
        System.out.println("\n========================================\n");
        
        // Запускаем тест с собственным классом Person1
        CustomClassTest.main(args);
        
        System.out.println("\n========================================\n");
        System.out.println("ТЕСТИРОВАНИЕ ЗАВЕРШЕНО");
    }
}