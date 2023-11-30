package fmi.plovdiv.application;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import fmi.plovdiv.application.model.Festival;

public class FestivalAdapter extends ArrayAdapter<Festival> {

    public FestivalAdapter(Context context, List<Festival> festivals) {
        super(context, 0, festivals);
    }

    public void addFestivals(List<Festival> festivals) {
        clear();
        addAll(festivals);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_festival, parent, false);
            viewHolder = setupViewHolder(convertView);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        populateFestivalElements(viewHolder, position);
        return convertView;
    }

    private ViewHolder setupViewHolder(View convertView) {
        ViewHolder viewHolder = new ViewHolder();
        viewHolder.festivalNameTextView = convertView.findViewById(R.id.festivalNameTextView);
        convertView.setTag(viewHolder);
        return viewHolder;
    }

    private void populateFestivalElements(ViewHolder viewHolder, int position) {
        Festival festival = getItem(position);
        if (festival != null) {
            viewHolder.festivalNameTextView.setText(festival.getName());
        }
    }

    private static class ViewHolder {
        TextView festivalNameTextView;
    }
}