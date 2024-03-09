package com.example.eklecticproject.Iservice;



import com.example.eklecticproject.entity.Services;

import java.util.List;

public interface IServiceService {
    Services addService(Services service);

    Services getbyname(String name);

    List<Services> retrieveAllServices();

    Services retrieveService(Integer idAbonnement);

    void removeService(Integer idService);

    Services updateService(Services serv);


}
