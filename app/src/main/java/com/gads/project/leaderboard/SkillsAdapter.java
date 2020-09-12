package com.gads.project.leaderboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//import com.danc.leaderboardfinal.HourAndSkillsApi;
//import com.danc.leaderboardfinal.Model.LearningHours;
//import com.danc.leaderboardfinal.Model.SkillIQ;
//import com.danc.leaderboardfinal.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SkillsAdapter extends RecyclerView.Adapter<SkillsAdapter.MySkillViewHolder>  {

    private static final String TAG = "SkillsAdapter";
    private List<SkillIQ> skillIQS1 = new ArrayList<>();

    public SkillsAdapter(){

    }

    public void setData(List<SkillIQ> skillIQS){
        this.skillIQS1 = skillIQS;
    }

    @NonNull
    @Override
    public MySkillViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.skills_item, parent, false);

        return new MySkillViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MySkillViewHolder holder, int position) {
        SkillIQ skillIQ = skillIQS1.get(position);
        holder.Bind(skillIQ);
        Picasso.get().load(skillIQ.getImageUrl()).into(holder.hoursImageBadge);
    }

    @Override
    public int getItemCount() {
        return skillIQS1.size();
    }

    public class MySkillViewHolder extends RecyclerView.ViewHolder{
        ImageView hoursImageBadge;
        TextView txtName;
        TextView txtScore;
        final TextView txtCountry;

        public MySkillViewHolder(@NonNull View itemView) {
            super(itemView);

            hoursImageBadge = itemView.findViewById(R.id.imageBadge);
            txtName = itemView.findViewById(R.id.txtName);
            txtScore = itemView.findViewById(R.id.txtScore);
            txtCountry = itemView.findViewById(R.id.txtCountry);
        }

        public void Bind(SkillIQ skillIQ){
            txtName.setText(skillIQ.getName());
            txtScore.setText(skillIQ.getScore());
            txtCountry.setText(skillIQ.getCountry() + ".");
        }
    }
}