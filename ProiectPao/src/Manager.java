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
    //public ArrayList<Vehicles> vehicles=new ArrayList<Vehicles>();
    public ArrayList<Trip> trips=new ArrayList<Trip>();

    public void updatePopularity(Trip trip){
        if(trip.isUsed()==1){
            return;
        }

        int currPop=trip.getTo().getPopularity();
        double currPrice=trip.getTo().getAvgPricePerDay()*trip.getTo().getPopularity();

        int newPop=currPop+trip.getCntPers();
        double newAvg=(currPrice+trip.getPrice())/newPop;

        trip.getTo().setPopularity(newPop);
        trip.getTo().setAvgPricePerDay(newAvg);
        trip.setUsed(1);

        DestinationService destinationService=DestinationService.getInst();
        destinationService.editDestination(trip.getTo().getName(),
                trip.getTo().getName(),newPop,newAvg);
    }

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

            int currIdx=0;
            while(rs.next()){
                String fDest=rs.getString("fdest");
                String sDest=rs.getString("sdest");
                double distance=Double.parseDouble(rs.getString("distance"));
                int time=Integer.parseInt(rs.getString("time"));
                double money=Double.parseDouble(rs.getString("money"));

                int fIdx=this.getIndex(fDest);
                int sIdx=this.getIndex(sDest);

                Route route=new Route(fDest,sDest,distance,time,money);
                route.setIndex(currIdx);
                currIdx++;

                this.cities.get(fIdx).addRoute(route);
                this.cities.get(sIdx).addRoute(route);
                this.routes.add(route);
            }
            statement.close();
            connection.close();
        } catch (Exception e){
            System.out.println(e);
        }
    }

    public void loadTrips(){
        Manager manager=Manager.getInst();

        String dbUrl="jdbc:mysql://localhost:3306/pao";
        String dbUser="root";
        String dbPass="root";

        try{
            Connection connection= DriverManager.getConnection(dbUrl,dbUser,dbPass);
            Statement statement=connection.createStatement();
            String sqlQuery="select * from trips";
            ResultSet rs=statement.executeQuery(sqlQuery);
            while(rs.next()){
                String fromName=rs.getString("fromname");
                String toName=rs.getString("toname");
                int cntPers=Integer.parseInt(rs.getString("cntpers"));
                double price=Double.parseDouble(rs.getString("price"));
                int used=Integer.parseInt(rs.getString("used"));

                int fromIdx=manager.getIndex(fromName);
                int toIdx=manager.getIndex(toName);

                System.out.println(fromIdx);
                System.out.println(toIdx);

                Trip trip=new Trip(manager.cities.get(fromIdx),manager.cities.get(toIdx),cntPers,price,used);
                System.out.println(trip.isUsed());
                System.out.println();
                this.updatePopularity(trip);
                this.trips.add(trip);
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
            String firstName=route.getfDest();
            String secondName=route.getsDest();

            System.out.println(firstName+"-"+secondName+": distance: "+route.getDistance()+" time "+route.getTime()+" money "+route.getMoneyCost());
        }
    }

    public void printRoutes(){
        for(Route route:this.routes){
            System.out.println(route);
        }
    }

    public void printDestinations(){
        for(Destination dest:cities){
            System.out.println(dest.toString());
        }
    }

    protected int getIndex(String name){
        for(int i=0;i<this.cities.size();i++){
            if(this.cities.get(i).getName().equals(name)){
                return i;
            }
        }
        return -1;
    }

    public boolean canReach(String cityA, String cityB){
        LinkedList<String> que=new LinkedList<String>();
        Map<String,Boolean> visited=new HashMap<String,Boolean>();

        visited.put(cityA,true);
        visited.put(cityB,false);
        que.add(cityA);

        while(que.size()>0){
            String currentNode=que.getFirst();
            System.out.println(currentNode);

            int currIdx=this.getIndex(currentNode);

            System.out.println(currIdx);
            que.removeFirst();
            for(Route r:cities.get(currIdx).routes){
                String other="";
                if(r.getfDest().compareTo(this.cities.get(currIdx).getName())==0){
                    other=r.getsDest();
                } else {
                    other=r.getfDest();
                }
                System.out.println(other);

                if(visited.containsKey(other)==false || visited.get(other)==false){
                    visited.put(other,true);
                    que.add(other);
                }
            }
        }

        return visited.get(cityB);
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
                int other=0;
                String alt=this.cities.get(currentNode.getCurrIdx()).getName();
                if(r.getfDest().compareTo(alt)==0){
                    other=this.getIndex(r.getsDest());
                } else {
                    other=this.getIndex(r.getfDest());
                }
                RouteCost newCost=new RouteCost(currentNode.getTime()+r.getTime(),currentNode.getDistance()+r.getDistance(),
                        currentNode.getMoney()+r.getMoneyCost(),other);
                heap.add(newCost);
            }
        }

        return res;
    }
}
