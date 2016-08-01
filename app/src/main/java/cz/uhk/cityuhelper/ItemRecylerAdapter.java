package cz.uhk.cityuhelper;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import cz.uhk.cityuhelper.model.Item;

/**
 * Created by petrw on 12.07.2016.
 */
public class ItemRecylerAdapter extends RecyclerView.Adapter<ItemRecylerAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Item> feedItemList;

    public ItemRecylerAdapter(List<Item> feedItemList, Context mContext) {
        this.feedItemList = feedItemList;
        this.mContext = mContext;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_row, viewGroup, false);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Item feedItem = feedItemList.get(i);
        customViewHolder.bindView(feedItem);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }


    // METHODS TO FILTER LIST
    // <editor-fold desc="FILTERING METHODS" >
    public void setModels(List<Item> model){
        feedItemList = new ArrayList<>(model);
    }
    public Item removeItem(int position) {
        final Item model = feedItemList.remove(position);
        notifyItemRemoved(position);
        return model;
    }
    public void addItem(int position, Item model) {
        feedItemList.add(position, model);
        notifyItemInserted(position);
    }
    public void moveItem(int fromPosition, int toPosition) {
        final Item model = feedItemList.remove(fromPosition);
        feedItemList.add(toPosition, model);
        notifyItemMoved(fromPosition, toPosition);
    }
    public void animateTo(List<Item> models) {
        applyAndAnimateRemovals(models);
        applyAndAnimateAdditions(models);
        applyAndAnimateMovedItems(models);
    }
    private void applyAndAnimateRemovals(List<Item> newModels) {
        for (int i = feedItemList.size() - 1; i >= 0; i--) {
            final Item model = feedItemList.get(i);
            if (!newModels.contains(model)) {
                removeItem(i);
            }
        }
    }
    private void applyAndAnimateAdditions(List<Item> newModels) {
        for (int i = 0, count = newModels.size(); i < count; i++) {
            final Item model = newModels.get(i);
            if (!feedItemList.contains(model)) {
                addItem(i, model);
            }
        }
    }
    private void applyAndAnimateMovedItems(List<Item> newModels) {
        for (int toPosition = newModels.size() - 1; toPosition >= 0; toPosition--) {
            final Item model = newModels.get(toPosition);
            final int fromPosition = feedItemList.indexOf(model);
            if (fromPosition >= 0 && fromPosition != toPosition) {
                moveItem(fromPosition, toPosition);
            }
        }
    }
    public void filter(Item.Type type, List<Item> items){
        List<Item> filteredItems = new ArrayList<>();
        if(type == null){
            //ALL
            filteredItems.addAll(items);
        }else{
            for(Item i : items){
                if(i.getType() == type)
                    filteredItems.add(i);
            }
        }
        //this.feedItemList = filteredItems;
        //notifyDataSetChanged();
        animateTo(filteredItems);
    }
    // </editor-fold>

    //VIEW HOLDER FOR RECYCLER ADAPTER
    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener, View.OnLongClickListener {

        private TextView txtItemTitle;
        private TextView txtItemAuthor;
        private TextView txtItemLocation,txtItemTimeFrom,txtItemTimeTo;
        private ImageView imgItemIcon;

        public CustomViewHolder(View view) {
            super(view);

            txtItemTitle = (TextView) view.findViewById(R.id.txtItemTitle);
            txtItemAuthor = (TextView) view.findViewById(R.id.txtItemAuthor);
            imgItemIcon = (ImageView) view.findViewById(R.id.imgItemIcon);
            txtItemTimeFrom = (TextView) view.findViewById(R.id.txtItemTimeFrom);
            txtItemTimeTo = (TextView) view.findViewById(R.id.txtItemTimeTo);

            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        public void bindView(final Item feedItem) {

            //Setting text view title
            txtItemTitle.setText(feedItem.getTitle());
            txtItemAuthor.setText(feedItem.getAuthor().getName());

            txtItemTimeFrom.setText(feedItem.getDateFrom());
            txtItemTimeTo.setText(feedItem.getDateTo());

            if(feedItem.getType() == Item.Type.DELIVER){
                imgItemIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_business_center_black_48dp));
            }else if (feedItem.getType() == Item.Type.FOOD){
                imgItemIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_restaurant_black_48dp));
            }else if (feedItem.getType() == Item.Type.PRINT){
                imgItemIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_local_printshop_black_48dp));
            }else if (feedItem.getType() == Item.Type.QUESTION){
                imgItemIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_notifications_active_black_48dp));
            }else if (feedItem.getType() == Item.Type.NEED){
                imgItemIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_help_black_48dp));
            }
        }

        @Override
        public void onClick(View view) {

            Intent myIntent = new Intent(mContext, DetailActivity.class);
            myIntent.putExtra("id", feedItemList.get(getLayoutPosition()).getId());
            mContext.startActivity(myIntent);

        }

        @Override
        public boolean onLongClick(View view) {

            new AlertDialog.Builder(mContext)
                    .setTitle(mContext.getResources().getString(R.string.removing))
                    .setMessage(mContext.getResources().getString(R.string.areyousure))
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int whichButton) {
                            StorageManager.deleteObject(mContext, feedItemList.get(getLayoutPosition()).getId());
                            notifyItemRemoved(getLayoutPosition());
                        }})

                    .setNegativeButton(android.R.string.no, null).show();

            return true;
        }
    }

}
