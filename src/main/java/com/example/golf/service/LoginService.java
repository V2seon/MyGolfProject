package com.example.golf.service;

import com.example.golf.entity.UserinfoEntity;
import com.example.golf.repository.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LoginService {

    private UserinfoRepository userinfoRepository;

    @Transactional
    public int loginAdmin(String userid, String userpw) {
        int returnValue = 0;
        Optional<UserinfoEntity> ggg = userinfoRepository.findByUiidAndUipassword(userid, userpw);

        if (!ggg.isPresent()) {
            returnValue = 0;
        } else {
            returnValue = 1;
        }
        return returnValue;
    }


}
