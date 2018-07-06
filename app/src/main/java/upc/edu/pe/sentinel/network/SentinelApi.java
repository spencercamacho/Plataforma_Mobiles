package upc.edu.pe.sentinel.network;

public class SentinelApi {
    private static String BASE_URL = "";

    public static String verifyUserUrl() {
        return BASE_URL + "/auth";
    }

    public static String getAssigmentsUrl() {
        return BASE_URL + "/assigments";
    }

}
