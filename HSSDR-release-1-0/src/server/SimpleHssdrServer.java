package server;

public class SimpleHssdrServer {

	public static void main(String[] args) {
    	HssdrServer example = new HssdrServer();
        example.handleConnection(new SimpleMessageHandler());
    }
}
