package com.security.acl.controller;

import java.util.List;

import com.security.acl.entity.Resource;
import com.security.acl.repository.ResourceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/resource")
public class ARestController {
  
  @Autowired
  private ResourceRepository repository;

  @GetMapping
  @PostFilter("hasPermission(filterObject, 'READ')")
  public List<Resource> getAllResources() {
    return repository.findAll();
  }
}
