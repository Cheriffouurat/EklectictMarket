package com.example.eklecticproject.service;


import com.example.eklecticproject.Iservice.IserviceUser;
import com.example.eklecticproject.entity.Utilisateur;
import com.example.eklecticproject.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
@Service
@AllArgsConstructor
public class ServiceUser implements IserviceUser {
    UserRepository userRepository;
    final MailerService mailerService;

    private final RandomString randomString;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<Utilisateur> GetALLUser() {
        return userRepository.findAll();
    }
    @Override
    public void DeleteUser(Integer IdUser) {
    userRepository.deleteById(IdUser);
    }
    @Override
    public String SendCode(String Email) {
        if(userRepository.GetUserByEmail(Email)==null){
            return "Email n'existe pas";
        }else{
            String code = randomString.randomGeneratedString(8);
            Utilisateur u =userRepository.GetUserByEmail(Email);
            u.setCodeVerification(code);
            u.setDateEndCode(LocalDateTime.now().plusMinutes(5));
            mailerService.sendEmail(u.getEmail(),"REST CODE","votre code de verification est :"+code+"\nNB:Le code ne fonctionne pas apres 5 minutes");
            userRepository.save(u);
            return "ok";
        }
    }

    @Override
    public String ResetPassword(String Code, String NewPassword) {
        Utilisateur u = userRepository.GetUserByCode(Code);
        if (u == null ){
            return "code est incorrect";
        }
        else if(u.getDateEndCode().isBefore(LocalDateTime.now())) {
            return "date expirer";
        }
        else{
                u.setPassword(passwordEncoder.encode(NewPassword));
                u.setCodeVerification(null);
                u.setDateEndCode(null);
                userRepository.save(u);
            }
        return "OK";
    }
    @Override
    public void EditUser(Utilisateur utilisateur) {
        Utilisateur u=userRepository.getById(utilisateur.getId());
        if(!u.getPassword().equals(utilisateur.getPassword())){
            System.out.println("je suis la");
            utilisateur.setPassword(this.passwordEncoder.encode(utilisateur.getPassword()));
        }
        userRepository.save(utilisateur);

    }

    @Override
    public Utilisateur GetUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }
}
