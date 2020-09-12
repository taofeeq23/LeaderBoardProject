package com.gads.project.leaderboard;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

//import com.danc.leaderboardfinal.Adapters.SkillsAdapter;
//import com.danc.leaderboardfinal.HourAndSkillsApi;
//import com.danc.leaderboardfinal.Model.SkillIQ;
//import com.danc.leaderboardfinal.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FragmentSkills#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FragmentSkills extends Fragment {
    private static final String TAG = "FragmentSkills";
    private SkillsAdapter adapter;
    Retrofit retrofit;
    RecyclerView recyclerViewSkills;
    HourAndSkillsApi hourAndSkillsApi;

    ProgressBar progressBar;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FragmentSkills() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     //     * @param param1 Parameter 1.
     //     * @param param2 Parameter 2.
     * @return A new instance of fragment FragmentSkills.
     */
    // TODO: Rename and change types and number of parameters
    public static FragmentSkills newInstance() {
        FragmentSkills fragment = new FragmentSkills();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_skills, container, false);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://gadsapi.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        hourAndSkillsApi = retrofit.create(HourAndSkillsApi.class);

        recyclerViewSkills = view.findViewById(R.id.recycler_view_skills);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        adapter = new SkillsAdapter();
        recyclerViewSkills.setLayoutManager(layoutManager);
        setData();
        return view;
    }

    private void setData(){
        Call<List<SkillIQ>> listCall = hourAndSkillsApi.getSkill();
        listCall.enqueue(new Callback<List<SkillIQ>>() {
            @Override
            public void onResponse(Call<List<SkillIQ>> call, Response<List<SkillIQ>> response) {

                if (!response.isSuccessful()){
                    Log.d(TAG, "onResponse: Response Error: " + response.code());
                }

                List<SkillIQ> list = response.body();
                adapter.setData(list);
                recyclerViewSkills.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);

            }

            @Override
            public void onFailure(Call<List<SkillIQ>> call, Throwable t) {
                Log.d(TAG, "onFailure: Error Message: " + t.getMessage());
            }
        });
    }
}