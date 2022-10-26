package or.example.rdesmarket_v10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class SubRubros extends AppCompatActivity {

    ArrayList<SubRuOBj> list_sub;
    RecyclerView rv_sub;
    SubRuAdapter adapter3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub_rubros);

        String a = getIntent().getStringExtra("tipo");

        rv_sub = findViewById(R.id.rv_SubRubro);
        rv_sub.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv_sub.setLayoutManager(new LinearLayoutManager(this));

        list_sub = new ArrayList<>();


        switch (a){
            case "LIMPIEZA": cargarLimpieza();
            break;
            case "BEBIDAS SIN ALCOHOL":cargarBebidas();
            break;
            case "ALMACEN": cargarAlmacen();
            break;
            case  "FRESCOS": cargarFrescos();
        }

        adapter3 = new SubRuAdapter(list_sub);
        accion();

        rv_sub.setAdapter(adapter3);
    }

    public void accion (){
        adapter3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //list_rubro.get(rvRubro.getChildAdapterPosition(view)).getTv_titulo();
                String a =  list_sub.get(rv_sub.getChildAdapterPosition(view)).getSubRubro();
                cambiarLista(view,a);


            }
        });
    }

    public void cambiarLista (View view, String a){
        Intent pasar = new Intent(this, ResultadoProducto.class);
        pasar.putExtra("tipo",a);
        startActivity(pasar);
    }

    public void cargarAlmacen (){
        list_sub.add(new SubRuOBj("ACEITES"));
        list_sub.add(new SubRuOBj("ENDULZANTES"));
        list_sub.add(new SubRuOBj("CEREALES Y LEGUMBRES"));
        list_sub.add(new SubRuOBj("LECHES"));
        list_sub.add(new SubRuOBj("REPOSTERIA"));
        list_sub.add(new SubRuOBj("DESAYUNO Y MERIENDA"));
        list_sub.add(new SubRuOBj("PASTA Y ARINA"));
        list_sub.add(new SubRuOBj("SEMI-PREPARADAS"));
    }
    public void cargarBebidas (){
        list_sub.add(new SubRuOBj("AGUAS"));
        list_sub.add(new SubRuOBj("JUGOS"));
        list_sub.add(new SubRuOBj("GASEOSAS"));
        list_sub.add(new SubRuOBj("AGUAS SABORIZADAS"));
        list_sub.add(new SubRuOBj("REIDRATANTES"));
        list_sub.add(new SubRuOBj("ENERGIZANTES"));
    }
    public void cargarFrescos (){
        list_sub.add(new SubRuOBj("LACTEOS"));
        list_sub.add(new SubRuOBj("POSTRES"));
        list_sub.add(new SubRuOBj("PASTAS Y TAPAS"));
        list_sub.add(new SubRuOBj("QUESOS"));
        list_sub.add(new SubRuOBj("FIAMBRE Y EMBUTIDOS"));
    }

    public void cargarLimpieza (){
        list_sub.add(new SubRuOBj("ACCESORIOS"));
        list_sub.add(new SubRuOBj("INSECTICIDAS"));
        list_sub.add(new SubRuOBj("PAPELES"));
        list_sub.add(new SubRuOBj("HIGIENE PERSONAL"));
        list_sub.add(new SubRuOBj("ARTICULOS PARA COCINA"));
        list_sub.add(new SubRuOBj("LIMPIEZA DE PISO Y MUEBLES"));
    }
}
