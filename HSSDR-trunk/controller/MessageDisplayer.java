
package controller;

import java.util.ArrayList;

 


public interface MessageDisplayer
{

	/**
	 * wyswietlenie wiadomosci oraz podswitlenie pomieszczen roomsToHighlight, znajdowanych po label
	 * @param message
	 * @param roomsToHighlight
	 */
      void displayMessageAndHighlight(String message, ArrayList<String> roomsToHighlight );
      
      /**
       * wyczysczenie wszystkich informacji i podswietlen z testow
       */
      void clearMessages();
}
