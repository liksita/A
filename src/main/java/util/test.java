package util;

import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class test {

	public static void main(String[] args) throws UnirestException {

		test("http://localhost:4567/dice");
		//test("http://vs-docker.informatik.haw-hamburg.de/ports/14471/dice");
	}

	public static void test(String uri) throws UnirestException {
		HttpResponse<JsonNode> response = Unirest.get(uri).asJson();
		
		JsonNode node = response.getBody();
		System.out.println(node);
		
		// retrieve the parsed JSONObject from the response
		JSONObject myObj = response.getBody().getObject();

		System.out.println(myObj.get("roll1"));
		System.out.println(myObj.get("roll2"));
		System.out.println(((JSONObject) myObj.get("roll1")).get("nummer"));
		System.out.println(((JSONObject) myObj.get("roll2")).get("nummer"));
		
		
	}
}
