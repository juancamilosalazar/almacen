package co.com.pilae.almacen.entidades;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Persona {

    private Integer idPersona;
    private String nombre;
    private String detalle;
    private String referencia;
    private String numeroTelefono;
    private double saldo;
    private double tope;
    private String idFirebase;
    private List<Movimiento> movimientos;

}
