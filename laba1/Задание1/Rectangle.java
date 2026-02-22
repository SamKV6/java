public class Rectangle {
    public double height = -1;
    public double width = -1;
    public Rectangle(){
    }
    public Rectangle(double _height, double _width){
        height = _height;
        width = _width;
    }
    public double getArea(){
        return height*width;
    }
    public double getPerimeter(){
        return (height+width) *2;
    }
    public String toString(){
        return ""+height+", "+width+", "+getArea()+", "+getPerimeter();
    }
}

