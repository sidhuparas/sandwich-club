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

        JSONObject name = mainJson.optJSONObject("name");
        String mainName = name.optString("mainName");
        JSONArray alsoKnownAsArray =  name.optJSONArray("alsoKnownAs");
        List<String> alsoKnownAs = new ArrayList<>();
        for (int i=0;i<alsoKnownAsArray.length();i++){
            alsoKnownAs.add(alsoKnownAsArray.optString(i));
        }

        String placeOfOrigin = mainJson.optString("placeOfOrigin");
        String description = mainJson.optString("description");
        String image = mainJson.optString("image");

        JSONArray ingredArray = mainJson.optJSONArray("ingredients");
        List<String> ingredients = new ArrayList<>();

        for (int i=0; i<ingredArray.length();i++){
            ingredients.add(ingredArray.optString(i));
        }
        Log.e("Json", "parseSandwichJson: "+ mainName );
        return new Sandwich(mainName,alsoKnownAs,placeOfOrigin,description,image,ingredients);
    }
}
