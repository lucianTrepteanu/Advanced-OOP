/*
    TODO: derivat din Route -> aerial Route / groundRoute
          (se poate face calatoria doar "pe jos"?)
          Adaugat clasa Calator
          Calatorie(sursa,destinatie,cost_total,nr_part)
          Audit!
 */

public class Main {
    public static void main(String[]args){
        Manager manager=Manager.getInst();
        manager.loadDestinations();
        manager.loadRoutes();
        DestinationService destinationService=DestinationService.getInst();
        RouteService routeService=RouteService.getInst();
        TripService tripService=TripService.getInst();

        //Destination dest=new Destination(800,"Mallorca",300);
        //destinationService.addDestination(dest);
        //manager.printDestinations();

        //Destination dest=new Destination(1000,"Cairo",400);
        //destinationService.deleteDestination(dest);
        //manager.printDestinations();

        //manager.editDestination("Rome","Cairo",400,225);
        //manager.printDestinations();
        //System.out.println();

        //manager.printRoutes("Bucuresti");
        //manager.printRoutes("Rm. Valcea");

        //manager.printRoutes();

        //System.out.println(manager.canReach("Dragasani","Paris"));

        //System.out.println(manager.getBest("Dragasani","Bucuresti",new DistanceCost()));

        //Route route=new Route("Paris","Toronto",3000,700,600);
        //System.out.println(routeService.showRoutes());
        //routeService.addRoute(route);

        manager.loadTrips();
        Destination cityA=manager.cities.get(manager.getIndex("Bucuresti"));
        Destination cityB=manager.cities.get(manager.getIndex("Singapore"));

        //Trip trip=new Trip(cityA,cityB,100,200.0,0);
        //tripService.addTrip(trip);

        System.out.println(tripService.showTrips());

        //MainFrame mainFrame=new MainFrame();
    }
}
