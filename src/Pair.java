class Pair {
    Punto2D pA;
    Punto2D pB;
    double distancia;

    public Pair(Punto2D pA, Punto2D pB, double distancia) {
        this.pA = pA;
        this.pB = pB;
        this.distancia = distancia;
    }

    public double getDistancia() {
        return distancia;
    }
}