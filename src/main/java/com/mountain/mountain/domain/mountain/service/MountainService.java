package com.mountain.mountain.domain.mountain.service;


import com.mountain.mountain.domain.community.model.Community;
import com.mountain.mountain.domain.mountain.dao.MountainRepository;
import com.mountain.mountain.domain.mountain.model.Mountain;
import com.mountain.mountain.exception.CustomException;
import com.mountain.mountain.exception.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class MountainService {

    @Autowired
    MountainRepository mountainRepository;

    @Transactional
    public Page<Mountain> findAll(Specification<Mountain> spec, Pageable pageable) {

        Page<Mountain> mountains = mountainRepository.findAll(spec, pageable);
        if(mountains.isEmpty())
            throw new CustomException(ErrorCode.NOT_FOUND_MOUNTAIN);
        return mountains;
    }

    @Transactional
    public Mountain findMountainDetail(Long mountainNo) {

        Mountain mountain = mountainRepository.findById(mountainNo)
                .orElseThrow(()-> new CustomException(ErrorCode.NOT_FOUND_MOUNTAIN));
        return mountain;
    }

}
