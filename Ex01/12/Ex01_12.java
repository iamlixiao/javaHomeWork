class Gcd{
	public int f(int a,int b){
		int t1=a<b?a:b,t2=a>b?a:b;
		while(true){
			t2=t2%t1;
			if(t2==0)return t1;
			else{
				t1=t1%t2;
				if(t1==0)return t2;
			}
		}
	}
}
class Lcm{
	public int f(int a,int b){
		Gcd g=new Gcd();
		return a*b/g.f(a,b);
	}
}
class GcdTst{
	public static void main(String args[]){
		Gcd g=new Gcd();
		System.out.println(g.f(129,18));
		Lcm l=new Lcm();
		System.out.println(l.f(129,18));
	}
}
		
