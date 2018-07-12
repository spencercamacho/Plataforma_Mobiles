package upc.edu.pe.sentinel.viewcontrollers.fragments;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import upc.edu.pe.sentinel.R;
import upc.edu.pe.sentinel.models.Assignment;
import upc.edu.pe.sentinel.network.SentinelApi;
import upc.edu.pe.sentinel.viewcontrollers.adapters.AssigmentsAdapter;

import static android.content.res.Configuration.ORIENTATION_PORTRAIT;

/**
 * A simple {@link Fragment} subclass.
 */
public class AssignmentsFragment extends Fragment {

    RecyclerView        assignmentsRecyclerView;
    LinearLayoutManager   assignmentsLayoutManager;
    AssigmentsAdapter assignmentsAdapter;
    List<Assignment> assignments;

    public AssignmentsFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_assignments, container, false);
        assignmentsRecyclerView = (RecyclerView) view.findViewById(R.id.assignmentsRecyclerView);
        assignments = new ArrayList<>();
        assignmentsAdapter = new AssigmentsAdapter(assignments);
        assignmentsLayoutManager = new LinearLayoutManager(view.getContext());
        assignmentsRecyclerView.setAdapter(assignmentsAdapter);
        assignmentsRecyclerView.setLayoutManager(assignmentsLayoutManager);

        assignmentsRecyclerView.addItemDecoration(
                (new DividerItemDecoration(view.getContext(), DividerItemDecoration.VERTICAL)));

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

        updateData();
    }

    private void updateData() {
        final ProgressDialog progressDialog = new ProgressDialog(getContext());
        progressDialog.setMessage("Cargando");
        progressDialog.show();

        String url_api = SentinelApi.getAssigmentsUrl();
        Log.d("Sentinel-Pietro", url_api);
        AndroidNetworking.get(SentinelApi.getAssigmentsUrl())
                .addPathParameter("employeeId", "1")
                .setPriority(Priority.LOW)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (isAdded()) {
                            progressDialog.dismiss();
                        }
                        try {
                            if("error".equalsIgnoreCase(response.getString("status"))) {
                                Log.d("Sentinel", response.getString("message"));
                                return;
                            }
                            Log.d("Sentinel", String.valueOf(assignments.size()));
                            assignments = Assignment.Builder.from(response.getJSONArray("data")).buildAll();
                            assignmentsAdapter.setAssignments(assignments);
                            assignmentsAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            Log.d("Sentinel", e.getLocalizedMessage());
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError anError) {
                        if (isAdded()) {
                            progressDialog.dismiss();
                        }
                        Log.d("Sentinel", anError.getLocalizedMessage());
                    }
                });
    }
}
