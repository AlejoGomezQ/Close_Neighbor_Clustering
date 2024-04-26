public class Vecino {
    double distancia;
    int clusterIndex;

    public Vecino(double distancia, int clusterIndex) {
        this.distancia = distancia;
        this.clusterIndex = clusterIndex;
    }

    public double getDistancia() {
        return distancia;
    }

    public int getClusterIndex() {
        return clusterIndex;
    }
}
