success_msg "Istnieje klatka schodowa wewnatrz powierzchni komunikacyjnej"
failure_msg "Nie istnieje klatka schodowa wewnatrz powierzchni komunikacyjnej"
exists x in Rooms :  exists y in Areas: 
type(x)="Staircase" and type(y)="Communication_Area"
and isInDescendants(x,y);
