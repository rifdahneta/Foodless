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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


import pnj.ac.id.foodless.Activity.DetailActivity;


import java.util.ArrayList;
import java.util.List;

import pnj.ac.id.foodless.Adapter.CustomSearchAdapter;
import pnj.ac.id.foodless.DetailActivity;

import pnj.ac.id.foodless.Model.Communities;
import pnj.ac.id.foodless.R;

public class HomeFragment extends Fragment {
    private static final String TAG = "HomeFragment";
    private RecyclerView rcy_komunitas;
    private View HomeView;
    private Button SearchBtn;
    private EditText InputText;
    private String SearchInput;



    private DatabaseReference KomunitasRef;


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


        SearchBtn = view.findViewById(R.id.searchBtn);
        InputText = view.findViewById(R.id.search_community);

        SearchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SearchInput = InputText.getText().toString();
                getSearch(SearchInput);
//                onStart();

            }
        });

        mAuth = FirebaseAuth.getInstance();

        //currentUserID = mAuth.getCurrentUser().getUid();

        KomunitasRef = FirebaseDatabase.getInstance().getReference().child("komunitas");

        initData();
    }

    public class RecyclerAdapaterSearch extends RecyclerView.Adapter<RecyclerAdapaterSearch.RecyclerViewHolder> {
        List<Communities> communities;

        public RecyclerAdapaterSearch(List<Communities> communities) {
            this.communities = communities;
        }

        @NonNull
        @Override
        public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_communities, viewGroup, false);
            RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
            return viewHolder;

        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerViewHolder recyclerViewHolder, int i) {
            String judul = communities.get(i).getNama_komunitas();
            String desc = communities.get(i).getJenis_kegiatan();
            String image = communities.get(i).getGambar_komunitas();
            final String refID = communities.get(i).getRefId();
            recyclerViewHolder.mJudul.setText(judul);
            recyclerViewHolder.mDesc.setText(desc);

            Log.e("image", "" + image);
            Glide.with(getActivity())
                    .load("" + image)
                    .override(150, 150)
                    .into(recyclerViewHolder.mImage);

            recyclerViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String communities_detail = refID;

                    Intent detailIntent = new Intent(HomeFragment.this.getActivity(), DetailActivity.class);
                    detailIntent.putExtra("communities_detail", communities_detail);
                    startActivity(detailIntent);
                }
            });
        }

        @Override
        public int getItemCount() {
            return communities.size();
        }


        class RecyclerViewHolder extends RecyclerView.ViewHolder {
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

        getSearch("");
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    private void getSearch(final String query) {
        final ArrayList<Communities> dataSearch = new ArrayList<>();



        DatabaseReference datas = FirebaseDatabase.getInstance().getReference().child("komunitas");
        datas.orderByChild("nama_komunitas").startAt(query).endAt(query + "\uf8ff").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot val : dataSnapshot.getChildren()) {
                    Communities item = new Communities();
                    item.setGambar_komunitas((String) val.child("gambar_komunitas").getValue());
                    item.setJenis_kegiatan((String) val.child("jenis_kegiatan").getValue());
                    item.setNama_komunitas((String) val.child("nama_komunitas").getValue());
                    item.setRefId(val.getKey());
                    dataSearch.add(item);
                    //I am not sure what record are you specifically looking for
                    //This is if you are getting the Key which is the record ID for your Coupon Object
                    Log.d(TAG, "onDataChange: " + "" + (String) val.child("name").getValue());

                }
                RecyclerAdapaterSearch adapter = new RecyclerAdapaterSearch(dataSearch);
                rcy_komunitas.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }



        };

        });



    }
}


/**
 * Query mQuery = FirebaseDatabase.getInstance().getReference().orderByChild("nama_komunitas").startAt(query).endAt(query+"\uf8ff");
 * mQuery.keepSynced(true);
 * <p>
 * FirebaseRecyclerOptions options = new FirebaseRecyclerOptions.Builder<Communities>()
 * .setQuery(mQuery, Communities.class)
 * .build();
 * <p>
 * FirebaseRecyclerAdapter<Communities, RecyclerViewHolder> adapter
 * = new FirebaseRecyclerAdapter<Communities, RecyclerViewHolder>(options) {
 *
 * @Override protected void onBindViewHolder(@NonNull final RecyclerViewHolder holder, final int position, @NonNull Communities model) {
 * final String userIDs = getRef(position).getKey();
 * Log.d(TAG, "onBindViewHolder: coba");
 * <p>
 * KomunitasRef.child(userIDs).addValueEventListener(new ValueEventListener() {
 * @Override public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
 * <p>
 * //                        for(DataSnapshot val : dataSnapshot.getChildren()){
 * //                            if(dataSnapshot.child("nama_komunitas").getValue(String.class).contains(query)){
 * String judul = dataSnapshot.child("nama_komunitas").getValue().toString();
 * String desc = dataSnapshot.child("jenis_kegiatan").getValue().toString();
 * String image = dataSnapshot.child("gambar_komunitas").getValue().toString();
 * <p>
 * holder.mJudul.setText(judul);
 * holder.mDesc.setText(desc);
 * Log.d(TAG, "onDataChange: " + judul);
 * Log.e("image", image);
 * Glide.with(getActivity())
 * .load(image)
 * .override(150, 150)
 * .into(holder.mImage);
 * //                            }
 * //                        }
 * <p>
 * //                        String judul = dataSnapshot.child("nama_komunitas").getValue().toString();
 * //                        String desc = dataSnapshot.child("jenis_kegiatan").getValue().toString();
 * //                        String image = dataSnapshot.child("gambar_komunitas").getValue().toString();
 * //
 * //                        holder.mJudul.setText(judul);
 * //                        holder.mDesc.setText(desc);
 * //                        Log.d(TAG, "onDataChange: " + judul);
 * //                        Log.e("image", image);
 * //                        Glide.with(getActivity())
 * //                                .load(image)
 * //                                .override(150, 150)
 * //                                .into(holder.mImage);
 * <p>
 * <p>
 * }
 * @Override public void onCancelled(@NonNull DatabaseError databaseError) {
 * <p>
 * }
 * });
 * <p>
 * holder.itemView.setOnClickListener(new View.OnClickListener() {
 * @Override public void onClick(View view) {
 * String communities_detail = getRef(position).getKey();
 * <p>
 * Intent detailIntent = new Intent(HomeFragment.this.getActivity(), DetailActivity.class);
 * detailIntent.putExtra("communities_detail", communities_detail);
 * startActivity(detailIntent);
 * }
 * });
 * }
 * @NonNull
 * @Override public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
 * View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_communities, viewGroup, false);
 * RecyclerViewHolder viewHolder = new RecyclerViewHolder(view);
 * return viewHolder;
 * }
 * };
 * <p>
 * rcy_komunitas.setAdapter(adapter);
 * adapter.startListening();
 * <p>
 * }
 * <p>
 * <p>
 * //    private void getSearch(String query)
 * //    {
 * //        Query mQuery = FirebaseDatabase.getInstance().getReference().child("komunitas").orderByChild("nama_komunitas").equalTo(query);
 * //        mQuery.keepSynced(true);
 * //
 * //        FirebaseRecyclerOptions<Communities> options =
 * //                new FirebaseRecyclerOptions.Builder<Communities>()
 * //                        .setQuery(teamQuery, Team.class)
 * //                        .setLifecycleOwner(this)
 * //                        .build();
 * //
 * //
 * //        FirebaseRecyclerAdapter<Communities, ViewHolder>firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<Communities, ViewHolder>() {
 * //            @Override
 * //            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull Communities model) {
 * //
 * //            }
 * //
 * //            @NonNull
 * //            @Override
 * //            public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
 * //                return null;
 * //            }
 * //        };
 * //        rcy_komunitas.setAdapter(firebaseRecyclerAdapter);
 * //    }
 * <p>
 * <p>
 * public static class ViewHolder extends RecyclerView.ViewHolder {
 * View mView;
 * TextView mJudul, mDesc;
 * ImageView mImage;
 * <p>
 * public ViewHolder(View itemView) {
 * super(itemView);
 * mView = itemView;
 * <p>
 * itemView.setOnClickListener(new View.OnClickListener() {
 * @Override public void onClick(View view) {
 * mClickListener.onItemClick(view, getAdapterPosition());
 * }
 * });
 * }
 * <p>
 * public void setNamaDaerah(String namaDaerah) {
 * mJudul = itemView.findViewById(R.id.modelJudul);
 * mDesc = itemView.findViewById(R.id.modelDeskripsi);
 * mImage = itemView.findViewById(R.id.modelGambar);
 * }
 * <p>
 * private ViewHolder.ClickListener mClickListener;
 * <p>
 * public interface ClickListener {
 * void onItemClick(View view, int position);
 * }
 * <p>
 * public void setOnClickListener(ViewHolder.ClickListener clickListener) {
 * mClickListener = clickListener;
 * }
 * }
 * <p>
 * public void search(View view) {
 * SearchInput = InputText.getText().toString();
 * onStart();
 * };
 */
