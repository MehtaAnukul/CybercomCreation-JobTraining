package com.anukul.fcmtestapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleTon {
    private static MySingleTon mInstance;
    private static Context mContext;
    private RequestQueue requestQueue;

   private MySingleTon(Context context){
       mContext = context;
       requestQueue = getRequestQueue();
   }

   private RequestQueue getRequestQueue(){
       if(requestQueue == null){
           requestQueue = Volley.newRequestQueue(mContext.getApplicationContext());
       }
       return requestQueue;
   }

   public static synchronized MySingleTon getmInstance(Context context){
       if(mInstance == null){
           mInstance = new MySingleTon(context);
       }
       return mInstance;
   }

   public<T> void addToRequestque(Request<T> request){
        getRequestQueue().add(request);
   }
}
