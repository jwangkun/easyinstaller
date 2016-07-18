package com.jianglibo.vaadin.dashboard.repositories;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.jianglibo.vaadin.dashboard.domain.Box;

@RepositoryRestResource(collectionResourceRel = "boxes", path = "boxes")
public interface BoxRepository extends JpaRepository<Box, Long>, BoxRepositoryCustom, JpaSpecificationExecutor<Box> {
    
}