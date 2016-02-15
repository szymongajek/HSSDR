# Parametry dróg pożarowych na podstawie
# Rozporządzenie Ministra Infrastruktury w sprawie warunków technicznych(...) z dnia 12 kwietnia 2002 
# (§ 236. ust 1,2,3) (§ 237. ust 1,8)

	# zbiór drzwi prowadzących do innych stref pożarowych
ZoneDoors(d) <=> exists r in Rooms: doorsInRoom(d, r) and  type(r) = "Staircase";
	# zbiór drzwi zapewniających  opuszczenie bieżącej strefy
FireSafetyDoors(d) <=>   ExternalDoors(d) or ZoneDoors(d)  ;
   
	# pokoje stanowiące odrębne strefy pożarowe
ZoneRooms(r) <=> Rooms(r) and (type(r) = "Staircase");   
	# pomieszczenia które mogą wchodzic w skład drogi ewakuacyjnej
EvacRoute(r) <=> Rooms(r) and (type(r) = "Corridor" or type(r) = "Hall");
	# pomieszczenia ktore moga wchodzic w sklad przejscia ewakuacyjnego
AccessPath(r) <=> Rooms(r) and !ZoneRooms(r) and !EvacRoute(r);
  
	# okresla drzwi które są oddalone o co najwyżej x od FireSafetyDoors, oddzielone od nich pokojem EvacRoute
validRouteFromAdj(d, x) <=>  Doors(d) and  ( exists d2 in Doors : d2!=d and FireSafetyDoors(d2) and
( exists p in Rooms:  doorsInRoom(d2,p) and doorsInRoom(d,p) and EvacRoute(p) and  x>=doorsDist(d, d2)  ));
  
	# okresla drzwi które są oddalone o co najwyżej x od FireSafetyDoors, oddzielone od nich co njawyżej n pokojami EvacRoute
validRouteFromInduction(d, x, n)<=>   
 (n = 1 and validRouteFromAdj(d, x) ) or
 (n <> 1 and Doors(d) and validRouteFromInduction(d, x, n-1)) or
 (n <> 1 and Doors(d) and exists d2 in Doors :  d2!=d and
 	(  exists p in Rooms:  
	   		doorsInRoom(d2,p) and 
	   		doorsInRoom(d,p) and 
	   		EvacRoute(p) and 
	   		 x>=doorsDist(d, d2)
	)  and
 	validRouteFromInduction(d2, x - doorsDist(d, d2), n-1)
 ) ;

	# okresla drzwi które są oddalone o co najwyżej x od FireSafetyDoors, oddzielone od nich co dowolną liczbą pokoi EvacRoute
validRouteFrom(d, x) <=> validRouteFromInduction(d, x, 99);
	# drzwi wchodzące w skład prawidłowej drogi ewakuacyjnej
ValidEvacRouteDoors(d) <=>FireSafetyDoors(d) or  validRouteFrom(d, 30);


	# zbiór pokoi użytkowych posiadających drzwi na drogę ewakuacyjną
steps0(r) <=>     AccessPath(r)  and ( exists d in ValidEvacRouteDoors: doorsInRoom(d, r) );
	# zbiór pokoi użytkowych sąsiadujących ze steps0
steps1(r) <=> steps0(r) or ( AccessPath(r)  and (exists r2 in steps0: accessible(r, r2) ));
	# zbiór pokoi użytkowych sąsiadujących ze steps1
steps2(r) <=> steps1(r) or ( AccessPath(r)  and (exists r2 in steps1: accessible(r, r2) ));
 
	# pokoje wchodzące w skład prawidłowej drogi ewakuacyjnej
ValidEvacRouteRooms(r) <=> EvacRoute(r) and ( exists d in ValidEvacRouteDoors: doorsInRoom(d, r) );

	# suma zbiorów pokoi: 
	# stanowiących odr�bne strefy po�arowe
	# sąsiadujących z droga ekwakuacyjną przez mniej niż trzy pomieszczenia
	# wchodzacych w skład prawidłowej drogi ewakuacyjnej
ValidRooms(r) <=> ZoneRooms(r) or ValidEvacRouteRooms(r) or steps2(r);

success_msg "wszystkie pokoje ok"
failure_msg "pewne pokoje nie spełniaja ograniczeń przeciwpożarowych"
forall r in Rooms: ValidRooms(r)
 