public class PartB {
    private final int id;
    private final PartC partC;
    private final PartD partD;
    
    public PartB(int id, PartC partC, PartD partD) {
        this.id = id;
        this.partC = partC;
        this.partD = partD;
    }
    
    public int getId() {
        return id;
    }
    
    @Override
    public String toString() {
        return "PartB{id=" + id + ", partC=" + partC.getId() + 
               ", partD=" + partD.getId() + "}";
    }
}