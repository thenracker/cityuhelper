package cz.uhk.cityuhelper;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import java.util.ArrayList;

import cz.uhk.cityuhelper.model.Item;

public class MainActivity extends AppCompatActivity {

    public final static int ITEMADDED = 1;
    public final static int ITEMADDEDOK = 2;
    public final static int ITEMADDEDABORTED = 3;

    //private ArrayAdapter<Item>
    private RecyclerView recyclerView;
    private ItemRecylerAdapter adapter;
    private ArrayList<Item> adapterArray;
    private ArrayList<Item> items;

    public void refreshPlease(){
        if(adapter != null){
            items.clear();
            items.addAll(StorageManager.loadArray(getApplicationContext()));
            adapter.animateTo(items);
            System.out.println("resumed new data show up");
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        items = StorageManager.loadArray(getApplicationContext()); //this list is with all
        adapterArray = StorageManager.loadArray(getApplicationContext()); //this is only for adapter

        LinearLayoutManager llm = new LinearLayoutManager(this);
        llm.setOrientation(LinearLayoutManager.VERTICAL);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(llm);
        adapter = new ItemRecylerAdapter(adapterArray, this);
        recyclerView.setAdapter(adapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(final MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_add) {
            Intent addIntent = new Intent(MainActivity.this, NewItemActivity.class);
            //startActivity(addIntent);
            startActivityForResult(addIntent,ITEMADDED);
            //adapter.filter(Item.Type.DELIVER, items);
        }
        if (id == R.id.action_filter){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);


            alertDialogBuilder.setTitle("Title");
            alertDialogBuilder
                    .setCancelable(false)
                    .setSingleChoiceItems(Item.Type.getStringValues(), -1, null)
                    .setPositiveButton("Filter", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            ListView lw = ((AlertDialog) dialog).getListView();

                            if(lw.getCheckedItemPosition() > -1){
                                Object checkedItem = lw.getAdapter().getItem(lw.getCheckedItemPosition());
                                for(Item.Type t : Item.Type.values()){
                                    if(t.toString().equals(checkedItem)){
                                        adapter.filter(t, items);
                                    }
                                }
                            }

                            else
                                dialog.dismiss();
                        }
                    })
                    .setNegativeButton("Show All", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            adapter.filter(null,items);
                            dialog.cancel();
                        }
                    });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ITEMADDED){
            if(resultCode == ITEMADDEDOK){
                refreshPlease();
            }
        }
    }

}
