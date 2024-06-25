package com.example.eklecticproject.repository;

import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.entity.ServicesType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceTypeRepositorie extends JpaRepository<ServicesType,Integer> {
    List<ServicesType> findByService_IdService(Integer serviceId);;

}
