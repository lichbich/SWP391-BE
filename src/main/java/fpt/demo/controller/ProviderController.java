package fpt.demo.controller;

import fpt.demo.model.Product;
import fpt.demo.model.Provider;
import fpt.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
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

    //add provider
    @PostMapping("/add")
    public String addProduct(@RequestBody Provider provider){
        providerService.addProvider(provider);
        return "Provider added successfully!";
    }

    @RequestMapping("{providerId}")
    public Provider getProviderById(@PathVariable("providerId") Long providerId) {
        return providerService.getProviderById(providerId);
    }

    @RequestMapping("")
    public List<Provider> getAllProvider() {
        return providerService.getAllProvider();
    }

    @DeleteMapping(path = "/delete/{providerId}")
    public void deleteProvider(@PathVariable("providerId") Long providerId) {
        providerService.deleteProvider(providerId);
    }

    @PutMapping(path = "/update/{providerId}")
    public Provider updateProvider(@RequestBody Provider provider, @PathVariable("providerId") Long providerId) {
        return providerService.updateProvider(provider, providerId);
    }

}
