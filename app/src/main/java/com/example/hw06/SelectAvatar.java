package com.example.hw06;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.gson.Gson;

import static android.content.Context.MODE_PRIVATE;

public class SelectAvatar extends Fragment {

    private onSelectAvatar mListener;
    private Details profileDetails;

    private ImageView iv_m_1;
    private ImageView iv_m_2;
    private ImageView iv_m_3;
    private ImageView iv_f_1;
    private ImageView iv_f_2;
    private ImageView iv_f_3;
    SharedPreferences mPrefs;


    public SelectAvatar() {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPrefs = getActivity().getSharedPreferences("Profile", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString("profileDetails", "");
        profileDetails = gson.fromJson(json, Details.class);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        iv_m_1 = view.findViewById(R.id.iv_m_1);
        iv_m_2 = view.findViewById(R.id.iv_m_2);
        iv_m_3 = view.findViewById(R.id.iv_m_3);
        iv_f_1 = view.findViewById(R.id.iv_f_1);
        iv_f_2 = view.findViewById(R.id.iv_f_2);
        iv_f_3 = view.findViewById(R.id.iv_f_3);

        iv_m_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDetails.setImageDrawable(R.drawable.avatar_m_1);
                Log.d("Bagh", profileDetails.getImageDrawable().toString());
                saveSharedPref();
                mListener.onSelectAvatar();
            }
        });

        iv_m_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Bagh", profileDetails.toString());
                profileDetails.setImageDrawable(R.drawable.avatar_m_2);
                saveSharedPref();
                mListener.onSelectAvatar();
            }
        });

        iv_m_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Bagh", profileDetails.toString());
                profileDetails.setImageDrawable(R.drawable.avatar_m_3);
                saveSharedPref();
                mListener.onSelectAvatar();
            }
        });

        iv_f_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Bagh", profileDetails.toString());
                profileDetails.setImageDrawable(R.drawable.avatar_f_1);
                saveSharedPref();
                mListener.onSelectAvatar();
            }
        });

        iv_f_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Bagh", profileDetails.toString());
                profileDetails.setImageDrawable(R.drawable.avatar_f_2);
                saveSharedPref();
                mListener.onSelectAvatar();
            }
        });

        iv_f_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("Bagh", profileDetails.toString());
                profileDetails.setImageDrawable(R.drawable.avatar_f_3);
                saveSharedPref();
                mListener.onSelectAvatar();
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_select_avatar, container, false);
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof onSelectAvatar) {
            mListener = (onSelectAvatar) context;
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


    public interface onSelectAvatar {
        void onSelectAvatar();
    }

    private void saveSharedPref(){
        SharedPreferences.Editor prefsEditor = mPrefs.edit();

        Gson gson = new Gson();
        String json = gson.toJson(profileDetails);
        Log.d("Bagh Avatar", "saveSharedPref: " + json);
        prefsEditor.putString("profileDetails", json);
        prefsEditor.apply();
    }
}
