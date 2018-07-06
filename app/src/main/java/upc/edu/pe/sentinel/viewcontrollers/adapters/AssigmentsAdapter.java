package upc.edu.pe.sentinel.viewcontrollers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.androidnetworking.widget.ANImageView;

import java.util.List;

import upc.edu.pe.sentinel.R;
import upc.edu.pe.sentinel.models.Assignment;
import upc.edu.pe.sentinel.viewcontrollers.activities.AssignmentActivity;

public class AssigmentsAdapter extends RecyclerView.Adapter<AssigmentsAdapter.ViewHolder> {

    private List<Assignment> assignments;

    public AssigmentsAdapter(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    @NonNull
    @Override
    public AssigmentsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(
                LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.item_assignment, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull AssigmentsAdapter.ViewHolder holder, int position) {
        Assignment assignment = assignments.get(position);
        holder.updateViewFrom(assignment);
    }

    @Override
    public int getItemCount() {
        return assignments.size();
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public AssigmentsAdapter setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
        return this;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ANImageView pictureImageView;
        private TextView titleTextView;
        private ConstraintLayout assignmentLayout;
        private Assignment assignment;
        public ViewHolder(View itemView) {
            super(itemView);
         /*   pictureImageView = (ANImageView) itemView.findViewById(R.id.pictureImageView);*/
            titleTextView = (TextView) itemView.findViewById(R.id.titleCompanyTextView);
            assignmentLayout = (ConstraintLayout) itemView.findViewById(R.id.assignmentLayout);
        }

        public void updateViewFrom(final Assignment assignment) {
           /* pictureImageView.setDefaultImageResId(R.mipmap.ic_launcher);
            pictureImageView.setErrorImageResId(R.mipmap.ic_launcher);
            pictureImageView.setImageUrl(assignment.getUrlToImage()); */
            titleTextView.setText(assignment.getName());
            this.assignment = assignment;
            assignmentLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Context context = v.getContext();
                    Intent intent = new Intent(context, AssignmentActivity.class);
                    intent.putExtras(assignment.toBundle());
                    context.startActivity(intent);
                }
            });

        }
    }
}
