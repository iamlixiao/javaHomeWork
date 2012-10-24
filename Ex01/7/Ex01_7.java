class MyClass 
{
	int data;
	public int getData()
	{
		return data;
	}
	public void setData(int a)
	{
		data=a;
	}
}
class MyClassTest
{
	public static void main(String[] args) 
	{
		MyClass a=new MyClass();
		a.setData(100);
		System.out.println(a.getData());
	}
}
