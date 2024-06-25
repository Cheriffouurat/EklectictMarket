package com.example.eklecticproject.service;

import com.example.eklecticproject.Iservice.IServiceService;
import com.example.eklecticproject.Iservice.IServiceType;
import com.example.eklecticproject.entity.Services;
import com.example.eklecticproject.entity.ServicesType;
import com.example.eklecticproject.repository.ServiceTypeRepositorie;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@AllArgsConstructor
public class ServiceTypeService implements IServiceType {
    ServiceTypeRepositorie serviceTypeRepositorie;
    IServiceService iServiceService;
    public LocalDate calculerDateExpiration(int idServiceType) {
        ServicesType prix = serviceTypeRepositorie.findById(idServiceType).orElse(null);

        switch (prix.getPeriode()) {
            case JOUR:
                return prix.getDate().plusDays(1);
            case MOIS:
                return prix.getDate().plusMonths(1);
            case TRIMESTRE:
                return prix.getDate().plusMonths(3);
            case SIMESTRE:
                return prix.getDate().plusMonths(6);
            case ANNUEL:
                return prix.getDate().plusYears(1);
            default:
                throw new IllegalArgumentException("Type de période non géré : " + prix.getPeriode());
        }
    }
    @Override
    public ServicesType retrieveServicesType(Integer idServicesType) {
        return serviceTypeRepositorie.findById(idServicesType).orElse(null);
    }
    @Override
    public ServicesType addServiceType(ServicesType servicesType) {

        return serviceTypeRepositorie.save(servicesType);
    }
    @Override
    public List<ServicesType> retrieveAllServicesType() {
        List<ServicesType> Servt  = new ArrayList<>();
        serviceTypeRepositorie.findAll().forEach(Servt::add);
        return Servt;
    }

    @Override
    public void removeServiceType(Integer idServiceType) {
        serviceTypeRepositorie.deleteById(idServiceType);
    }

    @Override
    public ServicesType updateServiceType(ServicesType servT) {

        return serviceTypeRepositorie.saveAndFlush(servT);
    }




    @Override

    public ServicesType setServiceIdInServiceType(ServicesType servicesType, int idService) {

        if (idService <= 0) {
            throw new IllegalArgumentException("L'ID de la Service doit être positif");
        }
        Services services = iServiceService.retrieveService(idService);
        if (services == null) {
            throw new IllegalArgumentException("La catégorie avec l'ID " + idService + " n'existe pas");
        }
        servicesType.setService(services);
        return serviceTypeRepositorie.save(servicesType);
    }
    @Override
    public List<ServicesType> trierServiceTypesParPrixCroissant(List<ServicesType> serviceTypes) {


        Comparator<ServicesType> prixComparator = Comparator.comparingDouble(serviceType -> {
            if (serviceType.getPrixApresOffer() != null && serviceType.getPrixApresOffer() != 0) {
                return serviceType.getPrixApresOffer();
            } else {
                return serviceType.getPrix();
            }
        });

        List<ServicesType> serviceTypesTries = serviceTypes.stream()
                .sorted(prixComparator)
                .collect(Collectors.toList());

        return serviceTypesTries;
    }
    @Override
    public List<ServicesType> trierServiceTypesParPrixDecroissant(List<ServicesType> serviceTypes) {
        Comparator<ServicesType> prixComparator = Comparator.comparingDouble(serviceType -> {
            if (serviceType.getPrixApresOffer() != null && serviceType.getPrixApresOffer() != 0) {
                return -serviceType.getPrixApresOffer();
            } else {
                return -serviceType.getPrix();
            }
        });

        List<ServicesType> serviceTypesTries = serviceTypes.stream()
                .sorted(prixComparator)
                .collect(Collectors.toList());

        return serviceTypesTries;
    }
    @Override
    public List<ServicesType> trierServiceTypesParNomCroisant(List<ServicesType> serviceTypes) {
        Comparator<ServicesType> nomComparator = Comparator.comparing(serviceType -> serviceType.getNom());

        List<ServicesType> serviceTypesTries = serviceTypes.stream()
                .sorted(nomComparator)
                .collect(Collectors.toList());

        return serviceTypesTries;
    }
    @Override
    public List<ServicesType> trierServiceTypesParNomDecroisant(List<ServicesType> serviceTypes) {
        Comparator<ServicesType> nomComparator = Comparator.comparing(serviceType -> serviceType.getNom());
        nomComparator = nomComparator.reversed();


        List<ServicesType> serviceTypesTries = serviceTypes.stream()
                .sorted(nomComparator)
                .collect(Collectors.toList());

        return serviceTypesTries;
    }

    @Override
    public List<ServicesType> getAllServicesTypeByServiceId(Integer serviceId) {
        return serviceTypeRepositorie.findByService_IdService(serviceId);
    }

}
