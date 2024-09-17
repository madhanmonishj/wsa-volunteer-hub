package com.example.wsa.qualification;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class QualificationServiceImpl implements QualificationService {

    private final QualificationRespository qualificationRespository;

    @Autowired
    public QualificationServiceImpl(QualificationRespository qualificationRespository) {
        this.qualificationRespository = qualificationRespository;
    }


    @Override
    public List<Qualification> getAllQualifications() {
        log.debug("QualificationServiceImpl.getAllQualifications() : {}", qualificationRespository.findAll());
        return qualificationRespository.findAll();
    }
}
