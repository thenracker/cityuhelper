package cz.uhk.cityuhelper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;

import cz.uhk.cityuhelper.model.Item;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toast.makeText(getApplicationContext(), getIntent().getExtras().getString("id")+"", Toast.LENGTH_SHORT).show();

        Item item = StorageManager.loadObject(getApplicationContext(),getIntent().getExtras().getString("id"));

        if(item != null){
            //TODO fill those components :)
            ((TextView)findViewById(R.id.txtDetailType)).setText(item.getType().toString());

            //.,.. HERE FILL THE TEXTS

            if(item.getPosition() != null){
                //add gps coordinates and so on :)
                //TODO KAI YOU CAN DO IT!
                String s = "Gps: "+item.getPosition().latitude+", "+item.getPosition().longitude;
                ((TextView)findViewById(R.id.txtDetailPosition)).setText(s);
            }
        }
    }
}
