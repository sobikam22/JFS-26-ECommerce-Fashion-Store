import java.util.List;

public class GenericToolkit {

    // 1. Generic Pair Class
    public static class Pair<K, V> {
        private K key;
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() { return key; }
        public V getValue() { return value; }
    }

    // 2. Bounded Box Class (T must extend Number)
    public static class Box<T extends Number> {
        private T item;

        public Box(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }
    } // <-- Make sure this closing brace is present

    // 3. Generic Max Method using Comparable (Bounded Type Parameter)
    public static <T extends Comparable<T>> T findMax(List<T> list) {
        if (list == null || list.isEmpty()) return null;
        T max = list.get(0);
        for (T item : list) {
            if (item.compareTo(max) > 0) {
                max = item;
            }
        }
        return max;
    }

    // 4. Wildcard Method demonstrating PECS (? extends / ? super)
    public static double sumNumbers(List<? extends Number> numbers) {
        double sum = 0.0;
        for (Number n : numbers) {
            sum += n.doubleValue();
        }
        return sum;
    }
}