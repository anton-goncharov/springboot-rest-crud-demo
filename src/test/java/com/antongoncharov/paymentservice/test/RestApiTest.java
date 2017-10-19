package com.antongoncharov.paymentservice.test;

import io.restassured.response.Response;
import org.apache.http.entity.ContentType;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * This test suite runs REST API checks on test H2 database in spring boot environment
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class RestApiTest {

    public static final String V1_PAYMENTS = "v1/payments";

    @LocalServerPort
    private int port;

    public static int numPaymentsCreated;

    @Test
    public void canCreatePayment() {
        // insert a payment with non-empty fields and embedded objects
        Response response =
                given().
                    port(port).
                    basePath(V1_PAYMENTS).
                    contentType(ContentType.APPLICATION_JSON.getMimeType()).
                    body("{\n" +
                            "      \"attributes\": {\n" +
                            "        \"amount\": \"100.21\",\n" +
                            "        \"beneficiary_party\": {\n" +
                            "          \"account_name\": \"W Owens\",\n" +
                            "          \"account_number\": \"31926819\",\n" +
                            "          \"account_number_code\": \"BBAN\",\n" +
                            "          \"account_type\": 0,\n" +
                            "          \"address\": \"1 The Beneficiary Localtown SE2\",\n" +
                            "          \"bank_id\": \"403000\",\n" +
                            "          \"bank_id_code\": \"GBDSC\",\n" +
                            "          \"name\": \"Wilfred Jeremiah Owens\"\n" +
                            "        },\n" +
                            "        \"charges_information\": {\n" +
                            "          \"bearer_code\": \"SHAR\",\n" +
                            "          \"receiver_charges_amount\": \"1.00\",\n" +
                            "          \"receiver_charges_currency\": \"USD\",\n" +
                            "          \"sender_charges\": [\n" +
                            "            {\n" +
                            "              \"amount\": \"5.00\",\n" +
                            "              \"currency\": \"GBP\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "              \"amount\": \"10.00\",\n" +
                            "              \"currency\": \"USD\"\n" +
                            "            }\n" +
                            "          ]\n" +
                            "        },\n" +
                            "        \"currency\": \"GBP\",\n" +
                            "        \"debtor_party\": {\n" +
                            "          \"account_name\": \"EJ Brown Black\",\n" +
                            "          \"account_number\": \"GB29XABC10161234567801\",\n" +
                            "          \"account_number_code\": \"IBAN\",\n" +
                            "          \"address\": \"10 Debtor Crescent Sourcetown NE1\",\n" +
                            "          \"bank_id\": \"203301\",\n" +
                            "          \"bank_id_code\": \"GBDSC\",\n" +
                            "          \"name\": \"Emelia Jane Brown\"\n" +
                            "        },\n" +
                            "        \"end_to_end_reference\": \"Wil piano Jan\",\n" +
                            "        \"fx\": {\n" +
                            "          \"contract_reference\": \"FX123\",\n" +
                            "          \"exchange_rate\": \"2.00000\",\n" +
                            "          \"original_amount\": \"200.42\",\n" +
                            "          \"original_currency\": \"USD\"\n" +
                            "        },\n" +
                            "        \"numeric_reference\": \"1002001\",\n" +
                            "        \"payment_id\": \"123456789012345678\",\n" +
                            "        \"payment_purpose\": \"Paying for goods/services\",\n" +
                            "        \"payment_scheme\": \"FPS\",\n" +
                            "        \"payment_type\": \"Credit\",\n" +
                            "        \"processing_date\": \"2017-01-18\",\n" +
                            "        \"reference\": \"Payment for Em's piano lessons\",\n" +
                            "        \"scheme_payment_sub_type\": \"InternetBanking\",\n" +
                            "        \"scheme_payment_type\": \"ImmediatePayment\",\n" +
                            "        \"sponsor_party\": {\n" +
                            "          \"account_number\": \"56781234\",\n" +
                            "          \"bank_id\": \"123123\",\n" +
                            "          \"bank_id_code\": \"GBDSC\"\n" +
                            "        }\n" +
                            "      },\n" +
                            "      \"organisation_id\": \"743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb\",\n" +
                            "      \"type\": \"Payment\",\n" +
                            "      \"version\": 0\n" +
                            "    }").
                when().
                    post().
                then().
                    assertThat().statusCode(201).
                    assertThat().header("Location", String::toString, notNullValue()).
                extract().
                    response();
        numPaymentsCreated++;
    }


    @Test
    public void canCreateEmptyPayment() {
        // create an empty payment
        Response response =
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
            body("{\n" +
                        "      \"attributes\": {\n" +
                        "        \"amount\": \"102.121\",\n" +
                        "        \"currency\": \"USD\"\n" +
                        "      },\n" +
                        "      \"organisation_id\": \"743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb\",\n" +
                        "      \"type\": \"Payment\",\n" +
                        "      \"version\": 0\n" +
                        "    }").
        when().
            post().
        then().
            assertThat().statusCode(201).
            assertThat().header("Location", String::toString, notNullValue()).
        extract().
            response();
        numPaymentsCreated++;

    }

    @Test
    public void canGetSingleInsertedPayment() {
        // create a payment
        Response response =
                given().
                        port(port).
                        basePath(V1_PAYMENTS).
                        contentType(ContentType.APPLICATION_JSON.getMimeType()).
                        body("{\n" +
                            "      \"attributes\": {\n" +
                            "        \"amount\": \"123.10001\",\n" +
                            "        \"currency\": \"USD\",\n" +
                            "        \"charges_information\": {\n" +
                            "          \"bearer_code\": \"SHAR\",\n" +
                            "          \"receiver_charges_amount\": \"1.00\",\n" +
                            "          \"receiver_charges_currency\": \"USD\",\n" +
                            "          \"sender_charges\": [\n" +
                            "            {\n" +
                            "              \"amount\": \"5.00\",\n" +
                            "              \"currency\": \"GBP\"\n" +
                            "            },\n" +
                            "            {\n" +
                            "              \"amount\": \"10.00\",\n" +
                            "              \"currency\": \"USD\"\n" +
                            "            }\n" +
                            "          ]\n" +
                            "        }\n" +
                            "      },\n" +
                            "      \"organisation_id\": \"743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb\",\n" +
                            "      \"type\": \"Payment\",\n" +
                            "      \"version\": 0\n" +
                            "    }").
                when().
                        post().
                then().
                        assertThat().statusCode(201).
                        assertThat().header("Location", String::toString, notNullValue()).
                extract().
                        response();
        numPaymentsCreated++;

        // extract inserted ID
        String insertedId = response.path("id");

        // query content
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
        when().
            get("/" + insertedId).
        then().
            assertThat().statusCode(200).
            body("data.id",equalTo(insertedId)).
            body("data.attributes.amount",hasToString("123.10001")).
            body("data.attributes.charges_information.sender_charges",hasSize(2));
    }

    @Test
    public void canGetAllPayments() {
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
        when().
            get().
        then().
            assertThat().statusCode(200).
            body("data.content", hasSize(numPaymentsCreated));
    }

    @Test
    public void whenInsertInvalidData_shouldReturn400() {
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
            body("{\"somedata\" : \"123\"}").
        when().
            post().
        then().
            assertThat().statusCode(400);
    }

    @Test
    public void whenSendInvalidDateFormat_shouldReturn400() {
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
            body("{\n" +
                        "      \"attributes\": {\n" +
                        "        \"amount\": \"123.10001\",\n" +
                        "        \"currency\": \"USD\",\n" +
                        "        \"processing_date\": \"01-18-2017\",\n" +
                        "      },\n" +
                        "      \"organisation_id\": \"743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb\",\n" +
                        "      \"type\": \"Payment\",\n" +
                        "      \"version\": 0\n" +
                        "    }").
        when().
            post().
        then().
            assertThat().statusCode(400);
    }

    @Test
    public void whenGetNotExistingPayment_shouldReturn404() {
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
        when().
            get("/0000000000000").
        then().
            assertThat().statusCode(404);
    }

    @Test
    public void canUpdatePayment() {
        // create a payment
        Response response =
                given().
                        port(port).
                        basePath(V1_PAYMENTS).
                        contentType(ContentType.APPLICATION_JSON.getMimeType()).
                        body("{\n" +
                                "      \"attributes\": {\n" +
                                "        \"amount\": \"102.121\",\n" +
                                "        \"currency\": \"USD\"\n" +
                                "      },\n" +
                                "      \"organisation_id\": \"743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb\",\n" +
                                "      \"type\": \"Payment\",\n" +
                                "      \"version\": 0\n" +
                                "    }").
                when().
                        post().
                then().
                        assertThat().statusCode(201).
                        assertThat().header("Location", String::toString, notNullValue()).
                extract().
                        response();
        numPaymentsCreated++;

        // extract inserted ID
        String insertedId = response.path("id");

        // update content
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
            body("{\n" +
                    "      \"attributes\": {\n" +
                    "        \"amount\": \"505.65\",\n" +
                    "        \"currency\": \"RUB\"\n" +
                    "      },\n" +
                    "      \"organisation_id\": \"743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb\",\n" +
                    "      \"type\": \"Payment\",\n" +
                    "      \"version\": 0\n" +
                    "    }").
        when().
            put("/" + insertedId).
        then().
            assertThat().statusCode(200);

        // query it and check updated fields
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
        when().
            get("/" + insertedId).
        then().
            assertThat().statusCode(200).
            body("data.attributes.amount",hasToString("505.65")).
            body("data.attributes.currency",equalTo("RUB"));
    }

    @Test
    public void canDeletePayment() {
        // create a payment
        Response response =
                given().
                    port(port).
                    basePath(V1_PAYMENTS).
                    contentType(ContentType.APPLICATION_JSON.getMimeType()).
                    body("{\n" +
                            "      \"attributes\": {\n" +
                            "        \"amount\": \"102.121\",\n" +
                            "        \"currency\": \"USD\"\n" +
                            "      },\n" +
                            "      \"organisation_id\": \"743d5b63-8e6f-432e-a8fa-c5d8d2ee5fcb\",\n" +
                            "      \"type\": \"Payment\",\n" +
                            "      \"version\": 0\n" +
                            "    }").
                when().
                    post().
                then().
                    assertThat().statusCode(201).
                    assertThat().header("Location", String::toString, notNullValue()).
                extract().
                    response();
        numPaymentsCreated++;

        // extract inserted ID
        String insertedId = response.path("id");

        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
            body("").
        when().
            delete("/" + insertedId).
        then().
            assertThat().statusCode(200);
        numPaymentsCreated--;

        // check amount by querying all again
        given().
            port(port).
            basePath(V1_PAYMENTS).
            contentType(ContentType.APPLICATION_JSON.getMimeType()).
        when().
            get().
        then().
            assertThat().statusCode(200).
            body("data.content", Matchers.hasSize(numPaymentsCreated));
    }

}
