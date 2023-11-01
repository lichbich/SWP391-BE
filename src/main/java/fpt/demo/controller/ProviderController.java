package fpt.demo.controller;

import fpt.demo.model.Product;
import fpt.demo.model.Provider;
import fpt.demo.model.dto.CreateProviderDTO;
import fpt.demo.model.dto.UpdateProviderDTO;
import fpt.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(path = "api/v0_01/provider")

public class ProviderController {

    @Autowired
    private ProviderService providerService;

    public ProviderController(ProviderService providerService) {
        this.providerService = providerService;
    }

    // Manager functionality
    // Add provider
    @PostMapping("/add")
    public ResponseEntity<String> addProvider(@RequestBody Provider provider){
        providerService.addProvider(provider); //provider
        return ResponseEntity.ok("Provider added successfully!");
    }

    // Manager functionality
    // Add provider by dto
    @PostMapping("/dto/add")
    public ResponseEntity<String> addProviderbyDTO(@RequestBody CreateProviderDTO providerDTO){
        providerService.addProviderByDTO(providerDTO); //provider
        return ResponseEntity.ok("Provider added successfully!");
    }

    // Manager functionality
    // Add provider lít by dto
    @PostMapping("/dto/list/add")
    public ResponseEntity<String> addProviderListbyDTO(@RequestBody List<CreateProviderDTO> providerDTOs){
        providerService.addProviderListByDTO(providerDTOs); //provider list
        return ResponseEntity.ok("Provider list added successfully!");
    }

    // Manager functionality
    // Get provider by id
    @RequestMapping("{providerId}")
    public ResponseEntity<Provider> getProviderById(@PathVariable("providerId") Long providerId) {
        return ResponseEntity.ok(providerService.getProviderById(providerId));
    }

    // Manager functionality
    // Get all providers
    @RequestMapping("")
    public ResponseEntity<List<Provider>> getAllProvider() {
        return ResponseEntity.ok(providerService.getAllProvider());
    }

    // Manager functionality
    // Delete provider by id
    @DeleteMapping(path = "/delete/{providerId}")
    public void deleteProvider(@PathVariable("providerId") Long providerId) {
        providerService.deleteProvider(providerId);
    }

    // Manager functionality
    // Update provider or create a new provider
    // Need to update all fields or else existing fields will be NULL
    @PutMapping(path = "/update/{providerId}")
    public ResponseEntity<Provider> updateProvider(@RequestBody Provider provider, @PathVariable("providerId") Long providerId) {
        return ResponseEntity.ok(providerService.updateProvider(provider, providerId));
    }

    // Manager functionality
    // Update provider or create a new provider
    @PutMapping(path = "/update/dto/{providerId}")
    public ResponseEntity<Provider> updateProviderByDTO(@RequestBody UpdateProviderDTO providerDTO, @PathVariable("providerId") Long providerId) {
        return ResponseEntity.ok(providerService.updateProviderByDTO(providerDTO, providerId)); //provider
    }

}
