import java.lang.Math;
abstract class Shape extends Object{
	private double xPos;
	private double yPos;
	public Shape(){
		xPos=0;
		yPos=0;
	}
	public Shape(double x,double y){
		xPos=x;
		yPos=y;
	}
	abstract public double area();
	abstract public void stretchBy(double factor);
	public final double getXPos(){
		return xPos;
	}
	public final double getYPos(){
		return yPos;
	}
	public String toString(){
		String str="(X,Y)Position:("+xPos+","+yPos+")\n";
		return str;
	}
}
class Circle extends Shape{
	private double radius;
	public Circle(){
		radius=0;
	}
	public Circle(double r){
		radius=r;
	}
	public Circle(double x,double y,double r){
		super(x,y);
		radius=r;
	}
	public double area(){
		return radius*radius*Math.PI;
	}
	public final double getRadius(){
		return radius;
	}
	public void stretchBy(double factor){
		radius*=factor;
	}
}
class Rect extends Shape{
	private double width;
	private double height;
	public Rect(){
		width=0;
		height=0;
	}
	public Rect(double w,double h){
		width=w;
		height=h;
	}
	public Rect(double x,double y,double w,double h){
		super(x,y);
		width=w;
		height=h;
	}
	public double area(){
		return width*height;
	}
	public final double getWidth(){
		return width;
	}
	public final double getHeight(){
		return height;
	}
	public void stretchBy(double factor){
		width*=factor;
		height*=factor;
	}
}
class Cylinder extends Shape{
	private double radius;
	private double height;
	public Cylinder(){
		radius=0;
		height=0;
	}
	public Cylinder(double r,double h){
		radius=r;
		height=h;
	}
	public Cylinder(double x,double y,double r,double h){
		super(x,y);
		radius=r;
		height=h;
	}
	public double area(){
		return radius*radius*Math.PI;
	}
	public double volume(){
		return area()*height;
	}
	public final double getRadius(){
		return radius;
	}
	public final double getHeight(){
		return height;
	}
	public void stretchBy(double factor){
		radius*=factor;
		height*=factor;
	}
}
class TestShape{
	public static void main(String args[]){
		System.out.println("CIRCLE");
		Circle circle=new Circle(1.0,2.0,1.0);
		System.out.print(circle.toString());
		System.out.println("Radius: "+circle.getRadius());
		System.out.println("Area: "+circle.area());
		System.out.println();
		System.out.println("RECTANGLE");
		Rect rect=new Rect(1.0,2.0,2.0,1.0);
		System.out.print(rect.toString());
		System.out.println("Width & Height: "+rect.getWidth()+" & "+rect.getHeight());
		System.out.println("Area: "+rect.area());
		System.out.println();
		System.out.println("CYLINDER");
		Cylinder cylinder=new Cylinder(1.0,2.0,1.0,2.0);
		System.out.print(cylinder.toString());
		System.out.println("Radius & Height: "+cylinder.getRadius()+" & "+cylinder.getHeight());
		System.out.println("Volume: "+cylinder.volume());
		System.out.println();
		System.out.println("CIRCLE");
		circle.stretchBy(2);
		System.out.print(circle.toString());
		System.out.println("Radius: "+circle.getRadius());
		System.out.println("Area: "+circle.area());
		System.out.println();
		System.out.println("RECTANGLE");
		rect.stretchBy(2);
		System.out.print(rect.toString());
		System.out.println("Width & Height: "+rect.getWidth()+" & "+rect.getHeight());
		System.out.println("Area: "+rect.area());
		System.out.println();
		System.out.println("CYLINDER");
		cylinder.stretchBy(2);
		System.out.print(cylinder.toString());
		System.out.println("Radius & Height: "+cylinder.getRadius()+" & "+cylinder.getHeight());
		System.out.println("Volume: "+cylinder.volume());

	}
}
