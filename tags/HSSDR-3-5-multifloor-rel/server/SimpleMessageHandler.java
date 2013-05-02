package server;

public class SimpleMessageHandler implements MessageHandler {

	@Override
	public String handle(String message) {
		System.out.println(message);
		return 	  "Thanks for : "+message.length();
		
	}

}
