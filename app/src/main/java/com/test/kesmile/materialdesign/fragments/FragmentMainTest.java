package com.test.kesmile.materialdesign.fragments;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.test.kesmile.materialdesign.R;
import com.test.kesmile.materialdesign.adapters.FragmentAdapter;
import com.test.kesmile.materialdesign.beans.BeanFragment;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

/**
 * Created by root on 8/01/15.
 */
public class FragmentMainTest extends Fragment implements LoaderManager.LoaderCallbacks<ArrayList<BeanFragment>> {
    private ListView lista;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity_main, container, false);
        lista = (ListView) rootView.findViewById(R.id.lst_fragment);
        return rootView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       /* ArrayList<BeanFragment> beans = new ArrayList<BeanFragment>();
        BeanFragment obj1 = new BeanFragment();
        obj1.setTitulo("Hola mundo");
        obj1.setDescription("Esta es una descripcion general");
        obj1.setImagen(BitmapFactory.decodeResource(getResources(), R.drawable.img));
        beans.add(obj1);
        BeanFragment obj2 = new BeanFragment();
        obj2.setTitulo("Hola mundo");
        obj2.setDescription("Esta es una descripcion general");
        obj2.setImagen(BitmapFactory.decodeResource(getResources(), R.drawable.img));
        beans.add(obj2);
        //beans.add(obj2);
        FragmentAdapter adapter = new FragmentAdapter(getActivity().getApplicationContext(),
                                                      beans);
        lista.setAdapter(adapter);*/
        getActivity().getSupportLoaderManager().initLoader(0, null, this);
    }

    @Override
    public Loader<ArrayList<BeanFragment>> onCreateLoader(int id, Bundle args) {
        return new Task(getActivity().getApplicationContext());
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BeanFragment>> loader, ArrayList<BeanFragment> data) {
        if(data != null){
            FragmentAdapter adapter = new FragmentAdapter(getActivity().getApplicationContext(),
                    data);
            lista.setAdapter(adapter);
        }
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BeanFragment>> loader) {
            lista.setAdapter(null);
    }


    public static class Task extends AsyncTaskLoader<ArrayList<BeanFragment>> {
        private boolean mCancelled = false;
        private ArrayList<BeanFragment> mData;
        public Task(Context context) {
            super(context);
        }
        @Override
        protected void onStartLoading() {
            Log.d("FragmentMain", "StartLoading");
            //super.onStartLoading();
            mCancelled = false;
            if(mData != null){
                deliverResult(mData);
                Log.d("FragmentMain", "deliverResult");
            }
            if (takeContentChanged() || mData == null) {
                Log.d("FragmentMain", "forceLoad");
                forceLoad();
            }
        }
        @Override
        public void deliverResult(ArrayList<BeanFragment> data) {
            mData = data;
            if(isStarted()){
                super.deliverResult(mData);
            }

        }

        @Override
        public ArrayList<BeanFragment> loadInBackground() {
            Log.d("FragmentMain", "loadBackground");
            mData = getDataUrl();
            if(mData != null){
                return mData;
            }
            return null;
        }

        @Override
        public void onCanceled(ArrayList<BeanFragment> data) {
            super.onCanceled(data);
            mCancelled = true;
            Log.d("FragmentMain", "onCanceled");
            cancelLoad();
        }

        private ArrayList<BeanFragment> getDataUrl(){
            Bitmap image;
            ArrayList<BeanFragment> data = new ArrayList<BeanFragment>();
            Log.d("FragmentMain", "getDataurl");
            HttpClient httpClient = new DefaultHttpClient();
            HttpGet httpget =
                    new HttpGet("http://200.35.162.27/test/materialdesign/");
            httpget.setHeader("content-type", "application/json");
            try{
                HttpResponse response = httpClient.execute(httpget);
                String respstring = EntityUtils.toString(response.getEntity());
                JSONArray respJSON = new JSONArray(respstring);
                if(respJSON != null){
                    for(int i=0; i<respJSON.length(); i++)
                    {
                        JSONObject obj = respJSON.getJSONObject(i);
                        BeanFragment value = new BeanFragment();
                        URLConnection conn = new URL(obj.getString("imagen")).openConnection();
                        image = BitmapFactory.decodeStream(conn.getInputStream());
                        value.setTitulo(obj.getString("titulo"));
                        value.setDescription(obj.getString("descripcion"));
                        value.setImagen(image);
                        data.add(value);
                    }
                }else{
                    return null;
                }
            }catch(Exception e){
                Log.e("Task","Error! " + e.toString());
                return null;
            }
            return data;
        }
    }
}
