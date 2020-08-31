import java.util.HashMap;
import java.util.Map;

/*
@author Alex Hurley
github @ahh1539
Date: 8/31/2020
 */

public class Warehouse {

    private static Map<String, Map<String, Integer>> inventoryAllocator(Map<String, Integer> itemsOrdered, Map<String, Map<String, Integer>> warehouseInventory) {
        int remainingNumNeeded = 0;
        Map<String, Map<String, Integer>> resultMap = new HashMap<String, Map<String, Integer>>();

        for (Map.Entry<String,Integer> ordered_item : itemsOrdered.entrySet()) {
            Boolean remainingWarehouses = true;
            remainingNumNeeded = ordered_item.getValue();
            
            while (remainingNumNeeded != 0 && remainingWarehouses) {
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
                remainingWarehouses = false;
            }
            if (remainingNumNeeded != 0) {
                for (Map.Entry<String, Map<String, Integer>> thingy : resultMap.entrySet()) {
                    resultMap.get(thingy.getKey()).remove(ordered_item.getKey());
                }
            }
        }
        return resultMap;
    }
    
    public static void main(String[] args) {

        Map<String, Integer> orderedItems = new HashMap<String, Integer>();
        Map<String, Map<String, Integer>> warehouseIventories = new HashMap<String, Map<String, Integer>>();

        /*
        First Test Case
        Input: [{apple:1},{owd:{apple:3}}]
        Expected output: {owd={apple=1}}
        */
        orderedItems.put("apple", 1);
        warehouseIventories.put("owd", new HashMap<String, Integer>());
        warehouseIventories.get("owd").put("apple", 3);
        System.out.println("\nFirst Test: " + inventoryAllocator(orderedItems, warehouseIventories));
        orderedItems.clear();
        warehouseIventories.clear();


        /*
        Second Test Case
        Input: [{apple:10},{owd:{apple:5}, dm:{apple:5}}]
        Expected output: {dm={apple=5}, owd={apple=5}}
        */
        orderedItems.put("apple", 10);
        warehouseIventories.put("owd", new HashMap<String, Integer>());
        warehouseIventories.put("dm", new HashMap<String, Integer>());

        warehouseIventories.get("owd").put("apple", 5);
        warehouseIventories.get("dm").put("apple", 5);

        System.out.println("\nSecond Test: " +inventoryAllocator(orderedItems, warehouseIventories));
        orderedItems.clear();
        warehouseIventories.clear();


        /*
        Third Test Case
        Input: [{apple:1},{owd:{apple:0}}]
        Expected output: {owd={}}
        */
        orderedItems.put("apple", 1);
        warehouseIventories.put("owd", new HashMap<String, Integer>());
        warehouseIventories.get("owd").put("apple", 0);
        System.out.println("\nThird Test: " +inventoryAllocator(orderedItems, warehouseIventories));
        orderedItems.clear();
        warehouseIventories.clear();


        /*
        Fourth Test Case
        Input: [{apple:4, orange:5, orange:5}, {owd:{apple:3, orange:1, banana:4}, dm:{apple:1, orange:4, banana:3}}]
        Expected output: {dm={orange=4, banana=3, apple=1}, owd={orange=1, banana=2, apple=3}}
        */        
        orderedItems.put("orange", 5);
        orderedItems.put("apple", 4);
        orderedItems.put("banana", 5);

        warehouseIventories.put("owd", new HashMap<String, Integer>());
        warehouseIventories.put("dm", new HashMap<String, Integer>());

        warehouseIventories.get("owd").put("apple", 3);
        warehouseIventories.get("owd").put("orange", 1);
        warehouseIventories.get("owd").put("banana", 4);

        warehouseIventories.get("dm").put("apple", 1);
        warehouseIventories.get("dm").put("orange", 4);
        warehouseIventories.get("dm").put("banana", 3);


        System.out.println("\nFourth Test: " +inventoryAllocator(orderedItems, warehouseIventories));
        orderedItems.clear();
        warehouseIventories.clear();
        

        /*
        Fifth Test Case
        Input: [{orange:24}, {owd:{orange:6}, dm:{orange:6}, pq:{orange:6}, gfk:{orange:7}}]
        Expected output: {pq={orange=6}, dm={orange=6}, owd={orange=6}, gfk={orange=6}}
        */        
        orderedItems.put("orange", 24);

        warehouseIventories.put("owd", new HashMap<String, Integer>());
        warehouseIventories.put("dm", new HashMap<String, Integer>());
        warehouseIventories.put("pq", new HashMap<String, Integer>());
        warehouseIventories.put("gfk", new HashMap<String, Integer>());

        warehouseIventories.get("owd").put("orange", 6);
        warehouseIventories.get("dm").put("orange", 6);
        warehouseIventories.get("pq").put("orange", 6);
        warehouseIventories.get("gfk").put("orange", 7);


        System.out.println("\nFifth Test: " +inventoryAllocator(orderedItems, warehouseIventories)+"\n");
        orderedItems.clear();
        warehouseIventories.clear();
     }
}