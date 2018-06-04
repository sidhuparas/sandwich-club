package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @BindView(R.id.origin_tv) TextView origin_tv;
    @BindView(R.id.also_known_tv) TextView also_known_tv;
    @BindView(R.id.ingredients_tv) TextView ingredients_tv;
    @BindView(R.id.description_tv) TextView description_tv;
    @BindView(R.id.toolbar_image) ImageView toolbar_iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }
        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        Log.e("Main", "onCreate: " + String.valueOf(position));
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        Sandwich sandwich = null;

        try {
            sandwich = JsonUtils.parseSandwichJson(json);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }

        populateUI(sandwich);

        setTitle(sandwich.getMainName());

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        description_tv.setText(sandwich.getDescription());
        origin_tv.setText(sandwich.getPlaceOfOrigin());
        ingredients_tv.setText(TextUtils.join("\n",sandwich.getIngredients()));
        also_known_tv.setText(TextUtils.join("\n", sandwich.getAlsoKnownAs()));
        Picasso.with(this)
                 .load(sandwich.getImage())
                 .into(toolbar_iv);
    }
}
