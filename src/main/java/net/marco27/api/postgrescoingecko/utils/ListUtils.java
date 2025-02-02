package net.marco27.api.postgrescoingecko.utils;

import lombok.NonNull;
import net.marco27.api.postgrescoingecko.domain.DeltaResult;
import net.marco27.api.postgrescoingecko.domain.DeltaResultComparator;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ListUtils {

    private ListUtils() {
        throw new UnsupportedOperationException("Do not instantiate Util class");
    }

    /**
     * Generic method to calculate the delta between two lists
     *
     * @param listA a list of objects
     * @param listB a list of objects
     * @param <T>
     * @return
     */
    public static <T> DeltaResult<T> calculateDelta(@NonNull List<T> listA,
                                                    @NonNull List<T> listB,
                                                    @NonNull DeltaResultComparator<T> comparator) {
        // Find items that were added
        final List<T> added = listB.stream()
                .filter(item -> !listA.contains(item))
                .collect(Collectors.toList());

        // Find items that were removed
        final List<T> removed = listA.stream()
                .filter(item -> !listB.contains(item))
                .collect(Collectors.toList());

        // Find items that were modified (check properties for changes)
        List<T> modified = new ArrayList<>();
        for (T newItem : listB) {
            for (T oldItem : listA) {
                // Compare by id (or another identifier) to check if they are the same item
                if (comparator.equals(newItem, oldItem)) {
                    // Now, check if properties have changed
                    if (comparator.hasChanged(newItem, oldItem)) {
                        modified.add(newItem);
                    }
                    break; // Found the matching old item, no need to check further
                }
            }
        }

        return new DeltaResult<>(added, removed, modified);
    }
}
