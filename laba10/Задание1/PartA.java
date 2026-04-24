public class PartA {
    private final int id;
    
    public PartA(int id) {
        this.id = id;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "PartA{id=" + id + "}";
    }
}