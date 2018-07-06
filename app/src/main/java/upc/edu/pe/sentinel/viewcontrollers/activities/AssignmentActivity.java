package upc.edu.pe.sentinel.viewcontrollers.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import upc.edu.pe.sentinel.R;
import upc.edu.pe.sentinel.models.Assignment;

public class AssignmentActivity extends AppCompatActivity {
    ViewHolder holder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assignment);




    }

    private class ViewHolder{

        public ViewHolder(View view){

        }

        public void updateViewFrom(Assignment assignment){

        }
    }


}
