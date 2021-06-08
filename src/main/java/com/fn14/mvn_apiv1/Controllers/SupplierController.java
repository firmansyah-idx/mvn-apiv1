/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fn14.mvn_apiv1.Controllers;

import com.fn14.mvn_apiv1.Dto.ResponseData;
import com.fn14.mvn_apiv1.Dto.SupplierDto;
import com.fn14.mvn_apiv1.Models.Supplier;
import com.fn14.mvn_apiv1.Services.SupplierService;
import javax.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author FN14
 */
@RestController
@RequestMapping("/api/suppliers")
public class SupplierController {
    
    @Autowired
    private SupplierService suppService;
    
    @Autowired
    private ModelMapper modelMapper;
    
    @PostMapping
    public ResponseEntity<ResponseData<Supplier>> create(@Valid @RequestBody SupplierDto suppDto, Errors errors){
        ResponseData<Supplier> resData = new ResponseData<>(); 
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(objErr -> {
                resData.getMessages().add(objErr.getDefaultMessage());
            });
            resData.setStatus(false); 
            resData.setPayload(null); 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resData); 
        }
        
        Supplier supplier = modelMapper.map(suppDto, Supplier.class);
            
        resData.setStatus(true);
        resData.setPayload(suppService.save(supplier));
        return ResponseEntity.ok(resData);
    }
    
    @GetMapping
    public Iterable<Supplier> findAll(){
        return suppService.findAll();
    }
    
    @GetMapping("/{id}")
    public Supplier findById(@PathVariable("id") Long id){
        return suppService.findById(id);
    }
    
    @PostMapping("/upd")
    public ResponseEntity<ResponseData<Supplier>> update(@Valid @RequestBody SupplierDto suppDto, Errors errors){
        ResponseData<Supplier> resData = new ResponseData<>(); 
        if(errors.hasErrors()){
            errors.getAllErrors().forEach(objErr -> {
                resData.getMessages().add(objErr.getDefaultMessage());
            });
            resData.setStatus(false); 
            resData.setPayload(null); 
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(resData); 
        }
        
        Supplier supplier = modelMapper.map(suppDto, Supplier.class);
            
        resData.setStatus(true);
        resData.setPayload(suppService.save(supplier));
        return ResponseEntity.ok(resData);
    }
    
    
}
