package pnj.ac.id.foodless.Fragment;

import android.content.Intent;
import android.os.Bundle;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

import android.support.v7.widget.CardView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import pnj.ac.id.foodless.DetailActivity;
import pnj.ac.id.foodless.Model.Communities;
import pnj.ac.id.foodless.R;

public class HomeFragment extends Fragment {

    private RecyclerView rcy_komunitas;
    private View HomeView;

    private DatabaseReference KomunitasRef;
    private FirebaseAuth mAuth;
    //private String currentUserID;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        HomeView = inflater.inflate(R.layout.fragment_home, container, false);
        rcy_komunitas = (RecyclerView) HomeView.findViewById(R.id.rcy_komunitas);
        rcy_komunitas.setLayoutManager(new GridLayoutManager(getActivity(),  2));


        mAuth = FirebaseAuth.getInstance();
        //currentUserID = mAuth.getCurrentUser().getUid();
        KomunitasRef = FirebaseDatabase.getInstance().getReference().child("komunitas");

        return HomeView;
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

    @Override
    public void onStart() {
        super.onStart();

        FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Communities>()
                .setQuery(KomunitasRef, Communities.class)
                .build();


        FirebaseRecyclerAdapter<Communities, RecyclerViewHolder> adapter
                = new FirebaseRecyclerAdapter<Communities, RecyclerViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position, @NonNull Communities model)
            {
                String userIDs = getRef(position).getKey();
                KomunitasRef.child(userIDs).addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot)
                    {
                        if(dataSnapshot.hasChild("komunitas"))
                        {
                            String image = dataSnapshot.child("gambar_komunitas").getValue().toString();
                            String judul = dataSnapshot.child("nama_komunitas").getValue().toString();
                            String desc = dataSnapshot.child("jenis_kegiatan").getValue().toString();

                            holder.mJudul.setText(judul);
                            holder.mDesc.setText(desc);
                            //Picasso.get().load(image).placeholder(R.drawable.buttonprofile).into(holder.mImage);
                            Picasso.get()
                                    .load(image)
                                    .into(holder.mImage, new Callback() {
                                        @Override
                                        public void onSuccess() {
                                        }

                                        @Override
                                        public void onError(Exception e) {
                                        }
                                    });
                        }

                        else {
                            String judul = dataSnapshot.child("nama_komunitas").getValue().toString();
                            String desc = dataSnapshot.child("jenis_kegiatan").getValue().toString();

                            holder.mJudul.setText(judul);
                            holder.mDesc.setText(desc);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

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
            public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i)
            {
                View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_communities, viewGroup, false);
                RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
                return viewHolder;
            }
        };

        rcy_komunitas.setAdapter(adapter);
        adapter.startListening();
    }
}