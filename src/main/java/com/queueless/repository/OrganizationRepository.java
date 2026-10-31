package com.queueless.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.queueless.entity.Organization;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {

}