package com.example.restaurantrater;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RatingBar;

public class Rating extends AppCompatActivity {

    private dish currentDish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        savePref();
        initDishType();
        initSaveButton();
        currentDish = new dish();
        rating(); //call the method to handle any changes to the rating
    }
   private void savePref() {
       String dishType = getSharedPreferences("type",
               Context.MODE_PRIVATE).getString("dishType", "entree");

       RadioButton rbEntree = findViewById(R.id.entreeButton);
       RadioButton rbDessert = findViewById(R.id.dessertButton);
       if (dishType.equalsIgnoreCase("entree")) {
           rbEntree.setChecked(true);
       } else {
           rbDessert.setChecked(true);
       }
   }
   private void initDishType() {
       RadioGroup rgDishType = findViewById(R.id.rgDishType);
       rgDishType.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
           @Override
           public void onCheckedChanged(RadioGroup group, int checkedId) {
               RadioButton rbEntree = findViewById(R.id.entreeButton);
               RadioButton rbDessert = findViewById(R.id.dessertButton);

               if (rbEntree.isChecked()) {
                   getSharedPreferences("type", Context.MODE_PRIVATE).edit()
                           .putString("dishtype ", "entree").apply();
               } else {
                   getSharedPreferences("type", Context.MODE_PRIVATE).edit()
                           .putString("dishtype ", "dessert").apply();
               }
           }
       });
   }
   //save button save the input of the name, and other fields
   private void initSaveButton() {
       Button saveButton = findViewById(R.id.savebutton);
       saveButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               //insert the editText to the database as well
               EditText dishName = findViewById(R.id.editDishName);
               currentDish.setName(dishName.getText().toString());
               //get the dataSource
              DishDS ds = new DishDS(Rating.this);
               try {
                   ds.open();
                   if (currentDish.getDishid() == -1) {
                       int newID = ds.getLastContactID();
                       currentDish.setDishid(newID);
                   }
                   ds.close();
               } catch (Exception e) {
               }
               Intent intent = new Intent(Rating.this, MainActivity.class);
               startActivity(intent);
               finish();
           }
       });

   }
   //rating is saved and inputted to th
    public void rating() {
        //get the rating and insert to the database
        RatingBar rate = findViewById(R.id.ratingBar);
        rate.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                currentDish.setRating(rating);
            }
        });
    }
}