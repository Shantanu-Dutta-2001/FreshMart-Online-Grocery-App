package com.example.freshmart.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.freshmart.R;
import com.example.freshmart.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailedActivity extends AppCompatActivity {

    ImageView detailedimg,additem,removeitem;
    TextView rating,quantity,price;
    int totalquantity = 1;
    int totalprice ;
    Button addtocart;
    Toolbar toolbar;

    FirebaseFirestore firestore;
    FirebaseAuth auth;
    ViewAllModel viewAllModel = null ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        final Object object = getIntent().getSerializableExtra("detail");
        if(object instanceof ViewAllModel)
        {
            viewAllModel = (ViewAllModel)object;
        }
        quantity = findViewById(R.id.quantity);
        detailedimg = findViewById(R.id.detailed_img);
        additem = findViewById(R.id.add_item);
        removeitem = findViewById(R.id.remove_item);
        price = findViewById(R.id.detailed_price);
        rating = findViewById(R.id.detailed_rating);

        if(viewAllModel != null)
        {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedimg);
            rating.setText(viewAllModel.getRating());
            price.setText("Price : ₹"+viewAllModel.getPrice()+"/ Kg");
            totalprice = viewAllModel.getPrice() * totalquantity;

            if(viewAllModel.getType().equalsIgnoreCase("eggs"))
            {
                price.setText("Price : ₹"+viewAllModel.getPrice()+"/ Dozen");
                totalprice = viewAllModel.getPrice() * totalquantity;
            }
            if(viewAllModel.getType().equalsIgnoreCase("milk"))
            {
                price.setText("Price : ₹"+viewAllModel.getPrice()+"/ Liter");
                totalprice = viewAllModel.getPrice() * totalquantity;
            }
            if(viewAllModel.getType().equalsIgnoreCase("fish"))
            {
                price.setText("Price : ₹"+viewAllModel.getPrice()+"/ Kg");
                totalprice = viewAllModel.getPrice() * totalquantity;
            }

        }

        addtocart = findViewById(R.id.add_to_cart);
        addtocart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addedToCart();
            }
        });
        additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if(totalquantity<10)
               {
                   totalquantity++;
                   quantity.setText(String.valueOf(totalquantity));
                   totalprice = viewAllModel.getPrice() * totalquantity;

               }
            }
        });
        removeitem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(totalquantity>1)
                {
                    totalquantity--;
                    quantity.setText(String.valueOf(totalquantity));
                    totalprice = viewAllModel.getPrice() * totalquantity;

                }
            }
        });
}

    private void setSupportActionBar(Toolbar toolbar) {
    }

    private void addedToCart() {

        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();
        SimpleDateFormat currentDate = new SimpleDateFormat("DD mm,yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());
        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",viewAllModel.getName());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalprice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
            @Override
            public void onComplete(@NonNull Task<DocumentReference> task) {

                Toast.makeText(DetailedActivity.this, "Added To Cart", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}