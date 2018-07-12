package upc.edu.pe.sentinel.viewcontrollers.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.widget.ANImageView;

import org.json.JSONException;
import org.json.JSONObject;

import upc.edu.pe.sentinel.R;
import upc.edu.pe.sentinel.models.Assignment;
import upc.edu.pe.sentinel.network.SentinelApi;

public class AssignmentActivity extends AppCompatActivity {

    private Button mAssignmentStateButton;
    private Button mShowRouteButton;

    ViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        holder = new ViewHolder(findViewById(android.R.id.content));
        Intent intent = getIntent();
        if(intent == null) return;
        final Assignment assignment = Assignment.Builder.from(intent.getExtras()).build();
        holder.updateViewFrom(assignment);

        mAssignmentStateButton = findViewById(R.id.assignment_state);
        mShowRouteButton = findViewById(R.id.assignment_show_route);

        String stateId = assignment.getStateId();

        updateStateButton(stateId);

        if (mAssignmentStateButton.isEnabled()) {
            mAssignmentStateButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    final ProgressDialog progressDialog = new ProgressDialog(AssignmentActivity.this);
                    progressDialog.setMessage("Cargando");
                    progressDialog.show();

                    JSONObject params = new JSONObject();
                    try {
                        params.put("state_", "1");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                    AndroidNetworking.put(SentinelApi.changeState())
                            .addPathParameter("id", assignment.getId())
                            .addBodyParameter("state_", "1")
                            .addJSONObjectBody(params)
                            .setPriority(Priority.LOW)
                            .build()
                            .getAsJSONObject(new JSONObjectRequestListener() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    progressDialog.dismiss();
                                    updateStateButton("1");
                                    Log.d("Success response", response != null ? response.toString() : "(null)");
                                }

                                @Override
                                public void onError(ANError anError) {
                                    progressDialog.dismiss();
                                    Toast.makeText(AssignmentActivity.this, "Error al asignar, inténtelo más tarde", Toast.LENGTH_SHORT).show();
                                    Log.d("Update Error", anError.getLocalizedMessage());
                                }
                            });
                }
            });
        }

        mShowRouteButton.setText("Mostrar Ruta");

        mShowRouteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String latitude = "-11.9238487";
                String longitude = "-77.1718023";

                Uri uri = Uri.parse("google.navigation:q=" + latitude + "," + longitude + "&mode=c");

                try {
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    intent.setPackage("com.google.android.apps.maps");
                    startActivity(intent);
                } catch (Exception exception) {
                    exception.printStackTrace();
                    Log.d("AssignmentActivity",
                            "Catch generic exception since it's possible that the user do not have maps installed or something like that");
                }
            }
        });
    }

    private void updateStateButton(String state) {
        if (state != null && state.equals("1")) {
            mAssignmentStateButton.setText("Atendido");
            mAssignmentStateButton.setEnabled(false);
            mAssignmentStateButton.setBackgroundColor(ContextCompat.getColor(this, android.R.color.darker_gray));
            holder.assignmentStateTextView.setText("Atendido");
        } else {
            mAssignmentStateButton.setText("Atender");
            mAssignmentStateButton.setEnabled(true);
            mAssignmentStateButton.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary));
            holder.assignmentStateTextView.setText("Pendiente");
        }
    }

    private class ViewHolder {
        private TextView assignmentCompanyTextView;
        private TextView assignmentStateTextView;

        public ViewHolder(View view) {
            assignmentCompanyTextView = (TextView) view.findViewById(R.id.assignmentCompanyTextView);
            assignmentStateTextView = (TextView) view.findViewById(R.id.assignmentStateTextView);
        }

        public void updateViewFrom(Assignment assignment) {
            // TODO: Update widgets
            assignmentCompanyTextView.setText(assignment.getName());
            assignmentStateTextView.setText(assignment.getStateDescription());
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
