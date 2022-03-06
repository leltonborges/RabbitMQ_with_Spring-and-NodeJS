package org.lib.amqp.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PrecoDTO implements Serializable {
    private static final long serialVersionUID = -8300265661406402425L;
    private Integer codProducto;
    private BigDecimal price;
}
