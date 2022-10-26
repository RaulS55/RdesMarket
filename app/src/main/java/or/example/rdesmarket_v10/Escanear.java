package or.example.rdesmarket_v10;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Escanear extends AppCompatActivity {
private RecyclerView rv_precios;
    ArrayList<ProductoObj> list_precios;
    Adapter adapter5;


    private static final int CODIGO_PERMISOS_CAMARA = 1, CODIGO_INTENT = 2;
    private boolean permisoCamaraConcedido = false, permisoSolicitadoDesdeBoton = false;
    private TextView tvCodigoLeido;
    TextView tvNombre,tvMarca,tvCodigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escanear);


        rv_precios = findViewById(R.id.rv_precios);
        rv_precios.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        rv_precios.setLayoutManager(new LinearLayoutManager(this));
        list_precios = new ArrayList<>();

        String a = getIntent().getStringExtra("val");
        if (a.equals("1")){
            verificarYPedirPermisosDeCamara();
            accionEscanear();

        }else {

                tvMarca = findViewById(R.id.tv_MarcaPrecio);
                tvNombre = findViewById(R.id.tv_NomPrecio);
                tvCodigo = findViewById(R.id.tvCodigoLeido);

                Bundle objEnviado = getIntent().getExtras();
                ProductoObj produc = null;
                if (objEnviado != null){
                    produc = (ProductoObj) objEnviado.getSerializable("pro");
                    tvNombre.setText(produc.getNombre().toString());
                    tvMarca.setText(produc.getMarca().toString());
                    tvCodigo.setText(produc.getEan().toString());

                    list_precios.removeAll(list_precios);

                    list_precios.add(produc);
                    adapter5= new Adapter(list_precios);

                    //accion();
                    rv_precios.setAdapter(adapter5);
                }

        }







    }


    public void busqueda(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        adapter5= new Adapter(list_precios);

        //accion();
        rv_precios.setAdapter(adapter5);

        database.getReference().getRoot().addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                list_precios.removeAll(list_precios);
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
                    list_precios.add(prod);
                }
                adapter5.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
    }



    public void accionEscanear (){
        tvCodigoLeido = findViewById(R.id.tvCodigoLeido);
        if (!permisoCamaraConcedido) {
            Toast.makeText(Escanear.this, "Por favor permite que la app acceda a la cámara", Toast.LENGTH_SHORT).show();
            permisoSolicitadoDesdeBoton = true;
            verificarYPedirPermisosDeCamara();
            return;
        }
        escanear();
    }

    private void escanear() {
        Intent i = new Intent( Escanear.this, SimpleScannerActivity.class);
        startActivityForResult(i, CODIGO_INTENT);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == CODIGO_INTENT) {
            if (resultCode == Activity.RESULT_OK) {
                if (data != null) {
                    //setContentView(R.layout.activity_escanear);
                    String codigo = data.getStringExtra("codigo");
                    tvCodigoLeido.setText(codigo);
                    busqueda();
                }
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case CODIGO_PERMISOS_CAMARA:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // Escanear directamten solo si fue pedido desde el botón
                    if (permisoSolicitadoDesdeBoton) {
                        escanear();
                    }
                    permisoCamaraConcedido = true;
                } else {
                    permisoDeCamaraDenegado();
                }
                break;
        }
    }

    private void verificarYPedirPermisosDeCamara() {
        int estadoDePermiso = ContextCompat.checkSelfPermission(Escanear.this, Manifest.permission.CAMERA);
        if (estadoDePermiso == PackageManager.PERMISSION_GRANTED) {
            // En caso de que haya dado permisos ponemos la bandera en true
            // y llamar al método
            permisoCamaraConcedido = true;
        } else {
            // Si no, pedimos permisos. Ahora mira onRequestPermissionsResult
            ActivityCompat.requestPermissions(Escanear.this,
                    new String[]{Manifest.permission.CAMERA},
                    CODIGO_PERMISOS_CAMARA);
        }
    }


    private void permisoDeCamaraDenegado() {
        // Esto se llama cuando el usuario hace click en "Denegar" o
        // cuando lo denegó anteriormente
        Toast.makeText(Escanear.this, "No puedes escanear si no das permiso", Toast.LENGTH_SHORT).show();
    }

















}
