class IntErrors{
	static int status;
	public static void main(String args[]){
		status=Integer.parseInt(args[0]);
		int b0=1,b1=2,b2=4,b3=8;
		if((status&b0)>0)System.out.println("׼����������");
		if((status&b1)>0)System.out.println("���ڴ���һ������");
		if((status&b2)>0)System.out.println("�ָ������еĴ���");
		if((status&b3)>0)System.out.println("���ɻָ��Ĵ���");
	}
}
/*
��1��  ����status��ֵ����8ʱ�����ʲô��Ϣ��
���ɻָ��Ĵ���
��2��  ����status��ֵ����7ʱ�����ʲô��Ϣ��
׼����������
���ڴ���һ������
�ָ������еĴ���
*/