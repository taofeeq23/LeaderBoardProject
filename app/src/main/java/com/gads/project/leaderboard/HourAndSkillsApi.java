package com.gads.project.leaderboard;

//import com.danc.leaderboardfinal.Model.LearningHours;
//import com.danc.leaderboardfinal.Model.SkillIQ;
//import com.danc.leaderboardfinal.Model.SubmitModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface HourAndSkillsApi {

    @GET("api/hours")
    Call<List<LearningHours>> getHours();

    @GET("api/skilliq")
    Call<List<SkillIQ>> getSkill();

    @FormUrlEncoded
    @POST("1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse")
    Call<Void> createPost (
            @Field("entry.1824927963") String EmailAddress,
            @Field("entry.1877115667") String Name,
            @Field("entry.2006916086") String LastName,
            @Field("entry.284483984") String ProjectLink
    );
}