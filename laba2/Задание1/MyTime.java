import java.util.Calendar;

public class MyTime {
    public int hour;
    public int minute;
    public int second;
    
    // Безаргументный конструктор для текущего времени
    public MyTime() {
        long currentTimeMillis = System.currentTimeMillis();
        setTime(currentTimeMillis);
    }
    
    // Конструктор с временем в миллисекундах
    public MyTime(long elapseTime) {
        setTime(elapseTime);
    }
    
    // Конструктор с указанными часами, минутами и секундами
    public MyTime(int hour, int minute, int second) {
        this.hour = hour;
        this.minute = minute;
        this.second = second;
    }
    
    // Getter-методы
    public int getHour() {
        return hour;
    }
    
    public int getMinute() {
        return minute;
    }
    
    public int getSecond() {
        return second;
    }
    
    // Метод для установки времени на основе прошедших миллисекунд
    public void setTime(long elapseTime) {
        // Общее количество секунд
        long totalSeconds = elapseTime / 1000;
        
        // Текущая секунда (остаток от деления на 60)
        this.second = (int)(totalSeconds % 60);
        
        // Общее количество минут
        long totalMinutes = totalSeconds / 60;
        
        // Текущая минута (остаток от деления на 60)
        this.minute = (int)(totalMinutes % 60);
        
        // Общее количество часов
        long totalHours = totalMinutes / 60;
        
        // Текущий час (с учетом часового пояса, остаток от деления на 24)
        // Добавляем 3 для московского часового пояса (UTC+3)
        this.hour = (int)((totalHours + 3) % 24);
    }
    
   
    public String toString() {
        return String.format("%02d:%02d:%02d", hour, minute, second);
    }
}
