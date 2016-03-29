package com.example.aleksandr.liberator.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.data_base.entity.EntitySettings;

import java.util.List;

/**
 * Created by Aleksandr on 24.03.2016.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ItemViewHolder> {

    private List<EntitySettings> mEvents;
    private Context mContext;

    public RecyclerViewAdapter(List<EntitySettings> mEvents, Context context) {
        this.mEvents = mEvents;
        this.mContext = context;
    }


    public class ItemViewHolder extends RecyclerView.ViewHolder {
        CardView mCardView;
        TextView tvTitle, tvValues;
        ImageView ivMin, ivPluse, iyOk;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_day);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_param_set);
            tvValues = (TextView) itemView.findViewById(R.id.tv_event_value);

            ivMin = (ImageView) itemView.findViewById(R.id.iv_set_minusr);
            ivPluse = (ImageView) itemView.findViewById(R.id.iv_set_pluse);
            iyOk = (ImageView) itemView.findViewById(R.id.iv_set_ok);

        }
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        EntitySettings entitySettings = mEvents.get(position);

        holder.tvTitle.setText(entitySettings.getId() + ". " + entitySettings.getTitle());
        holder.tvValues.setText(entitySettings.getValues());

    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.item_parametr, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public int getItemCount() {
        if (mEvents == null) return 0;
        return mEvents.size();
    }
}
