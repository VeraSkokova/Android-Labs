package com.noveogroup.task3.ui;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.noveogroup.task3.R;

import java.util.List;

public class CityStringsAdapter extends RecyclerView.Adapter<CityStringsAdapter.ViewHolder> {
    private final List<String> cityStrings;

    public CityStringsAdapter(List<String> cityStrings) {
        this.cityStrings = cityStrings;
    }

    public List<String> getCityStrings() {
        return cityStrings;
    }

    @Override
    public CityStringsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.city_string, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CityStringsAdapter.ViewHolder holder, int position) {
        holder.textView.setText(cityStrings.get(position));
    }

    @Override
    public int getItemCount() {
        return cityStrings.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        public ViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.city_string);
        }
    }
}
