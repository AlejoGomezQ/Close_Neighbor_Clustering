import java.util.*;

public class Clustering {
    private final double distanciaMax;
    List<Cluster> clusters;

    public Clustering(double distanciaMax) {
        this.distanciaMax = distanciaMax;
        clusters = new ArrayList<>();
    }

    private void inicializarClusters(List<Punto2D> puntos) {
        for (Punto2D punto : puntos) {
            Cluster cluster = new Cluster();
            cluster.agregarPunto(punto);
            clusters.add(cluster);
        }
    }

    public int clusterizar(List<Punto2D> puntos) {
        inicializarClusters(puntos);

        PriorityQueue<Pair> minPQ = new PriorityQueue<>(Comparator.comparingDouble(Pair::getDistancia));
        for (int i = 0; i < puntos.size(); i++) {
            for (int j = i + 1; j < puntos.size(); j++) {
                Punto2D pA = puntos.get(i);
                Punto2D pB = puntos.get(j);
                double distancia = pA.distancia(pB);
                minPQ.add(new Pair(pA, pB, distancia));
            }
        }

        while (!minPQ.isEmpty()) {
            Pair par = minPQ.poll();
            if (par.distancia <= distanciaMax) {
                Punto2D pA = par.pA;
                Punto2D pB = par.pB;

                Cluster cluster1 = null;
                Cluster cluster2 = null;

                for (Cluster cluster : clusters) {
                    if (cluster.contiene(pA)) {
                        cluster1 = cluster;
                    }
                    if (cluster.contiene(pB)) {
                        cluster2 = cluster;
                    }
                }

                if (cluster1 != null && cluster2 != null && cluster1 != cluster2) {
                    cluster1.fusionar(cluster2);
                    clusters.remove(cluster2);
                }
            } else {
                break;
            }
        }

        return clusters.size();
    }

    public int clasificar(Punto2D p, int k) {
        List<Vecino> vecinos = new ArrayList<>();

        for (int i = 0; i < clusters.size(); i++) {
            Cluster cluster = clusters.get(i);
            for (Punto2D punto : cluster.getPuntos()) {
                double distancia = p.distancia(punto);
                vecinos.add(new Vecino(distancia, i));
            }
        }

        Collections.sort(vecinos, Comparator.comparingDouble(Vecino::getDistancia));

        Map<Integer, Integer> clusterCount = new HashMap<>();
        for (int i = 0; i < k; i++) {
            Vecino vecino = vecinos.get(i);
            int clusterIndex = vecino.getClusterIndex();
            clusterCount.put(clusterIndex, clusterCount.getOrDefault(clusterIndex, 0) + 1);
        }

        int maxCount = 0;
        int asignarCluster = -1;
        for (Map.Entry<Integer, Integer> entry : clusterCount.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                asignarCluster = entry.getKey();
            }
        }

        return asignarCluster;
    }

    public void randomTest(int k) {
        Random rand = new Random();

        System.out.println("10 puntos aleatorios:");

        for (int i = 0; i < 10; i++) {
            double x = rand.nextDouble();
            double y = rand.nextDouble();
            Punto2D puntoAleatorio = new Punto2D(x, y);

            int clusterIndex = clasificar(puntoAleatorio, k);

            System.out.printf("Punto aleatorio (%f, %f) pertenece al clúster %d%n", x, y, clusterIndex);
        }
    }
}


