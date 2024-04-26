import java.util.ArrayList;
import java.util.List;

public class Cluster {
    public List<Punto2D> puntos;
    public Cluster() {
        puntos = new ArrayList<>();
    }
    public void agregarPunto(Punto2D punto) {
        puntos.add(punto);
    }
    public boolean contiene(Punto2D punto) {
        return puntos.contains(punto);
    }
    public void fusionar(Cluster otro) {
        puntos.addAll(otro.puntos);
    }
    public int getTamaño() {
        return puntos.size();
    }
    public List<Punto2D> getPuntos() {
        return puntos;
    }

    public double distanciaMedia(Cluster otroCluster) {
        double sumDistancia = 0;
        int contador = 0;
        for (Punto2D pA : puntos) {
            for (Punto2D pB : otroCluster.puntos) {
                sumDistancia += pA.distancia(pB);
                contador++;
            }
        }
        return sumDistancia / contador;
    }
}

