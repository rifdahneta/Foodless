package pnj.ac.id.foodless.Adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Objects;

import pnj.ac.id.foodless.Activity.Form;
import pnj.ac.id.foodless.CurrentOrder;
import pnj.ac.id.foodless.CurrentUser;
import pnj.ac.id.foodless.Model.Berita;
import pnj.ac.id.foodless.Model.FormModel;
import pnj.ac.id.foodless.R;
import pnj.ac.id.foodless.User;

import static android.support.constraint.Constraints.TAG;

public class CardOrderAdapter extends RecyclerView.Adapter<CardOrderAdapter.CardViewViewHolder> {

    private Context context;
    private ArrayList<FormModel> listOrder;

    public CardOrderAdapter(Context context){
        this.context = context;
    }

    public ArrayList<FormModel> getListOrder (){
        return listOrder;
    }

    public void setListOrder(ArrayList<FormModel> listOrder){
        this.listOrder = listOrder;
    }

    @NonNull
    @Override
    public CardOrderAdapter.CardViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_order, parent, false);
        return  new CardOrderAdapter.CardViewViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CardOrderAdapter.CardViewViewHolder holder, int position) {
        FormModel formModel = listOrder.get(position);

            holder.txtNamaDonatur.setText(formModel.getNama());
            holder.txtJenisDonatur.setText(formModel.getJenis());
            holder.txtPorsi.setText(formModel.getJumlahPorsi());
            holder.txtKeterangan.setText(formModel.getKeterangan());
            holder.txtNoTelp.setText(formModel.getTelepon());
            holder.txtEmail.setText(formModel.getEmail());
            holder.txtKadaluarsa.setText(formModel.getKadaluarsa());
            holder.txtJenisMakanan.setText(formModel.getJenisMakanan());
            holder.txtAlamat.setText(formModel.getAlamat());
        }

    @Override
    public int getItemCount() {
        return listOrder.size();
    }

    public class CardViewViewHolder extends RecyclerView.ViewHolder {

        TextView txtNamaDonatur, txtJenisDonatur, txtPorsi, txtAlamat, txtJenisMakanan, txtKadaluarsa, txtEmail, txtNoTelp, txtKeterangan;

        public CardViewViewHolder(View itemView) {
            super(itemView);
            txtNamaDonatur = (TextView)itemView.findViewById(R.id.txtNamaDonaturCard);
            txtJenisDonatur = (TextView) itemView.findViewById(R.id.txtJenisDonaturCard);
            txtPorsi = (TextView) itemView.findViewById(R.id.txtPorsiCard);
            txtAlamat = (TextView) itemView.findViewById(R.id.txtAlamatDetail);
            txtJenisMakanan = (TextView) itemView.findViewById(R.id.txtJenisMakananCard);
            txtKadaluarsa = (TextView) itemView.findViewById(R.id.txtKadaluarsaCard);
            txtEmail = (TextView) itemView.findViewById(R.id.txtEmailCard);
            txtNoTelp = (TextView) itemView.findViewById(R.id.txtTeleponCard);
            txtKeterangan = (TextView) itemView.findViewById(R.id.txtKeteranganCard);
        }
    }

}

