public class TestMyCharacter {
    public static void main(String[] args) {
        // Создаем объекты для тестирования
        MyCharacter charA = new MyCharacter('A');
        MyCharacter charB = new MyCharacter('B');
        MyCharacter char5 = new MyCharacter('5');
        MyCharacter charRussian = new MyCharacter('П');
        MyCharacter charAt = new MyCharacter('@');
        
        // Тест 1: Проверка конструктора и charValue()
        System.out.println("Тест конструктора и charValue():");
        System.out.println(charA.charValue());
        System.out.println(char5.charValue());
        System.out.println();
        
        // Тест 2: Проверка compareTo()
        System.out.println("Тест compareTo():");
        System.out.println(charA.compareTo(charB));
        System.out.println(charB.compareTo(charA));
        System.out.println(charA.compareTo(new MyCharacter('A')));
        System.out.println(char5.compareTo(new MyCharacter('0')));
        System.out.println();
        
        // Тест 3: Проверка equals()
        System.out.println("Тест equals():");
        System.out.println(charA.equals(new MyCharacter('A')));
        System.out.println(charA.equals(charB));
        System.out.println(charA.equals(char5));
        System.out.println(charA.equals("A"));
        System.out.println();
        
        // Тест 4: Проверка нестатического isDigit()
        System.out.println("Тест нестатического isDigit():");
        System.out.println(charA.isDigit());
        System.out.println(char5.isDigit());
        System.out.println(charRussian.isDigit());
        System.out.println(charAt.isDigit());
        System.out.println();
        
        // Тест 5: Проверка статического isDigit()
        System.out.println("Тест статического isDigit():");
        System.out.println(MyCharacter.isDigit(charA));
        System.out.println(MyCharacter.isDigit(char5));
        System.out.println(MyCharacter.isDigit(null));
        System.out.println();
        
        // Тест 6: Проверка isLetter()
        System.out.println("Тест isLetter():");
        System.out.println(MyCharacter.isLetter(charA));
        System.out.println(MyCharacter.isLetter(char5));
        System.out.println(MyCharacter.isLetter(charRussian));
        System.out.println(MyCharacter.isLetter(charAt));
        System.out.println(MyCharacter.isLetter(null));
        System.out.println();
        
        // Тест 7: Проверка isLetterOrDigit()
        System.out.println("Тест isLetterOrDigit():");
        System.out.println(MyCharacter.isLetterOrDigit(charA));
        System.out.println(MyCharacter.isLetterOrDigit(char5));
        System.out.println(MyCharacter.isLetterOrDigit(charRussian));
        System.out.println( MyCharacter.isLetterOrDigit(charAt));
        System.out.println(MyCharacter.isLetterOrDigit(null));
    }
}