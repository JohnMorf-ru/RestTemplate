package ru.johnmorf.restTemplate;

import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;

public class SpringRestClient {

    private static final String GET_EMPLOYEES_ENDPOINT_URL = "http://91.241.64.178:7081/api/users";
    private static final String CREATE_EMPLOYEE_ENDPOINT_URL = "http://91.241.64.178:7081/api/users";
    private static final String UPDATE_EMPLOYEE_ENDPOINT_URL = "http://91.241.64.178:7081/api/users";
    private static final String DELETE_EMPLOYEE_ENDPOINT_URL = "http://91.241.64.178:7081/api/users/3";
    private static String cookie;
    RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) throws IOException {
        SpringRestClient springRestClient = new SpringRestClient();

//       SpringRestClient // Step1: first create a new employee
        springRestClient.getEmployees();
        springRestClient.createEmployee(cookie);
//
//        // Step 2: get new created employee from step1
//        springRestClient.getEmployeeById();
//
        // Step3: get all employees
//
//
//        // Step4: Update employee with id = 1
        springRestClient.updateEmployee(cookie);
//
//        // Step5: Delete employee with id = 1
        springRestClient.deleteEmployee(cookie);
    }

    private void getEmployees() {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity< String > entity = new HttpEntity < String > ("parameters", headers);



        ResponseEntity< String > result = restTemplate.exchange(GET_EMPLOYEES_ENDPOINT_URL, HttpMethod.GET, entity,
                String.class);
        cookie = result.getHeaders().getFirst("Set-Cookie");
        System.out.println(cookie);

        System.out.println(result);
    }

    private void createEmployee(String cookie) throws IOException {
//        User newEmployee = new User(3L, "James", "Brown", (byte) 34);


        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("Cookie", cookie);

        HttpEntity<String>requestEntity = new HttpEntity("{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":23}", headers);


        ResponseEntity<String> response = restTemplate.exchange(CREATE_EMPLOYEE_ENDPOINT_URL, HttpMethod.POST,
                requestEntity, String.class);

        System.out.println(response);
    }

    private void updateEmployee(String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("Cookie", cookie);

        HttpEntity<String>requestEntity = new HttpEntity("{\"id\":3,\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":23}", headers);


        ResponseEntity<String> response = restTemplate.exchange(UPDATE_EMPLOYEE_ENDPOINT_URL, HttpMethod.PUT,
                requestEntity, String.class);

        System.out.println(response);
    }

    private void deleteEmployee(String cookie) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        headers.add("Cookie", cookie);

        HttpEntity<String>requestEntity = new HttpEntity(" ", headers);


        ResponseEntity<String> response = restTemplate.exchange(DELETE_EMPLOYEE_ENDPOINT_URL, HttpMethod.DELETE,
                requestEntity, String.class);

        System.out.println(response);
    }
}