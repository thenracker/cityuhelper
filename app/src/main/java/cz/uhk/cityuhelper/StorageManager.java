package cz.uhk.cityuhelper;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import cz.uhk.cityuhelper.model.FakeDataBuilder;
import cz.uhk.cityuhelper.model.Item;

/**
 * Created by petrw on 29.07.2016.
 */
public class StorageManager {

    public static boolean itemsExists(Context context){
        return new File(context.getExternalFilesDir(".data")+"/.items.dat").exists();
    }

    public static void deleteObject(Context context, String id){
        ArrayList<Item> items = loadArray(context);
        for(Item i : items){
            if(i.getId().equals(id)){
                items.remove(i);
                break;
            }
        }
        saveArray(context, items);
    }
    public static void saveObject(Context context, Item object){
        //LOAD ALL
        ArrayList<Item> objects = (ArrayList<Item>) loadArray(context);
        //ADD NEW ONE
        ArrayList<Item> newObjects = new ArrayList<>();
        newObjects.add(object);
        newObjects.addAll(objects); //add the rest of the objects
        //SAVE ALL
        saveArray(context,newObjects);
    }
    public static Item loadObject(Context context, String id){
        ArrayList<Item> items = loadArray(context);

        Item myItem = null;

        for(Item i : items){
            if(i.getId().equals(id))
                myItem = i;
        }

        return myItem;
    }

    //SAVE
    public static void saveArray(Context context, ArrayList<Item> list){
        try{

            File file = new File(context.getExternalFilesDir(".data")+"/.items.dat");

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));

            //save size First
            oos.writeInt(list.size());

            for(Object c : list){
                oos.writeObject(c);
                oos.flush();
            }

            oos.close();

        }catch(FileNotFoundException e){

        }catch(IOException ee){

        }
    }

    //LOAD
    public static ArrayList<Item> loadArray(Context context){

        //FIRST INIT OF APP
        if(!itemsExists(context)){
            //we will create them from fake builder
            saveArray(context, FakeDataBuilder.getListOfFakeDelivers());
            //then we can finally load them and send them to activity :)
        }

        ArrayList<Item> array = new ArrayList<>();

        try{

            File file = new File(context.getExternalFilesDir(".data")+"/.items.dat");

            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));

            int count = ois.readInt();

            for(int i = 0; i < count; i++){
                Item ii = (Item)ois.readObject();
                array.add(ii);
            }

            ois.close();

        }catch(FileNotFoundException e){
        }catch(IOException ee){
        }catch(ClassNotFoundException eee){
        }

        if(array.isEmpty())
            array = FakeDataBuilder.getListOfFakeDelivers();

        return array;
    }

}
