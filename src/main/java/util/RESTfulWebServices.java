package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

import org.apache.http.HttpHost;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;

public class RESTfulWebServices {

	private static final HttpHost HttpHost = null;

	public static HttpResponse<JsonNode> get(String httpRessource){
		Future<HttpResponse<JsonNode>> future = Unirest
				.get(httpRessource)
				.header("accept", "application/json").header("content-type", "application/json")
				.asJsonAsync(new Callback<JsonNode>() {

					public void failed(UnirestException e) {
						System.out.println("The request has failed: \n" + e);
					}

					public void completed(HttpResponse<JsonNode> response) {
						int code = response.getStatus();
						Map<String, List<String>> headers = response.getHeaders();
						JsonNode body = response.getBody();
						InputStream rawBody = response.getRawBody();
						System.out.println("The request has been completed");
						System.out.println(body);
					}

					public void cancelled() {
						System.out.println("The request has been cancelled");
					}
				});
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e1) {
			System.out.println("The request has failed: \n" + e1);
			e1.printStackTrace();
		}
		return null;
	}
	
	public static HttpResponse<JsonNode> put(String httpRessource){
		Future<HttpResponse<JsonNode>> future = Unirest
				.put(httpRessource)
				.header("accept", "application/json").header("content-type", "application/json")
				.asJsonAsync(new Callback<JsonNode>() {

					public void failed(UnirestException e) {
						System.out.println("The request has failed: \n" + e);
					}

					public void completed(HttpResponse<JsonNode> response) {
						int code = response.getStatus();
						Map<String, List<String>> headers = response.getHeaders();
						JsonNode body = response.getBody();
						InputStream rawBody = response.getRawBody();
						System.out.println("The request has been completed");
						System.out.println(body);
					}

					public void cancelled() {
						System.out.println("The request has been cancelled");
					}
				});
		try {
			return future.get();
		} catch (InterruptedException | ExecutionException e1) {
			System.out.println("The request has failed: \n" + e1);
			e1.printStackTrace();
		}
		return null;
	}
	
	public static void main(String[] args) throws UnirestException {
		//System.out.println(get("http://0.0.0.0:4567/games/0/players").getBody());

		try {
			// http://0.0.0.0:4567/games/0/players
			//http://0.0.0.0:4567/games

			String httpRessource = ConfigReader.getSetting("games"+"/0/players");			
			System.out.println(httpRessource);
			//System.out.println(get(httpRessource));
		} catch (IOException e) {
			System.out.println("no find Settingsfile");
			e.printStackTrace();
		}
	}
}
