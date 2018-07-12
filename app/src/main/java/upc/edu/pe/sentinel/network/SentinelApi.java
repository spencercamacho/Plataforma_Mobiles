package upc.edu.pe.sentinel.network;

public class SentinelApi {
    //private static String BASE_URL = "http://127.0.0.1:3000";
    private static String BASE_URL = "http://178.128.155.0/apiv1/public";

    public static String verifyUserUrl() {
        return BASE_URL + "/auth";
    }

    /*
    example:
    https://fierce-cove-29863.herokuapp.com/getAllUsers/{pageNumber}
     */
    public static String getAssigmentsUrl() {
        return BASE_URL + "/assignments/{employeeId}";
    }

    public static String changeState() {
        return BASE_URL + "/assignments/{id}";
    }

}
