package fpt.demo.service;

import fpt.demo.model.Provider;

import java.util.List;

public interface ProviderService {
    public Provider addProvider(Provider provider);
    public Provider getProviderById(Long providerId);
    public List<Provider> getAllProvider();
    public void deleteProvider(Long providerId);
    public Provider updateProvider(Provider provider, Long providerId);

}
