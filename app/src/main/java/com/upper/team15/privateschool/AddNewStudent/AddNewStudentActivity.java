package com.upper.team15.privateschool.AddNewStudent;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.firebase.client.DataSnapshot;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.upper.team15.privateschool.HomeActivity.SchoolServerHomeActivity;
import com.upper.team15.privateschool.Model.Money_Pay_Model;
import com.upper.team15.privateschool.Model.ParentCountModel;
import com.upper.team15.privateschool.Model.ParentModel;
import com.upper.team15.privateschool.Model.RegistrationModel;
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;
import com.firebase.client.Firebase;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;


public class AddNewStudentActivity extends AppCompatActivity implements View.OnClickListener, DatePickerDialog.OnDateSetListener {

    Spinner passed_spinner;
    Spinner passed_form;
    TextView student_attend;
    int num;
    static Firebase registerFb;
    EditText studentName;
    EditText address;
    String real_image_str;
    EditText student_Father_Name;
    EditText father_NRC_No;
    int i;
    EditText mothername;
    TextView student_birth;
    EditText student_phone_no;
    EditText mother_NRC_No;
    int RESULT_LOAD_IMG = 20;
    Button btnRegister;
    static String imageStr;
    Bitmap bm;
    ImageView licenseImage;
    int OTHER = 10;
    TextView cost;
    RegistrationModel rModel;
    ArrayList<RegistrationModel> next_registration_data=new ArrayList<>();
    static ImageView tvrestcimage;
    String[] spinnerPassed = {"အောင်မြင်ထားသောအတန်းမရှိပါ","သူငယ်တန်း",
            "ပထမတန်း" ,
            "ဒုတိယတန်း" ,
            "တတိယတန်း" ,
            "စတုတ္ထတန်း" ,
            "ပဉ္စမတန်း" ,
            "ဆဋ္ဌမတန်း" ,
            "သတ္တမတန်း" ,
            "အဋ္ဌမတန်း",
            "နဝမတန်း"};
    String[] spinnerForm = {"ကျောင်း", "ကျောင်း + ဘော်ဒါ", "ကျောင်း + ဆီမီးဘော်ဒါ"};
    String[] cost_list={"၁။  မူလတန်းမှ စတုတ္တတန်းအထိ-  ၆သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "     စတင်ပေးသွင်းရမည့်ငွေ    -    ၂သိန်းကျပ်\n" +
            "၂။ ပဉ္စမတန်းမှ သတ္တမတန်းအထိ   - ၈သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "     စတင်ပေးသွင်းရမည့်ငွေ         - ၃သိန်းကျပ်\n" +
            "၃။  အဌမတန်း                         - ၉သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "    စတင်ပေးသွင်းရမည့်ငွေ         - ၃သိန်းကျပ်\n" +
            "၄။ နဝမတန်း                          - ၁၁ သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "    စတင်ပေးသွင်းရမည့်ငွေ         - ၄သိန်းကျပ်\n" +
            "၅။ ဒသမတန်း                         -၁၂သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "     စတင်ပေးသွင်းရမည့်ငွေ       -၆သိန်းကျပ်"
            ,"၁။  မူလတန်းမှ စတုတ္တတန်းအထိ-  ၂၁သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "     စတင်ပေးသွင်းရမည့်ငွေ          -   ၁၁သိန်းကျပ်\n" +
            "၂။ ပဉ္စမတန်းမှ သတ္တမတန်းအထိ   - ၂၅သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "     စတင်ပေးသွင်းရမည့်ငွေ         -  ၁၅သိန်းကျပ်   \n" +
            "၃။  အဌမတန်း                         -၂၈သိန်းကျပ် (တစ်နှစ်စာ)\n" +
            "    စတင်ပေးသွင်းရမည့်ငွေ         - ၁၈သိန်းကျပ်\n" +
            "၄။ နဝမတန်း                          - ၃၁သိန်းကျပ်  (တစ်နှစ်စာ)\n" +
            "    စတင်ပေးသွင်းရမည့်ငွေ         - ၄သိန်းကျပ်\n" +
            "၅။ ဒသမတန်း                         - ၃၄သိန်းကျပ်   (တစ်နှစ်စာ)\n" +
            "     စတင်ပေးသွင်းရမည့်ငွေ       -၂၀သိန်းကျပ်"
            ,"၁။  မူလတန်းမှ စတုတ္တတန်းအထိ-၁၆သိန်းကျပ်(တစ်နှစ်စာ)                                                                                           \n" +
            "     စတင်ပေးသွင်းရမည့်ငွေ    -  ၆သိန်းကျပ်\n" +
            "၂။ ပဉ္စမတန်းမှ သတ္တမတန်းအထိ   - ၈သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "   စတင်ပေးသွင်းရမည့်ငွေ         - ၃သိန်းကျပ်\n" +
            "၃။  အဌမတန်း                       - ၉သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "   စတင်ပေးသွင်းရမည့်ငွေ         - ၃သိန်းကျပ်\n" +
            "၄။ နဝမတန်း                          - ၁၁ သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "  စတင်ပေးသွင်းရမည့်ငွေ         - ၄သိန်းကျပ်\n" +
            "၅။ ဒသမတန်း                        -၁၂သိန်းကျပ်(တစ်နှစ်စာ)\n" +
            "   စတင်ပေးသွင်းရမည့်ငွေ       -၆သိန်းကျပ်"};
    Button btnCancel;
    Toolbar toolbar;
    //for firebase image storage;
    ProgressDialog pd;
    String register_image_url;
    int endyear,endmonth,endday;
    String transfer_image_url;
    StorageReference add_license_storage;
    StorageReference add_transfer_storage;
    static ArrayList<Money_Pay_Model> money_data = new ArrayList<>();
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
    String atan = "အဋ္ဌမတန်း";
    String className[] = {"သူငယ်တန်း",
            "ပထမတန်း",
            "ဒုတိယတန်း",
            "တတိယတန်း",
            "စတုတ္ထတန်း",
            "ပဉ္စမတန်း",
            "ဆဠမတန်း",
            "သတ္တမတန်း",
            "အဋ္ဌမတန်း",
            "နဝမတန်း",
            "ဒသမတန်း"};

    String sign_username[] = {"01", "02", "03", "04", "05", "06", "07", "08", "09", "10", "11"};
    String sign;
    int endYearFinal,endMonthFinal,endDayFinal;
    AlertDialog.Builder alert, okcanceldialog;
    static AlertDialog alert_dialog_colse_and_open, dd;
    ArrayList<ParentModel> parentList=new ArrayList<ParentModel>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_student);
        add_license_storage= FirebaseStorage.getInstance().getReference("Real_student_image");
        add_transfer_storage=FirebaseStorage.getInstance().getReference("Real_transfer_image");
        Firebase.setAndroidContext(AddNewStudentActivity.this);
        btnCancel= (Button) findViewById(R.id.buttonCancel);
        btnCancel.setOnClickListener(this);
        toolbar= (Toolbar) findViewById(R.id.user_registration_tool);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("ကျောင်းသားထပ်ထည့်ရန်");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        registerFb=new Firebase("https://private-school-85adb.firebaseio.com/");
        initId();
        btnRegister.setOnClickListener(this);
        tvrestcimage.setOnClickListener(this);
        licenseImage.setOnClickListener(this);
        setSpinnerAdapter();
        setSpinnerListener();
        student_birth.setOnClickListener(this);
        pd=new ProgressDialog(this);
        pd.setMessage("Please wait");


    }


    private void initId() {
        licenseImage= (ImageView) findViewById(R.id.btnchoose);
        address= (EditText) findViewById(R.id.student_address);
        studentName = (EditText) findViewById(R.id.studentname);
        passed_spinner = (Spinner) findViewById(R.id.spinnerPassed);
        student_attend = (TextView) findViewById(R.id.student_Attend_Class);
        student_Father_Name = (EditText) findViewById(R.id.studentfathername);
        father_NRC_No = (EditText) findViewById(R.id.father_nrc_no);
        mothername = (EditText) findViewById(R.id.mother_name);
        mother_NRC_No = (EditText) findViewById(R.id.mother_nrc_no);
        student_birth= (TextView) findViewById(R.id.edit_stu_birth);
        btnRegister= (Button) findViewById(R.id.btnSend);
        passed_form = (Spinner) findViewById(R.id.spinner_attend_Form);
        //registration_rule = (TextView) findViewById(R.id.rule);
        cost= (TextView) findViewById(R.id.tv_cost);
        tvrestcimage= (ImageView) findViewById(R.id.tvrestcimage);
        student_phone_no= (EditText) findViewById(R.id.edit_ph_no);
    }

    private void setSpinnerAdapter() {
        ArrayAdapter<String> passed_Spinner_Adapter = new ArrayAdapter<String>(AddNewStudentActivity.this, android.R.layout.simple_dropdown_item_1line, spinnerPassed);
        passed_spinner.setAdapter(passed_Spinner_Adapter);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(passed_spinner);

            // Set popupWindow height to 500px
            popupWindow.setHeight(600);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
        ArrayAdapter<String> attend_Spinner_Form = new ArrayAdapter<String>(AddNewStudentActivity.this, android.R.layout.simple_dropdown_item_1line, spinnerForm);
        passed_form.setAdapter(attend_Spinner_Form);
        try {
            Field popup = Spinner.class.getDeclaredField("mPopup");
            popup.setAccessible(true);

            // Get private mPopup member variable and try cast to ListPopupWindow
            android.widget.ListPopupWindow popupWindow = (android.widget.ListPopupWindow) popup.get(passed_form);

            // Set popupWindow height to 500px
            popupWindow.setHeight(600);
        }
        catch (NoClassDefFoundError | ClassCastException | NoSuchFieldException | IllegalAccessException e) {
            // silently fail...
        }
    }
    private void setSpinnerListener() {
        passed_spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String str = (String) passed_spinner.getSelectedItem();

                for (int i = 0; i < spinnerPassed.length; ++i) {

                    if (str.equals(spinnerPassed[i])) {
                        num = i + 1;
                        if(num<11){
                            student_attend.setText(spinnerPassed[num]);
                        }
                        if(num == 11){
                            student_attend.setText("ဒသမတန်း");
                            num=0;
                        }


                    }
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        passed_form.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String strRule = (String) passed_form.getSelectedItem();
                for (int j = 0; j < 3; ++j) {
                    if (strRule.equals("ေက်ာင္း")) {
                        cost.setText(cost_list[0]);
                    } else if (strRule.equals("ေက်ာင္း + ေဘာ္ဒါ")) {
                        cost.setText(cost_list[1]);
                    } else {
                        cost.setText(cost_list[2]);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.edit_stu_birth:
                Calendar can=Calendar.getInstance();
                endyear=can.get(Calendar.YEAR);
                endmonth=can.get(Calendar.MONTH);
                endday=can.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog enddatePickerDialog=new DatePickerDialog(AddNewStudentActivity.this,AddNewStudentActivity.this,endday,endday,endday);
                enddatePickerDialog.show();
                break;
            case R.id.btnSend:
                String strpass=passed_spinner.getSelectedItem().toString();
                String strform=passed_form.getSelectedItem().toString();
                String str=null;
                if(studentName.getText().length()<=0 || strpass.equals(str)  || student_attend.getText().length()<=0 ||
                        address.getText().length()<=0  || student_Father_Name.getText().length()<=0  || father_NRC_No.getText().length()<=0
                        ||mothername.getText().length()<=0 || mother_NRC_No.getText().length()<=0 || student_birth.getText().length()<=0
                        || strform.equals(str) ){
                    Toast.makeText(this,  "ဖြည့်ရန်ကျန်ပါသေးသည်", Toast.LENGTH_SHORT).show();

                }
                else {
                    rModel=new RegistrationModel();
                    rModel.setLicenseImage(register_image_url);
                    rModel.setTrnsferForm(transfer_image_url);
                    rModel.setStudenName(studentName.getText().toString());
                    rModel.setPassedClass(passed_spinner.getSelectedItem().toString());
                    rModel.setAttendClass(student_attend.getText().toString());
                    rModel.setStudentaddress(address.getText().toString());
                    rModel.setFathername(student_Father_Name.getText().toString());
                    rModel.setFatherNRCNO(father_NRC_No.getText().toString());
                    rModel.setMothername(mothername.getText().toString());
                    rModel.setMotherNRCNO(mother_NRC_No.getText().toString());
                    rModel.setPhoneno(student_phone_no.getText().toString());
                    rModel.setStudent_birth(student_birth.getText().toString());
                    rModel.setAttendForm(passed_form.getSelectedItem().toString());
                    next_registration_data.add(rModel);
                    alert = new AlertDialog.Builder(AddNewStudentActivity.this);
                    alert.setMessage("ကျောင်းသားမိဘတွင်ကျောင်းသားအကောင့်ရှိပါက" +
                            "ယခုလက်ခံမည့်ကျောင်းသား\n" + "အားထပ်ထည့်ပေးပါမည်။\n" +
                            "အကောင့်ရှိပါသလား။");

                    alert.setPositiveButton("ရှိပါသည်", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {

                            View v = LayoutInflater.from(AddNewStudentActivity.this).inflate(R.layout.activity_enter_user_account, null);
                            final AlertDialog.Builder dialog = new AlertDialog.Builder(AddNewStudentActivity.this);
                            recyclerView= (RecyclerView) v.findViewById(R.id.parent_recycler);
                            recyclerView.setLayoutManager(new LinearLayoutManager(AddNewStudentActivity.this));
                            registerFb.child("ParentAccountTable").addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(DataSnapshot dataSnapshot) {
                                    parentList.clear();
                                    getData(dataSnapshot);
                                }

                                @Override
                                public void onCancelled(FirebaseError firebaseError) {

                                }
                            });
                            adapter=new MyRealParentAdapter(AddNewStudentActivity.this,parentList,rModel);
                            recyclerView.setAdapter(adapter);
                            studentName.setText(" ");
                            student_Father_Name.setText(" ");
                            father_NRC_No.setText(" ");
                            mothername.setText(" ");
                            mother_NRC_No.setText(" ");
                            address.setText(" ");
                            student_birth.setText(" ");
                            student_phone_no.setText(" ");
                            licenseImage.setImageResource(R.drawable.gallery);
                            tvrestcimage.setImageResource(R.drawable.gallery);
                            studentName.setText(" ");
                            student_phone_no.setText(" ");
                            dialog.setView(v);
                            alert_dialog_colse_and_open = dialog.create();
                            alert_dialog_colse_and_open.show();
                            alert_dialog_colse_and_open.setCancelable(false);
                        }
                    });
                    alert.setNegativeButton("မရှိပါ", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            okcanceldialog = new AlertDialog.Builder(AddNewStudentActivity.this);
                            okcanceldialog.setMessage(R.string.are_you_sure);
                            okcanceldialog.setPositiveButton(R.string.confirm, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    getParentCount();
                                    studentName.setText(" ");
                                    student_Father_Name.setText(" ");
                                    father_NRC_No.setText(" ");
                                    mothername.setText(" ");
                                    mother_NRC_No.setText(" ");
                                    address.setText(" ");
                                    student_birth.setText(" ");
                                    student_phone_no.setText(" ");
                                    licenseImage.setImageResource(R.drawable.gallery);
                                    tvrestcimage.setImageResource(R.drawable.gallery);
                                    studentName.setText(" ");
                                    student_phone_no.setText(" ");
                                    dd.dismiss();

                                }
                            });
                            okcanceldialog.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    alert_dialog_colse_and_open = okcanceldialog.create();
                                    alert_dialog_colse_and_open.dismiss();
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
                break;
            case R.id.btnchoose:
                getImageFromAlbum();
                break;
            case R.id.tvrestcimage:
                getImageAlbum();
                break;
            case R.id.buttonCancel:
                finish();
                break;


        }

    }

    private void deleteStudentFromMoneyPay() {
    }

    private void getParentCount() {
        registerFb.child("ParentCount").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot parentDs : dataSnapshot.getChildren()) {
                    parentCount = parentDs.getValue(Integer.class);
                }
                getParentAccountDetail();
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }
    private void getParentAccountDetail() {
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

        addParentAccountNewWithStudentId(parentKey,parentPass);
    }



    private void addParentAccountNewWithStudentId(String parentKey, String parentPass) {
        ParentModel parentModel = new ParentModel();
        parentModel.setParentId(parentKey);
        parentModel.setParentPassword(parentPass + "");
        parentModel.setPhoneno(rModel.getPhoneno());
        parentModel.setParentName(rModel.getFathername());
        registerFb.child("ParentAccountTable").child(rModel.getStudenName() + "_" + (rModel.getStudent_birth().replaceAll("/", "-"))).setValue(parentModel);
        updateParentCount(parentKey, parentPass);
        alertparentUserNameAndPassword();
    }


    private void alertparentUserNameAndPassword() {
        Button ok;
        TextView tvname, tvpassword;
        View v = LayoutInflater.from(AddNewStudentActivity.this).inflate(R.layout.activity_enter_parent_account, null);
        final AlertDialog.Builder alerttoParentdialog = new AlertDialog.Builder(AddNewStudentActivity.this);
        ok = (Button) v.findViewById(R.id.ok_button_alert);
        tvname = (TextView) v.findViewById(R.id.nameedit);
        alerttoParentdialog.setView(v);
        tvpassword = (TextView) v.findViewById(R.id.passedit);

        tvname.setText("" + parentKey);
        tvpassword.setText("" + parentPass + "");

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

    private void updateParentCount(String parentId,String parentPassword) {

        ParentCountModel model = new ParentCountModel();
        model.setCount(parentCount + 1);
        registerFb.child("ParentCount").setValue(model);
        addStudentToRealStudent(parentId,parentPassword);
    }

    private void addStudentToRealStudent(String parentId, String parentPassword) {
        StudentModel money_pay_model = new StudentModel();
        money_pay_model.setAttendClass(rModel.getAttendClass());
        money_pay_model.setAttendForm(rModel.getAttendForm());
        money_pay_model.setFathername(rModel.getFathername());
        money_pay_model.setFatherNRCNO(rModel.getFatherNRCNO());
        money_pay_model.setMothername(rModel.getMothername());
        money_pay_model.setMotherNRCNO(rModel.getMotherNRCNO());
        money_pay_model.setStudenName(rModel.getStudenName());
        money_pay_model.setTrnsferForm(rModel.getTrnsferForm());
        money_pay_model.setStudent_birth(rModel.getStudent_birth());
        money_pay_model.setParentId(parentId);
        money_pay_model.setParentPassword(parentPassword);
        money_pay_model.setLicenseImage(rModel.getLicenseImage());
        money_pay_model.setPassedClass(rModel.getPassedClass());
        money_pay_model.setStudentaddress(rModel.getStudentaddress());
        money_pay_model.setUsername(rModel.getStudenName() + "_" + (rModel.getStudent_birth().replaceAll("/", "-")));
       /* money_pay_model.setPassword(password);*/
        registerFb.child("Real_Student").child(rModel.getAttendClass()).child(rModel.getStudenName() + "_" + (rModel.getStudent_birth().replaceAll("/", "-"))).setValue(money_pay_model);
        deleteStudentFromMoneyPay();
    }

    private void getData(DataSnapshot dataSnapshot) {
        for(DataSnapshot sh:dataSnapshot.getChildren()){
            ParentModel pmodel=sh.getValue(ParentModel.class);
            parentList.add(pmodel);
            Log.i("PARENT :: ", pmodel.getParentName());
        }

        adapter.notifyDataSetChanged();
    }

    private void getImageAlbum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, OTHER);
    }


    private void getImageFromAlbum() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
    }
    @Override
    protected void onActivityResult(int reqCode, int resultCode, Intent data) {
        super.onActivityResult(reqCode, resultCode, data);


        if (resultCode == RESULT_OK && reqCode == RESULT_LOAD_IMG) {
            if(pd!=null){
                pd.setCancelable(false);
                pd.show();
            }
            final Uri register_imageUri = data.getData();
            UploadTask uploadTask=add_license_storage.child(register_imageUri.toString()).putFile(register_imageUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    register_image_url=taskSnapshot.getDownloadUrl().toString();
                    pd.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                }
            });
            Glide.with(this).load(register_imageUri).into(licenseImage);


        }else if(reqCode == OTHER && resultCode == RESULT_OK) {
            if(pd!=null){
                pd.setCancelable(false);
                pd.show();
            }
            final Uri license_imageUri = data.getData();
            UploadTask uploadTask=add_transfer_storage.child(license_imageUri.toString()).putFile(license_imageUri);
            uploadTask.addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    transfer_image_url=taskSnapshot.getDownloadUrl().toString();
                    pd.dismiss();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    pd.dismiss();
                }
            });
            Glide.with(this).load(license_imageUri).into(tvrestcimage);
        }
        else {
            Toast.makeText(this, "You do not have picked image", Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        endYearFinal = i;
        endMonthFinal = i1 + 1;
        endDayFinal = i2;

        {
            student_birth.setText(endYearFinal + "/" + endMonthFinal + "/" + endDayFinal);
        }
    }
}




