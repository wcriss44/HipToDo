package com.theironyard.novauc.repository;

import com.theironyard.novauc.domain.Owner;

import org.springframework.data.jpa.repository.*;

import java.util.List;

/**
 * Spring Data JPA repository for the Owner entity.
 */
@SuppressWarnings("unused")
public interface OwnerRepository extends JpaRepository<Owner,Long> {

}
