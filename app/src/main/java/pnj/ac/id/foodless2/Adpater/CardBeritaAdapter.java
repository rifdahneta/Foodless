package pnj.ac.id.foodless2.Adpater;

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

import pnj.ac.id.foodless2.Model.Berita;
import pnj.ac.id.foodless2.R;

public class CardBeritaAdapter extends RecyclerView.Adapter<CardBeritaAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<Berita> listBerita;

    public CardBeritaAdapter (Context context){
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
    public CardBeritaAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_foodfact, parent, false);
        return  new CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardViewViewHolder holder, int position) {
        Berita berita =listBerita.get(position);

        Glide.with(context)
                .load(berita.getGambarBerita())
                .into(holder.imgBerita);

        holder.txtJudulBerita.setText(berita.getJudulBerita());
        holder.txtIsiBerita.setText(berita.getIsiBerita());
    }

    @Override
    public int getItemCount() {
        return listBerita.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView txtJudulBerita;
        TextView txtIsiBerita;
        ImageView imgBerita;

        public CardViewViewHolder(View itemView) {
            super(itemView);
            txtJudulBerita = (TextView)itemView.findViewById(R.id.txtJudulBerita);
            txtIsiBerita = (TextView) itemView.findViewById(R.id.txtIsiBerita);
            imgBerita = (ImageView) itemView.findViewById(R.id.imgBerita);
        }
    }
}