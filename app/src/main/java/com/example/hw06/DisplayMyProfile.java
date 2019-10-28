package com.example.hw06;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;


public class DisplayMyProfile extends Fragment {

    private onDisplayProfile mListener;

    private Details profileDetails;

    public DisplayMyProfile() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mPrefs = getActivity().getSharedPreferences("Profile", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString("profileDetails", "");
        profileDetails = gson.fromJson(json, Details.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView tv_dep = view.findViewById(R.id.tv_dep);
        TextView tv_sidval = view.findViewById(R.id.tv_sidval);
        TextView tv_nameval = view.findViewById(R.id.tv_nameval);
        ImageView iv_avatar = view.findViewById(R.id.iv_avatar);

        tv_nameval.setText(String.format("%s %s", this.profileDetails.getFirstName(), this.profileDetails.getLastName()));
        tv_dep.setText(this.profileDetails.getDepartment());
        tv_sidval.setText(this.profileDetails.getStudentID());
        iv_avatar.setImageDrawable(getResources().getDrawable(this.profileDetails.getImageDrawable()));


        Button bt_edit = view.findViewById(R.id.bt_edit);
        bt_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onDisplayProfile();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_display_my_profile, container, false);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onDisplayProfile) {
            mListener = (onDisplayProfile) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface onDisplayProfile {
        void onDisplayProfile();
    }
}
