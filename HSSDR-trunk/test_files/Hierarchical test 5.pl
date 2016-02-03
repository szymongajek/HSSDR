failure_msg "Istnieje magazyn w sekcji publicznej"
forall x in Rooms : forall y in Areas: 
type(x)="Storage" and   type(y)="Public"
=> not isInDescendants(x,y));
