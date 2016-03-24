# Important area
isLift(x)  <=> type(x)="LiftConform" or type(x)="LiftNonConform" or type(x)="LiftCargo";
	
# Dozwolona winda 
AllowedForDisabled(p) <=> type(p)="LiftConform" or 
( type(p)="LiftCargo" and not exists x in Rooms: type(x)="LiftConform" or type(x)="LiftNonConform");
 

failure_msg "brak dostepu do windy z kazdej czesci bunynku"
success_msg "dostep do windy z kazdej czesci bunynku-ok"
forall r in Rooms: exists lift in Rooms: floorNr(r)=floorNr(lift) and AllowedForDisabled(lift) and accessible(lift,r) 
or (not exists p in Rooms: isLift(p) )
;
 