package com.javarush.task.task30.task3008.client;

import com.javarush.task.task30.task3008.Connection;
import com.javarush.task.task30.task3008.Message;

import java.io.IOException;

import static com.javarush.task.task30.task3008.MessageType.*;
import static com.javarush.task.task30.task3008.ConsoleHelper.*;

public class Client {
	protected Connection connection;
	private volatile boolean clientConnected = false;
	
	protected String getServerAddress() {
		return readString();
	}
	
	protected int getServerPort() {
		return readInt();
	}
	
	protected String getUserName() {
		return readString();
	}
	
	protected boolean shouldSendTextFromConsole() {
		return true;
	}
	
	protected SocketThread getSocketThread() {
		return new SocketThread();
	}
	
	protected void sendTextMessage(String text) {
		try
		{
			connection.send(new Message(TEXT, text));
		} catch (IOException e)
		{
			e.printStackTrace();
			clientConnected = false;
		}
	}
	
	public void run() {
		SocketThread socketThread = getSocketThread();
		socketThread.setDaemon(true);
		socketThread.start();
		
		synchronized (this)
		{
			try
			{
				this.wait();
			} catch (InterruptedException e)
			{
				writeMessage("Ошибка исполнения во время ожидания");
				e.printStackTrace();
				return;
			}
			
			if (clientConnected)
			{
				writeMessage("Соединение установлено. Для выхода наберите команду 'exit'.");
			} else
			{
				writeMessage("Произошла ошибка во время работы клиента.");
			}
			
			while (clientConnected)
			{
				String text = readString();
				if (text.equals("exit")) return;
				if (shouldSendTextFromConsole()) sendTextMessage(text);
			}
		}
	}
	
	public class SocketThread extends Thread {
		
		protected void processIncomingMessage(String message) {
			System.out.println(message);
		}
		
		protected void informAboutAddingNewUser(String userName) {
			System.out.printf("%s подключён к чату.", userName);
		}
		
		
		protected void informAboutDeletingNewUser(String userName) {
			System.out.printf("%s покинул чат.", userName);
		}
		
		protected void notifyConnectionStatusChanged(boolean clientConnected) {
			synchronized (Client.this)
			{
				Client.this.clientConnected = clientConnected;
				Client.this.notify();
			}
		}
		
		protected void clientHandshake() throws IOException, ClassNotFoundException {
		
		}
		
		@Override
		public void run() {
		
		}
	}
	
	public static void main(String[] args) {
		new Client().run();
	}
}