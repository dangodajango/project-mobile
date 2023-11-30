package fmi.plovdiv.application;

import static java.nio.charset.StandardCharsets.UTF_8;

import android.os.StrictMode;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class RestClient {

    static {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
    }

    private static final String BASE_PATH = "http://192.168.165.31:8080";

    private static final String HTTP_POST_METHOD = "POST";

    private static final String HTTP_GET_METHOD = "GET";

    private static final String HTTP_DELETE_METHOD = "DELETE";

    public static void post(String path, JSONObject requestBody) {
        try {
            URL url = new URL(String.format("%s/%s", BASE_PATH, path));
            HttpURLConnection urlConnection = setupHttpUrlConnectionInputOutput(url, HTTP_POST_METHOD);
            sendRequest(urlConnection, requestBody);
            readResponse(urlConnection);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public static String get(String path, String query) {
        try {
            URL url = new URL(String.format("%s/%s?%s", BASE_PATH, path, query));
            HttpURLConnection urlConnection = setupHttpUrlConnectionInput(url, HTTP_GET_METHOD);
            return readResponse(urlConnection);
        } catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
    }

    public static String delete(String path, String query) {
        try {
            URL url = new URL(String.format("%s/%s?%s", BASE_PATH, path, query));
            HttpURLConnection urlConnection = setupHttpUrlConnectionInput(url, HTTP_DELETE_METHOD);
            return readResponse(urlConnection);
        } catch (Exception exception) {
            exception.printStackTrace();
            return "";
        }
    }

    private static HttpURLConnection setupHttpUrlConnectionInputOutput(URL url, String httpMethod) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(httpMethod);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setDoOutput(true);
        urlConnection.setDoInput(true);
        return urlConnection;
    }

    private static HttpURLConnection setupHttpUrlConnectionInput(URL url, String httpMethod) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestMethod(httpMethod);
        urlConnection.setRequestProperty("Content-Type", "application/json");
        urlConnection.setDoInput(true);
        return urlConnection;
    }

    private static void sendRequest(HttpURLConnection urlConnection, JSONObject requestBody) throws IOException {
        try (OutputStream outputStream = urlConnection.getOutputStream()) {
            byte[] input = requestBody.toString().getBytes(UTF_8);
            outputStream.write(input, 0, input.length);
        }
    }

    private static String readResponse(HttpURLConnection urlConnection) throws IOException {
        try {
            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            return convertStreamToString(in);
        } finally {
            urlConnection.disconnect();
        }
    }

    private static String convertStreamToString(InputStream is) {
        Scanner scanner = new Scanner(is).useDelimiter("\\A");
        return scanner.hasNext() ? scanner.next() : "";
    }
}
