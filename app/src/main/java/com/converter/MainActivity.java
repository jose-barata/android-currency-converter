package com.converter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private String API_URL = "http://data.fixer.io/api/latest?access_key=< ! Insert fixer api access key here ! >=1&symbols=EUR,GBP,HKD,NZD,AUD";

    private TextView updatedInfo;
    private EditText editEUR;
    private EditText editGBP;
    private EditText editHKD;
    private EditText editNZD;
    private EditText editAUD;

    private float rateEUR;
    private float rateGBP;
    private float rateHKD;
    private float rateNZD;
    private float rateAUD;

    private final DecimalFormat decimalFormat = new DecimalFormat("##.00");
    private final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SharedPreferences mPrefs = MainActivity.this.getSharedPreferences("theme", Context.MODE_PRIVATE);
        setTheme(mPrefs.getInt("theme", android.R.style.Theme_Material_Light_NoActionBar));

        setContentView(R.layout.activity_main);

        updatedInfo = (TextView) findViewById(R.id.updatedInfo);
        editEUR = (EditText) findViewById(R.id.editEUR);
        editGBP = (EditText) findViewById(R.id.editGBP);
        editHKD = (EditText) findViewById(R.id.editHKD);
        editNZD = (EditText) findViewById(R.id.editNZD);
        editAUD = (EditText) findViewById(R.id.editAUD);

        editEUR.requestFocus();
        initRates();
        initButtons();

    }

    private void initRates() {
        SharedPreferences mPrefs = MainActivity.this.getSharedPreferences("rateEUR", Context.MODE_PRIVATE);
        rateEUR = mPrefs.getFloat("rateEUR", 1f);
        mPrefs = MainActivity.this.getSharedPreferences("rateGBP", Context.MODE_PRIVATE);
        rateGBP = mPrefs.getFloat("rateGBP", 1f);
        mPrefs = MainActivity.this.getSharedPreferences("rateHKD", Context.MODE_PRIVATE);
        rateHKD = mPrefs.getFloat("rateHKD", 1f);
        mPrefs = MainActivity.this.getSharedPreferences("rateNZD", Context.MODE_PRIVATE);
        rateNZD = mPrefs.getFloat("rateNZD", 1f);
        mPrefs = MainActivity.this.getSharedPreferences("rateAUD", Context.MODE_PRIVATE);
        rateAUD = mPrefs.getFloat("rateAUD", 1f);
    }

    private void initButtons() {

        View.OnClickListener clearButtonListener = new View.OnClickListener() {
            public void onClick(View v) {
                clearAllInputs();
            }
        };

        findViewById(R.id.btnClearEUR).setOnClickListener(clearButtonListener);
        findViewById(R.id.btnClearGBP).setOnClickListener(clearButtonListener);
        findViewById(R.id.btnClearHKD).setOnClickListener(clearButtonListener);
        findViewById(R.id.btnClearNZD).setOnClickListener(clearButtonListener);
        findViewById(R.id.btnClearAUD).setOnClickListener(clearButtonListener);

        findViewById(R.id.btnOkEUR).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float eurInputValue = Float.parseFloat(editEUR.getText().toString());
                editGBP.setText(decimalFormat.format(eurInputValue * rateGBP / rateEUR));
                editHKD.setText(decimalFormat.format(eurInputValue * rateHKD / rateEUR));
                editNZD.setText(decimalFormat.format(eurInputValue * rateNZD / rateEUR));
                editAUD.setText(decimalFormat.format(eurInputValue * rateAUD / rateEUR));
            }
        });

        findViewById(R.id.btnOkGBP).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float gbpInputValue = Float.parseFloat(editGBP.getText().toString());
                editEUR.setText(decimalFormat.format(gbpInputValue * rateEUR / rateGBP));
                editHKD.setText(decimalFormat.format(gbpInputValue * rateHKD / rateGBP));
                editNZD.setText(decimalFormat.format(gbpInputValue * rateNZD / rateGBP));
                editAUD.setText(decimalFormat.format(gbpInputValue * rateAUD / rateGBP));
            }
        });

        findViewById(R.id.btnOkHKD).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float hkdInputValue = Float.parseFloat(editHKD.getText().toString());
                editEUR.setText(decimalFormat.format(hkdInputValue * rateEUR / rateHKD));
                editGBP.setText(decimalFormat.format(hkdInputValue * rateGBP / rateHKD));
                editNZD.setText(decimalFormat.format(hkdInputValue * rateNZD / rateHKD));
                editAUD.setText(decimalFormat.format(hkdInputValue * rateAUD / rateHKD));
            }
        });

        findViewById(R.id.btnOkNZD).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float nzdInputValue = Float.parseFloat(editNZD.getText().toString());
                editEUR.setText(decimalFormat.format(nzdInputValue * rateEUR / rateNZD));
                editGBP.setText(decimalFormat.format(nzdInputValue * rateGBP / rateNZD));
                editHKD.setText(decimalFormat.format(nzdInputValue * rateHKD / rateNZD));
                editAUD.setText(decimalFormat.format(nzdInputValue * rateAUD / rateNZD));
            }
        });

        findViewById(R.id.btnOkAUD).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                float audInputValue = Float.parseFloat(editAUD.getText().toString());
                editEUR.setText(decimalFormat.format(audInputValue * rateEUR / rateAUD));
                editGBP.setText(decimalFormat.format(audInputValue * rateGBP / rateAUD));
                editHKD.setText(decimalFormat.format(audInputValue * rateHKD / rateAUD));
                editNZD.setText(decimalFormat.format(audInputValue * rateNZD / rateAUD));
            }
        });

        findViewById(R.id.btnUpdateRates).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                updateRates();
            }
        });

        findViewById(R.id.btnTheme).setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                SharedPreferences mPrefs = MainActivity.this.getSharedPreferences("theme", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = mPrefs.edit();
                int theme = mPrefs.getInt("theme", android.R.style.Theme_Material_Light_NoActionBar);
                switch (theme) {
                    case android.R.style.Theme_Material_Light_NoActionBar:
                        editor.putInt("theme", android.R.style.Theme_Material_NoActionBar);
                        break;
                    case android.R.style.Theme_Material_NoActionBar:
                        editor.putInt("theme", android.R.style.Theme_Material_Light_NoActionBar);
                        break;
                }
                if (editor.commit()) {
                    recreate();
                }
            }
        });
    }

    private void updateRates() {

        String url = API_URL;

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            if (response.has("rates")) {
                                JSONObject rates = response.getJSONObject("rates");
                                rateEUR = (float) rates.getDouble("EUR");
                                rateGBP = (float) rates.getDouble("GBP");
                                rateHKD = (float) rates.getDouble("HKD");
                                rateNZD = (float) rates.getDouble("NZD");
                                rateAUD = (float) rates.getDouble("AUD");

                                SharedPreferences mPrefs = MainActivity.this.getSharedPreferences("rateEUR", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = mPrefs.edit();
                                editor.putFloat("rateEUR", rateEUR);
                                editor.commit();

                                mPrefs = MainActivity.this.getSharedPreferences("rateGBP", Context.MODE_PRIVATE);
                                editor = mPrefs.edit();
                                editor.putFloat("rateGBP", rateGBP);
                                editor.commit();

                                mPrefs = MainActivity.this.getSharedPreferences("rateHKD", Context.MODE_PRIVATE);
                                editor = mPrefs.edit();
                                editor.putFloat("rateHKD", rateHKD);
                                editor.commit();

                                mPrefs = MainActivity.this.getSharedPreferences("rateNZD", Context.MODE_PRIVATE);
                                editor = mPrefs.edit();
                                editor.putFloat("rateNZD", rateNZD);
                                editor.commit();

                                mPrefs = MainActivity.this.getSharedPreferences("rateAUD", Context.MODE_PRIVATE);
                                editor = mPrefs.edit();
                                editor.putFloat("rateAUD", rateAUD);
                                editor.commit();
                            }
                        } catch (JSONException exception) {
                            updatedInfo.setText("Unable to get rates: " + exception.getMessage());
                        }
                        updatedInfo.setText("Updated at " + simpleDateFormat.format(new Date()));
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        updatedInfo.setText("Unable to get rates: " + error.getMessage());
                    }
                });
        RequestQueueSingleton.getInstance(this).addToRequestQueue(jsonObjectRequest);
    }

    private void clearAllInputs() {
        editEUR.setText("0");
        editGBP.setText("0");
        editHKD.setText("0");
        editNZD.setText("0");
        editAUD.setText("0");
    }

}
