package com.upper.team15.privateschool.RegistrationActivity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.Model.Money_Pay_Model;
import com.upper.team15.privateschool.R;

import java.lang.reflect.Field;
import java.util.ArrayList;

/**
 * Created by MAT on 11/10/2017.
 */

public class MoneyPayStudentFragment extends Fragment  {

    RecyclerView recyclerView;
    MoneyPayAdapter adapter;
    public static Firebase money_pay_server;
    ArrayList<Money_Pay_Model> money_data = new ArrayList<>();
    ArrayList<Money_Pay_Model> class1 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class2 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class3 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class4 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class5 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class6 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class7 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class8 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class9 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class10 = new ArrayList<>();
    ArrayList<Money_Pay_Model> class11=new ArrayList<>();
    ProgressBar progressBar;
    ImageButton btn_register_search;
    Spinner spinner_choose_class;
    Button btn_dialog_ok,btn_dialog_cancel;
    String[] choose_student_class= {"အတန်းအားလုံး","သူငယ်တန်း","ပထမတန်း",
            "ဒုတိယတန်း" ,
            "တတိယတန်း" ,
            "စတုတ္ထတန်း" ,
            "ပဉ္စမတန်း" ,
            "ဆဋ္ဌမတန်း" ,
            "သတ္တမတန်း" ,
            "အဋ္ဌမတန်း",
            "နဝမတန်း",
            "ဒသမတန်း"};
    String student_attend_class;
    public MoneyPayStudentFragment(){

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.money_pay_form,container,false);
        spinner_choose_class= (Spinner) v.findViewById(R.id.money_choose_class);
        setSpinner();
        progressBar = (ProgressBar) v.findViewById(R.id.money_pay_progress);
        Firebase.setAndroidContext(getContext());
        money_pay_server = new Firebase("https://private-school-85adb.firebaseio.com/");
        recyclerView = (RecyclerView) v.findViewById(R.id.money_pay_recycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new MoneyPayAdapter(getContext(),money_data);
        RegistrationData();
        recyclerView.setAdapter(adapter);
        return v;
    }

    private void setSpinner() {
        ArrayAdapter<String> choose_class= new ArrayAdapter<String>(getContext(), android.R.layout.simple_dropdown_item_1line,choose_student_class);
        spinner_choose_class.setAdapter(choose_class);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(spinner_choose_class);

            // Set popupWindow height to 500px
            popupWindow.setHeight(600);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        student_attend_class=spinner_choose_class.getSelectedItem().toString();
        spinner_choose_class.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) spinner_choose_class.getSelectedItem();
                if(str.equals("အတန်းအားလုံး")){
                    RegistrationData();
                }
                else if(str.equals("သူငယ်တန်း")){
                    class1Data();

                }
                else if(str.equals("ပထမတန်း")){
                    class2Data();

                }
                else if(str.equals("ဒုတိယတန်း")){
                    class3Data();

                }
                else if(str.equals("တတိယတန်း")){
                    class4Data();

                }
                else if(str.equals("စတုတ္ထတန်း")){
                    class5Data();

                }
                else if(str.equals("ပဉ္စမတန်း")){
                    class6Data();

                }
                else if(str.equals("ဆဋ္ဌမတန်း")){
                    class7Data();

                }
                else if(str.equals("သတ္တမတန်း")){
                    class8Data();

                }
                else if(str.equals("အဋ္ဌမတန်း")){
                    class9Data();

                }
                else if(str.equals("နဝမတန်း")){
                    class10Data();

                }
                else if(str.equals("ဒသမတန်း")){
                    class11Data();
                }

            }



            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

          /*  case R.id.btn_register_search:

                searchdata.clear();
                for (int i = 0; i < money_data.size(); i++) {
                    if (edit_register_search.getText().toString().equals(money_data.get(i).getStudenName())) {
                        searchdata.add(money_data.get(i));

                    }
                }

                progressBar.setVisibility(View.INVISIBLE);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);
                break;*/
    }

    private void RegistrationData() {
        Log.e("counting", "Counting1");

        money_pay_server.child("Money_Pay_Registeration").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data loading: ", dataSnapshot.toString());
                money_data.clear();
                upDateData(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void upDateData(DataSnapshot dataSnapshot) {
        for (DataSnapshot shClass : dataSnapshot.getChildren()) {
            for (DataSnapshot shStudent : shClass.getChildren()) {
                Log.e("counting", "Firebase data");
                Log.e("Firebase data", money_data.size() + "");
                Money_Pay_Model registrationModel = shStudent.getValue(Money_Pay_Model.class);
                money_data.add(registrationModel);
                Log.e("RegistrationData", registrationModel.getStudenName());
                //money_data.add(attend_class);
            }
        }

        progressBar.setVisibility(View.GONE);
        adapter.swapList(money_data);



    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }


    private void class1Data() {

        money_pay_server.child("Money_Pay_Registeration").child("သူငယ်တန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
                class1.clear();
                class1List(dataSnapshot);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void class1List(DataSnapshot dataSnapshot) {
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class1.add(registrationModel);
            Log.e("Class data", registrationModel.getStudenName());
            //money_data.add(attend_class);
        }
        adapter = new MoneyPayAdapter(getContext(), class1);
        recyclerView.setAdapter(adapter);
    }

    private void class11Data() {
        money_pay_server.child("Money_Pay_Registeration").child("ဒသမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class11.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class11);
        recyclerView.setAdapter(adapter);
    }

    private void class10Data() {
        money_pay_server.child("Money_Pay_Registeration").child("နဝမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class10.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class10);
        recyclerView.setAdapter(adapter);
    }

    private void class9Data() {
        money_pay_server.child("Money_Pay_Registeration").child("အဋ္ဌမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class9.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class9);
        recyclerView.setAdapter(adapter);
    }

    private void class8Data() {
        money_pay_server.child("Money_Pay_Registeration").child("သတ္တမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class8.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class8);
        recyclerView.setAdapter(adapter);
    }

    private void class7Data() {
        money_pay_server.child("Money_Pay_Registeration").child("ဆဋ္ဌမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class7.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class7);
        recyclerView.setAdapter(adapter);
    }

    private void class6Data() {
        money_pay_server.child("Money_Pay_Registeration").child("ပဉ္စမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class6.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class6);
        recyclerView.setAdapter(adapter);
    }

    private void class5Data() {

        money_pay_server.child("Money_Pay_Registeration").child("စတုတ္ထတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class5.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class5);
        recyclerView.setAdapter(adapter);
    }

    private void class4Data() {

        money_pay_server.child("Money_Pay_Registeration").child("တတိယတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class4.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class4);
        recyclerView.setAdapter(adapter);
    }

    private void class3Data() {
        money_pay_server.child("Money_Pay_Registeration").child("ဒုတိယတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class3.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class3);
        recyclerView.setAdapter(adapter);
    }

    private void class2Data() {
        money_pay_server.child("Money_Pay_Registeration").child("ပထမတန်း").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("Data : ", dataSnapshot.toString());
                Log.e("counting", "looping");
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
            Money_Pay_Model registrationModel = sh.getValue(Money_Pay_Model.class);
            class2.add(registrationModel);
        }
        adapter = new MoneyPayAdapter(getContext(), class2);
        recyclerView.setAdapter(adapter);
    }
}
