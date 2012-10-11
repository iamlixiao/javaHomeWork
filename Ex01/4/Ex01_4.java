class wanshu {
	public static void main(String[] args) {
		int i,j,m;
		m=Integer.parseInt(args[0]);
		for(i=1;i<=m;i++){
		int sum=0;
			for(j=1;j<i;j++){
				if(i%j==0)
				sum=sum+j;
			}   
		if(sum==i)
		System.out.println(i+"  ");//6 28 496
		}
	}
}
