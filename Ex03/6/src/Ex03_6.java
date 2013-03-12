class Ex03_6 implements Runnable {

	Thread 张某, 李某, 赵某;
	int 五元 = 3;

	public Ex03_6() {
		张某 = new Thread(this);
		张某.setName("张某");
		李某 = new Thread(this);
		李某.setName("李某");
		赵某 = new Thread(this);
		赵某.setName("赵某");
		五元 = 3;
		张某.start();
		李某.start();
		赵某.start();
	}

	synchronized void buy(int x) {
		if (x == 5) {
			五元++;
			System.out.println(Thread.currentThread().getName() + "给了5元,找赎0元");
		} else if (x == 10) {
			while (五元 < 1) {
				try {
					System.out.println(Thread.currentThread().getName()
							+ "靠边等。。。");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			五元--;
			System.out.println(Thread.currentThread().getName() + "给了10元,找赎5元");
		} else if (x == 20) {
			while (五元 < 3) {

				try {
					System.out.println(Thread.currentThread().getName()
							+ "靠边等。。。");
					wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			五元 -= 3;
			System.out.println(Thread.currentThread().getName() + "给了20元,找赎15元");
		}
		this.notifyAll();
	}

	@Override
	public void run() {
		if (Thread.currentThread() == 张某) {
			buy(20);
		}
		if (Thread.currentThread() == 李某) {
			try {
				李某.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			buy(10);
		}
		if (Thread.currentThread() == 赵某) {
			try {
				赵某.sleep(20);
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
