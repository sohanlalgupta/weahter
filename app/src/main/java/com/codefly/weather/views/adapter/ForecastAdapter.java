package com.codefly.weather.views.adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.codefly.weather.R;
import com.codefly.weather.models.Forecastday;
import com.codefly.weather.utils.Utils;

import java.util.List;

/**
 * Created by YATRAONLINE\sohan.gupta on 27/5/19.
 */

public class ForecastAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Forecastday> list;

    public ForecastAdapter(List<Forecastday> list){
        this.list=list;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=LayoutInflater.from(parent.getContext()).inflate(R.layout.item,parent,false);
        return new ForecastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ForecastViewHolder)holder).tv_day_temp.setText(String.valueOf(
                list.get(position).getDay().getAvgtempC()) +" C");
        ((ForecastViewHolder)holder).tv_day.setText(String.valueOf(
                Utils.getDayFromTimeStamp(list.get(position).getDateEpoch())));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void setList(List<Forecastday> list){
        this.list=list;
    }

    public static class ForecastViewHolder extends RecyclerView.ViewHolder{
        public TextView tv_day;
        public TextView tv_day_temp;

        public ForecastViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_day=(TextView)itemView.findViewById(R.id.txt_day);
            tv_day_temp=(TextView) itemView.findViewById(R.id.txt_day_temp);
        }
    }
}
