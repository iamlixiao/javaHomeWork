class Ex03_6 implements Runnable {

	Thread ��ĳ, ��ĳ, ��ĳ;
	int ��Ԫ = 3;

	public Ex03_6() {
		��ĳ = new Thread(this);
		��ĳ.setName("��ĳ");
		��ĳ = new Thread(this);
		��ĳ.setName("��ĳ");
		��ĳ = new Thread(this);
		��ĳ.setName("��ĳ");
		��Ԫ = 3;
		��ĳ.start();
		��ĳ.start();
		��ĳ.start();
	}

	synchronized void buy(int x) {
		if (x == 5) {
			��Ԫ++;
			System.out.println(Thread.currentThread().getName() + "����5Ԫ,����0Ԫ");
		} else if (x == 10) {
			while (��Ԫ < 1) {
				try {
					System.out.println(Thread.currentThread().getName()
							+ "���ߵȡ�����");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			��Ԫ--;
			System.out.println(Thread.currentThread().getName() + "����10Ԫ,����5Ԫ");
		} else if (x == 20) {
			while (��Ԫ < 3) {

				try {
					System.out.println(Thread.currentThread().getName()
							+ "���ߵȡ�����");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			��Ԫ -= 3;
			System.out.println(Thread.currentThread().getName() + "����20Ԫ,����15Ԫ");
		}
		this.notifyAll();
	}

	@Override
	public void run() {
		if (Thread.currentThread() == ��ĳ) {
			buy(20);
		}
		if (Thread.currentThread() == ��ĳ) {
			try {
				��ĳ.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buy(10);
		}
		if (Thread.currentThread() == ��ĳ) {
			try {
				��ĳ.sleep(20);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buy(5);
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new Ex03_6();
	}

}