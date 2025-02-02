package net.marco27.api.postgrescoingecko.domain;

public interface DeltaResultComparator<T> {
    boolean equals(T newItem, T oldItem);

    boolean hasChanged(T newItem, T oldItem);
}
