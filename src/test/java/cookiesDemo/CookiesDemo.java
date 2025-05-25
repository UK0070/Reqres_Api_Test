package cookiesDemo;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class CookiesDemo
{
   // @Test(priority = 1)
    void testCokkies()
    {
        given()
                .when()
                .get("https://www.google.co.in/")
                .then()
                //every time cookie info is not same so test(vallidation) should failed
                // (if test is failed means cookie is generated with new value)
                .cookie("AEC","AVh_V2jAPJaKIGr-iEdIj0uK4HYwSg-Nzq4L5G8sdJSejlDgzNnYKdIR6yM")
                .log().all();
    }
    @Test(priority = 2)
    void testCokkiesInfo()
    { //we capture cookies info in res variable
      Response res =  given()
                //getting entire response in varriable
        .when()
                .get("https://www.google.co.in/")
                ;
      //***********************capture single cokkie respone***********(get value of AEC cokkie)
    //  String cookie_value=res.getCookie("AEC");
      //System.out.println("value of cookieis"   +cookie_value);


        //*************************Get all cookies value***********************
        Map<String,String> cokkies_value=res.getCookies();
        //print only all cookies keys info
        System.out.println(cokkies_value.keySet());
        //once know the key then we extract the value of the key
        //Applying for loop and getting all keys of cookies one by one in a variable k
        for(String k : cokkies_value.keySet())
        {
            //getting value of each cokkie by using key
            String cookie_value=cokkies_value.get(k);
            //printing key and value of cokkie
            System.out.println("key of cookie is: " + k + " and value of cookie is: " + cookie_value);
        }
    }
}
// Note: In the above code, we are using Google as an example.
// The cookie values may change over time, so the test may fail if the expected cookie value is not found.
//*****************************so we cannot do validation on cookie value.*****************************