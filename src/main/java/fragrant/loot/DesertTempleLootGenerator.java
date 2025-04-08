package fragrant.loot;

import fragrant.loot.LootType.*;
import fragrant.random.BedrockRandom;

import java.util.ArrayList;
import java.util.List;

public class DesertTempleLootGenerator {

    /**
     * 指定されたシードとルートテーブルからアイテムを生成
     *
     * @param lootTable ルートテーブル
     * @param randomSeed 乱数シード
     * @return アイテムリスト
     */
    public static List<LootItem> generateLootItems(LootTable lootTable, int randomSeed) {
        BedrockRandom random = new BedrockRandom(randomSeed);
        List<LootItem> items = new ArrayList<>();

        for (LootPool pool : lootTable.getPools()) {
            int totalWeight = 0;
            for (LootEntry entry : pool.getEntries()) {
                totalWeight += entry.getWeight();
            }
            if (totalWeight <= 0) {
                continue;
            }
            random.nextFloat(); // 乱数を消費
            int rolls;
            if (pool.getRolls() instanceof Integer) {
                random.nextInt(); // 乱数を消費
                rolls = (Integer) pool.getRolls();
            } else {
                RollRange rollRange = (RollRange) pool.getRolls();
                rolls = BedrockRandom.genRandIntRange(rollRange.getMin(), rollRange.getMax(), random);
            }

            for (int i = 0; i < rolls; i++) {
                int selectedWeight = random.nextInt(totalWeight);
                int currentWeight = 0;

                for (LootEntry entry : pool.getEntries()) {
                    currentWeight += entry.getWeight();
                    if (selectedWeight < currentWeight) {
                        if ("item".equals(entry.getType())) {
                            items.add(generateItemFromEntry(entry, random));
                        }
                        break;
                    }
                }
            }
        }
        return items;
    }

    private static LootItem generateItemFromEntry(LootEntry entry, BedrockRandom random) {
        LootItem item = new LootItem(entry.getName(), 1);

        if (entry.getFunctions() != null) {
            for (LootFunction function : entry.getFunctions()) {
                applyLootFunction(item, function, random);
            }
        }
        return item;
    }

    private static void applyLootFunction(LootItem item, LootFunction function, BedrockRandom random) {
        switch (function.getFunction()) {
            case "set_count":
                CountRange countRange = function.getCount();
                item.setCount(BedrockRandom.genRandIntRange(countRange.getMin(), countRange.getMax(), random));
                break;
            case "enchant_randomly":
                random.nextInt(); // エンチャント用の乱数を消費
//                item.setEnchantment(generateRandomEnchantment(random));
                break;
        }
    }

/*
TODO: Bedrock用エンチャントを再現する必要があります。（再現をしなくても乱数に影響はありません）
 */
//    private static Enchantment generateRandomEnchantment(BedrockRandom random)
//
//        return new Enchantment(enchantName, level);
//    }
}