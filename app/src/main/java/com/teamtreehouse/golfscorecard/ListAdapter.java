package com.teamtreehouse.golfscorecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {
    private final Context mContext;
    private final Hole[] mHoles;

    public ListAdapter (Context context, Hole[] holes) {
        mContext = context;
        mHoles = holes;
    }

    @Override
    public int getCount() {
        return mHoles.length;
    }

    @Override
    public Object getItem(int i) {
        return mHoles[i];
    }

    @Override
    public long getItemId(int i) {
        return 0; // Not implemented
    }

    @Override
    public View getView(final int i, View convertView, ViewGroup viewGroup) {
        final ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.holeLabel = (TextView) convertView.findViewById(R.id.holeLabel);
            holder.strokeLabel = (TextView) convertView.findViewById(R.id.strokeLabel);
            holder.addStrokeButton = (Button) convertView.findViewById(R.id.addStrokeButton);
            holder.subStrokeButton = (Button) convertView.findViewById(R.id.subStrokeButton);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.holeLabel.setText(mHoles[i].getLabel());
        holder.strokeLabel.setText(mHoles[i].getStroke() + "");
        holder.addStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedStroke = mHoles[i].getStroke() + 1;
                mHoles[i].setStroke(updatedStroke);
                holder.strokeLabel.setText(updatedStroke + "");
            }
        });
        holder.subStrokeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int updatedStroke = mHoles[i].getStroke() - 1;
                if (updatedStroke < 0) updatedStroke = 0;
                mHoles[i].setStroke(updatedStroke);
                holder.strokeLabel.setText(updatedStroke + "");
            }
        });


        return convertView;
    }

    private static class ViewHolder {
        TextView holeLabel;
        TextView strokeLabel;
        Button addStrokeButton;
        Button subStrokeButton;
    }
}
