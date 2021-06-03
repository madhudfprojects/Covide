package com.dfcovid;

import android.app.Activity;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;

import com.dfcovid.model.Class_DashboardHospitalData_List;

import java.util.ArrayList;


public class DashboardHospitalListViewAdapter extends BaseAdapter
{
    public ArrayList<Class_DashboardHospitalData_List> feesPaidList;
    Activity activity;

    public DashboardHospitalListViewAdapter(Activity activity, ArrayList<Class_DashboardHospitalData_List> feesPaidList) {
        super();
        this.activity = activity;
        this.feesPaidList = feesPaidList;
    }

    @Override
    public int getCount() {
        //return projList.size();
        return feesPaidList.size();
    }

    @Override
    public Class_DashboardHospitalData_List getItem(int position) {

        //return projList.get(position);
        return feesPaidList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    class ViewHolder {
        TextView Title_tv;
        TextView total_tv;
        TextView available_tv;
        TextView occupied_tv;
        CardView card_view;
        CardView card_view1;
        CardView card_view2;
        CardView card_view3;
        TextView headoccupied_tv;
        TextView headavailable_tv;
        TextView headtotal_tv;
        LinearLayout ll1;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if (convertView == null) {
            convertView = inflater.inflate(R.layout.dashboard_adapter, null);
            holder = new ViewHolder();

            holder.Title_tv = convertView.findViewById(R.id.Title_tv);
            holder.total_tv = convertView.findViewById(R.id.total_tv);
            holder.available_tv = convertView.findViewById(R.id.available_tv);
            holder.occupied_tv = convertView.findViewById(R.id.occupied_tv);
            holder.headtotal_tv = convertView.findViewById(R.id.headtotal_tv);
            holder.headavailable_tv = convertView.findViewById(R.id.headavailable_tv);
            holder.headoccupied_tv = convertView.findViewById(R.id.headoccupied_tv);
            holder.card_view1 = convertView.findViewById(R.id.card_view1);
            holder.card_view2 = convertView.findViewById(R.id.card_view2);
            holder.card_view3 = convertView.findViewById(R.id.card_view3);
            holder.card_view = convertView.findViewById(R.id.card_view);


            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        final Class_DashboardHospitalData_List item = feesPaidList.get(position);


        if(item.getBedType()!=null) {
            holder.Title_tv.setText(item.getBedType());
        }
        if(item.getTotal()!=null) {
            holder.total_tv.setText(item.getTotal());
        }

        if(!item.getOccupied().equals(null)) {
            holder.occupied_tv.setText(item.getOccupied());
        }

        if(item.getAvailable()!=null) {
            holder.available_tv.setText(item.getAvailable().toString());
        }

        if(holder.Title_tv.getText().equals("Summary")){

          //  holder.card_view.setCardBackgroundColor(Color.parseColor("#a6c8ff"));
            holder.card_view.setCardBackgroundColor(Color.parseColor("#75a2eb"));

            holder.card_view1.setCardBackgroundColor(Color.WHITE);
            holder.card_view2.setCardBackgroundColor(Color.WHITE);
            holder.card_view3.setCardBackgroundColor(Color.WHITE);
            holder.Title_tv.setTextColor(Color.WHITE);
            holder.available_tv.setTextColor(Color.parseColor("#47a049"));
            holder.total_tv.setTextColor(Color.parseColor("#3a7be4"));
            holder.occupied_tv.setTextColor(Color.parseColor("#fdaa42"));
            holder.headavailable_tv.setTextColor(Color.parseColor("#47a049"));
            holder.headtotal_tv.setTextColor(Color.parseColor("#3a7be4"));
            holder.headoccupied_tv.setTextColor(Color.parseColor("#fdaa42"));
        }else{
            holder.card_view.setCardBackgroundColor(Color.parseColor("#ecf3fe"));

            holder.card_view1.setCardBackgroundColor(Color.parseColor("#3a7be4"));
            holder.card_view2.setCardBackgroundColor(Color.parseColor("#fdaa42"));
            holder.card_view3.setCardBackgroundColor(Color.parseColor("#47a049"));
            holder.Title_tv.setTextColor(Color.parseColor("#3a7be4"));
            holder.available_tv.setTextColor(Color.WHITE);
            holder.total_tv.setTextColor(Color.WHITE);
            holder.occupied_tv.setTextColor(Color.WHITE);
            holder.headavailable_tv.setTextColor(Color.WHITE);
            holder.headtotal_tv.setTextColor(Color.WHITE);
            holder.headoccupied_tv.setTextColor(Color.WHITE);
        }
        return convertView;
    }

}
