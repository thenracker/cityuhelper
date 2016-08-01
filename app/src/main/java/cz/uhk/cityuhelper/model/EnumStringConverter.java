package cz.uhk.cityuhelper.model;

import android.content.Context;

import cz.uhk.cityuhelper.R;

/**
 * Created by petrw on 01.08.2016.
 */
public class EnumStringConverter {

    Context context;

    public EnumStringConverter(Context context){
        this.context = context;
    }

    public String[] enumToString(Item.Type[] types){
        String[] strings = new String[types.length];
        int i = 0;
        for(Item.Type t : types){
            String s;
            switch(t){
                default:
                case DELIVER: s = context.getResources().getString(R.string.delivery); break;
                case PRINT: s = context.getResources().getString(R.string.print); break;
                case FOOD: s = context.getResources().getString(R.string.food); break;
                case QUESTION: s = context.getResources().getString(R.string.question); break;
                case NEED: s = context.getResources().getString(R.string.need); break;
            }
            strings[i] = s;
            i++;
        }
        return strings;
    }
    public String enumToString(Item.Type type){
        String s;
        switch(type){
            default:
            case DELIVER: s = context.getResources().getString(R.string.delivery); break;
            case PRINT: s = context.getResources().getString(R.string.print); break;
            case FOOD: s = context.getResources().getString(R.string.food); break;
            case QUESTION: s = context.getResources().getString(R.string.question); break;
            case NEED: s = context.getResources().getString(R.string.need); break;
        }
        return s;
    }

    public Item.Type getEnumForString(String name){
        Item.Type t;
        if(name.equals(context.getResources().getString(R.string.delivery))){
            t = Item.Type.DELIVER;
        }
        else if(name.equals(context.getResources().getString(R.string.print))){
            t = Item.Type.PRINT;
        }
        else if(name.equals(context.getResources().getString(R.string.question))){
            t = Item.Type.QUESTION;
        }
        else if(name.equals(context.getResources().getString(R.string.food))){
            t = Item.Type.FOOD;
        }
        else{
            t = Item.Type.NEED;
        }
        return t;
    }
}
