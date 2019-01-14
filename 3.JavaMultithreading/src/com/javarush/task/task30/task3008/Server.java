package com.javarush.task.task30.task3008;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.javarush.task.task30.task3008.ConsoleHelper.*;
import static com.javarush.task.task30.task3008.MessageType.*;

public class Server {
	public static void main(String[] args) {
		int port = ConsoleHelper.readInt();
		
		try (ServerSocket serverSocket = new ServerSocket(port))
		{
			ConsoleHelper.writeMessage("Сервер запущен");
			while (true)
			{
				try (Socket socket = serverSocket.accept())
				{
					new Handler(socket).start();
				}
			}
		} catch (IOException e)
		{
			e.printStackTrace();
		}
	}
	
	private static Map<String, Connection> connectionMap = new ConcurrentHashMap<>();
	
	public static void sendBroadcastMessage(Message message) {
		connectionMap.forEach((key, value) -> {
			try
			{
				value.send(message);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		});
	}
	
	private static class Handler extends Thread {
		
		private Socket socket;
		
		public Handler(Socket socket) {
			this.socket = socket;
		}
		
		@Override
		public void run() {
			writeMessage(String.format("Установлено соединение с удаленным адресом %s", socket.getRemoteSocketAddress()));
			String userName = "";
			try (Connection connection = new Connection(socket))
			{
				userName = serverHandshake(connection);
				sendBroadcastMessage(new Message(USER_ADDED, userName));
				sendListOfUsers(connection, userName);
				serverMainLoop(connection, userName);
				
			} catch (IOException | ClassNotFoundException e)
			{
				e.printStackTrace();
			} finally
			{
				if (!(userName.isEmpty()))
				{
					connectionMap.remove(userName);
					sendBroadcastMessage(new Message(USER_REMOVED, userName));
					
				}
			}
			
		}
		
		private String serverHandshake(Connection connection) throws IOException, ClassNotFoundException {
			while (true)
			{
				connection.send(new Message(NAME_REQUEST, "Погоняло: "));
				Message incoming = connection.receive();
				String nickName = incoming.getData();
				if (incoming.getType() == USER_NAME && !nickName.isEmpty())
				{
					if (!(connectionMap.containsKey(nickName)))
					{
						connectionMap.put(nickName, connection);
						connection.send(new Message(NAME_ACCEPTED, "Добро пожаловать на район, " + nickName + "!"));
						return nickName;
					}
				}
			}
		}
		
		private void sendListOfUsers(Connection connection, String userName) {
			Message message = new Message(USER_ADDED, userName);
			connectionMap.forEach((key, value) -> {
				if (!(key.equals(userName)))
				{
					try
					{
						value.send(message);
						connection.send(new Message(USER_ADDED, key));
					} catch (IOException e)
					{
						e.printStackTrace();
					}
				}
			});
		}
		
		private void serverMainLoop(Connection connection, String userName) throws IOException, ClassNotFoundException {
			Message message;
			String fullMessage;
			while (true)
			{
				message = connection.receive();
				if (message.getType() == TEXT)
				{
					fullMessage = String.format("%s: %s", userName, message.getData());
					sendBroadcastMessage(new Message(TEXT, fullMessage));
				} else ConsoleHelper.writeMessage("Ошибка формата сообщения");
			}
		}
	}
}
