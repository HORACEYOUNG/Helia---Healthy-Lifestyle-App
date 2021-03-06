package com.zy.helia;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zy.helia.Activities.LoginActivity;
import com.zy.helia.Activities.MainActivity;
import com.zy.helia.Activities.MeInterestedEventListAdapter;
import com.zy.helia.Activities.eventTypes;
import com.zy.helia.Event_Data.DatabaseHelp;

import java.util.ArrayList;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MeIrInterested.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link MeIrInterested#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MeIrInterested extends Fragment {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private ArrayList<String> EventName = new ArrayList<>();
    private List<Integer> EventID = new ArrayList<Integer>();

    public MeIrInterested() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MeIrInterested.
     */
    // TODO: Rename and change types and number of parameters
    public static MeIrInterested newInstance(String param1, String param2) {
        MeIrInterested fragment = new MeIrInterested();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        // Get all pending events
        DatabaseHelp dbHelper = new DatabaseHelp(getContext());
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor eventCursor = dbHelper.viewInterested(LoginActivity.getUserID());

        while (eventCursor.moveToNext())
        {
            int eventIndex = eventCursor.getColumnIndex("Event_Name");
            String eventName = eventCursor.getString(eventIndex);
            EventName.add(eventName);

            int IDIndex = eventCursor.getColumnIndex("Event_ID");
            int eventID = eventCursor.getInt(IDIndex);
            EventID.add(eventID);
        }
        db.close();
        // Block End


        //newly added section
        View view = inflater.inflate(R.layout.fragment_me__ir__interested, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.InterestedEventListRV);

        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager((mLayoutManager));

        mAdapter = new MeInterestedEventListAdapter(getContext(),EventName, EventID);
        mRecyclerView.setAdapter(mAdapter);
        //end of newly added section

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
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

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
