# nie mozna uzywac niezdefiniowanych symboli
pokojFajny(r) <=>  pokojZielony(r);

pokojZielony(r) <=> pokojFajny(r);

success_msg "ok"
failure_msg "ko"
exists r in Rooms: pokojFajny(r)