public class TestMyString2 {
    public static void main(String[] args) {
        char[] chars1 = {'П', 'р', 'и', 'в', 'е', 'т'};
        MyString2 str1 = new MyString2(chars1);
        
        char[] chars2 = {'М', 'и', 'р'};
        MyString2 str2 = new MyString2(chars2);
        
        MyString2 str3 = new MyString2(new char[]{'П', 'р', 'и', 'в', 'е', 'т'});
        
        // Демонстрация substring
        System.out.println(str1);                    // Привет
        System.out.println(str1.substring(2));       // ивет
        System.out.println(str1.substring(0, 3));    // При
        System.out.println();


        // Демонстрация equals
        System.out.println(str1.equals(str2));       // false
        System.out.println(str1.equals(str3));       // true
        System.out.println();



        // Демонстрация compareTo
        MyString2 aaa = new MyString2(new char[]{'a', 'a', 'a'});
        MyString2 aab = new MyString2(new char[]{'a', 'a', 'b'});
        MyString2 aa = new MyString2(new char[]{'a', 'a'});
        System.out.println();




        System.out.println(aaa.compareTo(aab));      // -1
        System.out.println(aab.compareTo(aaa));      // 1
        System.out.println(aaa.compareTo(aa));       // 1
        System.out.println();


        
        // Демонстрация toChars
        char[] chars = str1.toChars();
        for (char c : chars) {
            System.out.print(c + " ");               // П р и в е т 
        }
        System.out.println();
        System.out.println();

        
        // Демонстрация valueOf
        System.out.println(MyString2.valueOf(true));    // true
        System.out.println(MyString2.valueOf(false));   // false
    }
}