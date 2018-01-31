package com.cp;

public class Signleton {

	public static void main(String[] args) {

		BestSingleton obj1 = BestSingleton.INSTANCE;
		obj1.i = 5;
		BestSingleton obj2 = BestSingleton.INSTANCE;
		//obj2.i = 7;
		//obj1.show();
		//obj2.show();
		
		Thread t1 = new Thread(new Runnable() {

			@Override
			public void run() {
				BestSingleton obj1 = BestSingleton.INSTANCE;
				obj1.i = 5;
			}
			
		});
		
		Thread t2 = new Thread(new Runnable() {

			@Override
			public void run() {
				BestSingleton obj1 = BestSingleton.INSTANCE;
				//obj1.i = 7;
				obj1.show();
			}
			
		});
		t1.start();
		t2.start();
		
	}

	public static class Eager {
		// Early or Eager initialization leads to creation of global object 
		// which always use memory even if not required
		static Eager instance = new Eager();
		private Eager(){}
		public static Eager getInstance()
		{
			return instance;
		}
	}

	public static class Lazy {
		// Better than Early/Eager Initialization but not thread safe
		static Lazy instance ;
		private Lazy(){}
		public static Lazy getInstance()
		{
			if(instance == null)
			{
				instance = new Lazy();
			}
			return instance;
		}
	}

	public static class ThreadSafe {
		// applying synchronize over method reduces performance tremendously
		static ThreadSafe instance ;
		private ThreadSafe(){}
		public static synchronized ThreadSafe getInstance()
		{
			if(instance == null)
			{
				instance = new ThreadSafe();
			}
			return instance;
		}
	}

	public static class DoubleCheckLock {
		// it also reduce performance but better than using Synchronize directly over method
		static DoubleCheckLock instance ;
		private DoubleCheckLock(){}
		public static DoubleCheckLock getInstance()
		{
			if(instance == null)
			{
				synchronized (DoubleCheckLock.class) {
					if(instance == null)
					{
						instance = new DoubleCheckLock();
					}
				}
			}
			return instance;
		}
	}
	
	enum BestSingleton {
		INSTANCE;
		int i;
		public void show() {
			System.out.println(i);
		}
	}

}
