public class main2 {
    public static void main(String[] args) {
        System.out.println("=== Запуск процедурного кабинета (Вариант 2) ===\n");
        
        // 3 медсестры, 10 пациентов
        MedicalClinic clinic = new MedicalClinic(3, 10);
        clinic.start();
        
        System.out.println("\n=== Работа процедурного кабинета завершена ===");
    }
}