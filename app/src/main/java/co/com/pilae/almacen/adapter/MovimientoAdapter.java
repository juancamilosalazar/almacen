package co.com.pilae.almacen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.almacen.R;
import co.com.pilae.almacen.entidades.Movimiento;

public class MovimientoAdapter extends BaseAdapter {

    private final LayoutInflater inflater;
    private List<Movimiento> movimientosOut;
    private List<Movimiento> movimientosin;


    public MovimientoAdapter(Context context, List<Movimiento> Movimiento){
        movimientosOut = Movimiento;
        movimientosin  = Movimiento;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return movimientosOut.size();
    }

    @Override
    public Movimiento getItem(int position) {
        return movimientosOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView != null) {
            holder = (ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.movimeinto_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.movimientoDescripcion.setText(movimientosOut.get(position).getDescripcion());
        holder.movimientoValor.setText(String.valueOf(movimientosOut.get(position).getValor()));
        holder.movimientoFecha.setText(movimientosOut.get(position).getFecha().toString());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.movimientoFecha)
        TextView movimientoFecha;
        @BindView(R.id.movimientoDescripcion)
        TextView movimientoDescripcion;
        @BindView(R.id.movimientoValor)
        TextView movimientoValor;

        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
