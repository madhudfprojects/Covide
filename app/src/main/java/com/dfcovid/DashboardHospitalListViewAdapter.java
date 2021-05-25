package com.dfcovid;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

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
        TextView mApproved;
        TextView mRejected;
        TextView mEmpId;
        TextView mYearId;
        TextView mUserId;
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


        return convertView;
    }

}
