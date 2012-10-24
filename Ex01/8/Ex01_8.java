class SubClass extends MyClass
{
	int Mydata;
	public int getMydata()
	{
		return Mydata;
	}
	public void setMydata(int a)
	{
		Mydata=a;
	}
}
class SubClassTest
{
	public static void main(String[] args) 
	{
		SubClass b=new SubClass();
		b.setMydata(200);
		b.setData(300);
		System.out.println(b.getMydata());
		System.out.println(b.getData());
	}
}