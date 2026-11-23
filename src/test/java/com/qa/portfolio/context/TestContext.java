package com.qa.portfolio.context;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

public class TestContext {

    private static final ThreadLocal<RequestSpecification> requestSpec = ThreadLocal.withInitial(() -> null);
    private static final ThreadLocal<Response> response = ThreadLocal.withInitial(() -> null);
    private static final ThreadLocal<Map<String, Object>> sessionData = ThreadLocal.withInitial(HashMap::new);

    public static final String RESPONSE = "RESPONSE";
    public static final String REQUEST_BODY = "REQUEST_BODY";
    public static final String ENDPOINT = "ENDPOINT";

    public static RequestSpecification getRequestSpec() {
        return requestSpec.get();
    }

    public static void setRequestSpec(RequestSpecification spec) {
        requestSpec.set(spec);
    }

    public static Response getResponse() {
        Response currentResponse = response.get();
        if (currentResponse == null) {
            currentResponse = (Response) sessionData.get().get(RESPONSE);
        }

        if (currentResponse == null) {
            System.err.println("⚠️ [TestContext WARNING] A response NULL a következő szálon: " + Thread.currentThread().getName() + " (ID: " + Thread.currentThread().getId() + ")");
        }

        return currentResponse;
    }

    public static void setResponse(Response res) {
        System.out.println("[TestContext SAVE] Response elmentve a szálon: " + Thread.currentThread().getName() + " (ID: " + Thread.currentThread().getId() + ")");
        response.set(res);
        sessionData.get().put(RESPONSE, res);
    }

    public static Map<String, Object> getSessionData() {
        return sessionData.get();
    }

    public static void set(String key, Object value) {
        if (RESPONSE.equalsIgnoreCase(key) && value instanceof Response) {
            setResponse((Response) value);
        } else {
            sessionData.get().put(key, value);
        }
    }

    @SuppressWarnings("unchecked")
    public static <T> T get(String key) {
        if (RESPONSE.equalsIgnoreCase(key)) {
            return (T) getResponse();
        }
        return (T) sessionData.get().get(key);
    }

    public static void unload() {
        System.out.println("🧹 [TestContext UNLOAD] Kontextus törölve a szálon: " + Thread.currentThread().getName());
        requestSpec.remove();
        response.remove();
        sessionData.remove();
    }
}