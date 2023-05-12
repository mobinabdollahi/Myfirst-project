public class Avogadro {
    private double Avogadro;
    public double getAvogadro(String file){

        // Solution to find Avogadro's Number
        double temperature = 297;
        double water = 9.135*Math.pow(10,-4);
        double radius = 0.5*Math.pow(10,-6);
        double D = new BidTracker().Tracker(file);
        double k = D * 6 * Math.PI * radius * water / temperature ;
        double gaz = 8.31446;

        Avogadro=gaz/k;

        return Avogadro;
    }
}
