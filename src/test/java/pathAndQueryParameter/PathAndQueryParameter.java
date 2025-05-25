package pathAndQueryParameter;
import org.testng.annotations.Test;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PathAndQueryParameter
{
                                //https://reqres.in/api/users?page=2
                                                    //path    query
    @Test()
    void testQueryAndPathParams()
    {//path parameter and query parameter specify in given() as prerequisits
        // then pass them along with request inside when()
                 given()// specify path parameters(here we create varriable mypath)
                         .pathParams("mypath","users")
                         //specify query parameter(here we not create varriable we jus give exact key from URI)
                         .queryParams("page","2")
                .when()      //here we only path parameter in URI (bcz varriable created only for it)
                         // query parameter go along with request that we already specified so this part remove
                         .get("https://reqres.in/api/{mypath}")
                .then()
                         .statusCode(200)
                         .log().all();
    }
}
