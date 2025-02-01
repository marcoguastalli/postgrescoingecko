package net.marco27.api.postgrescoingecko.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Data
@AllArgsConstructor
@EqualsAndHashCode
public class JsonResponseResult implements Serializable {

    private Object result;

}
