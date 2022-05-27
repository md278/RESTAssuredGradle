package resources;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.io.*;
import java.util.Properties;

public class Utils {
    static RequestSpecification requestSpecification;

    public RequestSpecification requestSpecification() throws IOException {
        if(requestSpecification == null) {
            PrintStream logs = new PrintStream(new FileOutputStream("logs.txt"));
            requestSpecification = new RequestSpecBuilder()
                    .setBaseUri(getGlobalValue("baseURL"))
                    .addQueryParam("key", "qaclick123")
                    .addFilter(RequestLoggingFilter.logRequestTo(logs))
                    .addFilter(ResponseLoggingFilter.logResponseTo(logs))
                    .setContentType(ContentType.JSON)
                    .build();
            return requestSpecification;
        }
        return requestSpecification;
    }

    public static String getGlobalValue(String key) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream("C:\\RestAssuredFrameworkGradle\\src\\test\\java\\resources\\global.properties");
        properties.load(fileInputStream);
        return properties.getProperty(key);
    }

    public String getJsonPath(Response response, String key){
        String responseString = response.asString();
        System.out.println(responseString);
        JsonPath jsonPath = new JsonPath(responseString);
        return jsonPath.get(key).toString();
    }
}
