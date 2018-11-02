package com.boot.controller;

import com.boot.model.Shipwreck;

import com.boot.repository.ShipwreckRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/")      //<------path for REST requests
public class ShipwreckController {

    @Autowired
    private ShipwreckRepository shipwreckRepository;

    @RequestMapping(value = "shipwrecks", method = RequestMethod.GET)       //Returns method for GET requests to api/v1/shipwrecks
    public List<Shipwreck> list (){     //returns all items in repo
        return shipwreckRepository.findAll();
    }

    @RequestMapping(value = "shipwrecks", method = RequestMethod.POST)      //Returns method for POST requests
    public Shipwreck create(@RequestBody Shipwreck shipwreck) {
        return shipwreckRepository.saveAndFlush(shipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.GET)  //Returns method for GET requests to api with ID
    public Shipwreck get(@PathVariable Long id) {   //return item in repo with matching ID
        return shipwreckRepository.findOne(id);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.PUT)  //Returns method for PUT requests with id
    public Shipwreck update(@PathVariable Long id, @RequestBody Shipwreck shipwreck) {//update item in repo with matching ID
        Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
        BeanUtils.copyProperties(shipwreck, existingShipwreck);
        return shipwreckRepository.saveAndFlush(existingShipwreck);
    }

    @RequestMapping(value = "shipwrecks/{id}", method = RequestMethod.DELETE)//Returns method for DELET requests with id
    public Shipwreck delete(@PathVariable Long id) { //delete item in repository with matching ID
        Shipwreck existingShipwreck = shipwreckRepository.findOne(id);
        shipwreckRepository.delete(existingShipwreck);
        return existingShipwreck;
    }
}
