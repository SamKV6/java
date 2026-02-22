import java.util.Random;

public class TestStopWatch {
    public static void main(String[] args) {
        // Количество чисел для сортировки
        int arraySize = 100000; // Можно увеличить до 200000, 500000 и т.д.
        
        // Создаем массив случайных чисел
        int[] numbers = new int[arraySize];
        Random random = new Random();
        
        // Заполняем массив случайными числами
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(1000000); // Случайные числа от 0 до 999999
        }
        
        // Создаем секундомер
        StopWatch stopWatch = new StopWatch();
        
        // Запускаем секундомер
        stopWatch.start();
        
        // Сортируем массив методом пузырька
        bubbleSort(numbers);
        
        // Останавливаем секундомер
        stopWatch.stop();
        
        // Получаем прошедшее время
        long elapsedTime = stopWatch.getElapsedTime();
        
        System.out.println((elapsedTime / 1000.0) + " секунд");
        
        // Проверка, что массив действительно отсортирован (выводим первые 10 элементов)
        System.out.println("\nПервые 10 элементов отсортированного массива:");
        for (int i = 0; i < 10; i++) {
            System.out.print(numbers[i] + " ");
        }
        System.out.println();
        
        // Дополнительная информация о секундомере
        System.out.println("\nИнформация о секундомере:");
        System.out.println("Время старта: " + stopWatch.getStartTime() + " мс");
        System.out.println("Время остановки: " + stopWatch.getEndTime() + " мс");
    }
    
    // Метод сортировки пузырьком
    public static void bubbleSort(int[] arr) {
        int n = arr.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                // Если текущий элемент больше следующего, меняем их местами
                if (arr[j] > arr[j + 1]) {
                    // Меняем местами
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // Если не было обменов, массив уже отсортирован
            if (!swapped) {
                break;
            }
        }
    }
}