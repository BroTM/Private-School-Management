package com.upper.team15.privateschool.RegistrationActivity;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.client.Firebase;
import com.upper.team15.privateschool.Model.Money_Pay_Model;
import com.upper.team15.privateschool.Model.ParentModel;
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

import static com.upper.team15.privateschool.RegistrationActivity.MoneyPayAdapter.alertclose;
import static com.upper.team15.privateschool.RegistrationActivity.MoneyPayAdapter.money_data;

/**
 * Created by Aspire on 11/17/2017.
 */

class MyParentAdapter extends RecyclerView.Adapter<MyParentAdapter.MyHolder> {
    ArrayList<ParentModel> pmodel=new ArrayList<ParentModel>();
    Context context;
    Firebase real_student_count_firebase;
    static int parent_postion;
    Money_Pay_Model money;
    int posi;
    static Boolean CHECK=false;
    String parentId;
    String parentPassword;

    public MyParentAdapter(Context moneyContext, ArrayList<ParentModel> pmodel, Money_Pay_Model money, int posi) {
        this.pmodel = pmodel;
        this.context = moneyContext;
        this.posi = posi;
        this.money = money;
    }


    @Override
    public MyParentAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.parentlist,parent,false);
        MyHolder h=new MyHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(MyParentAdapter.MyHolder holder, final int position) {
        Firebase.setAndroidContext(context);
        real_student_count_firebase = new Firebase("https://private-school-85adb.firebaseio.com/");
        holder.t1.setText(pmodel.get(position).getParentName());
        holder.t2.setText(pmodel.get(position).getParentPassword());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent_postion=position;
                addStudentToRealStudent(money,posi,pmodel.get(parent_postion).getParentId(),pmodel.get(parent_postion).getParentPassword());

            }
        });
    }
    private void addStudentToRealStudent(Money_Pay_Model money, int posi, String parentId, String parent_password) {

        Log.i("ADD ::", "WORKED");

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
        alertclose.dismiss();
        CHECK=true;
        //deleteStudentFromMoneyPay(money, posi);
    }
    /*private void deleteStudentFromMoneyPay(final Money_Pay_Model money, final int posi) {
        real_student_count_firebase.child("Money_Pay_Registeration").child(money.getAttendClass()).child(money.getStudenName() + "_" + (money.getStudent_birth().replaceAll("/", "-"))).removeValue();
        if (money_data.size() > 0) {
            money_data.remove(posi);
            notifyDataSetChanged();
        }
    }*/
    @Override
    public int getItemCount() {
        return pmodel.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        public MyHolder(View itemView) {
            super(itemView);
            t1= (TextView) itemView.findViewById(R.id.text_pname);
            t2= (TextView) itemView.findViewById(R.id.text_ppassword);

        }
    }
}
