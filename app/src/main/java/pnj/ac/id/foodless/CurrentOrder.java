package pnj.ac.id.foodless;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import pnj.ac.id.foodless.Adapter.CardOrderAdapter;
import pnj.ac.id.foodless.Model.FormModel;

public class CurrentOrder extends AppCompatActivity {

//    private static final String TAG = "Order";
//    private ArrayList<FormModel> frm_list = new ArrayList();

//    public static String namaDonatur;
//    public static String jenis;
//    public static String kadaluarsa;
//    public static String porsi;
//    public static String alamat;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState){
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.fragment_home_komunitas);
//        RecyclerCardOrder();
//    }

//    private void RecyclerCardOrder(){
//        RecyclerView rcy_order = findViewById(R.id.rcy_order);
//        rcy_order.setLayoutManager(new GridLayoutManager(this, 1));
//
//        CardOrderAdapter cardOrderAdapter = new CardOrderAdapter(this);
//        cardOrderAdapter.setListOrder(frm_list);
//        rcy_order.setAdapter(cardOrderAdapter);
//        getDataOrderFromDatabase(cardOrderAdapter);
//    }
//
//    private void getDataOrderFromDatabase(final CardOrderAdapter adapter){
//        FirebaseDatabase database = FirebaseDatabase.getInstance();
//        DatabaseReference myRef = database.getReference("donasi");
//        myRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                for(DataSnapshot orderSnapshot : dataSnapshot.getChildren()){
//                    FormModel formModel = orderSnapshot.getValue(FormModel.class);
//                    frm_list.add(formModel);
//                }
//                adapter.notifyDataSetChanged();
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Log.w(TAG, "onCancelled: ", databaseError.toException());
//            }
//        });
//    }
}
