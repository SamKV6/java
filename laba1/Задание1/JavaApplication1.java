public class JavaApplication1 {

    public static void main(String[] args) {
        Rectangle r1  = new Rectangle();
        Rectangle r2 = new Rectangle(3,4);
        System.out.println(r1);
        System.out.println(r2);
        
        Rectangle[] rArr = new Rectangle[5];
        rArr[0] = new Rectangle(2, 3);
        rArr[1] = new Rectangle(5, 8);
        rArr[2] = new Rectangle(1, 4);
        rArr[3] = new Rectangle(2, 15);
        rArr[4] = new Rectangle(6, 2);
        
        for(int i=0; i<rArr.length; i++){
            System.out.println(rArr[i]);
        }

    }
    
}
