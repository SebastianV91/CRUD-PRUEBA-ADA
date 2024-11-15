package com.web.adasa.controller;

import com.web.adasa.dto.CompanyDTO;
import com.web.adasa.dto.Mensaje;
import com.web.adasa.entity.Company;
import com.web.adasa.service.CompanyService;
import io.micrometer.common.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @PostMapping("/create")
    public ResponseEntity<?> create(@RequestBody CompanyDTO companyDTO){
        if(StringUtils.isBlank(companyDTO.getCodigoCompany()))
            return new ResponseEntity(new Mensaje("el codigo es obligatorio"), HttpStatus.BAD_REQUEST);
        if(companyService.existsByCodigoCompany(companyDTO.getCodigoCompany()))
            return new ResponseEntity(new Mensaje("Ese codigo ya existe"), HttpStatus.BAD_REQUEST);
        Company company = new Company(companyDTO.getCodigoCompany(), companyDTO.getNameCompany(), companyDTO.getDescriptionCompany());
        companyService.save(company);
        return new ResponseEntity(new Mensaje("Compania creada"), HttpStatus.OK);
    }

    @GetMapping("/findById/{id}")
    public ResponseEntity<Company> getById(@PathVariable("id") int id){
        if(!companyService.existsById(id))
            return new ResponseEntity(new Mensaje("Este id no existe"), HttpStatus.NOT_FOUND);

        Company company = companyService.getOne(id).get();
        return new ResponseEntity(company, HttpStatus.OK);
    }

    @GetMapping("/findByCode/{code}")
    public ResponseEntity<Company> getByCode(@PathVariable("code") String code){

        if(!companyService.existsByCodigoCompany(code))
            return new ResponseEntity(new Mensaje("Este codigo no existe"), HttpStatus.NOT_FOUND);

        Company company = companyService.getByCode(code).get();
        return new ResponseEntity(company, HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable("id")int id, @RequestBody CompanyDTO companyDTO){
        if(!companyService.existsById(id))
            return new ResponseEntity(new Mensaje("Este id no existe"), HttpStatus.NOT_FOUND);

        Company company = companyService.getOne(id).get();
        company.setCodigoCompany(companyDTO.getCodigoCompany());
        company.setNameCompany(companyDTO.getNameCompany());
        company.setDescriptionCompany(companyDTO.getDescriptionCompany());
        companyService.save(company);
        return new ResponseEntity(new Mensaje("Compania actualizada"), HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id")int id){
        if(!companyService.existsById(id))
            return new ResponseEntity(new Mensaje("Este id no existe"), HttpStatus.NOT_FOUND);

        companyService.delete(id);
        return new ResponseEntity(new Mensaje("Compania eliminada"), HttpStatus.OK);
    }

}
