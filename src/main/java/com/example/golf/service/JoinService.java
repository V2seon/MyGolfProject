package com.example.golf.service;

import com.example.golf.dto.UserinfoDto;
import com.example.golf.repository.UserinfoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class JoinService {

    private UserinfoRepository userinfoRepository;

    @Transactional
    public Long join(UserinfoDto userinfoDto){
        return userinfoRepository.save(userinfoDto.toEntity()).getUino();
    }

}
