package com.web.adasa.repository;

import com.web.adasa.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    Optional<Company> findByCodigoCompany(String codigoCompany);

    boolean existsByCodigoCompany(String codigoCompany);

}
