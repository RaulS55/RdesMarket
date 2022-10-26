package or.example.rdesmarket_v10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Switch;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ResultadoProducto extends AppCompatActivity {
    private RecyclerView rv_lista;
    ArrayList<ProductoObj> list_producto;
    ListAdapter adapter4;
    String a;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado_producto);

        a = getIntent().getStringExtra("tipo");


        switch (a){
            case "CONGELADOS": a="Congelados";
            break;
            case "BEBIDAS CON ALCOHOL": a="Con Alcohol";
            break;
            case "ACEITES" : a="Aceite";
            break;
            case "ENDULZANTES" : a="endulzantes";
                break;
            case "CEREALES Y LEGUMBRES" : a="cereales y legumbres";
                break;
            case "LECHES" : a="leches";
                break;
            case "REPOSTERIA" : a="reposteria";
                break;
            case "DESAYUNO Y MERIENDA" : a="Desayuno y Merienda";
                break;
            case "PASTA Y ARINA" : a="Pasta y Arinas";
                break;
            case "AGUAS" : a="Aguas";
                break;
            case "JUGOS" : a="Jugos";
                break;
            case "GASEOSAS" : a="Gaseosas";
                break;
            case "AGUAS SABORIZADAS" : a="Aguas Sabolizadas";
                break;
            case "REIDRATANTES" : a="Reidratantes";
                break;
            case "ENERGIZANTES" : a="Energizantes";
                break;
            case "LACTEOS" : a="Lacteos";
                break;
            case "POSTRES" : a="Postres";
                break;
            case "PASTAS Y TAPAS" : a="Pastas y Tapas";
                break;
            case "QUESOS" : a="Quesos";
                break;
            case "FIAMBRE Y EMBUTIDOS" : a="Fiambres y Embutidos";
                break;
            case "ACCESORIOS" : a="Accesorios";
                break;
            case "INSECTICIDAS" : a="Insecticidas";
                break;
            case "PAPELES" : a="Papeles";
                break;
            case "HIGIENE PERSONAL" : a="Higiene Personal";
                break;
            case "ARTICULOS PARA COCINA" : a="Articulos para Cocinar";
                break;
            case "LIMPIEZA DE PISO Y MUEBLES" : a="Limpieza de Piso y Muebles";
                break;

        }



        rv_lista = findViewById(R.id.rv_listado);
        rv_lista.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv_lista.setLayoutManager(new LinearLayoutManager(this));
        list_producto = new ArrayList<>();

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter4 = new ListAdapter(list_producto);
        accion();
        rv_lista.setAdapter(adapter4);



        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_producto.removeAll(list_producto);
                for (DataSnapshot snapshot :
                        dataSnapshot.getChildren()) {
                    ProductoObj prod = snapshot.getValue(ProductoObj.class);
                    switch (prod.getLocal()){
                        case 700082: prod.setLocal(R.drawable.changomas);
                            break;
                        case 700029: prod.setLocal(R.drawable.carrefour);
                            break;
                        case 700009: prod.setLocal(R.drawable.comodin);
                            break;
                        case 700073: prod.setLocal(R.drawable.vea);
                            break;
                        case 700062: prod.setLocal(R.drawable.dia);
                            break;
                    }
                if (a.equals(prod.getRubro())){
                    list_producto.add(prod);
                }else {
                    if (a.equals(prod.getSubrubro())){
                        list_producto.add(prod);
                    }
                }
                }
                adapter4.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });

    }

    public void accion (){
        adapter4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //list_rubro.get(rvRubro.getChildAdapterPosition(view)).getTv_titulo();
                String s =  list_producto.get(rv_lista.getChildAdapterPosition(view)).getEan();

                ProductoObj obj = list_producto.get(rv_lista.getChildAdapterPosition(view));
                cambiarLista(view,s,obj);


            }
        });
    }

    public void cambiarLista (View view, String s,ProductoObj obj){
        Intent pasar = new Intent(this, Escanear.class);
        pasar.putExtra("val",s);

        Bundle bundle = new Bundle();
        bundle.putSerializable("pro",obj);
        pasar.putExtras(bundle);
        startActivity(pasar);
    }
}
