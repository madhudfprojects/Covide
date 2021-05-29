package com.dfcovid;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.dfcovid.model.Class_getdemo_resplist;
import com.dfcovid.model.Class_gethelp_resplist;

public class ContactUs_Activity extends AppCompatActivity {

   // Toolbar toolbar;
    ImageView add_newfarmpond_iv;

    Class_gethelp_resplist[] class_gethelp_resplist_arrayObj;
    Class_getdemo_resplist[] class_getdemo_resplist_arrayObj;

    String str_link,str_Googlelogin_Username="",str_Googlelogin_UserImg="";
    SharedPreferences sharedpref_username_Obj;
    SharedPreferences sharedpref_userimage_Obj;
    ImageView pdficon_IV;
    TextView usermanual_TV;
    ImageView usermanual_download_iv ;
    LinearLayout downloadlayout_LL;
    SharedPreferences sharedpref_usermanualpdf_Obj;
    String str_fileurl="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_contact_us);
//        sharedpref_usermanualpdf_Obj=getSharedPreferences(sharedpreferenc_usermanual, Context.MODE_PRIVATE);
//        str_fileurl = sharedpref_usermanualpdf_Obj.getString(key_usermanualpdfurl, "").trim();

        if (count_from_HelpDetails_table() > 0) {
            setContentView(R.layout.contactus_activity);
            pdficon_IV = (ImageView)findViewById(R.id.pdficon_IV);
            usermanual_TV = (TextView)findViewById(R.id.usermanual_TV);
            usermanual_download_iv = (ImageView)findViewById(R.id.usermanual_download_iv);
            downloadlayout_LL = (LinearLayout) findViewById(R.id.downloadlayout_LL);

           /* toolbar = (Toolbar) findViewById(R.id.toolbar_farmponddetails);
            // Set upon the actionbar
            setSupportActionBar(toolbar);*/
            // Now use actionbar methods to show navigation icon and title
            // getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Set upon the actionbar

            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            getSupportActionBar().setTitle("Help");

          /*  TextView title = (TextView) toolbar.findViewById(R.id.title_name);
            add_newfarmpond_iv = (ImageView) toolbar.findViewById(R.id.add_newfarmpond_iv);
            title.setText("About Us");
            getSupportActionBar().setTitle("");
            add_newfarmpond_iv.setVisibility(View.GONE);*/

           /* LinearLayout main_ll = findViewById(R.id.main2);
            TextView title_tv = new TextView(this);
            title_tv.setText("Contact us");
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            title_tv.setLayoutParams(params);
            main_ll.addView(title_tv);
*/
            Data_from_HelpDetails_table();


        } else {
            setContentView(R.layout.activity_contact_us);

           /* toolbar = (Toolbar) findViewById(R.id.toolbar_farmponddetails);
            // Set upon the actionbar
            setSupportActionBar(toolbar);*/
            // Now use actionbar methods to show navigation icon and title
            // getSupportActionBar().setDisplayHomeAsUpEnabled(false);
            // Set upon the actionbar

            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Help");

            final TextView phone_tv = (TextView) findViewById(R.id.phone_tv);
            final TextView email_tv = (TextView) findViewById(R.id.email_tv);

             downloadlayout_LL = (LinearLayout) findViewById(R.id.downloadlayout_LL);
              pdficon_IV = (ImageView)findViewById(R.id.pdficon_IV);
              usermanual_TV = (TextView)findViewById(R.id.usermanual_TV);
              usermanual_download_iv = (ImageView)findViewById(R.id.usermanual_download_iv);


            phone_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(Intent.ACTION_DIAL);
                    intent.setData(Uri.parse("tel:" + phone_tv.getText().toString()));
                    startActivity(intent);
                }
            });

            email_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + email_tv));
                    emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Technology Help Line");
                    startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
                }
            });

//            usermanual_TV.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i=new Intent(ContactUs_Activity.this,Activity_UserManual_OpenPDF.class);
//                    startActivity(i);
//
////                    if (isInternetPresent) {
////                        Intent i=new Intent(ContactUs_Activity.this,Activity_UserManual_OpenPDF.class);
////                        startActivity(i);
////
////                    }else{
////                        Toast.makeText(getApplicationContext(), "No Internet", Toast.LENGTH_SHORT).show();
////                    }
//
//                }
//            });
//
//
////            usermanual_TV.setOnClickListener(new View.OnClickListener() {
////                @Override
////                public void onClick(View v) {
////                    Intent intent = new Intent(ContactUs_Activity.this, Activity_ViewUserManualPDF_Downloaded_pdf.class);
////                    startActivity(intent);
////                }
////            });
//
//            usermanual_download_iv.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
////            "Content": "http://mis.detedu.org:8090/Document/Help/SIV_User_manual_1.0.pdf"
//
//                    Activity_UserManual_DownloadPDF.LoadUserManualDocument loadDocument = new Activity_UserManual_DownloadPDF.LoadUserManualDocument(ContactUs_Activity.this);
//                    loadDocument.execute("http://mis.detedu.org:8090/Document/Help/SIV_User_manual_1.0.pdf","User Manual");
//
////                    Class_SaveSharedPreference.setPrefFlagUsermanual(Activity_UserManual_DownloadPDF.this,"1");
////                    viewlayout_LL.setVisibility(View.VISIBLE);
//                    downloadlayout_LL.setVisibility(View.GONE);
//                }
//            });

           /* TextView title = (TextView) toolbar.findViewById(R.id.title_name);
            add_newfarmpond_iv = (ImageView) toolbar.findViewById(R.id.add_newfarmpond_iv);
            title.setText("About Us");
            getSupportActionBar().setTitle("");
            add_newfarmpond_iv.setVisibility(View.GONE);
            setContentView(R.layout.activity_contact_us);*/
        }


        //  Data_from_HelpDetails_table();


    }// end of Oncreate


    public int count_from_HelpDetails_table() {
        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS HelpDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,TitleDB VARCHAR,ContentDB VARCHAR);");
        Cursor cursor = db2.rawQuery("SELECT * FROM HelpDetails_table", null);
        int x = cursor.getCount();
        return x;
    }


    public void Data_from_HelpDetails_table() {
        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS HelpDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,TitleDB VARCHAR,ContentDB VARCHAR);");
        Cursor cursor = db2.rawQuery("SELECT * FROM HelpDetails_table", null);
        int x = cursor.getCount();

        Log.e("helpcount", String.valueOf(x));

        //class_gethelp_responses_arrayObj

        class_gethelp_resplist_arrayObj = new Class_gethelp_resplist[x];

        int i = 0;

        if (x > 0) {
            if (cursor.moveToFirst()) {
                do {

                    Class_gethelp_resplist innerObj_Class_gethelp_resplist = new Class_gethelp_resplist();
                    innerObj_Class_gethelp_resplist.setTitle(cursor.getString(cursor.getColumnIndex("TitleDB")));
                    innerObj_Class_gethelp_resplist.setContent(cursor.getString(cursor.getColumnIndex("ContentDB")));

                    Log.e("title", cursor.getString(cursor.getColumnIndex("TitleDB")).toString());
                    Log.e("content", cursor.getString(cursor.getColumnIndex("ContentDB")).toString());

                    class_gethelp_resplist_arrayObj[i] = innerObj_Class_gethelp_resplist;
                    i++;
                } while (cursor.moveToNext());
            }

        }


        LinearLayout help_ll = findViewById(R.id.help_ll);


        LinearLayout help_new_ll = findViewById(R.id.help_new_ll);
        ImageView help_new_iv = findViewById(R.id.help_new_iv);
        TextView help_new_tv = findViewById(R.id.help_new_tv);


        LinearLayout contct_ll = findViewById(R.id.contct_ll);
        ImageView ctc_iv = findViewById(R.id.ctc_iv);
        TextView phone_tv = findViewById(R.id.phone_tv);

        LinearLayout Email_ll = findViewById(R.id.Email_ll);
        ImageView email_iv = findViewById(R.id.email_iv);
        TextView email_tv = findViewById(R.id.email_tv);


        LinearLayout version_ll = findViewById(R.id.version_ll);
        ImageView version_iv = findViewById(R.id.version_iv);
        TextView version_tv = findViewById(R.id.version_tv);

        LinearLayout usrmanual_ll = findViewById(R.id.usrmanual_ll);
        ImageView usrmanual_iv = findViewById(R.id.usrmanual_iv);
        TextView usrmanual_tv = findViewById(R.id.usrmanual_tv);


        for (int k = 0; k < class_gethelp_resplist_arrayObj.length; k++) {
            if (x > 0) {
                String str_num = String.valueOf(k);
                Log.e("title",class_gethelp_resplist_arrayObj[k].getTitle());
                /*TextView title_tv = new TextView(this);
                title_tv.setText("Contact us");*/


                TextView title_tv = new TextView(this);
                final TextView content_tv = new TextView(this);
                TextView nextline_tv = new TextView(this);

//                ImageView  pdficon_new_IV = new ImageView(this);
//                pdficon_new_IV.setBackgroundResource(R.drawable.pdficon);
//                TextView usermanual_TV =new TextView(this);
//                ImageView  usermanual_download_iv = new ImageView(this);
                //0123
//                ImageView usermanual_download_iv = new ImageView(this);
//                TextView usermanual_TV= new TextView(this);
//                usermanual_download_iv.setImageResource(R.drawable.pdficon);
                title_tv.setText(class_gethelp_resplist_arrayObj[k].getTitle());


//                if(class_gethelp_resplist_arrayObj[k].getTitle().equals("User Manual")) {
//                    pdficon_new_IV.setBackgroundResource(R.drawable.pdficon);
//                    //title_tv.setText("Guide");
//                }else if(class_gethelp_resplist_arrayObj[k].getTitle().equals("Contact")) {
////                    pdficon_new_IV.setBackgroundResource(R.drawable.pdficon);
//                    //title_tv.setText("Guide");
//                    contct_ll.setVisibility(View.VISIBLE);
//                }else {
//                    title_tv.setText(class_gethelp_resplist_arrayObj[k].getTitle());
//                }

                title_tv.setTypeface(null, Typeface.BOLD);

                title_tv.setTextSize(18);
                //text.setGravity(Gravity.LEFT);
                title_tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                help_ll.addView(title_tv);
              //  nextline_tv.setText("\n");
//                if(class_gethelp_resplist_arrayObj[k].getContent().endsWith("SIV_User_manual_1.0.pdf")) {
                if(class_gethelp_resplist_arrayObj[k].getTitle().equals("User Manual")) {

                    // content_tv.setText("");
                    //content_tv.setText("Click here");
                    content_tv.setText(class_gethelp_resplist_arrayObj[k].getContent());
                  //  downloadlayout_LL.setVisibility(View.VISIBLE);
                 //   usermanual_TV.setText("User Manual");
                    str_fileurl=class_gethelp_resplist_arrayObj[k].getContent();
//                    SharedPreferences.Editor  myprefs_loginuserid= sharedpref_usermanualpdf_Obj.edit();
//                    myprefs_loginuserid.putString(key_usermanualpdfurl, str_fileurl);
//                    myprefs_loginuserid.apply();

                }else {
                    content_tv.setText(class_gethelp_resplist_arrayObj[k].getContent());
                }
                content_tv.setTextSize(12);
                //text.setGravity(Gravity.LEFT);
                content_tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                help_ll.addView(content_tv);


               // nextline_tv.setText("\n");
              //  help_ll.addView(nextline_tv);

                if (title_tv.getText().equals("Help")) {
                    help_new_ll.setVisibility(View.VISIBLE);
                    title_tv.setVisibility(View.GONE);
                    content_tv.setVisibility(View.GONE);
                    help_new_tv.setText(content_tv.getText().toString());


//                    Email_ll.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + content_tv.getText().toString()));
//                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Technology Help Line");
//                            startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
//                        }
//                    });
                }

                if (title_tv.getText().equals("Contact")) {
                    contct_ll.setVisibility(View.VISIBLE);
                    title_tv.setVisibility(View.GONE);
                    content_tv.setVisibility(View.GONE);
                    phone_tv.setText(content_tv.getText().toString());
                    ctc_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_DIAL);
                            intent.setData(Uri.parse("tel:" + content_tv.getText().toString()));
                            startActivity(intent);
                        }
                    });


                }
                if (title_tv.getText().equals("Email")) {
                    Email_ll.setVisibility(View.VISIBLE);
                    title_tv.setVisibility(View.GONE);
                    content_tv.setVisibility(View.GONE);
                    email_tv.setText(content_tv.getText().toString());


                    Email_ll.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + content_tv.getText().toString()));
                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Technology Help Line");
                            startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
                        }
                    });
                }

                if (title_tv.getText().equals("Version")) {
                    version_ll.setVisibility(View.VISIBLE);
                    title_tv.setVisibility(View.GONE);
                    content_tv.setVisibility(View.GONE);
                    version_tv.setText("Version  "+content_tv.getText().toString());


//                    Email_ll.setOnClickListener(new View.OnClickListener() {
//                        @Override
//                        public void onClick(View v) {
//                            Intent emailIntent = new Intent(Intent.ACTION_SENDTO, Uri.parse("mailto:" + content_tv.getText().toString()));
//                            emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Technology Help Line");
//                            startActivity(Intent.createChooser(emailIntent, "Chooser Title"));
//                        }
//                    });
                }
                if (title_tv.getText().equals("User Manual")) {

                    usrmanual_ll.setVisibility(View.VISIBLE);
                    title_tv.setVisibility(View.GONE);
                    content_tv.setVisibility(View.GONE);
                  //  usrmanual_tv.setText(content_tv.getText().toString());
                    usrmanual_tv.setText("User Manual");

                    //  downloadlayout_LL.setVisibility(View.VISIBLE);

 //                   pdficon_new_IV.setImageResource(R.drawable.pdficon);
//                    usermanual_download_iv.setImageResource(R.drawable.down_arrow);
//                    pdficon_IV.setVisibility(View.VISIBLE);
//                    usermanual_TV.setVisibility(View.VISIBLE);
//                    usermanual_download_iv.setVisibility(View.VISIBLE);
                   // content_tv.setText("Click here");
                    final int finalK = k;
                    usrmanual_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Log.e("usermanual",class_gethelp_resplist_arrayObj[finalK].getContent());
                            Intent i = new Intent(ContactUs_Activity.this, ContactCenter.class);
                            i.putExtra("usermanual",class_gethelp_resplist_arrayObj[finalK].getContent());
                            startActivity(i);
                            finish();

//                            Activity_UserManual_DownloadPDF.LoadUserManualDocument loadDocument = new Activity_UserManual_DownloadPDF.LoadUserManualDocument(ContactUs_Activity.this);
//                            loadDocument.execute(class_gethelp_resplist_arrayObj[finalK].getContent(),"User Manual");
//
//                            Intent i=new Intent(ContactUs_Activity.this,Activity_UserManual_OpenPDF.class);
//                            startActivity(i);

                        }
                    });


//                    title_tv.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
////                        Activity_UserManual_DownloadPDF.LoadUserManualDocument loadDocument = new Activity_UserManual_DownloadPDF.LoadUserManualDocument(ContactUs_Activity.this);
////                        loadDocument.execute("http://mis.detedu.org:8090/Document/Help/SIV_User_manual_1.0.pdf","User Manual");
//
////                        Activity_UserManual_DownloadPDF.LoadUserManualDocument loadDocument = new Activity_UserManual_DownloadPDF.LoadUserManualDocument(ContactUs_Activity.this);
////                        loadDocument.execute(class_gethelp_resplist_arrayObj[finalK].getContent(),"User Manual");
//
////                        Activity_UserManual_DownloadPDF.LoadUserManualDocument loadDocument = new Activity_UserManual_DownloadPDF.LoadUserManualDocument(ContactUs_Activity.this);
////                        loadDocument.execute(class_gethelp_resplist_arrayObj[finalK].getContent(),"User Manual");
////
////                        Intent i=new Intent(ContactUs_Activity.this,Activity_UserManual_OpenPDF.class);
////                        startActivity(i);
//
//                    }
//                });
            }

            }
        }

//demo_ll

        Data_from_DemoDetails_table();
    }


    public void Data_from_DemoDetails_table() {
        SQLiteDatabase db2 = this.openOrCreateDatabase("DFCOVID_DB", Context.MODE_PRIVATE, null);
        db2.execSQL("CREATE TABLE IF NOT EXISTS DemoDetails_table(SlNo INTEGER PRIMARY KEY AUTOINCREMENT,LanguageDB VARCHAR,LinkDB VARCHAR);");
        Cursor cursor = db2.rawQuery("SELECT * FROM DemoDetails_table", null);
        int x = cursor.getCount();

        Log.e("democount", String.valueOf(x));

        class_getdemo_resplist_arrayObj = new Class_getdemo_resplist[x];

        int i = 0;

        if (x > 0) {
            if (cursor.moveToFirst()) {
                do {

                    Class_getdemo_resplist innerObj_Class_getdemo_resplist = new Class_getdemo_resplist();
                    innerObj_Class_getdemo_resplist.setLanguage_Name(cursor.getString(cursor.getColumnIndex("LanguageDB")));
                    innerObj_Class_getdemo_resplist.setLanguage_Link(cursor.getString(cursor.getColumnIndex("LinkDB")));

                    Log.e("LanguageDB", cursor.getString(cursor.getColumnIndex("LanguageDB")).toString());
                    Log.e("LinkDB", cursor.getString(cursor.getColumnIndex("LinkDB")).toString());

                    class_getdemo_resplist_arrayObj[i] = innerObj_Class_getdemo_resplist;
                    i++;
                } while (cursor.moveToNext());
            }

        }


        LinearLayout demo_ll = findViewById(R.id.help_ll);

        LinearLayout kan_ll = findViewById(R.id.kan_ll);
        ImageView kan_iv = findViewById(R.id.kan_iv);
        TextView kan_tv = findViewById(R.id.kan_tv);

        LinearLayout eng_ll = findViewById(R.id.eng_ll);
        ImageView eng_iv = findViewById(R.id.eng_iv);
        TextView eng_tv = findViewById(R.id.eng_tv);

        LinearLayout telgu_ll = findViewById(R.id.telgu_ll);
        ImageView telgu_iv = findViewById(R.id.telgu_iv);
        TextView telgu_tv = findViewById(R.id.telgu_tv);

        for (int k = 0; k < class_getdemo_resplist_arrayObj.length; k++) {
            if (x > 0) {


                TextView language_tv = new TextView(this);
                TextView forlanguagename_tv = new TextView(this);
                TextView link_tv = new TextView(this);
                TextView nextline_tv = new TextView(this);
                //0123

                language_tv.setText(class_getdemo_resplist_arrayObj[k].getLanguage_Name());
                language_tv.setTypeface(null, Typeface.BOLD);

                Log.e("langname",class_getdemo_resplist_arrayObj[k].getLanguage_Name());
                language_tv.setTextSize(18);
                // text.setGravity(Gravity.LEFT);
                //change here
                language_tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));

                demo_ll.addView(language_tv);


                forlanguagename_tv.setText("Demo, Click here");
                forlanguagename_tv.setTextSize(12);
                forlanguagename_tv.setTypeface(Typeface.DEFAULT, Typeface.ITALIC);
                forlanguagename_tv.setTextColor(Color.BLUE);
                forlanguagename_tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                demo_ll.addView(forlanguagename_tv);


                link_tv.setText(class_getdemo_resplist_arrayObj[k].getLanguage_Link());
                link_tv.setTextSize(12);
                //text.setGravity(Gravity.LEFT);
                link_tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT));
                link_tv.setVisibility(View.GONE);

                str_link = class_getdemo_resplist_arrayObj[k].getLanguage_Link();


                /////////////////////////////////////
                Log.e("forlanguagename",forlanguagename_tv.getText().toString());
                if (language_tv.getText().equals("Kannada")) {
                    kan_ll.setVisibility(View.VISIBLE);
                    language_tv.setVisibility(View.GONE);
                    link_tv.setVisibility(View.GONE);
                    forlanguagename_tv.setVisibility(View.GONE);
                    kan_tv.setText("For Demo in Kannada,Click Here");
                    kan_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str_link));
                            startActivity(intent);
                            try {
                                //startActivity(appIntent);
                                //startActivity(webIntent);
                            } catch (ActivityNotFoundException ex) {
                                //
                            }
                        }
                    });

                    kan_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str_link));
                            startActivity(intent);
                            try {
                                //startActivity(appIntent);
                                //startActivity(webIntent);
                            } catch (ActivityNotFoundException ex) {
                                //
                            }
                        }
                    });



                }

                if (language_tv.getText().equals("English")) {
                    eng_ll.setVisibility(View.VISIBLE);
                    language_tv.setVisibility(View.GONE);
                    link_tv.setVisibility(View.GONE);
                    forlanguagename_tv.setVisibility(View.GONE);
                    eng_tv.setText("For Demo in English,Click Here");
                    eng_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str_link));
                            startActivity(intent);
                            try {
                                //startActivity(appIntent);
                                //startActivity(webIntent);
                            } catch (ActivityNotFoundException ex) {
                                //
                            }
                        }
                    });

                    eng_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str_link));
                            startActivity(intent);
                            try {
                                //startActivity(appIntent);
                                //startActivity(webIntent);
                            } catch (ActivityNotFoundException ex) {
                                //
                            }
                        }
                    });


                }

                if (language_tv.getText().equals("Telagu")) {
                    telgu_ll.setVisibility(View.VISIBLE);
                    language_tv.setVisibility(View.GONE);
                    link_tv.setVisibility(View.GONE);
                    forlanguagename_tv.setVisibility(View.GONE);
                    telgu_tv.setText("For Demo in Telagu,Click Here");
                    telgu_iv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str_link));
                            startActivity(intent);
                            try {
                                //startActivity(appIntent);
                                //startActivity(webIntent);
                            } catch (ActivityNotFoundException ex) {
                                //
                            }
                        }
                    });

                    telgu_tv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str_link));
                            startActivity(intent);
                            try {
                                //startActivity(appIntent);
                                //startActivity(webIntent);
                            } catch (ActivityNotFoundException ex) {
                                //
                            }
                        }
                    });


                }
                /////////////////////////////////////////
                link_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        // Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(class_getdemo_resplist_arrayObj[k].getLanguage_Link()));
                        /*Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                Uri.parse(class_getdemo_resplist_arrayObj[k].getLanguage_Link()));*/


                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str_link));
                        startActivity(intent);
                        try {
                            //startActivity(appIntent);
                            //startActivity(webIntent);
                        } catch (ActivityNotFoundException ex) {
                            //
                        }
                    }
                });


                forlanguagename_tv.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        try {
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(str_link));
                            startActivity(intent);
                        } catch (ActivityNotFoundException ex) {

                            Toast.makeText(getApplicationContext(), "WS:error:ex", Toast.LENGTH_SHORT).show();
                        }
                    }
                });


                //     demo_ll.addView(link_tv);


                nextline_tv.setText("\n");
                //     demo_ll.addView(nextline_tv);


            }
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.logout_menu, menu);
//        return true;
//    }


    @Override
    public void onBackPressed() {
        Intent i = new Intent(ContactUs_Activity.this, Dashboard_Activity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
        finish();
    }


}