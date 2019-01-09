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
		private static final Eager instance = new Eager();
		private Eager(){}
		public static Eager getInstance()
		{
			return instance;
		}
	}
	
	public static class EagerWithStaticBlock {
		// Same as Eager with added advantage of giving option to handle exception
		private static EagerWithStaticBlock instance;
		private EagerWithStaticBlock(){}
		static {
			try {
				instance = new Eager();
			}
			catch(Exception e) {
				throw new RuntimeException("Exception");
			}
		}
		public static EagerWithStaticBlock getInstance()
		{
			return instance;
		}
	}

	public static class Lazy {
		// Better than Early/Eager Initialization but not thread safe
		private static Lazy instance ;
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
		private static ThreadSafe instance ;
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
		private static DoubleCheckLock instance ;
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
	
	public static class DoubleCheckLockWithVolatile {
		// it resolves the problem of half baked instance
		/*
			To understand the half backed instance situation ( consider above class "DoubleCheckLock" )
			Say Thread1 reached Line-89 and started the process of object creation, but
			yet object creation is not completed. Now if in the meantime Thread2 check Line-84 and
			got that instance != null, so it got the half baked instance and Thread2 returned.
			To avoid such situation, we need to make instance as volatile, because volatile instance guarantees
			the completion of all the steps of object creation before another thread reads the reerence.
		*/
		private static volatile DoubleCheckLockWithVolatile instance ;
		private DoubleCheckLockWithVolatile(){}
		public static DoubleCheckLockWithVolatile getInstance()
		{
			if(instance == null)
			{
				synchronized (DoubleCheckLockWithVolatile.class) {
					if(instance == null)
					{
						instance = new DoubleCheckLockWithVolatile();
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
