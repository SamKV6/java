public class MyString2 {
 
    private final char[] value;
    
  
    public MyString2(char[] chars) {
     
        this.value = new char[chars.length];
        for (int i = 0; i < chars.length; i++) {
            this.value[i] = chars[i];
        }
    }
    
    
    private MyString2(char[] chars, boolean copy) {
        if (copy) {
            this.value = new char[chars.length];
            for (int i = 0; i < chars.length; i++) {
                this.value[i] = chars[i];
            }
        } else {
            this.value = chars; 
        }
    }
    

    public MyString2 substring(int begin) {
        return substring(begin, value.length);
    }
    

    public MyString2 substring(int begin, int end) {
        if (begin < 0 || end > value.length || begin > end) {
            throw new IndexOutOfBoundsException("Неверные индексы: begin=" + 
                                               begin + ", end=" + end);
        }
        

        char[] result = new char[end - begin];
        for (int i = begin; i < end; i++) {
            result[i - begin] = value[i];
        }
        
        return new MyString2(result, false); 
    }
    
   
    public boolean equals(Object obj) {
      
        if (this == obj) return true;
        

        if (obj == null || getClass() != obj.getClass()) return false;
        
        MyString2 other = (MyString2) obj;

        if (this.value.length != other.value.length) return false;
 
        for (int i = 0; i < this.value.length; i++) {
            if (this.value[i] != other.value[i]) return false;
        }
        
        return true;
    }

    public int compareTo(MyString2 obj) {
        if (obj == null) return 1; 
        
       
        int minLength = Math.min(this.value.length, obj.value.length);
        
        
        for (int i = 0; i < minLength; i++) {
            if (this.value[i] != obj.value[i]) {
         
                return this.value[i] - obj.value[i];
            }
        }
        
        
        return this.value.length - obj.value.length;
    }
    
    
    public char[] toChars() {
       
        char[] result = new char[value.length];
        for (int i = 0; i < value.length; i++) {
            result[i] = value[i];
        }
        return result;
    }
    
    
    public static MyString2 valueOf(boolean b) {
        if (b) {
            return new MyString2(new char[]{'t', 'r', 'u', 'e'});
        } else {
            return new MyString2(new char[]{'f', 'a', 'l', 's', 'e'});
        }
    }
    
  
    public String toString() {
        return new String(value);
    }
    
    
    
    
    public int length() {
        return value.length;
    }
    
    
    public char charAt(int index) {
        if (index < 0 || index >= value.length) {
            throw new IndexOutOfBoundsException("Индекс вне диапазона: " + index);
        }
        return value[index];
    }
}