package com.example.hackathon.fragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.hackathon.R;

public class info_fragment  extends Fragment {

    final int CAMERA_PIC_REQUEST = 1337;
    ImageView img;
    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_info_fragment,container,false);

        return v;

    }

    @Override
    public void onStart() {
        super.onStart();
        img = v.findViewById(R.id.avatarIv);
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,CAMERA_PIC_REQUEST);
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode==CAMERA_PIC_REQUEST){
            Bitmap image = (Bitmap)data.getExtras().get("data");
            img.setImageBitmap(image);
        }
        setFragment(new Ticket_fragment());
        super.onActivityResult(requestCode, resultCode, data);
    }
    private void setFragment(Fragment f)
    {
        Log.e("Fragment", "home");
        FragmentTransaction ft1 = getFragmentManager().beginTransaction();
        ft1.replace(R.id.form, f, "");
        ft1.commit();
    }
}
