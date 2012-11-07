import java.util.StringTokenizer;
public class WordCount{
	StringTokenizer counter;
	class Counter{
		public String word;
		public int count;
		public boolean before(Counter c){
			return word.compareToIgnoreCase(c.word)<0;
		}
	}
	WordCount(String s){
		String delims=new String();
		for(int i=0;i<'A';++i){
			delims+=(char)i;
			delims+=' ';
		}
		for(int i='Z'+1;i<'a';++i){
			delims+=(char)i;
			delims+=' ';
		}
		for(int i='z'+1;i<128;++i){
			delims+=(char)i;
			delims+=' ';
		}
		counter=new StringTokenizer(s,delims);
		System.out.println(counter.countTokens());
		Counter words[]=new Counter[counter.countTokens()];
		outer:while(counter.hasMoreTokens()){
			String t=counter.nextToken().toLowerCase();
			int i;
			for(i=0;words[i]!=null;i++){
				if(words[i].word.equals(t)){
					words[i].count++;
					continue outer;
				}
			}
			words[i]=new Counter();
			words[i].word=t;
			words[i].count=1;
		}
		for(int i=0;words[i]!=null;i++){
			Counter c=words[i];
			int k=i;
			for(int j=i;words[j]!=null;j++){
				if(words[j].before(c)){
					c=words[j];
					k=j;
				}
			}
			words[k]=words[i];
			words[i]=c;
		}
		for(int i=0;words[i]!=null;i++){
			System.out.println(words[i].word+' '+words[i].count);
		}
	}
}
