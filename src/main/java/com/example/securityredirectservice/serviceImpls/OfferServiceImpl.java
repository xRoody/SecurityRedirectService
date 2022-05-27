package com.example.securityredirectservice.serviceImpls;

import com.example.securityredirectservice.dtos.BodyExceptionWrapper;
import com.example.securityredirectservice.services.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class OfferServiceImpl implements OfferService {
    private WebClient offerClient;

    @Autowired
    @Qualifier("offer_service")
    public void setOfferClient(WebClient offerClient) {
        this.offerClient = offerClient;
    }

    @Override
    public ResponseEntity<Object> getAll() {
        return offerClient.get().uri("/offers").retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        try{
            return offerClient.get().uri("/offers/{id}", id).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return new ResponseEntity<>(new BodyExceptionWrapper("404", "Offer with id="+id+" not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> add(Object offer) {
        return offerClient.post().uri("/offers").contentType(MediaType.APPLICATION_JSON).bodyValue(offer).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return offerClient.delete().uri("/offers/{id}", id).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> getAllChars(Long id) {
        try{
            return offerClient.get().uri("/offers/{id}/characteristics", id).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return new ResponseEntity<>(new BodyExceptionWrapper("404", "Offer with id="+id+" not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> getCategoryAndPrice(Long id) {
        try{
            return offerClient.get().uri("/offers/{id}/categoryAndPrice", id).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return new ResponseEntity<>(new BodyExceptionWrapper("404", "Offer with id="+id+" not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> updateOffer(Object offer) {
        try{
            return offerClient.put().uri("/offers").contentType(MediaType.APPLICATION_JSON).bodyValue(offer).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }
}
