package com.example.securityredirectservice.serviceImpls;

import com.example.securityredirectservice.dtos.BodyExceptionWrapper;
import com.example.securityredirectservice.services.CharService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.Map;

@Service
public class CharServiceImpl implements CharService {
    private WebClient offerClient;

    @Autowired
    @Qualifier("offer_service")
    public void setOfferClient(WebClient offerClient) {
        this.offerClient = offerClient;
    }
    @Override
    public ResponseEntity<Object> getById(Long id) {
        try{
            return offerClient.get().uri("/characteristics/{id}", id).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return new ResponseEntity<>(new BodyExceptionWrapper("404", "Characteristic with id="+id+" not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> addCharacteristic(Object characteristicDTO) {
        return offerClient.post().uri("/characteristics").contentType(MediaType.APPLICATION_JSON).bodyValue(characteristicDTO).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> update(Object characteristicDTO) {
        try{
            return offerClient.put().uri("/characteristics").contentType(MediaType.APPLICATION_JSON).bodyValue(characteristicDTO).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return offerClient.delete().uri("/characteristics/{id}", id).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Void> getFromService(Map<String, Integer> map) {
        int status= offerClient
                .patch()
                .uri("/characteristics")
                .bodyValue(map)
                .retrieve()
                .onStatus(HttpStatus::is4xxClientError, response-> Mono.empty())
                .toBodilessEntity()
                .block()
                .getStatusCode()
                .value();
        if (status==400) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok().build();
    }
}
