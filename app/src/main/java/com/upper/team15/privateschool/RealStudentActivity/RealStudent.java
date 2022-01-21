package com.upper.team15.privateschool.RealStudentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.upper.team15.privateschool.AddNewStudent.AddNewStudentActivity;
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class RealStudent extends AppCompatActivity implements View.OnClickListener {

    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    Firebase firebase_School_Server;
    ArrayList<StudentModel> allData = new ArrayList<>();
    ArrayList<StudentModel> class2 = new ArrayList<>();
    ArrayList<StudentModel> class3 = new ArrayList<>();
    ArrayList<StudentModel> class4 = new ArrayList<>();
    ArrayList<StudentModel> class5 = new ArrayList<>();
    ArrayList<StudentModel> class6 = new ArrayList<>();
    ArrayList<StudentModel> class7 = new ArrayList<>();
    ArrayList<StudentModel> class8 = new ArrayList<>();
    ArrayList<StudentModel> class9 = new ArrayList<>();
    ArrayList<StudentModel> class10 = new ArrayList<>();
    ArrayList<StudentModel> class11=new ArrayList<>();
    ArrayList<StudentModel> all_search=new ArrayList<>();
    ArrayList<StudentModel> class1_search = new ArrayList<>();
    ArrayList<StudentModel> class2_search = new ArrayList<>();
    ArrayList<StudentModel> class3_search = new ArrayList<>();
    ArrayList<StudentModel> class4_search = new ArrayList<>();
    ArrayList<StudentModel> class5_search = new ArrayList<>();
    ArrayList<StudentModel> class6_search = new ArrayList<>();
    ArrayList<StudentModel> class7_search = new ArrayList<>();
    ArrayList<StudentModel> class8_search = new ArrayList<>();
    ArrayList<StudentModel> class9_search = new ArrayList<>();
    ArrayList<StudentModel> class10_search = new ArrayList<>();
    ArrayList<StudentModel> class11_search=new ArrayList<>();
    ArrayList<StudentModel> registrationData = new ArrayList<>();
    //ArrayList<ConfirmStudentModel> realRegistrationData=new ArrayList<>();
    ProgressBar progressBar;
    Button Delete;
    //ArrayList<String>
    Toolbar toolbar;
    //EditText edit_search;
    String str;
    //ImageButton btn_search;
    Spinner real_spinner_search;
    String[] choose_student_class= {"အတန်းအားလုံး","သူငယ်တန်း",
            "ပထမတန်း" ,
            "ဒုတိယတန်း" ,
            "တတိယတန်း" ,
            "စတုတ္ထတန်း" ,
            "ပဉ္စမတန်း" ,
            "ဆဋ္ဌမတန်း" ,
            "သတ္တမတန်း" ,
            "အဋ္ဌမတန်း",
            "နဝမတန်း",
            "ဒသမတန်း"};
    MaterialSearchView searchView;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_student);
        Firebase.setAndroidContext(RealStudent.this);
        floatingActionButton= (FloatingActionButton) findViewById(R.id.fab_real_server);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(RealStudent.this, AddNewStudentActivity.class);
                startActivity(i);
            }
        });
        firebase_School_Server = new Firebase("https://private-school-85adb.firebaseio.com/");
        recyclerView = (RecyclerView) findViewById(R.id.recycler_real_student);
        toolbar = (Toolbar) findViewById(R.id.include_real_student);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ကျောင်းသားစာရင်း");
        searchView = (MaterialSearchView) findViewById(R.id.search_view);
        searchName();
        real_spinner_search= (Spinner) findViewById(R.id.real_search_spinner);
        //btn_search.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progress_real);
        recyclerView.setLayoutManager(new LinearLayoutManager(RealStudent.this));
        ArrayAdapter<String> choose_class= new ArrayAdapter<String>(RealStudent.this, android.R.layout.simple_dropdown_item_1line,choose_student_class);
        real_spinner_search.setAdapter(choose_class);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(real_spinner_search);

            // Set popupWindow height to 500px
            popupWindow.setHeight(600);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        real_spinner_search.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                str=real_spinner_search.getSelectedItem().toString();
                if(str.equals("အတန်းအားလုံး")){
                    gettingRegistrationData();
                   //.setHint(R.string.all_search);
                }
                else if(str.equals("သူငယ်တန်း")){
                    class1Data();
                   //.setHint(R.string.real_student_search);

                }
                else if(str.equals("ပထမတန်း")){
                   //.setHint(R.string.class1_search);
                    class2Data();

                }

                else if(str.equals("ဒုတိယတန်း")){
                   //.setHint(R.string.class2_search);
                    class3Data();

                }

                else if(str.equals("တတိယတန်း")){
                   //.setHint(R.string.class3_search);
                    class4Data();

                }
                else if(str.equals("စတုတ္ထတန်း")){
                   //.setHint(R.string.class4_search);
                    class5Data();

                }
                else if(str.equals("ပဉ္စမတန်း")){
                   //.setHint(R.string.class5_search);
                    class6Data();

                }
                else if(str.equals("ဆဋ္ဌမတန်း")){
                   //.setHint(R.string.class6_search);
                    class7Data();

                }
                else if(str.equals("သတ္တမတန်း")){
                   //.setHint(R.string.class7_search);
                    class8Data();

                }
                else if(str.equals("အဋ္ဌမတန်း")){
                   //.setHint(R.string.class8_search);
                    class9Data();

                }
                else if(str.equals("နဝမတန်း")){
                   //.setHint(R.string.class9_search);
                    class10Data();

                }
                else if(str.equals("ဒသမတန်း")){
                   //.setHint(R.string.class10_search);
                    class11Data();
                }
                else {
                    str="";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void searchName() {
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(str.equals("အတန္းအားလံုး")){
                    all_search.clear();
                    for (int i = 0; i < allData.size(); i++) {
                        if (newText.equals(allData.get(i).getStudenName())) {
                            all_search.add(allData.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,all_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }

                else if(str.equals("သူငယ်တန်း")){
                    class1_search.clear();
                    for (int i = 0; i < registrationData.size(); i++) {
                        if (newText.equals(registrationData.get(i).getStudenName())) {
                            class1_search.add(registrationData.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class1_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ပထမတန်း")){
                    class2_search.clear();
                    for (int i = 0; i < class2.size(); i++) {
                        if (newText.equals(class2.get(i).getStudenName())) {
                            class2_search.add(class2.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class2_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ဒုတိယတန်း")){
                    class3_search.clear();
                    for (int i = 0; i <  class3.size(); i++) {
                        if (newText.equals( class3.get(i).getStudenName())) {
                            class3_search.add( class3.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class3_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("တတိယတန်း")){
                    class4_search.clear();
                    for (int i = 0; i <  class4.size(); i++) {
                        if (newText.equals( class4.get(i).getStudenName())) {
                            class4_search.add( class4.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class4_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("စတုတ္ထတန်း")){
                    class5_search.clear();
                    for (int i = 0; i <  class5.size(); i++) {
                        if (newText.equals( class5.get(i).getStudenName())) {
                            class5_search.add( class5.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class5_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ပဉ္စမတန်း")){
                    class6_search.clear();
                    for (int i = 0; i <  class6.size(); i++) {
                        if (newText.equals( class6.get(i).getStudenName())) {
                            class6_search.add( class6.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class6_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ဆဠမတန်း")){
                    class7_search.clear();
                    for (int i = 0; i <  class7.size(); i++) {
                        if (newText.equals( class7.get(i).getStudenName())) {
                            class7_search.add( class7.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class7_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("သတ္တမတန်း")){
                    class8_search.clear();
                    for (int i = 0; i <  class8.size(); i++) {
                        if (newText.equals( class8.get(i).getStudenName())) {
                            class8_search.add( class8.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class8_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("အဋ္ဌမတန်း")){
                    class9_search.clear();
                    for (int i = 0; i <  class9.size(); i++) {
                        if (newText.equals( class9.get(i).getStudenName())) {
                            class9_search.add( class9.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class9_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("နဝမတန်း")){
                    class10_search.clear();
                    for (int i = 0; i <  class10.size(); i++) {
                        if (newText.equals( class10.get(i).getStudenName())) {
                            class10_search.add( class10.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class10_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ဒသမတန်း")){
                    class11_search.clear();
                    for (int i = 0; i <  class11.size(); i++) {
                        if (newText.equals( class11.get(i).getStudenName())) {
                            class11_search.add( class11.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class11_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                return false;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_item,menu);
        MenuItem item=menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }

    private void gettingRegistrationData() {
        firebase_School_Server.child("Real_Student").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                registrationData.clear();
                allData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void allData(DataSnapshot dataSnapshot) {

        for (DataSnapshot shClass : dataSnapshot.getChildren()) {
            for (DataSnapshot shStudent : shClass.getChildren()) {
                StudentModel registrationModel = shStudent.getValue(StudentModel.class);
                allData.add(registrationModel);

                //registrationData.add(attend_class);
            }
        }

        adapter = new MyRealAdapter(RealStudent.this, allData);
        progressBar.setVisibility(View.INVISIBLE);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);


    }


    private void class1Data() {
        Log.e("counting", "Counting1");

        firebase_School_Server.child("Real_Student").child("သူငယ်တန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.e("counting", "looping");
                registrationData.clear();
                upDateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void upDateData(DataSnapshot dataSnapshot) {
        for (DataSnapshot shClass : dataSnapshot.getChildren()) {
                StudentModel registrationModel = shClass.getValue(StudentModel.class);
                registrationData.add(registrationModel);
            


        }

        adapter = new MyRealAdapter(RealStudent.this, registrationData);
        progressBar.setVisibility(View.INVISIBLE);
        adapter.notifyDataSetChanged();
        recyclerView.setAdapter(adapter);

    }
    


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            //case R.id.btn_real_student_search:
                /*if(str.equals("အတန္းအားလံုး")){
                    all_search.clear();
                    for (int i = 0; i < allData.size(); i++) {
                        if (edit_search.getText().toString().equals(allData.get(i).getStudenName())) {
                            all_search.add(allData.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,all_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }

               else if(str.equals("သူငယ်တန်း")){
                    class1_search.clear();
                    for (int i = 0; i < registrationData.size(); i++) {
                        if (edit_search.getText().toString().equals(registrationData.get(i).getStudenName())) {
                            class1_search.add(registrationData.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class1_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ပထမတန်း")){
                    class2_search.clear();
                    for (int i = 0; i < class2.size(); i++) {
                        if (edit_search.getText().toString().equals(class2.get(i).getStudenName())) {
                            class2_search.add(class2.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class2_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ဒုတိယတန်း")){
                    class3_search.clear();
                    for (int i = 0; i <  class3.size(); i++) {
                        if (edit_search.getText().toString().equals( class3.get(i).getStudenName())) {
                            class3_search.add( class3.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class3_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("တတိယတန်း")){
                    class4_search.clear();
                    for (int i = 0; i <  class4.size(); i++) {
                        if (edit_search.getText().toString().equals( class4.get(i).getStudenName())) {
                            class4_search.add( class4.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class4_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("စတုတ္ထတန်း")){
                    class5_search.clear();
                    for (int i = 0; i <  class5.size(); i++) {
                        if (edit_search.getText().toString().equals( class5.get(i).getStudenName())) {
                            class5_search.add( class5.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class5_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ပဉ္စမတန်း")){
                    class6_search.clear();
                    for (int i = 0; i <  class6.size(); i++) {
                        if (edit_search.getText().toString().equals( class6.get(i).getStudenName())) {
                            class6_search.add( class6.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class6_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ဆဠမတန်း")){
                    class7_search.clear();
                    for (int i = 0; i <  class7.size(); i++) {
                        if (edit_search.getText().toString().equals( class7.get(i).getStudenName())) {
                            class7_search.add( class7.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class7_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("သတ္တမတန်း")){
                    class8_search.clear();
                    for (int i = 0; i <  class8.size(); i++) {
                        if (edit_search.getText().toString().equals( class8.get(i).getStudenName())) {
                            class8_search.add( class8.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class8_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("အဋ္ဌမတန်း")){
                    class9_search.clear();
                    for (int i = 0; i <  class9.size(); i++) {
                        if (edit_search.getText().toString().equals( class9.get(i).getStudenName())) {
                            class9_search.add( class9.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class9_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("နဝမတန်း")){
                    class10_search.clear();
                    for (int i = 0; i <  class10.size(); i++) {
                        if (edit_search.getText().toString().equals( class10.get(i).getStudenName())) {
                            class10_search.add( class10.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class10_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }
                else if(str.equals("ဒသမတန်း")){
                    class11_search.clear();
                    for (int i = 0; i <  class11.size(); i++) {
                        if (edit_search.getText().toString().equals( class11.get(i).getStudenName())) {
                            class11_search.add( class11.get(i));

                        }
                    }
                    progressBar.setVisibility(View.INVISIBLE);
                    adapter=new MyRealAdapter(RealStudent.this,class11_search);
                    adapter.notifyDataSetChanged();
                    recyclerView.setAdapter(adapter);
                }*/



                //break;

        }

    }

    private void class2Data() {
        firebase_School_Server.child("Real_Student").child("ပထမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class2.clear();
                class2List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class2List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class2.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class2);
        recyclerView.setAdapter(adapter);
    }


    private void class11Data() {
        firebase_School_Server.child("Real_Student").child("ဒသမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class11.clear();
                class11List(dataSnapshot);
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class11List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class11.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class11);
        recyclerView.setAdapter(adapter);
    }

    private void class10Data() {
        firebase_School_Server.child("Real_Student").child("နဝမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class10.clear();
                class10List(dataSnapshot);
            }



            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class10List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class10.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class10);
        recyclerView.setAdapter(adapter);
    }

    private void class9Data() {
        firebase_School_Server.child("Real_Student").child("အဋ္ဌမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class9.clear();
                class9List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class9List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class9.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class9);
        recyclerView.setAdapter(adapter);
    }

    private void class8Data() {
        firebase_School_Server.child("Real_Student").child("သတ္တမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class8.clear();
                class8List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class8List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class8.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class8);
        recyclerView.setAdapter(adapter);
    }

    private void class7Data() {
        firebase_School_Server.child("Real_Student").child("ဆဋ္ဌမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class7.clear();
                class7List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class7List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class7.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class7);
        recyclerView.setAdapter(adapter);
    }

    private void class6Data() {
        firebase_School_Server.child("Real_Student").child("ပဉ္စမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class6.clear();
                class6List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class6List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class6.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class6);
        recyclerView.setAdapter(adapter);
    }

    private void class5Data() {

        firebase_School_Server.child("Real_Student").child("စတုတ္ထတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class5.clear();
                class5List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class5List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class5.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class5);
        recyclerView.setAdapter(adapter);
    }

    private void class4Data() {

        firebase_School_Server.child("Real_Student").child("တတိယတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class4.clear();
                class4List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class4List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class4.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class4);
        recyclerView.setAdapter(adapter);
    }

    private void class3Data() {
        firebase_School_Server.child("Real_Student").child("ဒုတိယတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                class3.clear();
                class3List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class3List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            StudentModel registrationModel = sh.getValue(StudentModel.class);
            class3.add(registrationModel);
        }
        adapter = new MyRealAdapter(RealStudent.this, class3);
        recyclerView.setAdapter(adapter);
    }


}
