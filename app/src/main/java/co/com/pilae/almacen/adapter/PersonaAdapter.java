package co.com.pilae.almacen.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import co.com.pilae.almacen.R;
import co.com.pilae.almacen.entidades.Persona;

public class PersonaAdapter extends BaseAdapter implements Filterable {

    private final LayoutInflater inflater;

    private List<Persona> personaOut;
    private  List<Persona> personaIn;


    public PersonaAdapter(Context context, List<Persona> personas){
        personaOut = personas;
        personaIn = personas;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return personaOut.size();
    }

    @Override
    public Persona getItem(int position) {
        return personaOut.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public Filter getFilter() {

        Filter filter = new Filter() {
            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                personaOut = (List<Persona>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                List<Persona> FilteredArrList = new ArrayList<>();
                if (personaIn == null) {
                    personaIn = new ArrayList<>(personaOut);
                }
                if (constraint == null || constraint.length() == 0) {
                    results.count = personaIn.size();
                    results.values = personaIn;
                } else {

                    constraint = constraint.toString().toLowerCase();
                    for (int i = 0; i < personaIn.size(); i++) {
                        String data = personaIn.get(i).getNombre();
                        if (data.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(personaIn.get(i));
                        }
                        String numero = personaIn.get(i).getNumeroTelefono();
                        if (numero.toLowerCase().contains(constraint.toString())) {
                            FilteredArrList.add(personaIn.get(i));
                        }
                    }
                    results.count = FilteredArrList.size();
                    results.values = FilteredArrList;
                }
                return results;
            }
        };
        return filter;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        PersonaAdapter.ViewHolder holder;
        if (convertView != null) {
            holder = (PersonaAdapter.ViewHolder) convertView.getTag();
        } else {
            convertView = inflater.inflate(R.layout.persona_item_layout, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        holder.telefono.setText(personaOut.get(position).getNumeroTelefono());
        holder.saldo.setText(String.valueOf(personaOut.get(position).getSaldo()));
        holder.nombre.setText(personaOut.get(position).getNombre());

        return convertView;
    }

    class ViewHolder {
        @BindView(R.id.nombre)
        TextView nombre;
        @BindView(R.id.telefono)
        TextView telefono;
        @BindView(R.id.saldo)
        TextView saldo;


        public ViewHolder(View view){
            ButterKnife.bind(this,view);
        }
    }
}
