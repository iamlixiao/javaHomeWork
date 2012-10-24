class A{
	final public void f(){
		String out;
		for(int i=65;i<91;i++){
			System.out.println((char)i+"\t"+(char)(i+32));
		}
	}
}
class B extends A{
	public void g(){
		for(int i=913;i<938;i++){
			if(i!=930){//930为Unicode保留字符
				System.out.print((char)i);
				System.out.print('\t');
				System.out.println((char)(i+32));
			}
		}
	}
}
public class Ex01_9 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		A a=new A();
		//a.f();
		B b=new B();
		b.f();
		b.g();
	}

}
