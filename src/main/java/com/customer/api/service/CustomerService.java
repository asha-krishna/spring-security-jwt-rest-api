package com.customer.api.service;

import com.customer.api.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    @Autowired
    OAuth2RestTemplate oAuth2RestTemplate;

    private HttpHeaders getHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("accept", "*/*");
        headers.add("Authorization", "Bearer "+oAuth2RestTemplate.getAccessToken().toString());
        return headers;
    }

    public void addCustomer(Customer customer) {
        String post_url = "http://localhost:8080/addCustomer";
        System.out.println(oAuth2RestTemplate.getAccessToken().toString());
        HttpEntity<Customer> entity = new HttpEntity<>(customer, getHeaders());
        ResponseEntity<Customer> response = oAuth2RestTemplate.exchange(post_url, HttpMethod.POST, entity, Customer.class);
        System.out.println("Response : " +response.getStatusCode());
    }

}
