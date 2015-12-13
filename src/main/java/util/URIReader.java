package util;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Future;

import com.google.gson.Gson;
import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.ObjectMapper;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.async.Callback;
import com.mashape.unirest.http.exceptions.UnirestException;
import com.mashape.unirest.request.GetRequest;

import Game.model.Game;

public class URIReader {

	public static void main(String[] args) throws UnirestException {

		// {"gameID":"0","players":[{"playerID":"1","name":"1","uri":"http://localhost:4567/player/1","position":0,"ready":false},{"playerID":"2","name":"2","uri":"http://localhost:4567/player/2","position":0,"ready":false}],"started":false}

		/*GetRequest response = Unirest.get("https://vs-docker.informatik.haw-hamburg.de/ports/14470/games/0");

		System.out.println(response);
		System.out.println(response);

		System.out.println(response.getUrl());
		System.out.println(response.getBody());
		System.out.println(response.getClass());
		System.out.println(response.getHeaders());
		System.out.println(response.getHttpMethod());
		System.out.println(response.getHttpMethod());*/
		
		

		  Future<HttpResponse<JsonNode>> future = Unirest.get("http://0.0.0.0:4567/games")
				  .header("accept", "application/json")
				  .asJsonAsync(new Callback<JsonNode>() {

				    public void failed(UnirestException e) {
				        System.out.println("The request has failed");
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
		  
	}
}
