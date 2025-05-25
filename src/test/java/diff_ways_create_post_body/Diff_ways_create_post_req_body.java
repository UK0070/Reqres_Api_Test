package diff_ways_create_post_body;
import com.google.gson.JsonObject;
import netscape.javascript.JSException;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;


/*DIFFERENT WAYS TO CREATE POST REQUEST BODY
1. Post request body using HashMap
2. Post request body using org,Json
3. Post request body using POJO Class
4. Post request body using external json file data*/

@Slf4j
public class Diff_ways_create_post_req_body
{
//********************************** 1. USING HASHMAP***************************
// @Test(priority = 1)
    void testUsingHashMap()
 {
     HashMap data = new HashMap();
     data.put("name","jasmd");
     data.put("job","qa");
                                       /*if field is of array type
                                        "course":[
                                                "c#"
                                                 "c"
                                                  ]
                                                   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                                                     String courseArr[]={"c","c#"}
                                                     data.put("course","courseArr[]")
                                                     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$*/
     given()
             .contentType("application/json")
             .body(data)
           // FOR API KEY AUTHENTICATION
             .header("x-api-key", "reqres-free-v1")
             .when()
             .post("https://reqres.in/api/users")
             .then()
             .statusCode(201)
             //if complex json we have to specifiy json path here for fields(verify reponse body)
             .body("name",equalTo("jasmd"))
             .body("job",equalTo("qa"))
             //verify header
             .header("Content-Type","application/json; charset=utf-8")
             .log().all();
                                            /*
                                            * .body("course[0]",equalTo("c#"))
                                            * .body("course[1]",equalTo("c"))
                                            */
 }
    //********************************** 2. org.json library***************************
  // @Test(priority = 1)
    void testUsingJsonLibrary()
    {
        JSONObject data = new JSONObject();
        data.put("name","jasmd");
        data.put("job","qa");

                                       /*if field is of array type
                                        "course":[
                                                "c#"
                                                 "c"
                                                  ]
                                                   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                                                     String courseArr[]={"c","c#"}
                                                     data.put("course","courseArr[]")
                                                     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$*/
        given()
                .contentType("application/json")
                .body(data.toString())//passing data as part of request by converting it to string format
                // FOR API KEY AUTHENTICATION
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                //if complex json we have to specifiy json path here for fields(verify reponse body)
                .body("name",equalTo("jasmd"))
                .body("job",equalTo("qa"))
                //verify header
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
        /*
         * .body("course[0]",equalTo("c#"))
         * .body("course[1]",equalTo("c"))
         */
    }
 //@Test(priority = 2)
 //void testDelete()
 {
     given()
             // FOR API KEY AUTHENTICATION
             .header("x-api-key", "reqres-free-v1")
             .when()
             .delete("https://reqres.in/api/users/")
             .then()
             .statusCode(204);
 }
    //********************************** 3.USING POJO CLASS***************************
    //first create seperate pojo class
  // @Test(priority = 1)
    void testUsingPOJOCLASS()
    {
        Pojo_PostRequest data = new Pojo_PostRequest();
        //call the setname(setter)and assign value to name varriable
        data.setName("john");
        data.setJob("qa");
                                       /*
                                                   $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$
                                                     String courseArr[]={"c","c#"}
                                                     data.setcourses("courseArr")
                                                     $$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$*/
        given()
                .contentType("application/json")
                .body(data)
                // FOR API KEY AUTHENTICATION
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                //if complex json we have to specifiy json path here for fields(verify reponse body)
                .body("name",equalTo("john"))
                .body("job",equalTo("qa"))
                //verify header
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
        /*
         * .body("course[0]",equalTo("c#"))
         * .body("course[1]",equalTo("c"))
         */
    }
  //  @Test(priority = 2)
//    void testDelete()
    {
        given()
                // FOR API KEY AUTHENTICATION
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/")
                .then()
                .statusCode(204);
    }
    //********************************** 4. USING EXTERNAL JSON FILE***************************
    //Vreate Body.json file to inject data
  @Test(priority = 1)
    void testpostUsingExternalFile() throws FileNotFoundException {
       //open file
        File f =new File(".\\Body.json");   //.// represemt cureent project location
        //REad data from file
        FileReader fr = new FileReader(f);
        //using jsontokener we get the data in json format
       JSONTokener jt = new JSONTokener(fr);
       //extract data in json object format
       JSONObject data = new JSONObject(jt);


        given()
                .contentType("application/json")
                .body(data.toString())  //convert data to string format
                // FOR API KEY AUTHENTICATION
                .header("x-api-key", "reqres-free-v1")
                .when()
                .post("https://reqres.in/api/users")
                .then()
                .statusCode(201)
                //if complex json we have to specifiy json path here for fields(verify reponse body)
                .body("name",equalTo("Ram"))
                .body("job",equalTo("leader"))
                //verify header
                .header("Content-Type","application/json; charset=utf-8")
                .log().all();
        /*
         * .body("course[0]",equalTo("c#"))
         * .body("course[1]",equalTo("c"))
         */
    }
   @Test(priority = 2)
    void testDelete()
    {
        given()
                // FOR API KEY AUTHENTICATION
                .header("x-api-key", "reqres-free-v1")
                .when()
                .delete("https://reqres.in/api/users/")
                .then()
                .statusCode(204);
    }
}
