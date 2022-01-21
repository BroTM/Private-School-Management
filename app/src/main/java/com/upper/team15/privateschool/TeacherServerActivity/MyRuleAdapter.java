package com.upper.team15.privateschool.TeacherServerActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.upper.team15.privateschool.Model.RuleModel;
import com.upper.team15.privateschool.R;

import java.util.ArrayList;

/**
 * Created by Aspire on 10/20/2017.
 */

class MyRuleAdapter extends RecyclerView.Adapter<MyRuleAdapter.RuleViewHolder>{
    ArrayList<RuleModel> Edata;
    Context context;
    Bitmap eventB;
    public MyRuleAdapter(Context schoolRule, ArrayList<RuleModel> ruleData) {
        this.context=schoolRule;
        Edata=new ArrayList<RuleModel>();
        this.Edata=ruleData;

    }


    @Override
    public RuleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_teacher_rule_cardview,parent,false);
        RuleViewHolder eventholder=new RuleViewHolder(v);
        return eventholder;
    }

    @Override
    public void onBindViewHolder(RuleViewHolder holder, int position) {
        holder.rule.setText(Edata.get(position).getRule());
    }

    @Override
    public int getItemCount() {
        return Edata.size();
    }

    public class RuleViewHolder extends RecyclerView.ViewHolder {
        TextView rule;
        public RuleViewHolder(View itemView) {
            super(itemView);
            rule= (TextView) itemView.findViewById(R.id.rule_Text);
        }
    }
}
