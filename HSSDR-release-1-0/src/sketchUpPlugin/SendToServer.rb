require 'q:/Programy/Ruby186/lib/ruby/1.8/i386-mingw32/socket' # wskazujemy na standardow� bibliotek� socket

#metoda ��czaca si� z zewn�trznym serwerem, wysy�aj�ca komunikat i zwracajaca odpowiedz
def sendData(dataToSend)
  port = 7777  #ustalamy port po��czenia
  streamSock = Socket::new(Socket::AF_INET, Socket::SOCK_STREAM, 0) #tworzymy socket
  myaddr = [Socket::AF_INET, port, 127, 0, 0, 1, 0, 0].pack("snCCCCNN") #tworzymy adres
  streamSock.connect( myaddr ) #��czymy si�
  msg = dataToSend
  
  streamSock.puts msg #wysy�amy komunikat do serwera
  streamSock.puts "END_OF_MESSAGE" #wysy�amy komunikat konca do serwera
  
  responseMessage = streamSock.recv(999999) #odbieramy odpowied� z serwera

  streamSock.close   #zamykamy socket
  return responseMessage
end

