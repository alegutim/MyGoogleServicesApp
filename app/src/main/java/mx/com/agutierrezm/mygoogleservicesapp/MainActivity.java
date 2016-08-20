package mx.com.agutierrezm.mygoogleservicesapp;

import android.content.Intent;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

  private static int PLACE_PICKER_REQUEST = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.main_map).setOnClickListener(this);
        findViewById(R.id.main_picker).setOnClickListener(this);
        findViewById(R.id.main_admob).setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_map:
                Intent intent = new Intent(getApplicationContext(), MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.main_picker:

                try {
                    startActivityForResult(new PlacePicker.IntentBuilder().build(MainActivity.this),PLACE_PICKER_REQUEST);
                } catch (GooglePlayServicesRepairableException e) {
                    e.printStackTrace();
                } catch (GooglePlayServicesNotAvailableException e) {
                    e.printStackTrace();
                }

                break;
            case R.id.main_admob:
                startActivity(new Intent(getApplicationContext(), AdMobActivity.class));
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode==PLACE_PICKER_REQUEST){
            if (data!=null){
                Place place = PlacePicker.getPlace(MainActivity.this,data);
                Toast.makeText(getApplicationContext(),place.getAddress(),Toast.LENGTH_LONG);
            }
        }
    }
}

