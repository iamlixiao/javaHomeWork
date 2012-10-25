class Teacher{
	public int number;//工号
	public String name;//姓名
	public boolean sex;//性别
	private int age;//年龄
	private String address;//家庭住址
	private String phone;//电话号码
	private String password;//口令
	Teacher(int a,String b,String c,String d){
		age=a;
		address=b;
		phone=c;
		password=d;
	}
	boolean checkPassword(String a){
		return a==password;
	}
	int getAge(String a){
		if(checkPassword(a))return age;else return 0;
	}
	String getAddress(String a){
		if(checkPassword(a))return address;else return "";
	}
	String getPhone(String a){
		if(checkPassword(a))return phone;else return "";
	}
}
class TeacherTest{
	public static void main(String[]args){
		Teacher t=new Teacher(30,"上海","50970432","12345");
		System.out.println(t.getAge("12345"));
		System.out.println(t.getAddress("12345"));
		System.out.println(t.getPhone("12345"));	
	}
}
