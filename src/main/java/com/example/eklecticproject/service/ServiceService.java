package com.example.eklecticproject.service;



import com.example.eklecticproject.Iservice.IAbonnementServices;
import com.example.eklecticproject.Iservice.IServiceService;
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
    IAbonnementServices iAbonnementServices;

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

   /* public boolean purchaseService(User user, Services service, int durationInDays ) {

        double totalCost = service.getPriceParJour() * durationInDays;

        if (user.getBalance() >= totalCost) {

            user.setBalance(user.getBalance() - totalCost);

            Abonnement abonnement = new Abonnement();
            abonnement.setUser(user);
            abonnement.setServices(service);
            abonnement.setDatedebut(LocalDate.now());

            abonnement.setDatefin(LocalDate.now().plusDays(durationInDays));

            iAbonnementServices.addAbonnement(abonnement);


            user.setLoyaltyPoints(user.getLoyaltyPoints() + 10);
            userServices.saveUser(user);
            return true;
        } else {
            return false;
        }
    }*/



}
