/*
    TODO: derivat din Route -> aerial Route / groundRoute
          Adaugat clasa Calator + boolean(vrea/nu vrea aerian)
          Adaugat edit/delete in bazele de date
          alte derivari?
          Audit????
          Frameurile sa mosteneasca dintr-o clasa mai mare?
 */

public class Main {
    public static void main(String[]args){
        Manager manager=Manager.getInst();
        manager.loadDestinations();
        manager.loadRoutes();
        DestinationService destinationService=DestinationService.getInst();

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

        //System.out.println(manager.canReach("Dragasani","Rm. Valcea"));

        //System.out.println(manager.getBest("Bucuresti","Rm. Valcea",new DistanceCost()));

        //Route route=new Route(1,2,50,90,45);
        //RouteService routeService=RouteService.getInst();
        //routeService.addRoute(route);
        //manager.printRoutes();

        //System.out.println(destinationService.showDestinations());

        //MainFrame mainFrame=new MainFrame();
    }
}
