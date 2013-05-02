	# doors leading to another section of the building
ZoneDoors(d) <=> Doors(d) and (exists r in Rooms: doorsInRoom(d, r) and  type(r) = "Staircase" );
	# doors which end evacuation route
FireSafetyDoors(d) <=>  ExternalDoors(d) or ZoneDoors(d) ;
   
	# safe exits
ZoneRooms(r) <=> Rooms(r) and (type(r) = "Staircase");   
	# common communication areas
EvacRoute(r) <=> Rooms(r) and (type(r) = "Corridor" or type(r) = "Hall");
	# other rooms,  acces path to evacuation route
AccessPath(r) <=> Rooms(r) and !ZoneRooms(r) and !EvacRoute(r);
  
aux1(d, x) <=>  Doors(d) and  ( exists d2 in Doors : d2!=d and FireSafetyDoors(d2) and
( exists p in Rooms:  doorsInRoom(d2,p) and doorsInRoom(d,p) and EvacRoute(p) and  x>=doorsDist(d, d2)  ));
aux2(d, x) <=> Doors(d) and  (exists d2 in Doors : d2!=d   and 
( exists p in Rooms:  doorsInRoom(d2,p) and doorsInRoom(d,p) and EvacRoute(p) and aux1(d2,x - doorsDist(d, d2)) ));
aux3(d, x) <=> Doors(d) and  (exists d2 in Doors : d2!=d  and 
( exists p in Rooms:  doorsInRoom(d2,p) and doorsInRoom(d,p) and EvacRoute(p) and aux2(d2,x - doorsDist(d, d2)) ));
aux4(d, x) <=> Doors(d) and  (exists d2 in Doors : d2!=d  and 
( exists p in Rooms:  doorsInRoom(d2,p) and doorsInRoom(d,p) and EvacRoute(p) and aux3(d2,x - doorsDist(d, d2)) ));

ValidEvacRouteDoors(d) <=>FireSafetyDoors(d) or  aux1(d, 30) or aux2(d, 30) or aux3(d, 30) or aux4(d, 30);

# rooms belonging to valid evacuation route
ValidEvacRouteRooms(r) <=> EvacRoute(r) and ( exists d in ValidEvacRouteDoors: doorsInRoom(d, r) );

# rooms with doors to valid evacuation route 
steps0(r) <=>     AccessPath(r)  and ( exists d in ValidEvacRouteDoors: doorsInRoom(d, r) );
# stepsN is set rooms from which one need to pass through N rooms to get to evacuation route
steps1(r) <=> steps0(r) or ( AccessPath(r)  and (exists r2 in steps0: accessible(r, r2) ));
steps2(r) <=> steps1(r) or ( AccessPath(r)  and (exists r2 in steps1: accessible(r, r2) ));
 
# set of valid rooms cosists of :
# rooms witch valid acces path
# rooms belonging to valid evacuation route
# safe exits 
ValidRooms(r) <=> steps2(r) or ValidEvacRouteRooms(r)  or  ZoneRooms(r);

# finalny test
success_msg "wszystkie pokoje ok"
failure_msg "pokój nie spełnia warunków"
forall r in Rooms: ValidRooms(r);
