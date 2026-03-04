public class MyCharacter {
        private final char value;
    
    
    public MyCharacter(char value) {
        this.value = value;  
    }

    public char charValue() {
        return value;
    }  
    

    public int compareTo(MyCharacter anotherCharacter) {
        return this.value - anotherCharacter.value;
    }

    public boolean equals(Object anotherCharacter) {
        if (anotherCharacter instanceof MyCharacter) {
            return this.value == ((MyCharacter) anotherCharacter).value;
        }
        return false;  
    }
    

    public boolean isDigit() {
        return this.value >= '0' && this.value <= '9';
    }
    

    public static boolean isDigit(MyCharacter ch) {
        return ch != null && ch.isDigit();
    }
    

    public static boolean isLetter(MyCharacter ch) {
        if (ch == null) return false;
        
        char c = ch.charValue();  
        boolean isRussian = (c >= 'А' && c <= 'Я') || (c >= 'а' && c <= 'я');
        boolean isEnglish = (c >= 'A' && c <= 'Z') || (c >= 'a' && c <= 'z');
        
        return isRussian || isEnglish;
    }
    

    public static boolean isLetterOrDigit(MyCharacter ch) {

        if (ch == null) return false;

        return isLetter(ch) || ch.isDigit();
    }
}