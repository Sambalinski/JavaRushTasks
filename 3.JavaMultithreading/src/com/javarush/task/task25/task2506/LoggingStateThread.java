package com.javarush.task.task25.task2506;

public class LoggingStateThread extends Thread {
	
	private Thread target;
	
	public LoggingStateThread(Thread target) {
		super(target);
		this.setDaemon(true);
		this.target = target;
	}
	
	@Override
	public void run() {
		super.run();
		
		State state, lastState;
		
		state = target.getState();
		lastState = state;
		
		System.out.println(state.toString());
		do
		{
			state = target.getState();
			if (state != lastState)
			{
				System.out.println(state.toString());
				lastState = state;
			}
		} while (state != State.TERMINATED);
		
	}
}
