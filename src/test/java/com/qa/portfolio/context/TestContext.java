package com.qa.portfolio.context;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static final ThreadLocal<RequestSpecification> requestSpec = new ThreadLocal<>();
    private static final ThreadLocal<Response> response = new ThreadLocal<>();
    private static final ThreadLocal<Map<String, Object>> sessionData = new ThreadLocal<>();

    public static RequestSpecification getRequestSpec() {
        return requestSpec.get();
    }

    public static void setRequestSpec(RequestSpecification spec) {
        requestSpec.set(spec);
    }

    public static Response getResponse() {
        return response.get();
    }

    public static void setResponse(Response res) {
        response.set(res);
    }

    public static Map<String, Object> getSessionData() {
        if (sessionData.get() == null) {
            sessionData.set(new HashMap<>());
        }
        return sessionData.get();
    }

    // Nagyon fontos: A szál végén üríteni kell a memóriát!
    public static void unload() {
        requestSpec.remove();
        response.remove();
        sessionData.remove();
    }
}