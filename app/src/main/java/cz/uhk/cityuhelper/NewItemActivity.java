package cz.uhk.cityuhelper;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import cz.uhk.cityuhelper.model.Author;
import cz.uhk.cityuhelper.model.EnumStringConverter;
import cz.uhk.cityuhelper.model.Item;
import cz.uhk.cityuhelper.model.MyLatLng;

public class NewItemActivity extends AppCompatActivity {

    public static final int CHOOSE_MAP_POSITION = 8;

    private EditText editName, editSubject, editDescription, editPhone, editEmail, editPosition;

    private Double dLatitude, dLongitude;

    private LinearLayout layoutLocation, layoutFile, layoutColorOrBlackPrint;
    private ImageButton btnFindLocation;

    EnumStringConverter esc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);

        esc = new EnumStringConverter(getApplicationContext());
        mySpinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, esc.enumToString(Item.Type.values())));

        layoutColorOrBlackPrint = (LinearLayout)findViewById(R.id.layoutColorOrBlackPrint);
        layoutFile = (LinearLayout)findViewById(R.id.layoutAttachFile);
        layoutLocation = (LinearLayout)findViewById(R.id.layoutPosition);

        AdapterView.OnItemSelectedListener oisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                Item.Type selectedItem = esc.getEnumForString((String)adapterView.getSelectedItem());

                if((Item.Type)selectedItem == Item.Type.DELIVER
                        || (Item.Type)selectedItem == Item.Type.FOOD){
                    layoutLocation.setVisibility(View.VISIBLE);
                    layoutFile.setVisibility(View.GONE);
                    layoutColorOrBlackPrint.setVisibility(View.GONE);

                }else if((Item.Type)selectedItem == Item.Type.PRINT
                        || (Item.Type)selectedItem == Item.Type.QUESTION){
                    layoutLocation.setVisibility(View.GONE);
                    layoutFile.setVisibility(View.VISIBLE);
                    if((Item.Type)selectedItem == Item.Type.PRINT){
                        layoutColorOrBlackPrint.setVisibility(View.VISIBLE);
                    }else{
                        layoutColorOrBlackPrint.setVisibility(View.GONE);
                    }

                }else{ //IT IS A NEED
                    layoutLocation.setVisibility(View.GONE);
                    layoutFile.setVisibility(View.VISIBLE);
                    layoutColorOrBlackPrint.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //HOPE IT WONT HAPPEN :(
            }
        };
        mySpinner.setOnItemSelectedListener(oisl); //setting the listener

        editName = (EditText)findViewById(R.id.editNameSurname);
        editSubject = (EditText)findViewById(R.id.editSubject);
        editDescription = (EditText)findViewById(R.id.editDescription);
        editPhone = (EditText)findViewById(R.id.editPhone);
        editEmail = (EditText)findViewById(R.id.editEmail);
        editPosition = (EditText)findViewById(R.id.editPosition);

        //fill in the name if there is
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editName.setText(prefs.getString("name", "")); //2nd parameter is default string nothing found
        editEmail.setText(prefs.getString("email", ""));
        editPhone.setText(prefs.getString("phone", ""));

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                MyLatLng lll = null;
                if(dLatitude != null && dLongitude != null){
                    lll = new MyLatLng(dLatitude,dLongitude);
                }

                //CREATE AND SAVE NEW OBJECT
                Item newItem = new Item(esc.getEnumForString((String)mySpinner.getSelectedItem()),
                        editSubject.getText().toString().trim(),
                        editDescription.getText().toString().trim(),
                        new Author(editName.getText().toString().trim(),
                                editEmail.getText().toString().trim(),
                                editPhone.getText().toString().trim()),
                        lll);

                //save name
                SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("name", editName.getText().toString()).commit();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("phone", editPhone.getText().toString()).commit();
                PreferenceManager.getDefaultSharedPreferences(getApplicationContext()).edit().putString("email", editEmail.getText().toString()).commit();


                Toast.makeText(getApplicationContext(), getResources().getString(R.string.saving), Toast.LENGTH_SHORT).show();

                StorageManager.saveObject(getApplicationContext(),newItem);

                setResult(MainActivity.ITEMADDEDOK);

                finish();
            }
        });

        btnFindLocation = (ImageButton)findViewById(R.id.btnFindLocation);
        btnFindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewItemActivity.this, MapActivity.class);

                startActivityForResult(i,CHOOSE_MAP_POSITION);

            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CHOOSE_MAP_POSITION && data != null) {
            Double latitude = data.getDoubleExtra("latitude",0);
            Double longitude = data.getDoubleExtra("longitude",0);

            if(latitude == null || longitude == null || latitude == 0 || longitude == 0){
                Toast.makeText(getApplicationContext(), "You didn't picked location!", Toast.LENGTH_SHORT).show();
                editPosition.setText("You didn't picked location");
                dLatitude = null; dLongitude = null;
            }
            else{
                dLatitude = latitude; dLongitude = longitude;
                editPosition.setText("GPS: "+latitude+", "+longitude);
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            setResult(MainActivity.ITEMADDEDABORTED);
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
