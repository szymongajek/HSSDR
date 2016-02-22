package editor;

import java.io.IOException;

import javax.swing.JEditorPane;

import util.Logger;

public class GuiUtil {
	
	/**
	 * wczytuje html do JEditorPane a i zwraca go
	 * podana sciezka przeszukiwana w class leaderach za pomoca java.lang.Class.getResource(String name) 
	 * @param fileName
	 * @return
	 */
	public static  JEditorPane loadHtml(String fileName) {
        JEditorPane editorPane = new JEditorPane();
        editorPane.setEditable(false);
        java.net.URL helpURL = GuiUtil.class.getResource(fileName);
        if (helpURL != null) {
            try {
                editorPane.setPage(helpURL);
            } catch (IOException e) {
                Logger.LOGGER.error("Attempted to read a bad URL: "+helpURL,e);
            }
        } else {
            Logger.LOGGER.error(  "Couldn't find file, helpURL == null:  "+fileName);
        }

        return editorPane;
    }

}
