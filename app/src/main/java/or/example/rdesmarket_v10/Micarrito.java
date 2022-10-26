package or.example.rdesmarket_v10;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Micarrito extends AppCompatActivity{

    private RecyclerView rvCarrito;
    ArrayList<ProductoObj> list_prod;
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_micarrito);

        //estructura
        rvCarrito = findViewById(R.id.rvCarrito);
        rvCarrito.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rvCarrito.setLayoutManager(new LinearLayoutManager(this));
        list_prod = new ArrayList<>();

        // llenarCarrito();

cargarBase();
        //NUEVO

    }

    public void cargarBase(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter= new Adapter(list_prod);

        accion();
        rvCarrito.setAdapter(adapter);

        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_prod.removeAll(list_prod);
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
                    list_prod.add(prod);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }

    private void accion() {
       adapter.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Toast.makeText(getApplicationContext(),
                       "SELECCION:" + list_prod.get(rvCarrito.getChildAdapterPosition(view)).getNombre(),Toast.LENGTH_SHORT).show();
           }
       });
    }


    //metodo para mostrar los botones de accion
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.bar_carrito, menu);
        return true;
    }

    //metodo para agregar las acciones de los botones

    public boolean onOptionsItemSelected (MenuItem item){
        int id = item.getItemId();

        if (id == R.id.it_agregar) {
            Intent pasar = new Intent(this, Buscar.class);
            startActivity(pasar);
            return true;
        }

        if (id == R.id.it_borrar) {
            Toast.makeText(this,"borrar" ,Toast.LENGTH_SHORT).show();
            return true;
        }

        if (id == R.id.it_escaner) {
            Intent intent = new Intent(this, Escanear.class);
            intent.putExtra("val","1");
            startActivity(intent);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    //Iteracion al clikear un elemento del recyclerView

}
