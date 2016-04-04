package server;

import util.Logger;

public class SimpleMessageHandler implements MessageHandler {

	@Override
	public String handle(String message) {
		Logger.LOGGER.debug(message);
		return 	  "Thanks for : "+message.length();
		
	}

}
