package com.dfcovid;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.Marker;

public class CustomInfoWindowGoogleMap_Adapter implements GoogleMap.InfoWindowAdapter
{

    private Context context;

    public CustomInfoWindowGoogleMap_Adapter(Context ctx){
        context = ctx;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        return null;
    }

    @Override
    public View getInfoContents(Marker marker)
    {
        View view = ((Activity)context).getLayoutInflater()
                .inflate(R.layout.custominfowindowgooglemap, null);

        TextView hsptalname_tv = view.findViewById(R.id.hsptalname_tv);
        TextView hsptaladdress_tv = view.findViewById(R.id.hsptaladdress_tv);
        //ImageView img = view.findViewById(R.id.hsptalpic_iv);

       /* TextView hsptalcellno_tv = view.findViewById(R.id.hsptalcellno_tv);
        TextView hsptalservice_tv = view.findViewById(R.id.hsptalservice_tv);
        TextView hsptalweburl_tv = view.findViewById(R.id.hsptalweburl_tv);*/

        hsptalname_tv.setText(marker.getTitle());
        hsptaladdress_tv.setText(marker.getSnippet());

        Class_hsptaldetalServices_listResp hsptaldetalServices_listResp_obj= new Class_hsptaldetalServices_listResp();
       //Log.e("cell no",hsptaldetalServices_listResp_obj.getHospitalName());

       /* int imageId = context.getResources().getIdentifier(infoWindowData.getImage().toLowerCase(),
                "drawable", context.getPackageName());
        img.setImageResource(imageId);
*/
       // hsptalcellno_tv.setText(hsptaldetalServices_listResp_obj.getContactNo());
       /* hsptalservice_tv.setText(hsptaldetalServices_listResp_obj.getHospitalServices());
        hsptalweburl_tv.setText(hsptaldetalServices_listResp_obj.getWebsite());*/

        return view;



    }
}
