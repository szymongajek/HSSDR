success_msg "Istnieje korytarz bezpo�rednio wewnatrz powierzchni komunikacyjnej"
failure_msg "Nie istnieje bezpo�rednio korytarz wewnatrz powierzchni komunikacyjnej"
exists x in Rooms :  exists y in Areas: 
type(x)="Corridor" and type(y)="Communication_Area"
and isDirectChild(x,y);
