import java.util.ArrayList;

// Абстрактный базовый класс для всех фигур
abstract class Shape {
    // Абстрактный метод для получения объема фигуры
    public abstract double getVolume();
    
    // Переопределяем toString для удобного вывода
    @Override
    public String toString() {
        return getClass().getSimpleName() + " { Volume = " + getVolume() + " }";
    }
}

// Абстрактный класс для фигур вращения
abstract class SolidOfRevolution extends Shape {
    protected double radius;  // радиус фигуры вращения
    
    // Конструктор
    public SolidOfRevolution(double radius) {
        this.radius = radius;
    }
    
    // Геттер для радиуса
    public double getRadius() {
        return radius;
    }
}

// Класс Ball (шар) - наследник SolidOfRevolution
class Ball extends SolidOfRevolution {
    
    public Ball(double radius) {
        super(radius);
    }
    
    // Расчет объема шара: V = (4/3) * π * r³
    @Override
    public double getVolume() {
        return (4.0 / 3.0) * Math.PI * Math.pow(radius, 3);
    }
}

// Класс Cylinder (цилиндр) - наследник SolidOfRevolution
class Cylinder extends SolidOfRevolution {
    private double height;  // высота цилиндра
    
    public Cylinder(double radius, double height) {
        super(radius);
        this.height = height;
    }
    
    // Расчет объема цилиндра: V = π * r² * h
    @Override
    public double getVolume() {
        return Math.PI * Math.pow(radius, 2) * height;
    }
}

// Класс Box (коробка) - наследник Shape
class Box extends Shape {
    private double volume;  // объем коробки
    private ArrayList<Shape> shapes = new ArrayList<>();  // список фигур внутри
    
    // Конструктор принимает объем коробки
    public Box(double volume) {
        this.volume = volume;
    }
    
    // Расчет объема коробки (объем всех фигур внутри)
    @Override
    public double getVolume() {
        double occupiedVolume = 0;
        for (Shape shape : shapes) {
            occupiedVolume += shape.getVolume();
        }
        return occupiedVolume;
    }
    
    // Свободный объем в коробке
    public double getFreeVolume() {
        return volume - getVolume();
    }
    
    // Метод добавления фигуры в коробку
    public boolean add(Shape shape) {
        // Проверяем, поместится ли фигура
        if (shape.getVolume() <= getFreeVolume()) {
            shapes.add(shape);
            return true;
        }
        return false;
    }
    
    // Переопределяем toString для наглядности
    @Override
    public String toString() {
        return "Box { Volume = " + volume + 
               ", Occupied = " + getVolume() + 
               ", Free = " + getFreeVolume() + 
               ", Shapes count = " + shapes.size() + " }";
    }
}
