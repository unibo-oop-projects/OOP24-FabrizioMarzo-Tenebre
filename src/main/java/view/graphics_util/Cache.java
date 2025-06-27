package view.graphics_util;


public interface Cache<K,V> {
    
    boolean contains(final K key);

    V get(K key);

    void put(K key, V value);
}
