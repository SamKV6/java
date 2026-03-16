import java.util.*;

// Перечисление для типов сортировки
enum SortType {
    ASCENDING,           // по возрастанию
    DESCENDING,          // по убыванию
    ABS_ASCENDING,       // по возрастанию абсолютных значений
    ABS_DESCENDING       // по убыванию абсолютных значений
}

// Класс Fraction (обыкновенная дробь)
class Fraction implements Comparable<Fraction> {
    private final int numerator;
    private final int denominator;

    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new IllegalArgumentException("Знаменатель не может быть равен нулю");
        }
        // Приводим дробь к стандартному виду: знаменатель положительный
        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }
        // Сокращаем дробь
        int gcd = gcd(Math.abs(numerator), denominator);
        this.numerator = numerator / gcd;
        this.denominator = denominator / gcd;
    }

    // Нахождение наибольшего общего делителя
    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    // Метод для получения вещественного значения дроби
    public double doubleValue() {
        return (double) numerator / denominator;
    }

    // Метод для получения абсолютного вещественного значения
    public double absDoubleValue() {
        return Math.abs(doubleValue());
    }

    @Override
    public int compareTo(Fraction other) {
        // Сравнение дробей a/b и c/d через приведение к общему знаменателю: a*d и c*b
        long left = (long) this.numerator * other.denominator;
        long right = (long) other.numerator * this.denominator;
        return Long.compare(left, right);
    }

    @Override
    public String toString() {
        return numerator + "/" + denominator;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction other = (Fraction) obj;
        return this.compareTo(other) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(numerator, denominator);
    }

    // Геттеры
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }
}

// Компаратор для дробей с различными стратегиями сравнения
class FractionComparator implements Comparator<Fraction> {
    private final SortType sortType;

    public FractionComparator(SortType sortType) {
        this.sortType = sortType;
    }

    @Override
    public int compare(Fraction f1, Fraction f2) {
        switch (sortType) {
            case ASCENDING:
                return f1.compareTo(f2);
            case DESCENDING:
                return -f1.compareTo(f2);
            case ABS_ASCENDING:
                return Double.compare(f1.absDoubleValue(), f2.absDoubleValue());
            case ABS_DESCENDING:
                return Double.compare(f2.absDoubleValue(), f1.absDoubleValue());
            default:
                throw new IllegalArgumentException("Неизвестный тип сортировки");
        }
    }
}

// Класс для дополнительного задания - обертка дроби с порядковым номером
class PrioritizedFraction {
    private final Fraction fraction;
    private final long sequenceNumber;
    private static long counter = 0;

    public PrioritizedFraction(Fraction fraction) {
        this.fraction = fraction;
        this.sequenceNumber = counter++;
    }

    public Fraction getFraction() {
        return fraction;
    }

    public long getSequenceNumber() {
        return sequenceNumber;
    }

    @Override
    public String toString() {
        return fraction.toString() + " (порядковый номер: " + sequenceNumber + ")";
    }
    
    // Метод для сброса счетчика (для тестирования)
    public static void resetCounter() {
        counter = 0;
    }
}

// Компаратор для приоритетной очереди с учетом порядка поступления
class StableFractionComparator implements Comparator<PrioritizedFraction> {
    private final Comparator<Fraction> fractionComparator;

    public StableFractionComparator(SortType sortType) {
        this.fractionComparator = new FractionComparator(sortType);
    }

    @Override
    public int compare(PrioritizedFraction pf1, PrioritizedFraction pf2) {
        // Сначала сравниваем дроби
        int fractionCompare = fractionComparator.compare(
            pf1.getFraction(), pf2.getFraction());
        
        // Если дроби равны, сравниваем по порядку поступления
        if (fractionCompare == 0) {
            return Long.compare(pf1.getSequenceNumber(), pf2.getSequenceNumber());
        }
        return fractionCompare;
    }
}