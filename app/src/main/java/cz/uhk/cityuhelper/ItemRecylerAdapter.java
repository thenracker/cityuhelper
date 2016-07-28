package cz.uhk.cityuhelper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cz.uhk.cityuhelper.model.Item;

/**
 * Created by petrw on 12.07.2016.
 */
public class ItemRecylerAdapter extends RecyclerView.Adapter<ItemRecylerAdapter.CustomViewHolder> {

    private Context mContext;
    private List<Item> feedItemList;

    public ItemRecylerAdapter(Context context, List<Item> feedItemList) {
        this.mContext = context;
        this.feedItemList = feedItemList;
    }

    @Override
    public CustomViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_row, null);
        return new CustomViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CustomViewHolder customViewHolder, int i) {
        Item feedItem = feedItemList.get(i);
        customViewHolder.bindView(feedItem);
    }

    public void runOnUiThred(Runnable runnable) {
        ((Activity) mContext).runOnUiThread(runnable);
    }

    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }


    //VIEW HOLDER FOR RECYCLER ADAPTER
    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private Item item;

        private TextView txtItemTitle;

        public CustomViewHolder(View view) {
            super(view);
            txtItemTitle = (TextView) view.findViewById(R.id.txtItemTitle);
            view.setOnClickListener(this);
        }

        public void bindView(final Item feedItem) {

            this.item = feedItem;

            //Setting text view title
            txtItemTitle.setText(feedItem.getTitle());

        }

        @Override
        public void onClick(View view) {
            Intent myIntent = new Intent(mContext, DetailActivity.class);
            /*
            myIntent.putExtra("id", item.getMarkerId());
            myIntent.putExtra("groupid", item.getGroupId());
            mContext.startActivity(myIntent);
            */
        }

    }

}
