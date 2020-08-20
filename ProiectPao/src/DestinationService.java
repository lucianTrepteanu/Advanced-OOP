import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class DestinationService {
    private static DestinationService inst=null;
    private DestinationService(){}
    public static DestinationService getInst(){
        if(inst==null){
            inst=new DestinationService();
        }
        return inst;
    }

    public String showDestinations(){
        String result="";

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";

        try{
            Connection connection=DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="select * from destinations";
            ResultSet rs=statement.executeQuery(sqlQuery);
            while(rs.next()){
                Destination dest=new Destination(Integer.parseInt(rs.getString("popularity")),rs.getString("name"),Integer.parseInt(rs.getString("avgpriceperday")));
                result+=dest+"\n";
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }

        return result;
    }

    public void addDestination(Destination dest){
        Manager manager=Manager.getInst();
        manager.cities.add(dest);

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";

        try{
            Connection connection= DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="insert into destinations (popularity,name,avgpriceperday) values("+dest.popularity+",'"+dest.name+"',"+dest.avgPricePerDay+")";
            System.out.println(sqlQuery);
            statement.execute(sqlQuery);
            statement.close();
            connection.close();
        } catch(Exception e){
            System.out.println(e);
        }
    }

    public void deleteDestination(Destination dest){
        Manager manager=Manager.getInst();

        String currName=dest.name;
        int idx=manager.getIndex(currName);

        manager.cities.remove(idx);

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";
        try{
            Connection connection=DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="delete from destinations where name='"+currName+"'";
            statement.execute(sqlQuery);
        } catch (Exception exception){
            System.out.println(exception);
        }
    }

    public void editDestination(String name,String newName,int newPop,int newPrice){
        Manager manager=Manager.getInst();

        int idx=manager.getIndex(name);
        manager.cities.get(idx).setName(newName);
        manager.cities.get(idx).setPopularity(newPop);
        manager.cities.get(idx).setAvgPricePerDay(newPrice);

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";
        try{
            Connection connection=DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="update destinations set name="+"'"+newName+"'"
                    +",popularity="+newPop
                    +",avgpriceperday="+newPrice
                    +" where name="+"'"+name+"'";
            statement.execute(sqlQuery);
        } catch (Exception exception){
            System.out.println(exception);
        }
    }
}
