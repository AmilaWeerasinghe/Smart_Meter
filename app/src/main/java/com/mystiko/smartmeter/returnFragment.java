package com.mystiko.smartmeter;



import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Call.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Call#newInstance} factory method to
 * create an instance of this fragment.
 */
public class returnFragment extends Fragment {
   // Button button1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_return,
                container, false);



        return view;
    }



    public void fragback(View view) {
        Intent r = new Intent(getActivity(), UserHome.class);
        startActivity(r);
    }

}

