package com.example.aleksandr.liberator.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.Snackbar;
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
import com.example.aleksandr.liberator.static_params.StaticParams;
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
                            } else {
                                Snackbar.make(v, R.string.is_min_value, Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            if (!textValue.equals(entitySettings.getMinValue())) {
                                String[] massValue = getStringMass(entitySettings.getTitle());
                                int numberValue = -1;
                                for (int i = 0; i < massValue.length; i++) {
                                    if (textValue.equals(massValue[i])) {
                                        numberValue = i;
                                    }
                                }
                                if (!textValue.equals(massValue[0]) && numberValue != -1) {
                                    holder.tvValues.setText(massValue[numberValue - 1]);
                                }
                            } else {
                                Snackbar.make(v, R.string.is_min_value, Snackbar.LENGTH_SHORT).show();
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
                            } else {
                                Snackbar.make(v, R.string.is_max_value, Snackbar.LENGTH_SHORT).show();
                            }
                        } else {
                            if (!textValue.equals(entitySettings.getMaxValue())) {
                                String[] massValue = getStringMass(entitySettings.getTitle());
                                int numberValue = -1;
                                for (int i = 0; i < massValue.length; i++) {
                                    if (textValue.equals(massValue[i])) {
                                        numberValue = i;
                                    }
                                }
                                if (!textValue.equals(massValue[massValue.length - 1]) && numberValue != -1) {
                                    holder.tvValues.setText(massValue[numberValue + 1]);
                                }
                            } else {
                                Snackbar.make(v, R.string.is_max_value, Snackbar.LENGTH_SHORT).show();
                            }
                        }
                        break;

                    case R.id.iv_set_ok:
                        Db.getInstance(mContext).setParamByEntity(textValue, entitySettings.getTitle());
                        Snackbar.make(v, "Параметр сохранен", Snackbar.LENGTH_SHORT).show();

                        break;

                }


            }
        };


        holder.btOk.setOnClickListener(listener);
        holder.ivMin.setOnClickListener(listener);
        holder.ivPluse.setOnClickListener(listener);


    }

    private String[] getStringMass(String mTitle) {

        if (mTitle.equals(mContext.getResources().getString(R.string.auto_start))) {
            return StaticParams.AUTO_START_PARAMS;

        } else if (mTitle.equals(mContext.getResources().getString(R.string.mode))) {
            return StaticParams.MODE_PARAMS;

        } else if (mTitle.equals(mContext.getResources().getString(R.string.fuel_loading))) {
            return StaticParams.LOAD_FUEL_PARAMS;

        } else if (mTitle.equals(mContext.getResources().getString(R.string.system_burn))) {
            return StaticParams.SYSTEM_BURNING_PARAMS;

        } else if (mTitle.equals(mContext.getResources().getString(R.string.enable_out_termosstat))) {
            return StaticParams.OUT_TERMOSTAAT;

        } else if (mTitle.equals(mContext.getResources().getString(R.string.settings_module))) {
            return StaticParams.SETTINGS_MODEL_PARAMS;

        } else if (mTitle.equals(mContext.getResources().getString(R.string.control_relay))) {
            return StaticParams.RELAY_PARAMS;

        } else if (mTitle.equals(mContext.getResources().getString(R.string.select_pellet))) {
            return StaticParams.SELECT_PELET;
        } else return null;
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
