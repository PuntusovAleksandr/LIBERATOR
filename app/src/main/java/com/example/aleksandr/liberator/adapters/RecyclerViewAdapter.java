package com.example.aleksandr.liberator.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.aleksandr.liberator.R;
import com.example.aleksandr.liberator.data_base.Db;
import com.example.aleksandr.liberator.data_base.entity.EntitySettings;
import com.example.aleksandr.liberator.utils.Utils;

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
        ImageView ivMin, ivPluse, btOk;

        public ItemViewHolder(View itemView) {
            super(itemView);
            mCardView = (CardView) itemView.findViewById(R.id.cv_day);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_title_param_set);
            tvValues = (TextView) itemView.findViewById(R.id.tv_event_value);

            ivMin = (ImageView) itemView.findViewById(R.id.iv_set_minusr);
            ivPluse = (ImageView) itemView.findViewById(R.id.iv_set_pluse);
            btOk = (ImageView) itemView.findViewById(R.id.iv_set_ok);

        }
    }

    @Override
    public void onBindViewHolder(final ItemViewHolder holder, int position) {
        final EntitySettings entitySettings = mEvents.get(position);

        StringBuilder builder = new StringBuilder();
        builder.append(entitySettings.getId())
                .append(". ")
                .append(entitySettings.getTitle())
                .append(" ")
                .append(entitySettings.getParam());

        holder.tvTitle.setText(builder.toString());
        holder.tvValues.setText(entitySettings.getValues());


        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Utils.disableButton(v);
                String textValue = holder.tvValues.getText().toString();

                switch (v.getId()) {
                    case R.id.iv_set_minusr:
                        if (entitySettings.getCountParam().equals("1")) {
                            int paramValue = Integer.parseInt(textValue),
                                    paramMin = Integer.parseInt(entitySettings.getMinValue());
                            if (paramValue > paramMin) {
                                --paramValue;
                                holder.tvValues.setText(paramValue + "");
                            }
                        } else {
                            if (!textValue.equals(entitySettings.getMinValue())) {

                            }
                        }
                        break;

                    case R.id.iv_set_pluse:
                        if (entitySettings.getCountParam().equals("1")) {
                            int paramValue = Integer.parseInt(textValue),
                                    paramMax = Integer.parseInt(entitySettings.getMaxValue());
                            if (paramValue < paramMax) {
                                ++paramValue;
                                holder.tvValues.setText(paramValue + "");
                            }
                        } else {
                            if (!textValue.equals(entitySettings.getMaxValue())) {

                            }
                        }
                        break;

                    case R.id.iv_set_ok:
                        // TODO: 16.04.2016 надо сделать запись в базу данных параметра paramValue

                        Db.getInstance(mContext).setParamByEntity(textValue, entitySettings.getTitle());
                        break;

                }


            }
        };


        holder.btOk.setOnClickListener(listener);
        holder.ivMin.setOnClickListener(listener);
        holder.ivPluse.setOnClickListener(listener);


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
