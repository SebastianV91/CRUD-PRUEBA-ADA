package com.web.adasa.repository;

import com.web.adasa.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyRepository extends JpaRepository<Company, Integer> {

    boolean existsByCodigoCompany(String codigoCompany);

}
