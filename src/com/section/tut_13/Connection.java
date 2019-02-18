package com.section.tut_13;

import java.util.concurrent.Semaphore;

public class Connection {

	private static Connection instance = new Connection();

	private int count;

	Semaphore sem = new Semaphore(5); // 5 available permits/or connections allowed

	private Connection() {

	}

	public static Connection getInstance() {
		return instance;
	}

	public void connect() {
		try {
			
			doConnect();
			
		} catch (InterruptedException ie) {

		} finally {
			
			sem.release();
		}

	}

	public void doConnect() throws InterruptedException {
		try {
			sem.acquire();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		synchronized (this) {
			count++;
			System.out.println("Connection number :" + count);
		}

		try {
			Thread.sleep(3200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		synchronized (this) {
			count--;
		}

	}

}
