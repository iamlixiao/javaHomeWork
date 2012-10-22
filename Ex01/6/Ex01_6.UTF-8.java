class IntErrors{
	static int status;
	public static void main(String args[]){
		status=Integer.parseInt(args[0]);
		int b0=1,b1=2,b2=4,b3=8;
		if((status&b0)>0)System.out.println("准备接受请求");
		if((status&b1)>0)System.out.println("正在处理一个请求");
		if((status&b2)>0)System.out.println("恢复过程中的错误");
		if((status&b3)>0)System.out.println("不可恢复的错误");
	}
}
/*
（1）  测试status的值等于8时，输出什么信息？
不可恢复的错误
（2）  测试status的值等于7时，输出什么信息？
准备接受请求
正在处理一个请求
恢复过程中的错误
*/