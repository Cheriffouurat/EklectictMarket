package com.example.eklecticproject.service;



import com.example.eklecticproject.Iservice.ICategorieServices;
import com.example.eklecticproject.Iservice.IServiceService;
import com.example.eklecticproject.entity.Categorie;
import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.repository.IServiceRepositorie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceService implements IServiceService {
    IServiceRepositorie ServiceRepositorie;

    MailerService mailerService;
    private ICategorieServices iCategorieServices;


  

    @Override
    public Services addService(Services service) {

        return ServiceRepositorie.save(service);
    }



    @Override
    public Services getbyname(String name) {
        return ServiceRepositorie.findServicesByNameService(name);
    }

    @Override
    public List<Services> retrieveAllServices() {
        List<Services> Serv  = new ArrayList<>();
        ServiceRepositorie.findAll().forEach(Serv::add);
        return Serv;
    }
    @Override
    public List<Services> getAllServicesByCategorieId(Integer categorieId) {
        return ServiceRepositorie.findByCategorie_IdCategorie(categorieId);
    }


    @Override
    public Services retrieveService(Integer idService) {
        return ServiceRepositorie.findById(idService).orElse(null);
    }

    @Override
    public void removeService(Integer idService) {
        ServiceRepositorie.deleteById(idService);
    }

    @Override
    public Services updateService(Services serv) {

        return ServiceRepositorie.saveAndFlush(serv);
    }

    @Override

    public Services setCategoryIdInService(Services service, int idCategorie) {
        if (idCategorie <= 0) {
            throw new IllegalArgumentException("L'ID de la catégorie doit être positif");
        }
        Categorie category = iCategorieServices.retrieveCategorie(idCategorie);
        if (category == null) {
            throw new IllegalArgumentException("La catégorie avec l'ID " + idCategorie + " n'existe pas");
        }
        service.setCategorie(category);
        return ServiceRepositorie.save(service);
    }





}
