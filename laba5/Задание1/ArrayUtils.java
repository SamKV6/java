public class ArrayUtils {
    
    /**
     * Находит максимальное и минимальное значения в массиве
     * @param array входной массив элементов, реализующих Comparable
     * @return массив из двух элементов [max, min] в порядке убывания
     * @throws IllegalArgumentException если массив null или содержит меньше 2 элементов,
     *                                  или содержит null элементы
     */
    public static Comparable[] maxAndMin(Comparable[] array) {
        // Проверка на null и длину массива
        if (array == null || array.length < 2) {
            throw new IllegalArgumentException("Массив должен содержать минимум 2 элемента");
        }
        
        // Проверка на null элементы
        for (int i = 0; i < array.length; i++) {
            if (array[i] == null) {
                throw new IllegalArgumentException("Массив не должен содержать null элементы");
            }
        }
        
        // Инициализируем начальные значения
        Comparable max = array[0];
        Comparable min = array[0];
        
        // Проходим по массиву, начиная со второго элемента
        for (int i = 1; i < array.length; i++) {
            if (array[i].compareTo(max) > 0) {
                max = array[i];
            }
            if (array[i].compareTo(min) < 0) {
                min = array[i];
            }
        }
        
        // Возвращаем массив [max, min] в порядке убывания
        return new Comparable[]{max, min};
    }
}