package pnj.ac.id.foodless.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import pnj.ac.id.foodless.Model.Berita;
import pnj.ac.id.foodless.R;

public class CardIsiBeritaAdapter extends RecyclerView.Adapter<CardIsiBeritaAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Berita> listBerita;

    public CardIsiBeritaAdapter (Context context){
        this.context = context;
    }

    public ArrayList<Berita> getListBerita() {
        return listBerita;
    }

    public void setListBerita(ArrayList<Berita> listBerita){
        this.listBerita = listBerita;
    }

    @NonNull
    @Override
    public CardIsiBeritaAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_berita, parent, false);
        return  new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Berita berita =listBerita.get(position);

        Glide.with(context)
                .load(berita.getGambarBerita())
                .into(holder.imgBerita2);

        holder.txtJudulBerita2.setText(berita.getJudulBerita());
        holder.txtIsiBerita2.setText(berita.getIsiBerita());
    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudulBerita2;
        TextView txtIsiBerita2;
        ImageView imgBerita2;

        public CardViewViewHolder(View itemView) {
            super(itemView);
            txtJudulBerita2 = (TextView)itemView.findViewById(R.id.txtJudulBerita2);
            txtIsiBerita2 = (TextView) itemView.findViewById(R.id.txtIsiBerita2);
            imgBerita2 = (ImageView) itemView.findViewById(R.id.imgBerita2);
        }
    }
}
