package specifications;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;

public class Specifications {
    public static RequestSpecification petstoreSpec() {
        return new RequestSpecBuilder()
                .setBaseUri("https://petstore.swagger.io/v2")
                .setContentType("application/json")
                .log(LogDetail.ALL)
                .build();
    }
}