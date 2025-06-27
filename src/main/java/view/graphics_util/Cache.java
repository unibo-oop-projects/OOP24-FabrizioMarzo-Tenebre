package view.graphics_util;


public interface Cache<K,V> {
    
    boolean contains(final K key);

    V get(final K key);

    void put(final K key,final V value);
}
