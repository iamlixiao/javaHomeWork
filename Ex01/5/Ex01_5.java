class RandomnSort{
	public static void main(String args[]){
		int length=100;
		int randint[]=new int[length];
		for(int i=0;i<length;i++)randint[i]=(int)(Math.random()*90000)+10000;
		for(int i=0;i<length+1;i++)
			for(int j=0;j<i;j++)
				if(randint[i]>randint[j])
				{
					int t=randint[i];
					for(int k=i;k>j;k--)randint[k]=randint[k-1];
					randint[j]=t;
					break;
				}
		for(int i=0;i<10;i++){
			for(int j=0;j<10;j++)System.out.print(randint[10*i+j] + " ");
			System.out.println();
		}
	}
}
/*
（1）当数组元素超出数组定义的长度时，编译能通过吗？会提示怎样的信息？
不能通过，提示
Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 100
	at RandomnSort.main(Ex01_5.java:8)
*/
