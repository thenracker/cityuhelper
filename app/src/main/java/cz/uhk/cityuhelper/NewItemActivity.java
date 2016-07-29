package cz.uhk.cityuhelper;

import android.content.Intent;
import android.os.Bundle;
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
import cz.uhk.cityuhelper.model.Item;

public class NewItemActivity extends AppCompatActivity {

    private EditText editName, editSubject, editDescription, editPhone, editEmail;
    private LinearLayout layoutLocation, layoutFile, layoutColorOrBlackPrint;
    private ImageButton btnFindLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        final Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setAdapter(new ArrayAdapter<Item.Type>(this, android.R.layout.simple_spinner_dropdown_item, Item.Type.values()));

        layoutColorOrBlackPrint = (LinearLayout)findViewById(R.id.layoutColorOrBlackPrint);
        layoutFile = (LinearLayout)findViewById(R.id.layoutAttachFile);
        layoutLocation = (LinearLayout)findViewById(R.id.layoutPosition);

        AdapterView.OnItemSelectedListener oisl = new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if((Item.Type)adapterView.getSelectedItem() == Item.Type.DELIVER
                        || (Item.Type)adapterView.getSelectedItem() == Item.Type.FOOD){
                    layoutLocation.setVisibility(View.VISIBLE);
                    layoutFile.setVisibility(View.GONE);
                    layoutColorOrBlackPrint.setVisibility(View.GONE);

                }else if((Item.Type)adapterView.getSelectedItem() == Item.Type.PRINT
                        || (Item.Type)adapterView.getSelectedItem() == Item.Type.QUESTION){
                    layoutLocation.setVisibility(View.GONE);
                    layoutFile.setVisibility(View.VISIBLE);
                    if((Item.Type)adapterView.getSelectedItem() == Item.Type.PRINT){
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

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //CREATE AND SAVE NEW OBJECT
                Item newItem = new Item((Item.Type)mySpinner.getSelectedItem(),
                        editSubject.getText().toString().trim(),
                        editDescription.getText().toString().trim(),
                        new Author(editName.getText().toString().trim(),
                                editEmail.getText().toString().trim(),
                                editPhone.getText().toString().trim()),
                                null); //TODO add LATLNG

                Toast.makeText(getApplicationContext(), "I'm saving your task..", Toast.LENGTH_SHORT).show();

                StorageManager.saveObject(getApplicationContext(),newItem);

                finish();
            }
        });

        btnFindLocation = (ImageButton)findViewById(R.id.btnFindLocation);
        btnFindLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NewItemActivity.this, MapActivity.class);
                startActivity(i);
            }
        });


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == android.R.id.home) {
            this.finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
