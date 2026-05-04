import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Вариант 2: Обобщённый контейнер с двунаправленным кольцевым итератором,
 * который пропускает элементы, меньшие первого.
 *
 * Итератор:
 *  - кольцевой  — после последнего → первый (и наоборот)
 *  - двунаправленный — next() и prev()
 *  - фильтрующий — пропускает элементы < первый (по Comparable)
 */
public class Variant2_GenericContainer {

    // ─────────────────────────────────────────────────────────────
    //  Контейнер
    // ─────────────────────────────────────────────────────────────
    static class GenericContainer<T extends Comparable<T>> implements Iterable<T> {

        private final ArrayList<T> data = new ArrayList<>();

        public void add(T item) {
            data.add(item);
        }

        public int size() {
            return data.size();
        }

        // Возвращает двунаправленный кольцевой итератор
        @Override
        public Iterator<T> iterator() {
            return new CircularFilteredIterator();
        }

        // ── Итератор ──────────────────────────────────────────
        private class CircularFilteredIterator implements Iterator<T> {

            // "Видимые" (прошедшие фильтр) индексы
            private final ArrayList<Integer> visible = new ArrayList<>();
            private int pos = 0; // текущий индекс внутри visible

            CircularFilteredIterator() {
                if (data.isEmpty()) return;

                T threshold = data.get(0); // первый элемент — порог фильтрации

                for (int i = 0; i < data.size(); i++) {
                    // Оставляем только элементы >= первого
                    if (data.get(i).compareTo(threshold) >= 0) {
                        visible.add(i);
                    }
                }
            }

            // hasNext() для кольцевого итератора всегда true, если есть элементы
            @Override
            public boolean hasNext() {
                return !visible.isEmpty();
            }

            /** Следующий элемент (кольцо: после последнего — первый) */
            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("Контейнер пуст");
                T value = data.get(visible.get(pos));
                pos = (pos + 1) % visible.size(); // кольцо вперёд
                return value;
            }

            /** Предыдущий элемент (кольцо: перед первым — последний) */
            public T prev() {
                if (!hasNext()) throw new NoSuchElementException("Контейнер пуст");
                pos = (pos - 1 + visible.size()) % visible.size(); // кольцо назад
                return data.get(visible.get(pos));
            }

            /** Количество видимых элементов */
            public int visibleCount() {
                return visible.size();
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  Собственный тип данных — Студент
    // ─────────────────────────────────────────────────────────────
    static class Student implements Comparable<Student> {

        private final String name;
        private final double gpa; // средний балл

        Student(String name, double gpa) {
            this.name = name;
            this.gpa  = gpa;
        }

        /** Сравниваем по среднему баллу */
        @Override
        public int compareTo(Student other) {
            return Double.compare(this.gpa, other.gpa);
        }

        @Override
        public String toString() {
            return String.format("Student{%s, gpa=%.1f}", name, gpa);
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  Вспомогательный метод: печать N следующих элементов
    // ─────────────────────────────────────────────────────────────
    @SuppressWarnings("unchecked")
    static <T extends Comparable<T>> void printNext(Iterator<T> it, int count, String label) {
        System.out.println(label);
        for (int i = 0; i < count; i++) {
            System.out.println("  next() → " + it.next());
        }
    }

    @SuppressWarnings("unchecked")
    static <T extends Comparable<T>> void printPrev(
            GenericContainer.CircularFilteredIterator it, int count, String label) {
        System.out.println(label);
        for (int i = 0; i < count; i++) {
            System.out.println("  prev() → " + it.prev());
        }
    }

    // ─────────────────────────────────────────────────────────────
    //  Демонстрация
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {

        System.out.println("=== Вариант 2: Двунаправленный кольцевой итератор ===\n");

        // ── 1. Встроенный тип: Integer ────────────────────────
        System.out.println("──────────────────────────────────────────");
        System.out.println(" Тип: Integer");
        System.out.println("──────────────────────────────────────────");

        GenericContainer<Integer> intBox = new GenericContainer<>();
        // Добавляем: первый = 5 → порог фильтра = 5
        // 3 и 1 будут отфильтрованы (< 5)
        int[] values = {5, 8, 3, 10, 1, 7, 6};
        System.out.print("Добавляем: ");
        for (int v : values) { intBox.add(v); System.out.print(v + " "); }
        System.out.println();
        System.out.println("Первый элемент (порог): 5");
        System.out.println("Отфильтровываются элементы < 5 → (3, 1)\n");

        GenericContainer<Integer>.CircularFilteredIterator itInt =
                (GenericContainer<Integer>.CircularFilteredIterator) intBox.iterator();

        System.out.println("Видимых элементов: " + itInt.visibleCount());

        System.out.println("\n▶ next() × 6 (два полных круга через 5 элементов):");
        for (int i = 0; i < 6; i++) {
            System.out.println("  [" + i + "] next() → " + itInt.next());
        }

        System.out.println("\n▶ prev() × 4 (назад):");
        for (int i = 0; i < 4; i++) {
            System.out.println("  [" + i + "] prev() → " + itInt.prev());
        }

        // ── 2. Собственный тип: Student ───────────────────────
        System.out.println("\n──────────────────────────────────────────");
        System.out.println(" Тип: Student (сравнение по среднему баллу)");
        System.out.println("──────────────────────────────────────────");

        GenericContainer<Student> studentBox = new GenericContainer<>();
        // Первый студент gpa=4.0 → порог = 4.0
        // Студенты с gpa < 4.0 будут отфильтрованы
        studentBox.add(new Student("Алиса",   4.0));
        studentBox.add(new Student("Борис",   3.5)); // < 4.0 → фильтр
        studentBox.add(new Student("Вера",    4.8));
        studentBox.add(new Student("Григорий",2.9)); // < 4.0 → фильтр
        studentBox.add(new Student("Дарья",   4.0));
        studentBox.add(new Student("Егор",    5.0));

        System.out.println("Добавлено 6 студентов, порог gpa >= 4.0");
        System.out.println("Отфильтрованы: Борис(3.5), Григорий(2.9)\n");

        GenericContainer<Student>.CircularFilteredIterator itSt =
                (GenericContainer<Student>.CircularFilteredIterator) studentBox.iterator();

        System.out.println("Видимых студентов: " + itSt.visibleCount());

        System.out.println("\n▶ next() × 5 (чуть больше одного круга из 4):");
        for (int i = 0; i < 5; i++) {
            System.out.println("  [" + i + "] next() → " + itSt.next());
        }

        System.out.println("\n▶ prev() × 3:");
        for (int i = 0; i < 3; i++) {
            System.out.println("  [" + i + "] prev() → " + itSt.prev());
        }

        System.out.println("\n▶ for-each (Iterator стандартный, только next):");
        for (Student s : studentBox) {
            System.out.println("  " + s);
            // Чтобы не зациклиться — останавливаемся после полного круга
            // (for-each здесь будет бесконечным — покажем с break для демо)
            break; // в реальных задачах кольцевой итератор используют с явным счётчиком
        }
        System.out.println("  ... (for-each c кольцевым итератором бесконечен — используйте явный счётчик)");
    }
}
