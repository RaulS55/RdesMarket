package or.example.rdesmarket_v10;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ViewHolderLista> implements View.OnClickListener {
    ArrayList<ProductoObj> lista;
    private View.OnClickListener listener;

    public ListAdapter(ArrayList<ProductoObj> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public ViewHolderLista onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listado_row,parent,false);
        view.setOnClickListener(this);
        return new ViewHolderLista(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderLista holder, int position) {
        holder.etiNombre.setText(lista.get(position).getNombre());
        holder.etiPrecio.setText(lista.get(position).getPrecio());
        holder.etiMarca.setText(lista.get(position).getMarca());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public void setOnClickListener (View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View view) {
        if (listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderLista extends RecyclerView.ViewHolder {
        TextView etiNombre,etiMarca,etiPrecio;
        public ViewHolderLista(@NonNull View itemView) {
            super(itemView);
            etiNombre = (TextView) itemView.findViewById(R.id.tv_list_nom);
            etiMarca = (TextView) itemView.findViewById(R.id.tv_list_marca);
            etiPrecio = (TextView) itemView.findViewById(R.id.tv_list_precio);
        }
    }
}
