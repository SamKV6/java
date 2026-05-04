// Функциональный интерфейс — особый вид интерфейса с ОДНИМ методом
@FunctionalInterface
interface StringComparator {
    // Метод принимает две строки и возвращает одну
    String compare(String a, String b);
}
