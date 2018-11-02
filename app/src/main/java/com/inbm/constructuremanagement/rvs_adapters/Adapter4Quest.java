package com.inbm.constructuremanagement.rvs_adapters;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.inbm.constructuremanagement.R;
import com.inbm.constructuremanagement.ReportActivity;
import com.inbm.constructuremanagement.inbm.recycler.AbsAdapter4Header;
import com.inbm.constructuremanagement.inbm.recycler.AbsViewHolder;

import java.util.ArrayList;

public class Adapter4Quest extends AbsAdapter4Header {
    ArrayList<_questions_> questions;
    ArrayList<_answers_> answers;
    _report_ report;
    String radiovalue;

    public Adapter4Quest(Context context, OnClicked clicklistener) {
        super(context, clicklistener);
    }

    public Adapter4Quest(Context context, OnClicked onClicked, ArrayList<_questions_> questions) {
        super(context);
        this.questions = questions;
    }

    public Adapter4Quest(Context context, ArrayList<_questions_> questions) {
        super(context);
        this.questions = questions;
    }

    @Override
    protected int getLayout() {
        return R.layout.row_4_report;
    }

    @Override
    protected int getHeadLayout() {
        return 0;
    }

    @Override
    protected AbsViewHolder getViewHolder(View v) {
        return new VH4Report(v);
    }

    @Override
    protected void display(AbsViewHolder holder, int position) {
        VH4Report vh = (VH4Report) holder;
        vh.tv_quest_title.setText(questions.get(position).quest);
        vh.rb_quest1.setText(questions.get(position).answers.get(0).toString());
        vh.rb_quest2.setText(questions.get(position).answers.get(1).toString());
        vh.rb_quest3.setText(questions.get(position).answers.get(2).toString());
        vh.rb_quest4.setText(questions.get(position).answers.get(3).toString());

        vh.rg_quest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                radiovalue = ((RadioButton) vh.rg_quest.findViewById(vh.rg_quest.getCheckedRadioButtonId())).getText().toString();
                questions.get(position).answer = radiovalue;
            }
        });
    }

    @Override
    protected int getCount() {
        return questions.size();
    }

    private class VH4Report extends AbsViewHolder {

        TextView tv_quest_title;
        RadioGroup rg_quest;
        RadioButton rb_quest1, rb_quest2,rb_quest3,rb_quest4;

        public VH4Report(View itemView) {
            super(itemView);
            tv_quest_title = itemView.findViewById(R.id.tv_quest_title);
            rg_quest = itemView.findViewById(R.id.rg_quests);
            rb_quest1 = itemView.findViewById(R.id.rb_quest1);
            rb_quest2 = itemView.findViewById(R.id.rb_quest2);
            rb_quest3 = itemView.findViewById(R.id.rb_quest3);
            rb_quest4 = itemView.findViewById(R.id.rb_quest4);

            rg_quest.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                }
            });
        }

        @Override
        protected void ui4FocusIn(View v, AbsViewHolder holder) {

        }

        @Override
        protected void ui4FocusOut(View v, AbsViewHolder holder) {

        }
    }
//
}
