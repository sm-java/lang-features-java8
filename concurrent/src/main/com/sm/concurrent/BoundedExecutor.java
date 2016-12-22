package com.sm.concurrent;

import java.util.concurrent.Executor;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.Semaphore;

/**
 * A bounded Executor provides a way of controlling task submission. When tasks are submitted at a rate that
 * is beyond what the executor can handle, internally the executor queues tasks for submission. This queue by default
 * is unbounded and can result in OutOfMemoryExceptions. One way to avoid this is to provide  a bounded blocking queue to 
 * the executor for tasks to be held for submission.
 * 
 * This solution was taken from Java Concurrency in Practice.
 * 
 * 
 * @author smazumder6
 *
 */
public class BoundedExecutor {
	private final Executor exec;
	private final Semaphore semaphore;
	
	//Constructor
	public BoundedExecutor(Executor e, int upperBound) {

		this.exec = e;
		semaphore = new Semaphore(upperBound);
	}
	
	/**
	 * 
	 * @param task
	 * @throws InterruptedException
	 */
	public void submitTask(final Runnable task) throws InterruptedException {
		
		semaphore.acquire();
		try {
			exec.execute( new Runnable() {
				public void run() {
					try {
						task.run();
					} finally {
						semaphore.release();
					}
				}
			});
		} catch (RejectedExecutionException e) {
			semaphore.release();
		}	
	}
}
