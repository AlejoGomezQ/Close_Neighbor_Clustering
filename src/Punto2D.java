class Punto2D {
    double x;
    double y;

    public Punto2D(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double distancia(Punto2D otroPunto) {
        double dx = this.x - otroPunto.x;
        double dy = this.y - otroPunto.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    @Override
    public String toString() {
        return "(" + x + ", " + y + ")";
    }
}
