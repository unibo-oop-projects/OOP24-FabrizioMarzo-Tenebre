package view.graphics_util;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.awt.image.BufferedImage;


public class CacheFactoryImpl implements CacheFactory{

   private class CacheTemplate<K,V> implements Cache<K,V> {

    private final Map<K,V> mapCache;

    protected CacheTemplate(final Supplier<Map<K,V>> mapSupplier){
        this.mapCache= mapSupplier.get();
    }

    @Override
    public boolean contains(final K key) {
        return mapCache.containsKey(key);
    }

    @Override
    public V get(final K key) {
        return mapCache.get(key);
    }

    @Override
    public void put(final K key,final V value) {
        mapCache.put(key, value);
    }
   
   }

    @Override
    public Cache<String,BufferedImage> imageCache() {
        return new CacheTemplate<>(HashMap::new);
    }
    
}
