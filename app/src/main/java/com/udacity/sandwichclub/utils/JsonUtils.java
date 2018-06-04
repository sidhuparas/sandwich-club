package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static Sandwich parseSandwichJson(String json) throws JSONException {
        JSONObject mainJson = new JSONObject(json);

        JSONObject name = mainJson.getJSONObject("name");
        String mainName = name.getString("mainName");
        JSONArray alsoKnownAsArray =  name.getJSONArray("alsoKnownAs");
        List<String> alsoKnownAs = new ArrayList<>();
        for (int i=0;i<alsoKnownAsArray.length();i++){
            alsoKnownAs.add(alsoKnownAsArray.getString(i));
        }

        String placeOfOrigin = mainJson.getString("placeOfOrigin");
        String description = mainJson.getString("description");
        String image = mainJson.getString("image");

        JSONArray ingredArray = mainJson.getJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();

        for (int i=0; i<ingredArray.length();i++){
            ingredients.add(ingredArray.getString(i));
        }
        Log.e("Json", "parseSandwichJson: "+ mainName );
        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }
}
