package com.example.hw06;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import static android.content.Context.MODE_PRIVATE;

public class MyProfileFragment extends Fragment {

    private OnFragmentInteractionListener mListener;

    private Details profileDetails;

    private EditText et_firstname;
    private EditText et_lastname;
    private EditText et_studentid;

    public MyProfileFragment() {

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences mPrefs = getActivity().getSharedPreferences("Profile", MODE_PRIVATE);

        Gson gson = new Gson();
        String json = mPrefs.getString("profileDetails", "");
        profileDetails = gson.fromJson(json, Details.class);
        if(profileDetails == null) {
            profileDetails = new Details();
        }

    }

    @Override
    public void onViewCreated(@NonNull final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        et_firstname = view.findViewById(R.id.et_firstname);
        et_lastname = view.findViewById(R.id.et_lastname);
        et_studentid = view.findViewById(R.id.et_studentid);

        final ImageView iv_selectAvatar = view.findViewById(R.id.iv_selectAvatar);
        final RadioGroup rg_selectstream = view.findViewById(R.id.rg_selectstream);
        Button bt_save = view.findViewById(R.id.bt_save);


        if(profileDetails.getFirstName() != null){
            et_firstname.setText(profileDetails.getFirstName());
        }

        if(profileDetails.getFirstName() != null){
            et_lastname.setText(profileDetails.getLastName());
        }

        if(profileDetails.getStudentID() != null){
            et_studentid.setText(profileDetails.getStudentID());
        }

        if(profileDetails.getDepartment() != null){
            switch (profileDetails.getDepartment()) {
                case "CS":
                    rg_selectstream.check(R.id.rb_cs);
                    break;
                case "BIO":
                    rg_selectstream.check(R.id.rb_bio);
                    break;
                case "SIS":
                    rg_selectstream.check(R.id.rb_sis);
                    break;
                case "Other":
                    rg_selectstream.check(R.id.rb_other);
                    break;
            }
        }

        if(profileDetails.getImageDrawable() != null) {
            iv_selectAvatar.setImageDrawable(getResources().getDrawable(profileDetails.getImageDrawable()));
        }

        iv_selectAvatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDetails.setFirstName(et_firstname.getText().toString());
                profileDetails.setLastName(et_lastname.getText().toString());
                profileDetails.setStudentID(et_studentid.getText().toString());
                saveSharedPref();
                int mainActivityView = Objects.requireNonNull(getActivity()).findViewById(R.id.container).getId();
                assert getFragmentManager() != null;

                getActivity().setTitle("Select Avatar");

                getFragmentManager().beginTransaction()
                        .replace(mainActivityView, new SelectAvatar(), "selectAvatar")
                        .commit();
            }
        });

        rg_selectstream.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton rb = Objects.requireNonNull(getActivity()).findViewById(checkedId);
                String selectedDept = rb.getText().toString();
                profileDetails.setDepartment(selectedDept);
            }
        });

        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                profileDetails.setFirstName(et_firstname.getText().toString());
                profileDetails.setLastName(et_lastname.getText().toString());
                profileDetails.setStudentID(et_studentid.getText().toString());
                Log.d("Bagh Val", profileDetails.getLastName());
                if(profileDetails.getFirstName() == null || profileDetails.getFirstName().trim().length() == 0){
                    et_firstname.setError("First Name Cannot Be Null");
                } else if(profileDetails.getLastName() == null || profileDetails.getLastName().trim().length() == 0){
                    et_lastname.setError("Last Name Cannot Be Null");
                } else if(profileDetails.getDepartment() == null){
                    Toast.makeText(getActivity(), "Please Select a Department", Toast.LENGTH_SHORT).show();
                } else if(profileDetails.getStudentID() == null){
                    et_studentid.setError("Enter a valid Student ID");
                } else if (profileDetails.getImageDrawable() == null){
                    Toast.makeText(getActivity(), "Please Select an Avatar", Toast.LENGTH_SHORT).show();
                } else if(profileDetails.getStudentID().length() > 9 || profileDetails.getStudentID().length() <= 0){
                    et_studentid.setError("StudentID should be 1-9 Digits Long");
                } else if(profileDetails.getStudentID() != null && profileDetails.getStudentID().startsWith("-")){
                    et_studentid.setError("Enter a valid Student ID");
                } else {
                    saveSharedPref();
                    mListener.onFragmentInteraction();
                }
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_my_profile, container, false);
        // Inflate the layout for this fragment
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction();
    }

    public void saveSharedPref(){
        SharedPreferences.Editor prefsEditor = getActivity().getSharedPreferences("Profile", MODE_PRIVATE).edit();
        Gson gson = new Gson();
        String json = gson.toJson(profileDetails);
        prefsEditor.putString("profileDetails", json);
        prefsEditor.apply();
    }


}
