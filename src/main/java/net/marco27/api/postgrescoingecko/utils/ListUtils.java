package net.marco27.api.postgrescoingecko.utils;

import lombok.NonNull;
import net.marco27.api.postgrescoingecko.domain.DeltaResult;
import net.marco27.api.postgrescoingecko.domain.DeltaResultComparator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ListUtils {

    private ListUtils() {
        throw new UnsupportedOperationException("Do not instantiate Util class");
    }

    /**
     * Generic method to calculate the delta between two lists
     *
     * @param listA      a list of objects
     * @param listB      a list of objects
     * @param comparator to compare the Lists
     * @param <T>
     * @return
     */
    public static <T> DeltaResult<T> calculateDelta(@NonNull List<T> listA,
                                                    @NonNull List<T> listB,
                                                    @NonNull DeltaResultComparator<T> comparator) {
        List<T> added = new ArrayList<>();
        List<T> removed = new ArrayList<>();
        List<T> modified = new ArrayList<>();

        // Build a map of listA for O(1) lookups
        Map<String, T> mapA = new HashMap<>();
        for (T item : listA) {
            mapA.put(comparator.getKey(item), item);
        }

        // Build a map of listB for O(1) lookups
        Map<String, T> mapB = new HashMap<>();
        for (T item : listB) {
            mapB.put(comparator.getKey(item), item);
        }

        // Check items in listB
        for (T newItem : listB) {
            String key = comparator.getKey(newItem);
            T oldItem = mapA.get(key);

            if (oldItem == null) {
                added.add(newItem);
            } else if (comparator.hasChanged(newItem, oldItem)) {
                modified.add(newItem);
            }
        }

        // Check for removed items
        for (T oldItem : listA) {
            String key = comparator.getKey(oldItem);
            if (!mapB.containsKey(key)) {
                removed.add(oldItem);
            }
        }

        return new DeltaResult<>(added, removed, modified);
    }
}
