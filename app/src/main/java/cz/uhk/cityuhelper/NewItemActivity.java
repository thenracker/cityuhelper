package cz.uhk.cityuhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import cz.uhk.cityuhelper.model.Item;

public class NewItemActivity extends AppCompatActivity {

    private EditText editName, editSubject, editDescription, editPhone, editEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_item);

        Spinner mySpinner = (Spinner) findViewById(R.id.spinner);
        mySpinner.setAdapter(new ArrayAdapter<Item.Type>(this, android.R.layout.simple_spinner_dropdown_item, Item.Type.values()));

        editName = (EditText)findViewById(R.id.editNameSurname);
        editSubject = (EditText)findViewById(R.id.editSubject);
        editDescription = (EditText)findViewById(R.id.editDescription);
        editPhone = (EditText)findViewById(R.id.editPhone);
        editEmail = (EditText)findViewById(R.id.editEmail);

        findViewById(R.id.btnSend).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO SAVE IT
                Toast.makeText(getApplicationContext(), "I'm saving your task..", Toast.LENGTH_SHORT).show();
                finish();
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
