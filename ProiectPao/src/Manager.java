import java.sql.*;
import java.util.*;

public class Manager {
    private static Manager inst=null;
    private Manager(){}
    public static Manager getInst(){
        if(inst==null){
            inst=new Manager();
        }
        return inst;
    }

    public ArrayList<Destination> cities=new ArrayList<Destination>();
    public ArrayList<Route> routes=new ArrayList<Route>();
    public ArrayList<Vehicles> vehicles=new ArrayList<Vehicles>();

    public void loadDestinations(){
        cities.clear();
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
                this.cities.add(dest);
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void loadRoutes(){
        routes.clear();
        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";

        try{
            Connection connection=DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="select * from routes";
            ResultSet rs=statement.executeQuery(sqlQuery);
            while(rs.next()){
                int fDest=Integer.parseInt(rs.getString("fdest"));
                int sDest=Integer.parseInt(rs.getString("sdest"));
                double distance=Double.parseDouble(rs.getString("distance"));
                int time=Integer.parseInt(rs.getString("time"));
                double money=Double.parseDouble(rs.getString("money"));

                Route route=new Route(fDest,sDest,distance,time,money);
                this.cities.get(fDest).addRoute(route);
                this.cities.get(sDest).addRoute(route);
                this.routes.add(route);
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void printRoutes(String cityName){
        int idx=getIndex(cityName);
        if(idx<0){
            System.out.println("City not found");
            return;
        }
        for(Route route:cities.get(idx).routes){
            String firstName=cities.get(route.getfDest()).getName();
            String secondName=cities.get(route.getsDest()).getName();

            System.out.println(firstName+"-"+secondName+": distance: "+route.getDistance()+" time "+route.getTime()+" money "+route.getMoneyCost());
        }
    }

    public void printRoutes(){
        for(Route route:this.routes){
            System.out.println(route);
        }
    }

    public void addDestination(Destination dest){
        this.cities.add(dest);

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";

        try{
            Connection connection=DriverManager.getConnection(dbUrl,dbUser,dbPass);
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

    public void printDestinations(){
        for(Destination dest:cities){
            System.out.println(dest.toString());
        }
    }

    protected int getIndex(String name){
        for(int i=0;i<this.cities.size();i++){
            if(this.cities.get(i).name.equals(name)){
                return i;
            }
        }
        return -1;
    }

    public boolean canReach(String cityA, String cityB){
        int idxA=getIndex(cityA);
        int idxB=getIndex(cityB);

        LinkedList<Integer> que=new LinkedList<Integer>();
        boolean []visited=new boolean[cities.size()];
        visited[idxA]=true;
        que.add(idxA);
        while(que.size()>0){
            int currentNode=que.getFirst();
            que.removeFirst();
            for(Route r:cities.get(currentNode).routes){
                if(visited[r.getsDest()]==false){
                    visited[r.getsDest()]=true;
                    que.add(r.getsDest());
                }
            }
        }

        return visited[idxB];
    }

    public RouteCost getBest(String cityA,String cityB,Comparator<RouteCost> comparator){
        int idxA=getIndex(cityA);
        int idxB=getIndex(cityB);

        RouteCost res=new RouteCost(Double.MAX_VALUE/2,Double.MAX_VALUE/2,Double.MAX_VALUE/2,0);

        if(idxA<0 || idxB<0){
            System.out.println("One of the cities was not found");
            return res;
        }

        //PriorityQueue<Pair<RouteCost,Integer> > heap=new PriorityQueue<>(10,comparator);
        PriorityQueue<RouteCost> heap=new PriorityQueue<RouteCost>(10,comparator);

        boolean []visited=new boolean[cities.size()];
        heap.add(new RouteCost(0,0,0,idxA));
        while (heap.size()>0){
            RouteCost currentNode=heap.poll();
            if(visited[currentNode.getCurrIdx()]==true){
                continue;
            }
            visited[currentNode.getCurrIdx()]=true;

            if(currentNode.getCurrIdx()==idxB) {
                RouteCost thisRes = currentNode;
                if (comparator.compare(thisRes, res) < 0) {
                    res = thisRes;
                }
                heap.clear();
                continue;
            }

            for(Route r:cities.get(currentNode.getCurrIdx()).routes){
                RouteCost newCost=new RouteCost(currentNode.getTime()+r.getTime(),currentNode.getDistance()+r.getDistance(),
                        currentNode.getMoney()+r.getMoneyCost(),r.getsDest());
                heap.add(newCost);
            }
        }

        return res;
    }
}
