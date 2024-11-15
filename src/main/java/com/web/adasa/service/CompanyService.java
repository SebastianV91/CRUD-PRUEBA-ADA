package com.web.adasa.service;

import com.web.adasa.entity.Company;
import com.web.adasa.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    @Autowired
    CompanyRepository companyRepository;

    public void save(Company company){
        companyRepository.save(company);
    }

    public Optional<Company> getOne(Integer id){
        return companyRepository.findById(id);
    }

    public void delete(Integer id){
        companyRepository.deleteById(id);
    }

    public boolean existsById(Integer id){
        return companyRepository.existsById(id);
    }

    public boolean existsByCodigoCompany(String codigoCompany){
        return companyRepository.existsByCodigoCompany(codigoCompany);
    }

}
