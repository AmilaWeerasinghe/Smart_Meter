package com.mystiko.smartmeter;



import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.telephony.SmsManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

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
public class msg extends Fragment implements  View.OnClickListener{
    Button button1;
    EditText textBox;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_msg,
                container, false);
        textBox=(EditText)view.findViewById(R.id.msgBox);
        Button buttonsms = (Button) view.findViewById(R.id.officer1Text);
        buttonsms.setOnClickListener(new Button.OnClickListener(){

            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub

                String smsNumber ="0775558888"; //edittextSmsNumber.getText().toString();
                String smsText = textBox.getText().toString();;//edittextSmsText.getText().toString();

                Uri uri = Uri.parse("smsto:" + smsNumber);
                Intent intent = new Intent(Intent.ACTION_SENDTO, uri);
                intent.putExtra("sms_body", smsText);
                startActivity(intent);
            }});
        return view;
    }


    @Override
    public void onClick(View v) {
        String messageToSend = "this is a message";
        String number = "2121234567";

        SmsManager.getDefault().sendTextMessage(number, null, messageToSend, null,null);

    }
}

