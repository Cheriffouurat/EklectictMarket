package com.example.eklecticproject.Iservice;


import com.example.eklecticproject.entity.Utilisateur;

import java.util.List;

public interface IserviceUser {
     List<Utilisateur> GetALLUser();
     void DeleteUser(Integer IdUser);
     String SendCode(String Email);
     String ResetPassword(String Code, String NewPassword);
     void EditUser(Utilisateur U);
     Utilisateur GetUserById(Integer id);
}
