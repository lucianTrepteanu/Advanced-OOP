public class Route {
    private String fDest;
    private String sDest;
    private double distance;
    private int time;
    private double moneyCost;

    private int index;

    public Route(String fDest, String sDest, double distance, int time, double moneyCost) {
        this.fDest = fDest;
        this.sDest = sDest;
        this.distance = distance;
        this.time = time;
        this.moneyCost = moneyCost;
    }

    public double getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    void printData(){
        System.out.println("Route from city "+this.fDest+" to "+this.sDest+" of length "+this.distance);
    }

    public String getfDest() {
        return fDest;
    }

    public String getsDest() {
        return sDest;
    }

    public double getDistance() {
        return distance;
    }

    public int getTime() {
        return time;
    }

    public double getMoneyCost() {
        return moneyCost;
    }

    @Override
    public String toString() {
        return "" +
                "fDest=" + fDest +
                ", sDest=" + sDest +
                ", distance=" + distance +
                ", time=" + time +
                ", moneyCost=" + moneyCost;
    }
}
