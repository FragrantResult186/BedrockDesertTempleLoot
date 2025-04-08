package fragrant.loot;

import java.util.List;

public class LootType {
    public static class LootTable {
        private final List<LootPool> pools;

        public LootTable(List<LootPool> pools) {
            this.pools = pools;
        }

        public List<LootPool> getPools() {
            return pools;
        }
    }

    public static class LootPool {
        private final Object rolls; // Integer or RollRange
        private final List<LootEntry> entries;

        public LootPool(Object rolls, List<LootEntry> entries) {
            this.rolls = rolls;
            this.entries = entries;
        }

        public LootPool(int rolls, List<LootEntry> entries) {
            this.rolls = rolls;
            this.entries = entries;
        }

        public Object getRolls() {
            return rolls;
        }

        public List<LootEntry> getEntries() {
            return entries;
        }
    }

    public static class LootEntry {
        private final String name;
        private final int weight;
        private final String type;
        private final List<LootFunction> functions;

        public LootEntry(String name, int weight, String type, List<LootFunction> functions) {
            this.name = name;
            this.weight = weight;
            this.type = type;
            this.functions = functions;
        }

        public String getName() {
            return name;
        }

        public int getWeight() {
            return weight;
        }

        public String getType() {
            return type;
        }

        public List<LootFunction> getFunctions() {
            return functions;
        }
    }

    public static class LootFunction {
        private final String function;
        private final CountRange count;

        public LootFunction(String function, CountRange count) {
            this.function = function;
            this.count = count;
        }

        public String getFunction() {
            return function;
        }

        public CountRange getCount() {
            return count;
        }
    }

    public static class CountRange {
        private final int min;
        private final int max;

        public CountRange(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }

    public static class RollRange {
        private final int min;
        private final int max;

        public RollRange(int min, int max) {
            this.min = min;
            this.max = max;
        }

        public int getMin() {
            return min;
        }

        public int getMax() {
            return max;
        }
    }

    public static class LootItem {
        private final String name;
        private int count;
        private Enchantment enchantment;

        public LootItem(String name, int count) {
            this.name = name;
            this.count = count;
        }

        public String getName() {
            return name;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public Enchantment getEnchantment() {
            return enchantment;
        }

        public void setEnchantment(Enchantment enchantment) {
            this.enchantment = enchantment;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder(name).append(" x").append(count);
            if (enchantment != null) {
                sb.append(" (").append(enchantment).append(")");
            }
            return sb.toString();
        }
    }

    public static class Enchantment {
        private final String name;
        private final int level;

        public Enchantment(String name, int level) {
            this.name = name;
            this.level = level;
        }

        public String getName() {
            return name;
        }

        public int getLevel() {
            return level;
        }

        @Override
        public String toString() {
            return name + " Lv." + level;
        }
    }
}