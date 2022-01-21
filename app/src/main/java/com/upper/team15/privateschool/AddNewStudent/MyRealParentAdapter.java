package com.upper.team15.privateschool.AddNewStudent;

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
import com.upper.team15.privateschool.Model.RegistrationModel;
import com.upper.team15.privateschool.Model.StudentModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

import static com.upper.team15.privateschool.AddNewStudent.AddNewStudentActivity.alert_dialog_colse_and_open;
import static com.upper.team15.privateschool.AddNewStudent.AddNewStudentActivity.registerFb;

/**
 * Created by Aspire on 11/17/2017.
 */

class MyRealParentAdapter extends RecyclerView.Adapter<MyRealParentAdapter.MyHolder> {
    ArrayList<ParentModel> pmodel=new ArrayList<ParentModel>();
    Context context;
    Firebase real_student_count_firebase;
    static int parent_postion;
    ArrayList<RegistrationModel> registration_data=new ArrayList<RegistrationModel>();
    RegistrationModel rModel;
    int posi;
    static Boolean CHECK=false;
    String parentId;
    String parentPassword;

    public MyRealParentAdapter(Context moneyContext, ArrayList<ParentModel> pmodel,RegistrationModel money) {
        this.pmodel = pmodel;
        this.context = moneyContext;
        this.rModel = money;
    }


    @Override
    public MyRealParentAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.real_parentlist,parent,false);
        MyHolder h=new MyHolder(v);
        return h;
    }

    @Override
    public void onBindViewHolder(MyRealParentAdapter.MyHolder holder, final int position) {
        holder.t1.setText(pmodel.get(position).getParentName());
        holder.t2.setText(pmodel.get(position).getParentPassword());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parent_postion=position;
                /*StudentModel money_pay_model = new StudentModel();
                money_pay_model.setAttendClass(registration_data.get(position).getAttendClass());
                money_pay_model.setAttendForm(registration_data.get(position).getAttendForm());
                money_pay_model.setFathername(registration_data.get(position).getFathername());
                money_pay_model.setFatherNRCNO(registration_data.get(position).getFatherNRCNO());
                money_pay_model.setMothername(registration_data.get(position).getMothername());
                money_pay_model.setMotherNRCNO(registration_data.get(position).getMotherNRCNO());
                money_pay_model.setStudenName(registration_data.get(position).getStudenName());
                money_pay_model.setTrnsferForm(registration_data.get(position).getTrnsferForm());
                money_pay_model.setStudent_birth(registration_data.get(position).getStudent_birth());
                money_pay_model.setParentId(pmodel.get(position).getParentId());
                money_pay_model.setParentPassword(pmodel.get(position).getParentPassword());
                money_pay_model.setLicenseImage(registration_data.get(position).getLicenseImage());
                money_pay_model.setPassedClass(registration_data.get(position).getPassedClass());
                money_pay_model.setStudentaddress(registration_data.get(position).getStudentaddress());
                money_pay_model.setUsername(registration_data.get(position).getStudenName() + "_" + (registration_data.get(position).getStudent_birth().replaceAll("/", "-")));
                registerFb.child("Real_Student").child(registration_data.get(position).getAttendClass()).child(registration_data.get(position).getStudenName() + "_" + (registration_data.get(position).getStudent_birth().replaceAll("/", "-"))).setValue(money_pay_model);*/
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
                money_pay_model.setParentId(pmodel.get(position).getParentId());
                money_pay_model.setParentPassword(pmodel.get(position).getParentPassword());
                money_pay_model.setLicenseImage(rModel.getLicenseImage());
                money_pay_model.setPassedClass(rModel.getPassedClass());
                money_pay_model.setStudentaddress(rModel.getStudentaddress());
                money_pay_model.setUsername(rModel.getStudenName() + "_" + (rModel.getStudent_birth().replaceAll("/", "-")));
       /* money_pay_model.setPassword(password);*/
                registerFb.child("Real_Student").child(rModel.getAttendClass()).child(rModel.getStudenName() + "_" + (rModel.getStudent_birth().replaceAll("/", "-"))).setValue(money_pay_model);
                // deleteStudentFromMoneyPay();








                alert_dialog_colse_and_open.dismiss();
                CHECK=true;

            }
        });
    }
    private void addStudentToRealStudent(RegistrationModel money, int posi, String parentId, String parent_password) {

        Log.i("ADD ::", "WORKED");

    }
    @Override
    public int getItemCount() {
        return pmodel.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView t1,t2;
        public MyHolder(View itemView) {
            super(itemView);
            t1= (TextView) itemView.findViewById(R.id.text_next_pname);
            t2= (TextView) itemView.findViewById(R.id.text_next_ppassword);

        }
    }
}
