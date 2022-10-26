package or.example.rdesmarket_v10;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    ArrayList<ProductoObj> todos_prod;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // llamarBD();


    }

    public void llamarBD (){
        todos_prod = new ArrayList<>();
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               todos_prod.removeAll(todos_prod);
               for (DataSnapshot snapshot :
                    dataSnapshot.getChildren()){
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
                   }

                todos_prod.add(prod);
               }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }


    public void cambiarOfertas (View view){
            Intent pasar = new Intent(this, Ofertas.class);
            startActivity(pasar);
        }

        //metodo para el boton carrito
        public void cambiarCarrito (View view){
            Intent pasar = new Intent(this, Micarrito.class);


            startActivity(pasar);
        }
        public void cambiarBuscar (View view){
            Intent pasar = new Intent(this, Buscar.class);
            startActivity(pasar);
        }

        public void cambiarEscanear (View view){
            Intent pasar = new Intent(this, Escanear.class);
            pasar.putExtra("val","1");
            startActivity(pasar);
        }

}