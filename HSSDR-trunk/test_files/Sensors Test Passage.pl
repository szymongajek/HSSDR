#Pomieszczenia wymagające nadzoru
SecuredArea(r) <=> Rooms(r) and (type(r) = "Office" or type(r) = "Technical" or type(r) = "Storage" or type(r) = "Server_Room");
	 
#Możliwe przejście pomiędzy d1 i d2 w obrębie jednego pomieszczenia omijające zasięg czujników
UnwatchedPassage(d1, d2) <=> d1!=d2 and  Doors(d1) and  Doors(d2) and
 	(exists r in Rooms: doorsInRoom(d1,r) and doorsInRoom(d2,r) and not isPassageWatched(d1,d2) );

UnwatchedPassageInduction(d1, d2, n) <=>
 (n = 1 and UnwatchedPassage(d1, d2)) or
 (n <> 1 and UnwatchedPassageInduction(d1, d2, n-1)) or
 (n <> 1 and exists d in Doors:
   UnwatchedPassageInduction(d1, d, n-1) and
   UnwatchedPassage(d, d2));
   
#Możliwe przejście pomiędzy d1 i d2 omijające zasięg czujników
UnwatchedPassagePlus(d1, d2) <=> UnwatchedPassageInduction(d1, d2, 99);

success_msg "przejścia zabezpieczone"
failure_msg "istnieje przejście niezabezpieczone"
forall r in Rooms: forall d1, d2 in Doors:  
  not (ExternalDoors(d1) and SecuredArea(r) and doorsInRoom(d2,r) and  UnwatchedPassagePlus(d1,d2) );
