package editor;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.JEditorPane;
import javax.swing.event.HyperlinkEvent;
import javax.swing.event.HyperlinkListener;

import util.Logger;

public class GuiUtil {
	
	/**
	 * wczytuje html do JEditorPane a i zwraca go
	 * podana sciezka przeszukiwana w class leaderach za pomoca java.lang.Class.getResource(String name) 
	 * @param fileName
	 * @return
	 */
	public static  JEditorPane loadHtml(String fileName) {
        final JEditorPane editorPane = new JEditorPane();
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
        
        
        editorPane.addHyperlinkListener(new HyperlinkListener() {
            public void hyperlinkUpdate(HyperlinkEvent e) {
                if(e.getEventType() == HyperlinkEvent.EventType.ACTIVATED) {
                	 if(Desktop.isDesktopSupported()) {
                         try {
							Desktop.getDesktop().browse(e.getURL().toURI());
						} catch (IOException e1) {
						Logger.LOGGER.error("problem browser opening", e1);
						} catch (URISyntaxException e1) {
							Logger.LOGGER.error("problem browser opening", e1);
						}
                     }
                }
            }
        });

       
        
        
//        editorPane.addHyperlinkListener(new HyperlinkListener() {
//            @Override public void hyperlinkUpdate(final HyperlinkEvent pE) {
//                if (HyperlinkEvent.EventType.ACTIVATED == pE.getEventType()) {
//                    String desc = pE.getDescription();
//                    if (desc == null || !desc.startsWith("#")) return;
//                    desc = desc.substring(1);
//                    editorPane.scrollToReference(desc);
//                    Logger.LOGGER.debug(  "scrollToReference  "+desc);
//                }
//            }
//        });

        return editorPane;
    }

}
