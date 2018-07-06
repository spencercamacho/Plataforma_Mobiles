package upc.edu.pe.sentinel.models;


import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Assignment {

    /*
    {"id":"c1dfd96eea8cc2b62785275bca38ac261256e278",
    "name":"Jhonson",
    "schedule_":"2018-07-02T08:13:43.000Z",
    "serviceDescription":"Verihhh asdasdh66",
    "latitude":"",
    "longitude":"",
    "stateDescription":"Pendiente",
    "stateId":0}
     */

    private String id;
    private String name;
    private String schedule;
    private String serviceDescription;
    private String latitude;
    private String longitude;
    private String stateId;
    private String stateDescription;

    public Assignment(String id, String name, String schedule, String serviceDescription, String latitude, String longitude, String stateId, String stateDescription) {
        this.id = id;
        this.name = name;
        this.schedule = schedule;
        this.serviceDescription = serviceDescription;
        this.latitude = latitude;
        this.longitude = longitude;
        this.stateId = stateId;
        this.stateDescription = stateDescription;
    }

    public Assignment() {
    }

    public String getId() {
        return id;
    }

    public Assignment setId(String id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Assignment setName(String name) {
        this.name = name;
        return this;
    }

    public String getSchedule() {
        return schedule;
    }

    public Assignment setSchedule(String schedule) {
        this.schedule = schedule;
        return this;
    }

    public String getServiceDescription() {
        return serviceDescription;
    }

    public Assignment setServiceDescription(String serviceDescription) {
        this.serviceDescription = serviceDescription;
        return this;
    }

    public String getLatitude() {
        return latitude;
    }

    public Assignment setLatitude(String latitude) {
        this.latitude = latitude;
        return this;
    }

    public String getLongitude() {
        return longitude;
    }

    public Assignment setLongitude(String longitude) {
        this.longitude = longitude;
        return this;
    }

    public String getStateId() {
        return stateId;
    }

    public Assignment setStateId(String stateId) {
        this.stateId = stateId;
        return this;
    }

    public String getStateDescription() {
        return stateDescription;
    }

    public Assignment setStateDescription(String stateDescription) {
        this.stateDescription = stateDescription;
        return this;
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        bundle.putString("id", this.getId());
        bundle.putString("name", this.getName());
        bundle.putString("schedule", this.getSchedule());
        bundle.putString("serviceDescription", this.getServiceDescription());
        bundle.putString("latitude", this.getLatitude());
        bundle.putString("longitude", this.getLongitude());
        bundle.putString("stateId", this.getStateId());
        bundle.putString("stateDescription", this.getStateDescription());
        return bundle;
    }

    public static class Builder{
        private Assignment assignment;
        private List<Assignment> assignments;

        public Builder(Assignment assignment){ this.assignment = assignment;}

        public Builder(List<Assignment> assignments) {
            this.assignments = assignments;
        }

        public static Builder from(Bundle bundle){
            Assignment assignment = new Assignment();
            assignment.setId(bundle.getString("id"))
                    .setName(bundle.getString("name"))
                    .setSchedule(bundle.getString("schedule"))
                    .setServiceDescription(bundle.getString("serviceDescription"))
                    .setLatitude(bundle.getString("latitude"))
                    .setLongitude(bundle.getString("longitude"))
                    .setStateId(bundle.getString("stateId"))
                    .setStateDescription(bundle.getString("stateDescription"));
                return new Builder(assignment);
        }

        public static Builder from(JSONObject jsonArticle) {
            Assignment assignment = new Assignment();
            try {
                assignment.setId(jsonArticle.getString("id"))
                        .setName(jsonArticle.getString("name"))
                        .setSchedule(jsonArticle.getString("schedule"))
                        .setServiceDescription(jsonArticle.getString("serviceDescription"))
                        .setLatitude(jsonArticle.getString("latitude"))
                        .setLongitude(jsonArticle.getString("longitude"))
                        .setStateId(jsonArticle.getString("stateId"))
                        .setStateDescription(jsonArticle.getString("stateDescription"));
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new Builder(assignment);
        }

        public static Builder from(JSONArray jsonAssignments) {
            List<Assignment> assignments = new ArrayList<>();
            int length = jsonAssignments.length();
            for(int i = 0; i < length; i++) {
                try {
                    assignments.add(
                            Builder.from(
                                    jsonAssignments.getJSONObject(i)).build());
                } catch (JSONException e) {
                    Log.d("Sentinel", e.getLocalizedMessage());
                }
            }
            return new Builder(assignments);
        }

        public Assignment build() {
            return this.assignment;
        }

        public List<Assignment> buildAll() {
            return this.assignments;
        }

    }
}
