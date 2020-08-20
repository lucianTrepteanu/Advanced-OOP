/*
    TODO: derivat din Route -> aerial Route / groundRoute
          Adaugat clasa Calator + boolean(vrea/nu vrea aerian)
          alte derivari?
          Audit????
          Frameurile sa mosteneasca dintr-o clasa mai mare?
 */

public class Main {
    public static void main(String[]args){
        Manager manager=Manager.getInst();
        manager.loadDestinations();
        manager.loadRoutes();

        //Destination dest=new Destination(1000,"Paris",400);
        //manager.addDestination(dest);
        //manager.printDestinations();
        //System.out.println();

        //manager.printRoutes("Bucuresti");
        //manager.printRoutes("Rm. Valcea");

        //manager.printRoutes();

        //System.out.println(manager.canReach("Dragasani","Rm. Valcea"));

        //System.out.println(manager.getBest("Bucuresti","Rm. Valcea",new DistanceCost()));

        MainFrame mainFrame=new MainFrame();
    }
}
