success_msg "istnieje pokój mający dwoje drzwi odległych o 5"
failure_msg "żaden pokój nie ma dwojga drzwi odległych o 5"
exists x in Rooms : exists d1, d2 in Doors:  doorsInRoom(d1,x) and  doorsInRoom(d2,x) and d1!=d2
and doorsDist(d1,d2)<5
;
