package pnj.ac.id.foodless.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

import pnj.ac.id.foodless.Model.Communities;
import pnj.ac.id.foodless.R;

public class CustomSearchAdapter extends FirebaseRecyclerAdapter<Communities, CustomSearchAdapter.SearchViewHolder> {

    private RecyclerView recyclerView;
    private List<Communities> communities;
    private static final String TAG = "SearchListAdapter";
    Context context;

    public CustomSearchAdapter(Context context, FirebaseRecyclerOptions<Communities> options){
        super(options);
        this.context=context;

    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_communities, parent, false);
        return new SearchViewHolder(view);
    }

    @Override
    protected void onBindViewHolder(@NonNull CustomSearchAdapter.SearchViewHolder holder, int position, @NonNull Communities model) {
        holder.mJudul.setText(model.getNama_komunitas());
        holder.mDesc.setText(model.getJenis_kegiatan());

        Log.e("image", ""+model.getGambar_komunitas());
        Glide.with(context)
                .load(""+model.getGambar_komunitas())
                .override(150, 150)
                .into(holder.mImage);
    }

    protected boolean filterCondition(Communities model, String filterPattern){
        return model.getNama_komunitas().toLowerCase().contains(filterPattern);
    }

    public class SearchViewHolder extends RecyclerView.ViewHolder {

        TextView mJudul, mDesc;
        ImageView mImage;

        SearchViewHolder(View view) {
            super(view);

            mJudul = view.findViewById(R.id.modelJudul);
            mDesc = view.findViewById(R.id.modelDeskripsi);
            mImage = view.findViewById(R.id.modelGambar);
        }
    }


//        KomunitasRef.orderByChild("nama_komunitas").startAt("Garda").endAt("Garda"+"\uf8ff").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                Log.e("datachange", ""+model.getNama_komunitas());
//
//
//                        holder.mJudul.setText(judul);
//                        holder.mDesc.setText(desc);
//
//                        Log.e("image", image);
//                        Glide.with(getActivity())
//                                .load(image)
//                                .override(150, 150)
//                                .into(holder.mImage);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//
//            }
//        });
//
//        }


}

