import java.util.Scanner;
class wanshu {
	public static void main(String[] args) {
		int i,j,m;
		Scanner reader=new Scanner(System.in);
		m=reader.nextInt(); 
		for(i=1;i<=m;i++){
		int sum=0;
			for(j=1;j<i;j++){
				if(i%j==0)
				sum=sum+j;
			}   
		if(sum==i)
		System.out.println(i+"  ");
		}
	}
}
