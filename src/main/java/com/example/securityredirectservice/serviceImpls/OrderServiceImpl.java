package com.example.securityredirectservice.serviceImpls;

import com.example.securityredirectservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class OrderServiceImpl implements OrderService {
    private WebClient orderClient;
    @Autowired
    @Qualifier("orders")
    public void setOrderClient(WebClient orderClient) {
        this.orderClient = orderClient;
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return orderClient.get().uri("/orders").retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        try{
            return orderClient.get().uri("/orders/{id}",id).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> delete(Long id) {
        return orderClient.delete().uri("/orders/{id}",id).retrieve().toBodilessEntity().block();
    }

    @Override
    public ResponseEntity<Void> changeStatus(Long id, Long statusId) {
        try{
            return orderClient.patch().uri("/orders/{id}", id).contentType(MediaType.APPLICATION_JSON).bodyValue(statusId).retrieve().toBodilessEntity().block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> add(Object orderDTO) {
        return orderClient.post().uri("/orders").contentType(MediaType.APPLICATION_JSON).bodyValue(orderDTO).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> update(Object orderDTO, Long id) {
        try{
            return orderClient.put().uri("/orders/{id}",id).contentType(MediaType.APPLICATION_JSON).bodyValue(orderDTO).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> getPrice(Long id) {
        return orderClient.get().uri("/orders/{id}/getPrice", id).retrieve().toEntity(Object.class).block();
    }

    @Override
    public Object trueGetAll() {
        return orderClient.get().uri("/orders/all").retrieve().toEntity(Object.class).block();
    }
}
