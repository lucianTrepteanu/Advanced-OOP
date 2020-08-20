public class Route {
    private int fDest;
    private int sDest;
    private double distance;
    private int time;
    private double moneyCost;
    private double index;
    static int currIdx=0;

    public Route(int fDest, int sDest, double distance, int time, double moneyCost) {
        this.fDest = fDest;
        this.sDest = sDest;
        this.distance = distance;
        this.time = time;
        this.moneyCost = moneyCost;
        this.index = currIdx;
        currIdx++;
    }

    public double getIndex() {
        return index;
    }

    void printData(){
        System.out.println("Route from city "+this.fDest+" to "+this.sDest+" of length "+this.distance);
    }

    public int getfDest() {
        return fDest;
    }

    public int getsDest() {
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
