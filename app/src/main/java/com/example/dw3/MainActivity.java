package com.example.dw3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements RecyclerViewInterface{

    private static String url="https://proyectodesarrollosql.com/Android/mostrar_productos.php";
    List<Productos> productosList;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView=findViewById(R.id.recy);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
        productosList= new ArrayList<>();


        cargarProductos();

        //Adapter adapter = new Adapter(MainActivity.this, productosList,this);
        //recyclerView.setAdapter(adapter);
        //recyclerView.setLayoutManager(new LinearLayoutManager(this));*/
    }

    private void cargarProductos() {
        Adapter adapter = new Adapter(MainActivity.this, productosList,this);
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);

                            for (int i = 0; i < array.length(); i++) {
                                JSONObject Productos = array.getJSONObject(i);
                                System.out.println("fffffffffffff");
                                productosList.add(new Productos(
                                        Productos.getInt("id_producto"),
                                        Productos.getInt("id_categoria"),
                                        Productos.getInt("id_imagen"),
                                        Productos.getInt("id_usuario"),
                                        Productos.getString("nombre"),
                                        Productos.getString("descripcion"),
                                        Productos.getInt("cantidad"),
                                        (float) Productos.getDouble("precio"),
                                        Productos.getString("fechaCaptura")
                                        //Productos.getString("imagen")
                                ));

                            }
                            //Adapter adapter = new Adapter(MainActivity.this, productosList,this);
                            recyclerView.setAdapter(adapter);

                            //-----------------------
                            /*adapter.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(getApplicationContext(),Venta.class);
                                    startActivity(intent);
                                }
                            });*/
                            //recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            System.out.println("la concha de la lira no dio");
                            System.out.println(e);
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        Volley.newRequestQueue(this).add(stringRequest);
    }


    @Override
    public void onItemClick(int position) {
        Intent intent = new Intent(MainActivity.this,Venta.class);

        intent.putExtra("ID_PRODUCTO",productosList.get(position).getId_producto());
        intent.putExtra("ID_USUARIO",productosList.get(position).getId_usuario());
        intent.putExtra("NOMBRE",productosList.get(position).getNombre());
        intent.putExtra("DESCRIPCION",productosList.get(position).getDescripcion());
        intent.putExtra("PRECIO",productosList.get(position).getPrecio());
        intent.putExtra("FECHACAPTURA",productosList.get(position).getFechaCaptura());


        startActivity(intent);
    }
}