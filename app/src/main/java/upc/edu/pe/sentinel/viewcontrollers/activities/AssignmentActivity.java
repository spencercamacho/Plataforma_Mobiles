package upc.edu.pe.sentinel.viewcontrollers.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import upc.edu.pe.sentinel.R;
import upc.edu.pe.sentinel.models.Assignment;

public class AssignmentActivity extends AppCompatActivity {
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
        Assignment assignment = Assignment.Builder.from(intent.getExtras()).build();
        holder.updateViewFrom(assignment);
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


}
