package com.itstep.myrestapp;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.itstep.myrestapp.models.UserModel;
import com.itstep.myrestapp.repositories.UserRepository;
import com.squareup.picasso.Picasso;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        UserRepository repository = UserRepository.getInstance();

        repository.getAll();

        UserModel newUser = repository.createModel();
        newUser.setName("test from mobile");

        repository.create(newUser);

        ImageView imgView = findViewById(R.id.imageView);


        Picasso.get().load("https://i.imgur.com/DvpvklR.png").into(imgView);


    }
}