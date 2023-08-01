package Photolab;

import java.util.List;

public interface InventoryDao {
    void addItem(InventoryItem item);
    void updateItem(InventoryItem item);
    void deleteItem(int itemId);
    List<InventoryItem> getAllItems();
    InventoryItem getItemById(int itemId);
}
