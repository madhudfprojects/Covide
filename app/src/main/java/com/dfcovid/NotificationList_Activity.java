package com.dfcovid;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.dfcovid.Constants.Constants;
import com.dfcovid.Constants.Database;


//import com.pushbots.push.Pushbots;

public class NotificationList_Activity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    RecyclerView notificationsRecycler;
    Database database;
    SwipeRefreshLayout swipeRefreshLayout;
    TextView title;
    ImageView backbtn_iv;
    public static int count_ofnotifications;

    public static final String sharedpreference_notification = "sharedpreferencebook_notification";
    public static final String KeyValue_flag = "KeyValue_flag";
    SharedPreferences.Editor editor_obj;
    SharedPreferences sharedpreference_notification_Obj;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_list_);
        /*Toolbar toolbar = findViewById(R.id.toolbar_n_actionbar);
        setSupportActionBar(toolbar);
        title = toolbar.findViewById(R.id.title_name);
        title.setText("Notifications");
        title.setTextColor(Color.WHITE);*/
      /*  getSupportActionBar().setTitle("");
        backbtn_iv = findViewById(R.id.backbtn_iv);
        backbtn_iv.setVisibility(View.VISIBLE);*/

/*
        backbtn_iv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(NotificationList_Activity.this, Dashboard_Activity.class);
                startActivity(i);
                finish();
            }
        });*/
        sharedpreference_notification_Obj=getSharedPreferences(sharedpreference_notification, Context.MODE_PRIVATE);

        editor_obj = sharedpreference_notification_Obj.edit();
        editor_obj.putString(KeyValue_flag, "0");
        editor_obj.commit();

        notificationsRecycler = (RecyclerView) findViewById(R.id.notificationsRecycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipeRefresh);
        swipeRefreshLayout.setOnRefreshListener(this);
        notificationsRecycler.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        notificationsRecycler.setHasFixedSize(true);
//        Pushbots.sharedInstance().setCustomHandler(ConnectivityReceiver.class);
//        Pushbots.sharedInstance().registerForRemoteNotifications();
    }

    @Override
    protected void onStart() {
        super.onStart();
        database = new Database(this);
        swipeRefreshLayout.setRefreshing(true);
        fillNotificationsData();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                swipeRefreshLayout.setRefreshing(false);
            }
        }, Constants.DELAY_TIME);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.notification_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_delete:
                database.deleteNotifications();
                fillNotificationsData();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onRefresh() {
        fillNotificationsData();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i = new Intent(NotificationList_Activity.this, Dashboard_Activity.class);
        startActivity(i);
        finish();

    }

    private void fillNotificationsData() {
        NotificationAdapter notificationAdapter = new NotificationAdapter(this, database.readNotificationData());
        notificationAdapter.notifyDataSetChanged();
        notificationsRecycler.setAdapter(notificationAdapter);
        swipeRefreshLayout.setRefreshing(false);
    }


}