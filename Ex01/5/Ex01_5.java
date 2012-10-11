class RandomnSort{
	static void quicksort(int a[],int l,int r)
	{
		if(l<r){
			int p=a[l-1],i=l-1,j=r;
			while(i<j){
				while(a[++i]<p&&i<j);
				while(a[--j]>p);
				if(i<j){
					int t=a[i];
					a[i]=a[j];
					a[j]=t;
				}
			}
			a[l-1]=a[j];
			a[j]=p;
			if(l-1<j-1)quicksort(a,l,j);
			if(i+1<r-1)quicksort(a,j+2,r);
		}
	}
	public static void main(String args[]){
		int randint[]=new int[10];
		for(int i=0;i<10;i++)randint[i]=(int)(Math.random()*90000)+10000;
		quicksort(randint,1,10);
		for(int i=0;i<1;i++){
			for(int j=0;j<10;j++)System.out.print(randint[10*i+j] + " ");
			System.out.println();
		}
	}
}
