
class Hello {
	public static void main(String[] args) {
		System.out.print("我的第一个ＪＡＶＡ作品\n学号：10300240007\n姓名：李霄\n爱好：ＪＡＶＡ");
	}
}
/*

（1）编译器怎样提示丢失大括号的错误？
a.java:6: 进行语法解析时已到达文件结尾
}
 ^
1 错误
（2）编译器怎样提示语句丢失分号的错误？
a.java:4: 需要 ';'
                System.out.print("我的第一个ＪＡＶＡ作品")
                                               ^
1 错误
（3）编译器怎样提示将System写成system的错误？
a.java:4: 软件包 system 不存在
                system.out.print("我的第一个ＪＡＶＡ作品");
                      ^
1 错误
（4）编译器怎样提示将String写成string的错误？
a.java:3: 找不到符号
符号： 类 string
位置： 类 Hello
        public static void main(string[] args) {
                                ^
1 错误
（5）任意改动程序，编译器怎样提示各种错误？
随便加一行
		sjdfjal;
提示
a.java:5: 不是语句
                sjdfjal;
                ^
1 错误
将static改为stati，提示
a.java:3: 需要 <标识符>
        public stati void main(String[] args) {
                    ^
a.java:3: 方法声明无效；需要返回类型
        public stati void main(String[] args) {
                          ^
2 错误
*/
