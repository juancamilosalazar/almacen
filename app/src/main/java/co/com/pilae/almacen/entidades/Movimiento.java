package co.com.pilae.almacen.entidades;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Movimiento {
    private String descripcion;
    private double valor;
    private String fecha;
}
