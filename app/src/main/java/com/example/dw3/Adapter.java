package com.example.dw3;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Response;
import com.bumptech.glide.Glide;

import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.PlayerViewnHolder>
        /*implements View.OnClickListener*/{
    private final RecyclerViewInterface recyclerViewInterface;
    ImageButton venta;
    Context mCtx;
    List<Productos>productosList;



    //listener
    private View.OnClickListener listener;

    public Adapter(Context mCtx, List<Productos>productosList,
                    RecyclerViewInterface recyclerViewInterface){
        this.mCtx=mCtx;
        this.productosList=productosList;
        this.recyclerViewInterface=recyclerViewInterface;

    }




    @NonNull
    @Override
    public PlayerViewnHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.lista,null);
        //------------
        /*view.setOnClickListener(this);*/

        return new /*inflater*/PlayerViewnHolder(view, recyclerViewInterface);/*, recyclerViewInterface*/
    }

    @Override
    public void onBindViewHolder(@NonNull PlayerViewnHolder holder, int position) {

        Productos productos=productosList.get(position);


        /*Glide.with(mCtx)
                .load(productos.getImagen())
                .into(holder.img);*/
        holder.tv1.setText(productos.getNombre());
        holder.tv3.setText(String.valueOf(productos.getPrecio()));

        /*holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(holder.itemView.getContext(),Venta.class);
                intent.putExtra("productosven",productos);
                holder.itemView.getContext().startActivity(intent);
            }
        });*/
    }



    @Override
    public int getItemCount() {
        return productosList.size();
    }

//------------------------------
    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;

    }

    //metodo onclick creado
    /*@Override
    public void onClick(View view) {

        if(listener!=null){
            listener.onClick(view);
        }
    }*/

    static class PlayerViewnHolder extends RecyclerView.ViewHolder{

        TextView tv1,tv3;
        ImageView img;
        ImageButton ibtnVenta;
        public PlayerViewnHolder(@NonNull View itemView, RecyclerViewInterface recyclerViewInterface) {
            super(itemView);

            tv1 = itemView.findViewById(R.id.tv1);
            tv3 = itemView.findViewById(R.id.tv3);
            img = itemView.findViewById(R.id.img);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION){
                            recyclerViewInterface.onItemClick(pos);
                        }
                    }
                }
            });
        }
    }
}
