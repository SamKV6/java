public class Person1 implements Comparable<Person1> {
    private String name;
    private double grade;  // оценка (средний балл)
    private int age;       // возраст
    
    public Person1(String name, double grade, int age) {
        this.name = name;
        this.grade = grade;
        this.age = age;
    }
    
    public String getName() {
        return name;
    }
    
    public double getGrade() {
        return grade;
    }
    
    public int getAge() {
        return age;
    }
    
    @Override
    public int compareTo(Person1 other) {
        // Сравниваем по оценке (среднему баллу)
        if (this.grade > other.grade) {
            return 1;  // этот человек лучше (у него балл выше)
        } else if (this.grade < other.grade) {
            return -1; // этот человек хуже
        } else {
            // Если оценки равны, сравниваем по возрасту (младше = лучше)
            return Integer.compare(this.age, other.age);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Person1 person = (Person1) obj;
        return Double.compare(person.grade, grade) == 0 && 
               age == person.age &&
               name.equals(person.name);
    }
    
    @Override
    public String toString() {
        return String.format("%s (оценка: %.1f, возраст: %d)", name, grade, age);
    }
}