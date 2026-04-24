public class Product {
    private final int id;
    private final long creationTime;
    
    public Product(int id) {
        this.id = id;
        this.creationTime = System.currentTimeMillis();
    }
    
    public int getId() {
        return id;
    }
    
    public long getCreationTime() {
        return creationTime;
    }
    
    @Override
    public String toString() {
        return "Product{id=" + id + "}";
    }
}