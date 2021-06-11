package com.dfcovid;

import android.content.Context;

import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.dfcovid.Constants.PushBotsModel;

import java.util.ArrayList;

/**
 * Created by Muhammad on 1/17/2017.
 */

public class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.NotificationsHistoryHolder> {
    ArrayList<PushBotsModel> pushBotsModels;
    Context context;

    public NotificationAdapter(Context context, ArrayList<PushBotsModel> pushBotsModels) {
        this.pushBotsModels = pushBotsModels;
        this.context = context;
    }

    @Override
    public NotificationAdapter.NotificationsHistoryHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_notification_item, parent, false);
        NotificationsHistoryHolder notificationsHistoryHolder = new NotificationsHistoryHolder(v);
        return notificationsHistoryHolder;
    }

    @Override
    public void onBindViewHolder(NotificationsHistoryHolder holder, int position) {
        try {
            /*
            Alert On Usage.
    10-06-2021
    KIMS- Vent Beds:98%

    District Hospital(civil)- ICU Beds:91%,

    Railway Hospital- Oxy Bed:100%,ICU Beds:100%,Vent Beds:100%

    Kundagol Taluka Hospital- ICU Beds:100%,Vent Beds:100%

    SDM Medical College Sattur- Vent Beds:100%
             */
            Log.e("tag","Notification message="+pushBotsModels.get(position).getMessage());
          //  if(pushBotsModels.get(position).getMessage().equalsIgnoreCase("Oxy Bed:")){
            String currentString = pushBotsModels.get(position).getMessage();
            /*for(int i=0;i<pushBotsModels.get(position).getMessage().length();i++) {
                String[] separated = currentString.split("-");
                Log.e("tag", " separated[0]=" + separated[i] + " separated[1]=" + separated[i]);
            }*/
           /* String[] words = currentString.split(":-");
            int itemCount = words.length;
            System.out.println("The number of words is: " + itemCount);
            for (int i = 0; i < itemCount; i++) {
                String word = words[i];
                System.out.println(word);
                currentString = currentString.replace(word, "<font color='#EE0000'>"+word+"</font>");
                //   txtDesc.setText(Html.fromHtml(help));
                holder.itemName.setText(Html.fromHtml(currentString));
            }*/

                String help=pushBotsModels.get(position).getMessage();
            help = help.replace("Alert On Usage", "<font color='#EE0000'>Alert On Usage</font>");
            help = help.replace("Oxy Beds", "<font color='#3a7be4'>Oxy Beds</font>");
            help = help.replace("ICU Beds", "<font color='#3a7be4'>ICU Beds</font>");
            help = help.replace("Vent Beds", "<font color='#3a7be4'>Vent Beds</font>");
            help = help.replace("..", "<br><br>");

            //   txtDesc.setText(Html.fromHtml(help));
                holder.itemName.setText(Html.fromHtml(help));
            //}

            holder.date_time_N_TV.setText(pushBotsModels.get(position).getDate());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return pushBotsModels.size();
    }


    public static class NotificationsHistoryHolder extends RecyclerView.ViewHolder {
        TextView itemName,date_time_N_TV;
        ImageView imageView;
        CardView cardView;

        public NotificationsHistoryHolder(View itemView) {
            super(itemView);
            itemName = (TextView) itemView.findViewById(R.id.itemName);
            imageView = (ImageView) itemView.findViewById(R.id.notificationImage);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
            date_time_N_TV = (TextView) itemView.findViewById(R.id.date_time_N_TV);

        }
    }
}
