/*
    TODO: derivat din Route -> aerial Route / groundRoute
          (se poate face calatoria doar "pe jos"?)
          Adaugat clasa Calator ?
          Audit!
 */

import java.io.IOException;

public class Main {
    public static void main(String[]args) throws IOException {
        Manager manager=Manager.getInst();
        manager.loadDestinations();
        manager.loadRoutes();
        manager.loadTrips();
        manager.loadTravellers();

        DestinationService destinationService=DestinationService.getInst();
        RouteService routeService=RouteService.getInst();
        TripService tripService=TripService.getInst();
        TravellerService travellerService=TravellerService.getInst();

        /*Destination destBeius=new Destination(1000,"Beius",400);
        destinationService.addDestination(destBeius);
        System.out.println(destinationService.showDestinations());
        destinationService.deleteDestination(destBeius);
        System.out.println(destinationService.showDestinations());*/

        /*manager.editDestination("Rome","Cairo",400,225);
        System.out.println(destinationService.showDestinations());*/

        //manager.printRoutes("Bucuresti");
        //manager.printRoutes("Rm. Valcea");
        //manager.printRoutes();

        /*System.out.println(manager.canReach("Dragasani","Mallorca"));
        System.out.println(manager.canReachNoPlane("Dragasani","Mallorca"));*/
        /*System.out.println(manager.canReach("Dragasani","Paris"));
        System.out.println(manager.getBest("Dragasani","Bucuresti",new DistanceCost()));*/

        /*Route route=new AirRoute("Paris","Dragasani",25000,500,400);
        routeService.addRoute(route);
        System.out.println(routeService.showRoutes());

        routeService.editRoute(route,10,1000000,20);
        System.out.println(routeService.showRoutes());*/

        /*routeService.deleteRoute(route);
        System.out.println(routeService.showRoutes());*/

        /*manager.loadTrips();
        Destination cityA=manager.cities.get(manager.getIndex("Bucuresti"));
        Destination cityB=manager.cities.get(manager.getIndex("Singapore"));

        Trip trip=new Trip(cityA,cityB,100,200.0,0);
        tripService.addTrip(trip);
        System.out.println(tripService.showTrips());*/

        /*Traveller tr1=new AllRoutesTraveller("Lucian","1991024123456","Dragasani");
        Traveller tr2=new AllRoutesTraveller("Dragos","1234567891234","Bucuresti");*/

        /*travellerService.addTraveller(tr1);
        System.out.println(manager.travellers);
        travellerService.addTraveller(tr2);
        System.out.println(manager.travellers);*/

        /*Traveller tr2=new AirFearTraveller("Dragos","1234567891234","botosani");
        System.out.println(manager.travellers);
        travellerService.deleteTraveller(tr2);
        System.out.println(manager.travellers);*/
        /*travellerService.editTraveller("1991024123456","Lucian","Dragasani");
        System.out.println(manager.travellers.get(0).getCity());*/

        //System.out.println(tr1 instanceof AirFearTraveller);
        //System.out.println(tr1.canTravel("Mallorca"));
        //System.out.println(tr1.getRecommendations());

        /*System.out.println(tr1.canTravel("Mallorca"));
        System.out.println(tr2.canTravel("Mallorca"));*/

        //MainFrame mainFrame=new MainFrame();
    }
}
