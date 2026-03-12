public class Student implements Comparable<Student> {
    private String name;
    private double grade;
    private int age;
    
    public Student(String name, double grade, int age) {
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
    public int compareTo(Student other) {
        if (this.grade > other.grade) {
            return 1;
        } else if (this.grade < other.grade) {
            return -1;
        } else {
            return Integer.compare(this.age, other.age);
        }
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return Double.compare(student.grade, grade) == 0 && 
               age == student.age &&
               name.equals(student.name);
    }
    
    @Override
    public String toString() {
        return String.format("Студент: %s (оценка: %.1f, возраст: %d)", 
                            name, grade, age);
    }
}