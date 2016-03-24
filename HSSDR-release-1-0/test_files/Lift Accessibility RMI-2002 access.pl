# Important area
isLift(x)  <=> type(x)="LiftConform" or type(x)="LiftNonConform" or type(x)="LiftCargo";

#Budynek bez windy
BuildingWithoutLift()<=> not exists p in Rooms: isLift(p);	

# Dozwolona winda 
AllowedForDisabled(p) <=> type(p)="LiftConform" or 
( type(p)="LiftCargo" and not exists x in Rooms: type(x)="LiftConform" or type(x)="LiftNonConform");
 
AccessiblePathFloorInduction(p1, p2, n) <=> floorNr(p1)=floorNr(p2) and
( (n = 1 and accessible(p1, p2)) or
 (n <> 1 and AccessiblePathFloorInduction(p1, p2, n-1)) or
 (n <> 1 and exists p3 in Rooms: 
   floorNr(p1)=floorNr(p3) and
   AccessiblePathFloorInduction(p1, p3, n-1) and
   accessible(p3, p2)) );

# Mozliwe przejscie z p1 do p2 przez dowlna inna innych pomieszczen w obrebie jednego pietra
AccessiblePathFloor(p1, p2) <=> AccessiblePathFloorInduction(p1, p2, 99);

failure_msg "brak dostepu do windy z kazdej czesci bunynku"
success_msg "dostep do windy z kazdej czesci bunynku-ok"
BuildingWithoutLift() 
or
forall r in Rooms: exists lift in Rooms: 
         floorNr(r)=floorNr(lift) and AllowedForDisabled(lift) and AccessiblePathFloor(lift,r) 
 

;
 