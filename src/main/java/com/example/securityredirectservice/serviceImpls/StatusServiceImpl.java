package com.example.securityredirectservice.serviceImpls;

import com.example.securityredirectservice.dtos.BodyExceptionWrapper;
import com.example.securityredirectservice.services.StatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StatusServiceImpl implements StatusService {
    private WebClient orderClient;
    @Autowired
    @Qualifier("orders")
    public void setOrderClient(WebClient orderClient) {
        this.orderClient = orderClient;
    }

    @Override
    public ResponseEntity<Object> getAllStatuses() {
        return orderClient.get().uri("/statuses").retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> getStatus(Long id) {
        try{
            return orderClient.get().uri("/statuses/{id}", id).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return new ResponseEntity<>(new BodyExceptionWrapper("404", "Status with id="+id+" not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> addNewStatus(Object value) {
        return orderClient.post().uri("/statuses").contentType(MediaType.APPLICATION_JSON).bodyValue(value).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> update(Object statusDTO) {
        try{
            return orderClient.put().uri("/statuses").contentType(MediaType.APPLICATION_JSON).bodyValue(statusDTO).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return orderClient.delete().uri("/statuses/{id}", id).retrieve().toEntity(Object.class).block();
    }
}
