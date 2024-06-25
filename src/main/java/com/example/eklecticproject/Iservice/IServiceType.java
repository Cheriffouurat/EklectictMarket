package com.example.eklecticproject.Iservice;

import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.entity.ServicesType;

import java.util.List;

public interface IServiceType {
    ServicesType retrieveServicesType(Integer idServicesType);


    ServicesType addServiceType(ServicesType servicesType);

    List<ServicesType> retrieveAllServicesType();

    void removeServiceType(Integer idServiceType);

    ServicesType updateServiceType(ServicesType servT);


    ServicesType setServiceIdInServiceType(ServicesType servicesType, int idService);


    List<ServicesType> trierServiceTypesParPrixCroissant(List<ServicesType> serviceTypes);

    List<ServicesType> trierServiceTypesParPrixDecroissant(List<ServicesType> serviceTypes);

    List<ServicesType> trierServiceTypesParNomCroisant(List<ServicesType> serviceTypes);

    List<ServicesType> trierServiceTypesParNomDecroisant(List<ServicesType> serviceTypes);

    List<ServicesType> getAllServicesTypeByServiceId(Integer serviceId);
}
