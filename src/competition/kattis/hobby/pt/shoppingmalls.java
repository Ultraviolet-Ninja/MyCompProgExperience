package competition.kattis.hobby.pt;

import competition.annotations.Incorrect;
import competition.annotations.SiteType;
import competition.annotations.Website;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

@SiteType(type = Website.KATTIS)
@Incorrect(url = "https://open.kattis.com/problems/shoppingmalls")
public class shoppingmalls {
    private static final StringBuilder BUFFER = new StringBuilder();
    private static final BufferedReader IN = new BufferedReader(new InputStreamReader(System.in));
    private static final BufferedOutputStream OUT = new BufferedOutputStream(System.out);

    public static void main(String[] args) throws IOException {
        String[] line = IN.readLine().split(" ");

        int shopCount = Integer.parseInt(line[0]);
        int connections = Integer.parseInt(line[1]);

        Shop[] shops = new Shop[shopCount];
        for (int i = 0; i < shopCount; i++) {
            var shop = new Shop(i, IN.readLine().split(" "));
            shops[i] = shop;
        }

        for (int i = 0; i < connections; i++) {
            line = IN.readLine().split(" ");
            Shop a = shops[Integer.parseInt(line[0])];
            Shop b = shops[Integer.parseInt(line[1])];
            createEdges(a, b, line[2]);
        }

        int queries = Integer.parseInt(IN.readLine());

        for (int i = 0; i < queries; i++) {
            line = IN.readLine().split(" ");
            int a = Integer.parseInt(line[0]);
            int b = Integer.parseInt(line[1]);
            var minimums = pathBetween(shops, a, b);

            print(minimums, b);
        }
        OUT.flush();
    }

    private static ShopEdge[] pathBetween(Shop[] shops, int a, int b) {
        ShopEdge[] explored = new ShopEdge[shops.length];
        Shop from = shops[a];
        Shop to = shops[b];

        PriorityQueue<ShopEdge> frontier = new PriorityQueue<>();
        frontier.add(new ShopEdge(0, null, from));

        while (!frontier.isEmpty()) {
            var edge = frontier.poll();
            var shop = edge.to;

            explored[shop.id] = edge;

            if (shop == to) {
                return explored;
            }

            for (ShopEdge next : shop.edges) {
                var nextShop = next.to;

                if (explored[nextShop.id] == null) {
                    frontier.add(next);
                }
            }
        }
        return explored;
    }

    private static void print(ShopEdge[] minimums, int b) throws IOException {
        var edge = minimums[b];
        BUFFER.append(edge.to);

        while (edge.from != null) {
            BUFFER.append(" ").append(edge.from);

            edge = minimums[edge.from.id];
        }
        OUT.write(BUFFER.reverse().append("\n").toString().getBytes());
        BUFFER.setLength(0);
    }

    private static void createEdges(Shop a, Shop b, String method) {
        switch (method) {
            case "walking":
            case "stairs":
                double distance = a.euclideanDistance(b);
                a.edges.add(new ShopEdge(distance, a, b));
                b.edges.add(new ShopEdge(distance, b, a));
                break;
            case "lift":
                a.edges.add(new ShopEdge(1, a, b));
                b.edges.add(new ShopEdge(1, b, a));
                break;
            default:
                a.edges.add(new ShopEdge(1, a, b));
                b.edges.add(new ShopEdge(a.euclideanDistance(b) * 3, b, a));
        }
    }

    private static class Shop {
        private final int id, x, y, height;
        private final List<ShopEdge> edges = new ArrayList<>();

        public Shop(int id, String[] line) {
            this.id = id;
            this.x = Integer.parseInt(line[1]);
            this.y = Integer.parseInt(line[2]);
            this.height = Integer.parseInt(line[0]) * 5;
        }

        public double euclideanDistance(Shop to) {
            double dX = x - to.x;
            double dY = y - to.y;
            double dZ = height - to.height;

            return Math.sqrt((dX * dX) + (dY * dY) + (dZ * dZ));
        }

        @Override
        public int hashCode() {
            return id;
        }

        @Override
        public String toString() {
            return String.valueOf(id);
        }
    }

    private static class ShopEdge implements Comparable<ShopEdge> {
        private final double weight;
        private final Shop from, to;

        public ShopEdge(double weight, Shop from, Shop to) {
            this.weight = weight;
            this.from = from;
            this.to = to;
        }

        @Override
        public int compareTo(ShopEdge o) {
            return Double.compare(this.weight, o.weight);
        }

        @Override
        public String toString() {
            return from + " -> " + to;
        }
    }
}
