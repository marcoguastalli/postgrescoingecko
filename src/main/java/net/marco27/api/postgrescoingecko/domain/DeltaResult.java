package net.marco27.api.postgrescoingecko.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class DeltaResult<T> {
    private List<T> added;
    private List<T> removed;
    private List<T> modified;
}
