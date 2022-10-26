package or.example.rdesmarket_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class Buscar extends AppCompatActivity {

    private RecyclerView rvRubro;
    ArrayList<RubroObj> list_rubro;
    GreedAdapter adapter2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buscar);

        rvRubro = (RecyclerView) findViewById(R.id.rv_Rubro);
        rvRubro.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvRubro.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.HORIZONTAL));
        rvRubro.setLayoutManager(new GridLayoutManager(this,2));
        list_rubro = new ArrayList<>();
        list_rubro.add(new RubroObj(R.drawable.congelados,"CONGELADOS"));
        list_rubro.add(new RubroObj(R.drawable.limpieza,"LIMPIEZA"));
        list_rubro.add(new RubroObj(R.drawable.bebidas,"BEBIDAS SIN ALCOHOL"));
        list_rubro.add(new RubroObj(R.drawable.alcohol,"BEBIDAS CON ALCOHOL"));
        list_rubro.add(new RubroObj(R.drawable.almacen,"ALMACEN"));
        list_rubro.add(new RubroObj(R.drawable.frescos,"FRESCOS"));

        adapter2 = new GreedAdapter(list_rubro);
        accion();
        rvRubro.setAdapter(adapter2);

    }

    public void accion (){
        adapter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               //list_rubro.get(rvRubro.getChildAdapterPosition(view)).getTv_titulo();
                String a =  list_rubro.get(rvRubro.getChildAdapterPosition(view)).getTv_titulo();

                switch (a){
                    case "LIMPIEZA":
                    case "BEBIDAS SIN ALCOHOL":
                    case "ALMACEN":
                    case "FRESCOS": cambiarSubrubros(view,a);
                    break;
                    case "CONGELADOS":
                    case "BEBIDAS CON ALCOHOL": cambiarLista(view,a);
                        break;

                    default:
                }

            }
        });
    }

    public void cambiarSubrubros (View view, String a){
        Intent pasar = new Intent(this, SubRubros.class);
        pasar.putExtra("tipo",a);
        startActivity(pasar);
    }
    public void cambiarLista (View view, String a){
        Intent pasar = new Intent(this, ResultadoProducto.class);
        pasar.putExtra("tipo",a);
        startActivity(pasar);
    }
}
