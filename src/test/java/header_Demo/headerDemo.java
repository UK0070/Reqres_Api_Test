package header_Demo;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Test;

import static io.restassured.matcher.RestAssuredMatchers.*;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
//capture header imfo(focus on content type,content encoding,server))
//most of the time header content is same ,so we can vallidate them only
@Slf4j
public class headerDemo {
    @Test(priority = 1)
    void testCokkies() {
        given()
                .when()
                .get("https://www.google.co.in/")
                .then()
                //verify header content correct or not
                .header("Content-Type", "text/html; charset=ISO-8859-1")//vallidating content type from header
                .header("Content-Encoding", "gzip")//validating content encoding from header
                .header("Server", "gws")//validating server from header
                //$$$$$$$$$$$$$$$$$$$    LOGGING     $$$$$$$$$$$$$$$$$$$$$$$$$
                .log().all()//---------->print all  info on console
                .log().body()//---------->print all response info on console
                .log().headers()//---------->print headers info on console
                .log().cookies();//---------->print all cookies info on console
    }
//********************captur  information from header using Response interface varriable******************
    @Test(priority = 2)
    void testHeaderInfo() {
        //capture entire response in Response in Response interface varriable
        Response res = given()

                .when()
                .get("https://www.google.co.in/");
        //HERE FOR VALLIDATION WE DONOT USE tHEN() HERE WE USE TESTNG
        //capture SINGLE HEADER value using Response interface varriable and store in string varriable****************************
        String header_value= res.getHeader("Content-Type");
        System.out.println("value of content type"+header_value);
        //capture ALL HEADER value using Response interface varriable and store in Header class reference varriable
        Headers headers_value = res.getHeaders();//this is not hashmap but store data in key and value pair
        //Extracting each header data from Headers varriable to Header variable one by one using enhance for loop
        for (Header hd:headers_value)//single header key-value pair is OF HEADER type
        {                      //extracting name & value of header using hd (Header) varriable and print
            //&&&&&&&&&&&&&&&&&&&&&&&&This can be done by .log().all()$$$$$$$$$$$$$$$$$$4
            System.out.println(hd.getName()+"     "+hd.getValue());
        }
    }
}