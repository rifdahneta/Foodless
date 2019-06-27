package pnj.ac.id.foodless.Fragment;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pnj.ac.id.foodless.Activity.Form;
import pnj.ac.id.foodless.Adapter.CardOrderAdapter;
import pnj.ac.id.foodless.DetailActivity;
import pnj.ac.id.foodless.Model.Communities;
import pnj.ac.id.foodless.Model.FormModel;
import pnj.ac.id.foodless.R;

public class HomeKomunitasFragment extends Fragment {

    private static final String TAG = "Order";
    private ArrayList<FormModel> frm_list = new ArrayList();
    private View HomeKomunitasFragment;
    private RecyclerView rcy_order;

    private DatabaseReference DonasiRef;
    private FirebaseAuth mAuth;

    public static String namaDonatur;
    public static String jenis;
    public static String kadaluarsa;
    public static String porsi;
    public static String alamat;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeKomunitasFragment =  inflater.inflate(R.layout.fragment_home_komunitas, container, false);
        return HomeKomunitasFragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcy_order = (RecyclerView) HomeKomunitasFragment.findViewById(R.id.rcy_order);
        rcy_order.setLayoutManager(new GridLayoutManager(getActivity(), 1));

        mAuth = FirebaseAuth.getInstance();
        //currentUserID = mAuth.getCurrentUser().getUid();
        DonasiRef = FirebaseDatabase.getInstance().getReference().child("donasi");
        initData();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        CardView mcardOrder;
        TextView mNama, mJenis, mKeterangan, mAlamat, mJenisMakanan, mPorsi, mKadaluarsa, mTelepon, mEmail;


        public RecyclerViewHolder(View itemView) {
            super(itemView);

            mcardOrder = itemView.findViewById(R.id.card_order);
            mNama = itemView.findViewById(R.id.txtNamaDonaturCard);
            mJenis = itemView.findViewById(R.id.txtJenisDonaturCard);
            mKeterangan = itemView.findViewById(R.id.txtKeteranganCard);
            mAlamat = itemView.findViewById(R.id.txtAlamatCard);
            mJenisMakanan = itemView.findViewById(R.id.txtJenisMakananCard);
            mPorsi = itemView.findViewById(R.id.txtPorsiCard);
            mKadaluarsa = itemView.findViewById(R.id.txtKadaluarsaCard);
            mTelepon = itemView.findViewById(R.id.txtTeleponCard);
            mEmail = itemView.findViewById(R.id.txtEmailCard);
        }
    }

    public void initData() {
        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<FormModel>()
                .setQuery(DonasiRef, FormModel.class)
                .build();


        FirebaseRecyclerAdapter<FormModel, HomeKomunitasFragment.RecyclerViewHolder> adapter
                = new FirebaseRecyclerAdapter<FormModel, HomeKomunitasFragment.RecyclerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final HomeKomunitasFragment.RecyclerViewHolder holder, final int position, @NonNull FormModel model) {
                String userIDs = getRef(position).getKey();
                DonasiRef.child(userIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        String Nama = ""+ dataSnapshot.child("nama").getValue();
                        String Jenis = ""+ dataSnapshot.child("jenis").getValue();
                        String JenisMakanan = ""+ dataSnapshot.child("jenisMakanan").getValue();
                        String Alamat = ""+dataSnapshot.child("alamat").getValue();
                        String Kadaluarsa = ""+ dataSnapshot.child("kadaluarsa").getValue();
                        String Keterangan = ""+ dataSnapshot.child("keterangan").getValue();
                        String Email = ""+ dataSnapshot.child("email").getValue();
                        String NoTelepon = ""+ dataSnapshot.child("telepon").getValue();
                        String Porsi = ""+ dataSnapshot.child("jumlahPorsi").getValue();

                        holder.mNama.setText(Nama);
                        holder.mJenis.setText(Jenis);
                        holder.mJenisMakanan.setText(JenisMakanan);
                        holder.mEmail.setText(Email);
                        holder.mAlamat.setText(Alamat);
                        holder.mKadaluarsa.setText(Kadaluarsa);
                        holder.mKeterangan.setText(Keterangan);
                        holder.mTelepon.setText(NoTelepon);
                        holder.mPorsi.setText(Porsi);

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
//                        String order_detail = getRef(position).getKey();
//
//                        Intent detailIntent = new Intent(HomeKomunitasFragment.this.getActivity(), ProfileKomunitasFragment.class);
//                        detailIntent.putExtra("order_detail", order_detail);
//                        startActivity(detailIntent);
                    }
                });


            }

            @NonNull
            @Override
            public HomeKomunitasFragment.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_order, viewGroup, false);
                HomeKomunitasFragment.RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
                return viewHolder;
            }
        };

        rcy_order.setAdapter(adapter);
        adapter.startListening();
    }
}
