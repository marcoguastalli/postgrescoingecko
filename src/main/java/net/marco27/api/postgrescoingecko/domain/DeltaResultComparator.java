package net.marco27.api.postgrescoingecko.domain;

public interface DeltaResultComparator<T> {
    String getKey(T item);

    boolean equals(T newItem, T oldItem);

    boolean hasChanged(T newItem, T oldItem);
}
