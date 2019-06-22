package com.gene.service;

import com.gene.dao.GeneDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class GeneServiceImpl implements GeneService {

    @Autowired
    private GeneDao gd;

    @Override
    public List queryGeneAll(){
        return gd.findAll();
    }
}
