package com.example.home.suntistassigment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by home on 21/03/18.
 */

public class UserAdapter extends ArrayAdapter<User> implements ActivityCompat.OnRequestPermissionsResultCallback {
    private static class ViewHolder {
        TextView name;
        TextView phone;
        TextView gender;
    }

    public UserAdapter(Context context, ArrayList<User> users) {
        super(context, R.layout.single_list_item_view, users);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        User user = getItem(position);
        ViewHolder viewHolder;
        if (convertView == null) {
           viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.single_list_item_view, parent, false);
            viewHolder.name = (TextView) convertView.findViewById(R.id.name);
            viewHolder.phone = (TextView) convertView.findViewById(R.id.telephone);
            viewHolder.gender = (TextView) convertView.findViewById(R.id.gender);
            convertView.setTag(viewHolder);
        } else {

            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.name.setText(user.getName().toString());
        viewHolder.phone.setText(user.getCellNumber());
        viewHolder.gender.setText(user.getGender());
        viewHolder.phone.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("MissingPermission")
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:"+getItem(position).getCellNumber()));

                if(isPermissionGranted()){
                    getContext().startActivity(callIntent);
                }

            }
        });
        return convertView;
    }
    public  boolean isPermissionGranted() {
        if (Build.VERSION.SDK_INT >= 23) {
            if (ActivityCompat.checkSelfPermission(getContext(),android.Manifest.permission.CALL_PHONE)
                    == PackageManager.PERMISSION_GRANTED) {
                Log.v("TAG","Permission is granted");
                return true;
            } else {

                Log.v("TAG","Permission is revoked");
                ActivityCompat.requestPermissions((Activity) getContext(), new String[]{Manifest.permission.CALL_PHONE}, 1);
                return false;
            }
        }
        else { //permission is automatically granted on sdk<23 upon installation
            Log.v("TAG","Permission is granted");
            return true;
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {

            case 1: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(getContext(), "Permission granted", Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(getContext(), "Permission denied", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }
}