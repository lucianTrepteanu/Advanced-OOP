import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
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

    public String showRoutes(){
        String result="";

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";

        try{
            Connection connection=DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="select * from routes";
            ResultSet rs=statement.executeQuery(sqlQuery);
            while(rs.next()){
                Route route=new Route(rs.getString("fDest"),rs.getString("sDest"),
                        Double.parseDouble(rs.getString("distance")),
                        Integer.parseInt(rs.getString("time")),
                        Double.parseDouble(rs.getString("money")));
                result+=route+"\n";
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return result;
    }

    public void addRoute(Route route){
        Manager manager=Manager.getInst();
        manager.routes.add(route);

        int idxA=manager.getIndex(route.getfDest());
        int idxB=manager.getIndex(route.getsDest());

        if(idxA<0 || idxB<0){
            System.out.println("Cities do not exist");
            return;
        }

        manager.cities.get(idxA).addRoute(route);
        manager.cities.get(idxB).addRoute(route);

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";

        try{
            Connection connection= DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="insert into routes (fDest,sDest,distance,time,money) values("+"'"+route.getfDest()
                    +"'"+","+"'"+route.getsDest()+"',"+route.getDistance()
                    +","+route.getTime()
                    +","+route.getMoneyCost()
                    +")";
            System.out.println(sqlQuery);
            statement.execute(sqlQuery);
            statement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }
}
