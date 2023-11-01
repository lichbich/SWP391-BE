package fpt.demo.service;

import fpt.demo.model.Provider;
import fpt.demo.model.dto.CreateProviderDTO;
import fpt.demo.model.dto.UpdateProviderDTO;

import java.util.List;

public interface ProviderService {
    public Provider addProvider(Provider provider);
    public Provider addProviderByDTO(CreateProviderDTO providerDTO);
    public List<Provider> addProviderListByDTO(List<CreateProviderDTO> createProviderDTOs);
    public Provider getProviderById(Long providerId);
    public List<Provider> getAllProvider();
    public void deleteProvider(Long providerId);
    public Provider updateProvider(Provider provider, Long providerId);
    public Provider updateProviderByDTO(UpdateProviderDTO providerDTO, Long providerId);

}
