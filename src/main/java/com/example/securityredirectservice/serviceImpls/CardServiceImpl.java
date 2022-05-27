package com.example.securityredirectservice.serviceImpls;

import com.example.securityredirectservice.services.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CardServiceImpl implements CardService {
    private WebClient orderClient;
    @Autowired
    @Qualifier("orders")
    public void setOrderClient(WebClient orderClient) {
        this.orderClient = orderClient;
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return orderClient.get().uri("/card").retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        try{
            return orderClient.get().uri("/card/{id}", id).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return orderClient.delete().uri("/card/{id}", id).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> add(Object offerOrderCardDTO) {
        return orderClient.post().uri("/card").contentType(MediaType.APPLICATION_JSON).bodyValue(offerOrderCardDTO).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> update(Object offerOrderCardDTO) {
        try{
            return orderClient.put().uri("/card").contentType(MediaType.APPLICATION_JSON).bodyValue(offerOrderCardDTO).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> getInfo(Long id) {
        try{
            return orderClient.get().uri("/card/{id}/getInfo", id).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> saveChange(Object characteristicDTO) {
        return orderClient.post().uri("/card/addChange").contentType(MediaType.APPLICATION_JSON).bodyValue(characteristicDTO).retrieve().toEntity(Object.class).block();

    }
}
