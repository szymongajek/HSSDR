package sketchUpXmlTesting;

import server.HssdrServer;

public class SketchupHssdrServer {

	public static void main(String[] args) {
    	HssdrServer example = new HssdrServer();
        example.handleConnection(new SketchUpMEesageHandler());
    }
}
