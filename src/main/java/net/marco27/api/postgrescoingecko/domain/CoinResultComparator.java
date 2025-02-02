package net.marco27.api.postgrescoingecko.domain;

import net.marco27.api.postgrescoingecko.model.Coin;

public class CoinResultComparator implements DeltaResultComparator<Coin> {
    @Override
    public boolean equals(Coin newItem, Coin oldItem) {
        return newItem.getId().equals(oldItem.getId());
    }

    @Override
    public boolean hasChanged(Coin newItem, Coin oldItem) {
        // Check if properties like 'symbol' or 'name' have changed
        return !newItem.getSymbol().equals(oldItem.getSymbol()) || !newItem.getName().equals(oldItem.getName());
    }
}