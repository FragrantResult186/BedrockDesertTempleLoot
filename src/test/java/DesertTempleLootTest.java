import fragrant.loot.DesertTempleLoot;
import fragrant.loot.LootType;

import java.util.List;

public class DesertTempleLootTest {
    public static void main(String[] args) {

        List<LootType.LootItem> items = DesertTempleLoot.generateLoot(-999999999999999999L, 355, -669);

        for (LootType.LootItem item : items) {
            System.out.println(item);
        }
    }
}