class MyException extends Exception{
	String message;
	MyException(){
		message="出现异常";
	}
	public String toString(){
		return message;
	}
}
class Student{
	public void speak(int m) throws MyException{
		if(m>1000)throw new MyException();
	}
}
class A{
	public static void main(String args[]){
		Student stu=new Student();
		try{
			stu.speak(1000);
		}
		catch(MyException e){
			System.out.println(e.toString());
		}
	}
}
