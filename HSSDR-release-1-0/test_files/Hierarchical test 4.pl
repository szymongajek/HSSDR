# toalety męskie, damksie oraz dla osób niepełnosprawnych
isToilet(x)  <=> type(x)="ToiletMen" or type(x)="ToiletWomen" or type(x)="ToiledAccessible";

failure_msg "Brak toalety w sekcji prywatnej"
   exists a in Areas: exists r in Rooms : type(a) = "Private" and isToilet(r) and isInDescendants(r,a);
   

failure_msg "Brak toalety w sekcji publicznej"
   exists a in Areas: exists r in Rooms : type(a) = "Public" and isToilet(r) and isInDescendants(r,a);
