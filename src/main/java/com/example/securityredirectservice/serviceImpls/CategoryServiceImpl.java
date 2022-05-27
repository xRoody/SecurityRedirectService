package com.example.securityredirectservice.serviceImpls;

import com.example.securityredirectservice.dtos.BodyExceptionWrapper;
import com.example.securityredirectservice.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class CategoryServiceImpl implements CategoryService {
    private WebClient offerClient;

    @Autowired
    @Qualifier("offer_service")
    public void setOfferClient(WebClient offerClient) {
        this.offerClient = offerClient;
    }


    @Override
    public ResponseEntity<Object> getAllCategories() {
        return offerClient.get().uri("/category").retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> getById(Long id) {
        try {
            return offerClient.get().uri("/category/{id}", id).retrieve().toEntity(Object.class).block();
        } catch (Exception e) {
            return new ResponseEntity<>(new BodyExceptionWrapper("404", "Category with id=" + id + " not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> add(Object categoryDTO) {
        return offerClient.post().uri("/category").contentType(MediaType.APPLICATION_JSON).bodyValue(categoryDTO).retrieve().toEntity(Object.class).block();
    }

    @Override
    public ResponseEntity<Object> update(Object categoryDTO) {
        try{
            return offerClient.put().uri("/category").contentType(MediaType.APPLICATION_JSON).bodyValue(categoryDTO).retrieve().toEntity(Object.class).block();
        }catch (Exception e){
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Object> getAllOffersByCategory(Long id) {
        try {
            return offerClient.get().uri("/category/{id}/allOffers", id).retrieve().toEntity(Object.class).block();
        } catch (Exception e) {
            return new ResponseEntity<>(new BodyExceptionWrapper("404", "Category with id=" + id + " not found"), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public ResponseEntity<Object> delete(Long id) {
        return offerClient.delete().uri("/category/{id}", id).retrieve().toEntity(Object.class).block();
    }
}
