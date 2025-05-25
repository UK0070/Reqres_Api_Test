package reqres_api;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import java.lang.ref.PhantomReference;
import java.util.HashMap;
import java.util.PriorityQueue;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
@Slf4j
public class HttpRequest
{// making id global varriable
    int id;
    //Here we use testng style annotations
    @Test(priority = 1)
void getUsers()
{
                given()
                .when()
                .get("https://reqres.in/api/users?page=2")
                .then()
                .statusCode(200)
                .body("page", equalTo(2))
                        .log().all();
}
@Test(priority = 2)
void createUser()
{
    HashMap data= new HashMap();
    data.put("name", "balu");
    data.put("job", "leader");
 //GETTING THE RETURNED ID
  id=given()
            .contentType("application/json")
            .body(data)
          //FOR ATHENTICATION PASSING API KEY
            .header("x-api-key", "reqres-free-v1")

            .when()
            .post("https://reqres.in/api/users")
          //id capture from response by jsonpath
          .jsonPath().getInt("id") //RETURN ID to given(
           // .then()
         //   .statusCode(201)
         //  .log().all()
    ;

}
//id used for updating the request(CHAINING DONE HERE)
    @Test(priority = 3,dependsOnMethods = {"createUser"})
                        //before exicuting update user check create user pass or not by dependsOnMethods
    void updateUser()
    {
        HashMap data= new HashMap();
        data.put("name", "balu");
        data.put("job", "leader");
        //GETTING THE RETURNED ID
        given()
                .contentType("application/json")
                .body(data)
                //FOR ATHENTICATION PASSING API KEY
                .header("x-api-key", "reqres-free-v1")

                .when()
                .put("https://reqres.in/api/users/"+id)//id get dynamically after post request

         .then()
          .statusCode(200)
         .log().all()
        ;
    }
    @Test(priority = 4)
    void  deleteUser()
    {
        given()                       //NO REQUEST PAYLOAD
                .when()
                .put("https://reqres.in/api/users/"+id)//id get dynamically after post request
                .then()
                .statusCode(204)
                .log().all();//NO BODY SO LOG HEADER , COOKIES ETC INFORMATION
       //***********************HERE WE CANNOT UNSET VARRIABLE LIKE POSTMAN*******************
    }
}
