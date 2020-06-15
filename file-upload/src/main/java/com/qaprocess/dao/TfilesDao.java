package com.qaprocess.dao;

import com.qaprocess.entity.TfilesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TfilesDao extends JpaRepository<TfilesEntity,Long> {
}
