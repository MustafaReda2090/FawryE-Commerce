import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ShippingService {
    public void ship(List<Shippable> items) {
        double totalWeight = 0;
        System.out.println("\n- * Shipment notice **\n");
        Map<String, Double> itemWeights = new LinkedHashMap<>();
        Map<String, Integer> itemCounts = new LinkedHashMap<>();

        for (Shippable item : items) {
            String name = item.getName();
            double weight = item.getWeight();
            itemWeights.put(name, weight + itemWeights.getOrDefault(name, 0.0));
            itemCounts.put(name, 1 + itemCounts.getOrDefault(name, 0));
            totalWeight += weight;
        }

        for (String name : itemWeights.keySet()) {
            System.out.printf("%dx %-12s %dg\n",
                itemCounts.get(name),
                name,
                (int)(itemWeights.get(name) * 1000));
        }

        System.out.printf("\nTotal package weight %.1fkg\n\n", totalWeight);
    }
}
