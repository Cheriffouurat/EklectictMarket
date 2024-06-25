package com.example.eklecticproject.Iservice;

import com.example.eklecticproject.entity.Offre;

import java.util.List;

public interface IOffreServices {

    Offre setServiceTypeIdInOffer(Offre Offre, int idServiceType);

    Offre setServiceTypeIdInOffer22(Offre offre, int idServiceType);

    List<Offre> AllOffre();
}
