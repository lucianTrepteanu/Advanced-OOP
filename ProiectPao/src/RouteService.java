import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class RouteService {
    private static RouteService inst=null;
    private RouteService(){}
    public static RouteService getInst(){
        if(inst==null){
            inst=new RouteService();
        }
        return inst;
    }

    public void addRoute(Route route){
        Manager manager=Manager.getInst();
        manager.routes.add(route);
        manager.cities.get(route.getfDest()).addRoute(route);
        manager.cities.get(route.getsDest()).addRoute(route);

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";

        try{
            Connection connection= DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="insert into routes (fDest,sDest,distance,time,money) values("+route.getfDest()
                    +","+route.getsDest()+","+route.getDistance()
                    +","+route.getTime()
                    +","+route.getMoneyCost()
                    +")";
            statement.execute(sqlQuery);
            statement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
