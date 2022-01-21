package com.upper.team15.privateschool.RegistrationActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.upper.team15.privateschool.Model.Money_Pay_Model;
import com.upper.team15.privateschool.Model.ParentCountModel;
import com.upper.team15.privateschool.Model.ParentModel;
import com.upper.team15.privateschool.Model.Real_Student_Count;
import com.upper.team15.privateschool.Model.RegistrationModel;
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;
import java.util.Random;

import static com.upper.team15.privateschool.RegistrationActivity.MyParentAdapter.CHECK;
import static com.upper.team15.privateschool.RegistrationActivity.MyParentAdapter.parent_postion;

/**
 * Created by MAT on 11/10/2017.
 */

public class MoneyPayAdapter extends RecyclerView.Adapter<MoneyPayAdapter.MyRegistrationViewHolder> {
    Context moneyContext;
    static ArrayList<Money_Pay_Model> money_data = new ArrayList<>();
    Firebase real_student_count_firebase;
    AlertDialog alert_dialog;
    int retrieve_count;
    String username;
    EditText editUserNameforCondiId, editPasswordforCondiId;
    Button addButton, cancelButton;
    public static int pos;
    String myinputname, myinputPassword;
    ParentCountModel parentCountModel;
    RecyclerView.Adapter adapter;
    //parent count
    private int parentCount = 0;
    //parent id key
    private String parentKey = "";
    //parent password
    private String parentPass = " ";
    //to check parent acc
    boolean isHas = false;

    String key = "";
    RecyclerView recyclerView;
    int password;
    String atan = "?????????";
    String className[] = {"?????????",
            "??????? ",
            "?????????",
            "????????",
            "??????????",
            "?????????",
            "???????",
            "?????????",
            "?????????",
            "???????",
            "???????"};
    String sign_username[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
    String sign;

    AlertDialog.Builder alert, okcanceldialog;
    static AlertDialog alertclose, dd;
    ArrayList<ParentModel> parentList=new ArrayList<ParentModel>();

    public MoneyPayAdapter(Context schoolServer, ArrayList<Money_Pay_Model> data) {
        Firebase.setAndroidContext(schoolServer);
        real_student_count_firebase = new Firebase("https://private-school-85adb.firebaseio.com/");
        this.moneyContext = schoolServer;
        this.money_data = data;
    }

    public void swapList(ArrayList<Money_Pay_Model> data) {
        this.money_data = data;
        notifyDataSetChanged();
    }


    @Override
    public MoneyPayAdapter.MyRegistrationViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(moneyContext).inflate(R.layout.activity_money_schoolserver_registration_recycler, parent, false);
        MoneyPayAdapter.MyRegistrationViewHolder holder = new MoneyPayAdapter.MyRegistrationViewHolder(v);
        return holder;
    }


    @Override
    public void onBindViewHolder(final MoneyPayAdapter.MyRegistrationViewHolder holder, final int position) {

        holder.tname.setText(money_data.get(position).getStudenName());
        Log.i("Student Name ", money_data.get(position).getStudenName());
        Glide.with(moneyContext).load(money_data.get(position).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        holder.license.setVisibility(View.INVISIBLE);
                        holder.license_loading.setVisibility(View.GONE);
                        holder.license_refresh.setVisibility(View.VISIBLE);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        holder.license.setVisibility(View.VISIBLE);
                        holder.license_loading.setVisibility(View.GONE);
                        holder.license_refresh.setVisibility(View.GONE);
                        return false;
                    }
                }).into(holder.license);
        holder.license_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(moneyContext, "Continue", Toast.LENGTH_SHORT).show();
                Glide.with(moneyContext).load(money_data.get(position).getLicenseImage()).diskCacheStrategy(DiskCacheStrategy.ALL)
                        .listener(new RequestListener<String, GlideDrawable>() {
                            @Override
                            public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                                holder.license.setVisibility(View.INVISIBLE);
                                holder.license_loading.setVisibility(View.GONE);
                                holder.license_refresh.setVisibility(View.VISIBLE);
                                return false;
                            }

                            @Override
                            public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                                holder.license.setVisibility(View.VISIBLE);
                                holder.license_loading.setVisibility(View.GONE);
                                holder.license_refresh.setVisibility(View.GONE);
                                return false;
                            }
                        }).into(holder.license);
            }

        });
        holder.btn_confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog(money_data.get(position), position);                                                   // acc show dialog
            }
        });
        holder.btn_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteStudentFromMoneyPay(money_data.get(position), position);
            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pos = position;
                Intent check_student = new Intent(moneyContext, CheckMoneyPayStudent.class);
                check_student.putExtra("posi", position);
                moneyContext.startActivity(check_student);
            }
        });


    }

    private void updateStudentCount(Money_Pay_Model money) {
        Real_Student_Count model = new Real_Student_Count();
        model.setCount(retrieve_count + 1);
        real_student_count_firebase.child("Real_Student_Count").child(money.getAttendClass()).setValue(model);
    }

    private void deleteStudentFromMoneyPay(final Money_Pay_Model money, final int posi) {
        // real_student_count_firebase.child("Money_Pay_Registeration").child(money.getAttendClass()).child(money.getStudenName() + "_" + (money.getStudent_birth().replaceAll("/", "-"))).removeValue();
       /* if (money_data.size() > 0) {
            money_data.remove(posi);
           notifyDataSetChanged();
        }*/
        Query applesQuery = MoneyPayStudentFragment.money_pay_server.child("Money_Pay_Registeration").child(money_data.get(posi).getAttendClass()).orderByChild("studenName").equalTo(money_data.get(posi).getStudenName());

        applesQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot appleSnapshot : dataSnapshot.getChildren()) {
                    appleSnapshot.getRef().removeValue();
                    Log.i("DELETE NAME ::", money_data.get(posi).getStudenName());
                    Log.i("REMOVE ::", posi+"");
                    money_data.remove(posi);
                    notifyDataSetChanged();

                    // gettingRegistrationData();

                    // need to send notification to user
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    private void addStudentToRealStudent(Money_Pay_Model money, int posi,String parentId,String parent_password) {
        StudentModel money_pay_model = new StudentModel();
        money_pay_model.setAttendClass(money.getAttendClass());
        money_pay_model.setAttendForm(money.getAttendForm());
        money_pay_model.setFathername(money.getFathername());
        money_pay_model.setFatherNRCNO(money.getFatherNRCNO());
        money_pay_model.setMothername(money.getMothername());
        money_pay_model.setMotherNRCNO(money.getMotherNRCNO());
        money_pay_model.setStudenName(money.getStudenName());
        money_pay_model.setTrnsferForm(money.getTrnsferForm());
        money_pay_model.setStudent_birth(money.getStudent_birth());
        money_pay_model.setParentId(parentId);
        money_pay_model.setParentPassword(parent_password);
        money_pay_model.setLicenseImage(money.getLicenseImage());
        money_pay_model.setPassedClass(money.getPassedClass());
        money_pay_model.setStudentaddress(money.getStudentaddress());
        money_pay_model.setUsername(money.getStudenName() + "_" + (money.getStudent_birth().replaceAll("/", "-")));
       /* money_pay_model.setPassword(password);*/
        real_student_count_firebase.child("Real_Student").child(money.getAttendClass()).child(money.getStudenName() + "_" + (money.getStudent_birth().replaceAll("/", "-"))).setValue(money_pay_model);
        deleteStudentFromMoneyPay(money, posi);
    }

    private void getUsernameAndPasword() {
        username = "ps" + sign + key;
        Random r = new Random();
        int i1 = (r.nextInt(999999) + 100000);
        password = i1;
    }

    private void getClassCodefromFirebase(Money_Pay_Model money) {
        if (money_data.size() > 0)
            for (int i = 0; i < className.length; i++) {
                if (money.getAttendClass().equals(className[i])) {
                    sign = sign_username[i];
                }

            }
    }

    private void getStudentCountfromFirebase(Money_Pay_Model money) {
        if (money_data.size() > 0)
            real_student_count_firebase.child("Real_Student_Count").child(money.getAttendClass()).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        Log.e("Real Student Count", ds.getValue(Real_Student_Count.class).getCount() + "");
                        retrieve_count = ds.getValue(Real_Student_Count.class).getCount();
                    }
                }

                @Override
                public void onCancelled(FirebaseError firebaseError) {

                }
            });
    }

    private void condiCount_key(int retrieve_count) {
        if (retrieve_count < 10) {
            key = "00" + retrieve_count;
        } else if (retrieve_count < 100)
            key = "0" + retrieve_count;


        else if (retrieve_count == 100)
            key = retrieve_count + "";


    }


    @Override
    public int getItemCount() {
        return money_data.size();
    }

    public class MyRegistrationViewHolder extends RecyclerView.ViewHolder {
        ImageView license;
        TextView tname;
        ProgressBar license_loading;
        ImageView license_refresh;
        Button btn_confirm, btn_delete;

        public MyRegistrationViewHolder(View itemView) {
            super(itemView);
            license = (ImageView) itemView.findViewById(R.id.money_license_image);
            tname = (TextView) itemView.findViewById(R.id.money_student_registration_name);
            license_loading = (ProgressBar) itemView.findViewById(R.id.money_license_loading);
            license_refresh = (ImageView) itemView.findViewById(R.id.money_license_refresh);
            btn_confirm = (Button) itemView.findViewById(R.id.money_btn_register_confirm);
            btn_delete = (Button) itemView.findViewById(R.id.money_btn_register_cancel);


        }

    }

    public void showDialog(final Money_Pay_Model money, final int posi) {
        alert = new AlertDialog.Builder(moneyContext);
        alert.setMessage("ကျောင်းသားမိဘတွင်ကျောင်းသားအကောင့်ရှိပါက" +
                "ယခုလက်ခံမည့်ကျောင်းသား\n" + "အားထပ်ထည့်ပေးပါမည်။\n" +
                "အကောင့်ရှိပါသလား။");
        alert.setPositiveButton("ရှိပါသည်", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                View v = LayoutInflater.from(moneyContext).inflate(R.layout.activity_enter_user_account, null);
                final AlertDialog.Builder dialog = new AlertDialog.Builder(moneyContext);
                recyclerView= (RecyclerView) v.findViewById(R.id.parent_recycler);
                recyclerView.setLayoutManager(new LinearLayoutManager(moneyContext));
                real_student_count_firebase.child("ParentAccountTable").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        parentList.clear();
                        getData(dataSnapshot);
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
                adapter=new MyParentAdapter(moneyContext,parentList,money,posi);
                recyclerView.setAdapter(adapter);
                Log.i("DEL BUTTON :: ", "WORKED");
                deleteStudentFromMoneyPay(money, posi);

                //addStudentToRealStudent(money,posi,parentList.get(parent_postion).getParentId(),parentList.get(parent_postion).getParentPassword());

               /* addButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (editPasswordforCondiId.getText().toString().equals("") || editUserNameforCondiId.getText().toString().equals("")) {
                            Toast.makeText(moneyContext, R.string.enter_again, Toast.LENGTH_LONG).show();
                            alertclose.dismiss();


                        } else {
                            getEditData(money, posi);

                            alertclose.dismiss();

                        }

                    }
                });*/
               /* cancelButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        alertclose.dismiss();

                    }
                });*/
                dialog.setView(v);
                alertclose = dialog.create();
                alertclose.show();
                alertclose.setCancelable(false);
            }
        });
        alert.setNegativeButton("မရှိပါ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                okcanceldialog = new AlertDialog.Builder(moneyContext);
                okcanceldialog.setMessage(R.string.are_you_sure);
                okcanceldialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        getParentCount(money, posi);
//                        getStudentCountfromFirebase(money);
//                        getClassCodefromFirebase(money);
//                        condiCount_key(retrieve_count + 1);
//                        getUsernameAndPasword();
//
//                        getParentCount();
//                        condiCount_key(parentCountModel.getCount() + 1);
//                        getUsernameAndPaswordforparent();
//                        addNewToParentAccountCondi(posi);

                        dd.dismiss();

                    }
                });
                okcanceldialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alertclose = okcanceldialog.create();
                        alertclose.dismiss();
                    }
                });
                okcanceldialog.setCancelable(false);
                dd = okcanceldialog.create();
                dd.show();
            }
        });
        alert.show();
        alert.setCancelable(false);
    }

    private void getData(DataSnapshot dataSnapshot) {
        for(DataSnapshot sh:dataSnapshot.getChildren()){
            ParentModel pmodel=sh.getValue(ParentModel.class);
            parentList.add(pmodel);
            Log.i("PARENT :: ", pmodel.getParentName());
        }

        adapter.notifyDataSetChanged();
    }

    private void getUsernameAndPaswordforparent() {

        username = "psparent" + sign + key;
        Random r = new Random();
        int i1 = (r.nextInt(999999) + 100000);
        password = i1;
    }

    private void updateParentCount(Money_Pay_Model money, int posi,String parentId,String parentPassword) {

        ParentCountModel model = new ParentCountModel();
        model.setCount(parentCount + 1);
        real_student_count_firebase.child("ParentCount").setValue(model);
        addStudentToRealStudent(money, posi,parentId,parentPassword);
    }

   /* private void addNewToParentAccountCondi(int posi) {

        ParentModel parentModel = new ParentModel();
        parentModel.setAttendClass(money_data.get(posi).getAttendClass());
        parentModel.setStudentId(money_data.get(posi).getKey());
        parentModel.setParentId(myinputname);
        parentModel.setParentPassword(myinputPassword);
        real_student_count_firebase.child("ParentAccountTable").child(username).setValue(parentModel);
    }*/

//    private void getParentName(final Money_Pay_Model money, final int posi) {
//        real_student_count_firebase.child("ParentAccountTable").addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                Log.i("Data : ", dataSnapshot.toString());
//                if (iscorrectNameAndPassword(dataSnapshot)) {
//                    getStudentCountfromFirebase(money);
//                    getClassCodefromFirebase(money);
//                    condiCount_key(retrieve_count + 1);
//                    getUsernameAndPasword();
//                    addNewToParentAccount(money);
//                    updateStudentCount(money);
//                    addStudentToRealStudent(money);
//                    deleteStudentFromMoneyPay(money, posi);
//                    editUserNameforCondiId.setText("");
//                    editPasswordforCondiId.setText("");
////                    alertparentUserNameAndPassword();
//                } else {
//                    Toast.makeText(moneyContext, R.string.enter_again, Toast.LENGTH_LONG).show();
//                    editPasswordforCondiId.setText("");
//                    editUserNameforCondiId.setText("");
//                    if (dd != null)
//                        dd.dismiss();
//                    Toast.makeText(moneyContext, "Error", Toast.LENGTH_LONG).show();
//                    return;
//                }
//            }
//
//            @Override
//            public void onCancelled(FirebaseError firebaseError) {
//            }
//        });
//    }

    private void alertparentUserNameAndPassword() {
        Button ok;
        TextView tvname, tvpassword;
        View v = LayoutInflater.from(moneyContext).inflate(R.layout.activity_enter_parent_account, null);
        final AlertDialog.Builder alerttoParentdialog = new AlertDialog.Builder(moneyContext);
        ok = (Button) v.findViewById(R.id.ok_button_alert);
        tvname = (TextView) v.findViewById(R.id.nameedit);
        alerttoParentdialog.setView(v);
        tvpassword = (TextView) v.findViewById(R.id.passedit);

        tvname.setText(" : " + parentKey);
        tvpassword.setText(" : " + parentPass + "");

        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alert_dialog.dismiss();
            }
        });
        alert_dialog=alerttoParentdialog.create();
        alert_dialog.show();
        alert_dialog.setCancelable(false);
    }

    /* private void addNewToParentAccount(Money_Pay_Model money) {

         ParentModel parentModel = new ParentModel();
         parentModel.setAttendClass(money.getAttendClass());
         parentModel.setStudentId(username);
         parentModel.setParentId(myinputname);
         parentModel.setParentPassword(String.valueOf(password + 1));
         real_student_count_firebase.child("ParentAccountTable").child(username).setValue(parentModel);
     }*/
    private void getParentCount(final Money_Pay_Model money, final int posi) {
        real_student_count_firebase.child("ParentCount").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot parentDs : dataSnapshot.getChildren()) {
                    parentCount = parentDs.getValue(Integer.class);
                }
                getParentAccountDetail(money, posi);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }

    private void getParentAccountDetail(Money_Pay_Model money, int posi) {
        if (parentCount == 0)
            parentCount = 1;

        if (parentCount < 10) {
            parentKey = "psparent00" + parentCount;
        } else if (parentCount < 100)
            parentKey = "psparent0" + parentCount;
        else if (retrieve_count >= 100)
            parentKey = "psparent" + parentCount + "";

        Random r = new Random();
        parentPass = (r.nextInt(999999) + 100000)+"";

        addParentAccountNewWithStudentId(money, posi,parentKey,parentPass);
    }

    private void addParentAccountNewWithStudentId(Money_Pay_Model money, int posi,String parentId,String parentPassword) {
        ParentModel parentModel = new ParentModel();
        parentModel.setParentId(parentKey);
        parentModel.setParentPassword(parentPass + "");
        parentModel.setPhoneno(money.getPhoneno());
        parentModel.setParentName(money.getFathername());
        real_student_count_firebase.child("ParentAccountTable").child(money.getStudenName() + "_" + (money.getStudent_birth().replaceAll("/", "-"))).setValue(parentModel);
        updateParentCount(money, posi,parentId, parentPassword);
        alertparentUserNameAndPassword();
    }

    private boolean iscorrectNameAndPassword(DataSnapshot dataSnapshot) {
        Toast.makeText(moneyContext, "" + myinputname + myinputPassword, Toast.LENGTH_SHORT).show();
        for (DataSnapshot sh : dataSnapshot.getChildren()) {
            if (sh.getValue(ParentModel.class).getParentId().equals(myinputname) && sh.getValue(ParentModel.class).getParentPassword().equals(myinputPassword))
                return true;
        }
        return false;
    }

   /* private void getEditData(final Money_Pay_Model money, final int posi) {
        myinputname = editUserNameforCondiId.getText().toString();
        myinputPassword = editPasswordforCondiId.getText().toString();
        real_student_count_firebase.child("ParentAccountTable").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot parent : dataSnapshot.getChildren()){
                    ParentModel p = parent.getValue(ParentModel.class);
                    if (p.getParentId().equals(myinputname) && p.getParentPassword().equals(myinputPassword)) {
                        isHas = true;
                        real_student_count_firebase.child("ParentAccountTable").child(money.getStudenName() + "_" + (money.getStudent_birth().replaceAll("/", "-"))).setValue(p);
                        addStudentToRealStudent(money, posi);
                        break;
                    }


                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });

        if (!isHas) {
            Toast.makeText(moneyContext, "Id or Password is worng", Toast.LENGTH_LONG).show();
        }
    }*/
}

