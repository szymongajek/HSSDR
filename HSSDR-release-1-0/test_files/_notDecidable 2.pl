# stack overflow
pokojOstatni(r) <=>  not exists p in Rooms: accessible(r,p) and not pokojOstatni(p);

success_msg "ok"
failure_msg "ko"
exists r in Rooms: pokojOstatni(r);