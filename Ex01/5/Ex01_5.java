class RandomnSort{
	public static void main(String args[]){
		int length=100;
		int randint[]=new int[length];
		for(int i=0;i<length;i++)randint[i]=(int)(Math.random()*90000)+10000;
		for(int i=0;i<length;i++)
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
