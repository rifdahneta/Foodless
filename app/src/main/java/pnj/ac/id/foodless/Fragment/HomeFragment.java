package pnj.ac.id.foodless.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import pnj.ac.id.foodless.Activity.DetailActivity;
import pnj.ac.id.foodless.Model.Communities;
import pnj.ac.id.foodless.R;

public class HomeFragment extends Fragment {

    private RecyclerView rcy_komunitas;
    private View HomeView;


    private FirebaseAuth mAuth;
    //private String currentUserID;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeView = inflater.inflate(R.layout.fragment_home, container, false);


        return HomeView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcy_komunitas =  HomeView.findViewById(R.id.rcy_komunitas);
        rcy_komunitas.setLayoutManager(new GridLayoutManager(getActivity(), 2));


        mAuth = FirebaseAuth.getInstance();
        //currentUserID = mAuth.getCurrentUser().getUid();
        initData();
    }

    public static class RecyclerViewHolder extends RecyclerView.ViewHolder {

        CardView mCardView;
        TextView mJudul, mDesc;
        ImageView mImage;


        public RecyclerViewHolder(View itemView) {
            super(itemView);

            mCardView = itemView.findViewById(R.id.card_komunitas);
            mJudul = itemView.findViewById(R.id.modelJudul);
            mDesc = itemView.findViewById(R.id.modelDeskripsi);
            mImage = itemView.findViewById(R.id.modelGambar);
        }
    }

    public void initData() {
         DatabaseReference KomunitasRef = FirebaseDatabase.getInstance().getReference().child("komunitas");
         KomunitasRef.orderByChild("nama_komunitas").startAt("Garda").endAt("Garda"+"\uf8ff");
        Query query = KomunitasRef;
        query.orderByChild("nama_komunitas").startAt("tes").endAt("tes"+"\uf8ff");


        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Communities>()
                .setQuery(query, Communities.class)
                .build();

//        KomunitasRef.orderByChild("nama_komunitas").startAt("Garda").endAt("Garda"+"\uf8ff").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.e("datachange", ""+model.getNama_komunitas());
//
//
////                        String judul = dataSnapshot.child("nama_komunitas").getValue().toString();
////                        String desc = dataSnapshot.child("jenis_kegiatan").getValue().toString();
////                        String image = dataSnapshot.child("gambar_komunitas").getValue().toString();
////
////                        holder.mJudul.setText(judul);
////                        holder.mDesc.setText(desc);
////
////                        Log.e("image", image);
////                        Glide.with(getActivity())
////                                .load(image)
////                                .override(150, 150)
////                                .into(holder.mImage);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
        FirebaseRecyclerAdapter<Communities, RecyclerViewHolder> adapter
                = new FirebaseRecyclerAdapter<Communities, RecyclerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position, @NonNull final Communities model) {



                holder.mJudul.setText(model.getNama_komunitas());
                holder.mDesc.setText(model.getJenis_kegiatan());

                Log.e("image","tes="+ model.getNama_komunitas());
                Glide.with(getActivity())
                        .load(model.getGambar_komunitas())
                        .override(150, 150)
                        .into(holder.mImage);
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String communities_detail = getRef(position).getKey();


                        Intent detailIntent = new Intent(HomeFragment.this.getActivity(), DetailActivity.class);
                        detailIntent.putExtra("communities_detail", communities_detail);
                        startActivity(detailIntent);
                    }
                });


            }

            @NonNull
            @Override
            public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_communities, viewGroup, false);
                RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
                return viewHolder;
            }


        };

        rcy_komunitas.setAdapter(adapter);
        adapter.startListening();
    }
}