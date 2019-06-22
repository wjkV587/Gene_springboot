package com.gene.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneDao extends JpaRepository<GeneEntity,Long> {
    //默认继承JpaRepository的数据访问方法
    GeneEntity findById(int id);//根据基因ID查询
}