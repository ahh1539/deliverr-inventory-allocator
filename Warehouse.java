import java.util.HashMap;
import java.util.Map;

public class Warehouse {
    
    public static void main(String[] args) {

        Map<String, Integer> orderedItems = new HashMap<String, Integer>();
        Map<String, Map<String, Integer>> warehouseIventories = new HashMap<String, Map<String, Integer>>();


        // Items ordered test 1
        orderedItems.put("apple", 1);

        // Warehouse inventory test 1
        warehouseIventories.put("owd", new HashMap<String, Integer>());
        warehouseIventories.get("owd").put("apple", 3);
        System.out.println(inventoryAllocator(orderedItems, warehouseIventories));
        orderedItems.clear();
        warehouseIventories.clear();




        // Items ordered test 2
        orderedItems.put("apple", 10);

        // Warehouse inventory test 2
        warehouseIventories.put("owd", new HashMap<String, Integer>());
        warehouseIventories.put("dm", new HashMap<String, Integer>());

        warehouseIventories.get("owd").put("apple", 5);
        warehouseIventories.get("dm").put("apple", 5);

        System.out.println(inventoryAllocator(orderedItems, warehouseIventories));
        orderedItems.clear();
        warehouseIventories.clear();



        // Items ordered test 3
        orderedItems.put("apple", 1);

        // Warehouse inventory test 3
        warehouseIventories.put("owd", new HashMap<String, Integer>());

        warehouseIventories.get("owd").put("apple", 0);

        System.out.println(inventoryAllocator(orderedItems, warehouseIventories));
        orderedItems.clear();
        warehouseIventories.clear();




        // Items ordered test 3
        orderedItems.put("orange", 5);
        orderedItems.put("apple", 4);
        orderedItems.put("banana", 5);

        // Warehouse inventory test 3
        warehouseIventories.put("owd", new HashMap<String, Integer>());
        warehouseIventories.put("dm", new HashMap<String, Integer>());

        warehouseIventories.get("owd").put("apple", 3);
        warehouseIventories.get("owd").put("orange", 1);
        warehouseIventories.get("owd").put("banana", 4);

        warehouseIventories.get("dm").put("apple", 1);
        warehouseIventories.get("dm").put("orange", 4);
        warehouseIventories.get("dm").put("banana", 3);


        System.out.println(inventoryAllocator(orderedItems, warehouseIventories));
        orderedItems.clear();
        warehouseIventories.clear();
        
     }
    
    private static Map<String, Map<String, Integer>> inventoryAllocator(Map<String, Integer> itemsOrdered, Map<String, Map<String, Integer>> warehouseInventory) {
        int remainingNumNeeded = 0;
        Map<String, Map<String, Integer>> resultMap = new HashMap<String, Map<String, Integer>>();

        for (Map.Entry<String,Integer> ordered_item : itemsOrdered.entrySet()) {
            Boolean remaining_warehouses = true;
            remainingNumNeeded = ordered_item.getValue();
            
            while (remainingNumNeeded != 0 && remaining_warehouses) {
                for (Map.Entry<String, Map<String, Integer>> warehouseItem : warehouseInventory.entrySet()) {
                    if (!resultMap.containsKey(warehouseItem.getKey())){
                        resultMap.put(warehouseItem.getKey(), new HashMap<String, Integer>());
                    }
                    if (warehouseItem.getValue().get(ordered_item.getKey()) != null) {
                        if (remainingNumNeeded <= warehouseItem.getValue().get(ordered_item.getKey())) {
                            resultMap.get(warehouseItem.getKey()).put(ordered_item.getKey(), remainingNumNeeded);
                            remainingNumNeeded = 0;
                            break;
                        } else {
                            remainingNumNeeded = remainingNumNeeded - warehouseItem.getValue().get(ordered_item.getKey());
                            resultMap.get(warehouseItem.getKey()).put(ordered_item.getKey(), warehouseItem.getValue().get(ordered_item.getKey()));
                        }
                    }
                }
                remaining_warehouses = false;
            }
            if (remainingNumNeeded != 0) {
                for (Map.Entry<String, Map<String, Integer>> thingy : resultMap.entrySet()) {
                    resultMap.get(thingy.getKey()).remove(ordered_item.getKey());
                }
            }
        }
        return resultMap;
    }
}