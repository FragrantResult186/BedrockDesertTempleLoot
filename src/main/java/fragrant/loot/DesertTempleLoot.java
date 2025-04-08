package fragrant.loot;

import fragrant.random.BedrockRandom;

import java.util.ArrayList;
import java.util.List;

public class DesertTempleLoot {

    /**
     * 指定されたチャンク座標での砂漠の寺院の戦利品を生成
     *
     * @param worldSeed ワールドシード
     * @param chunkX チャンクX座標
     * @param chunkZ チャンクZ座標
     * @return アイテムリスト
     */
    public static List<LootType.LootItem> generateLoot(long worldSeed, int chunkX, int chunkZ) {
        List<Integer> chunkSeeds = generateChestSeed(worldSeed, chunkX, chunkZ);
        List<LootType.LootItem> result = new ArrayList<>();

        for (int chunkSeed : chunkSeeds) {
            List<LootType.LootItem> items = DesertTempleLootGenerator.generateLootItems(
                    DesertTempleLootTable.getDesertTempleLootTable(),
                    chunkSeed
            );
            result.addAll(items);
        }
        return result;
    }

    /**
     * チャンク座標から砂漠の寺院の４つのチェストシードを生成
     */
    private static List<Integer> generateChestSeed(long worldSeed, int chunkX, int chunkZ) {
        BedrockRandom.BedrockChunkRandom chunkRandom = new BedrockRandom.BedrockChunkRandom(worldSeed);
        BedrockRandom random = chunkRandom.chunkRandom(chunkX, chunkZ);

        random.nextInt(); // 乱数を消費
        List<Integer> seeds = new ArrayList<>();
        for (int i = 0; i < 4; i++) { // 4つのチェストシードを生成
            seeds.add(random.nextInt());
        }
        return seeds;
    }
}