package cz.uhk.cityuhelper;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cz.uhk.cityuhelper.model.Delivery;
import cz.uhk.cityuhelper.model.Food;
import cz.uhk.cityuhelper.model.Item;
import cz.uhk.cityuhelper.model.Print;

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
/*
    public void runOnUiThred(Runnable runnable) {
        ((Activity) mContext).runOnUiThread(runnable);
    }
*/
    @Override
    public int getItemCount() {
        return (null != feedItemList ? feedItemList.size() : 0);
    }


    //VIEW HOLDER FOR RECYCLER ADAPTER
    public class CustomViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private TextView txtItemTitle;
        private TextView txtItemAuthor;
        private TextView txtItemLocation;
        private ImageView imgItemIcon;

        public CustomViewHolder(View view) {
            super(view);
            txtItemTitle = (TextView) view.findViewById(R.id.txtItemTitle);
            txtItemAuthor = (TextView) view.findViewById(R.id.txtItemAuthor);
            txtItemLocation = (TextView) view.findViewById(R.id.txtItemLocation);
            imgItemIcon = (ImageView) view.findViewById(R.id.imgItemIcon);
            view.setOnClickListener(this);
        }

        public void bindView(final Item feedItem) {

            //Setting text view title
            txtItemTitle.setText(feedItem.getTitle());
            txtItemAuthor.setText(feedItem.getAuthor().getName()+" "+feedItem.getAuthor().getSurname());
            txtItemLocation.setText("GPS: "+feedItem.getPosition().latitude+", "+feedItem.getPosition().longitude);

            if(feedItem instanceof Delivery){
                imgItemIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_business_center_black_48dp));
            }else if (feedItem instanceof Food){
                imgItemIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_restaurant_black_48dp));
            }else if (feedItem instanceof Print){
                imgItemIcon.setImageDrawable(ContextCompat.getDrawable(mContext,R.drawable.ic_local_printshop_black_48dp));
            }
        }

        @Override
        public void onClick(View view) {
            //Intent myIntent = new Intent(mContext, DetailActivity.class);
            /*
            myIntent.putExtra("id", item.getMarkerId());
            myIntent.putExtra("groupid", item.getGroupId());
            mContext.startActivity(myIntent);
            */
        }

    }

}
