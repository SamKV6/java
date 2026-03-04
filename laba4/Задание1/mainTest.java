public class mainTest {
    public static void main(String[] args) {
        // Создаем коробку объемом 100
        Box box = new Box(100);
        System.out.println("Создана коробка: " + box);
        System.out.println();
        
        // Создаем разные фигуры
        Ball ball1 = new Ball(2);  // объем ~33.51
        Ball ball2 = new Ball(3);  // объем ~113.1 - слишком большой для коробки
        Cylinder cylinder = new Cylinder(2, 5);  // объем ~62.83
        
        System.out.println("Созданы фигуры:");
        System.out.println("  " + ball1);
        System.out.println("  " + ball2);
        System.out.println("  " + cylinder);
        System.out.println();
        
        // Добавляем фигуры в коробку
        System.out.println("Добавляем шар радиусом 2: " + box.add(ball1) + 
                          " (должен поместиться)");
        System.out.println("Состояние коробки: " + box);
        System.out.println();
        
        System.out.println("Добавляем цилиндр: " + box.add(cylinder) + 
                          " (должен поместиться)");
        System.out.println("Состояние коробки: " + box);
        System.out.println();
        
        System.out.println("Добавляем шар радиусом 3: " + box.add(ball2) + 
                          " (не должен поместиться)");
        System.out.println("Финальное состояние коробки: " + box);
    }
}