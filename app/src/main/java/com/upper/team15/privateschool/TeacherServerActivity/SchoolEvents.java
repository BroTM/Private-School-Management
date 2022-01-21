package com.upper.team15.privateschool.TeacherServerActivity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.upper.team15.privateschool.Model.EventModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.TeacherServerActivity.MyEventAdapter;

import java.util.ArrayList;
public class SchoolEvents extends Fragment implements SwipeRefreshLayout.OnRefreshListener {

    public static RecyclerView recyclerView;
    public static RecyclerView.Adapter eventAdapter;
    public static Firebase eventFirebase;
    Toolbar toolbar;
    public static ArrayList<EventModel> eventData;
    public static SwipeRefreshLayout refresh;

    public SchoolEvents(){}
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_school_events, container, false);
        toolbar= (Toolbar) view.findViewById(R.id.eventToolbar);
        refresh = (SwipeRefreshLayout) view.findViewById(R.id.refresh);
        refresh.setOnRefreshListener(this);
        eventData=new ArrayList<EventModel>();
        recyclerView= (RecyclerView) view.findViewById(R.id.event_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        Firebase.setAndroidContext(getContext());
        eventFirebase=new Firebase("https://private-school-85adb.firebaseio.com/");

        GettingSchoolEventSData(getContext());
        return view;
    }
    public static void GettingSchoolEventSData(final Context context) {
        refresh.setRefreshing(true);
        eventFirebase.child("Event").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                eventData.clear();
                upDateData(dataSnapshot,context);
            }
            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    public static void upDateData(DataSnapshot dataSnapshot, Context context) {
        for (DataSnapshot sh:dataSnapshot.getChildren()){

            EventModel eModel=sh.getValue(EventModel.class);
//            eModel.setEventImage(sh.getValue(EventModel.class).getEventImage());
//            eModel.setDate(sh.getValue(EventModel.class).getDate());
//            eModel.setTime(sh.getValue(EventModel.class).getTime());
//            eModel.setText(sh.getValue(EventModel.class).getText());
            //Log.e("DataEvent",sh.getValue(EventModel.class).getText());
            eventData.add(eModel);
        }
        eventAdapter=new MyEventAdapter(context,eventData);
        refresh.setRefreshing(false);
        //eventData.clear();
        eventAdapter.notifyDataSetChanged();
        recyclerView.setAdapter(eventAdapter);
    }
    @Override
    public void onRefresh() {
        GettingSchoolEventSData(getContext());
    }
}
