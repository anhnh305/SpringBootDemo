package com.savvycom.springjwt.entity.service;

import com.savvycom.springjwt.data.LoverDTO;
import com.savvycom.springjwt.entity.Lover;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

/**
 * Project: springjwt
 * Author: Nhokxayda at 27/08/21
 */

@Service
public class LoverServiceImpl implements LoverService {

    @Autowired
    LoverRepository loverRepository;

    @Override
    public List<LoverDTO> getAlllover() {
        List<Lover> loverList = loverRepository.findAll();
        if(loverList.isEmpty()){
            throw new InputMismatchException("Khong query duoc du lieu");
        }
        List<LoverDTO> loverDTOList = new ArrayList<>();
        for (Lover lover : loverList){
            LoverDTO loverDTO = new LoverDTO(0,"","",0);
            loverDTO.setId(lover.getId());
            loverDTO.setName(lover.getName());
            loverDTO.setLover(lover.getLover());
            loverDTO.setMark(lover.getMark());
            loverDTOList.add(loverDTO);
        }
        return loverDTOList;
    }
}
