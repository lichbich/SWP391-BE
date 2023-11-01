package fpt.demo.service.servceImp;






import fpt.demo.model.Product;
import fpt.demo.model.Provider;
import fpt.demo.model.dto.CreateProviderDTO;
import fpt.demo.model.dto.UpdateProviderDTO;
import fpt.demo.repository.ProviderRepository;
import fpt.demo.service.ProviderService;
import fpt.demo.utils.DTOMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProviderServiceImp implements ProviderService {


    @Autowired
    DTOMapper dtoMapper;

    @Autowired
    ProviderRepository providerRepository;

    // Manager functionality
    // Add provider
    @Override
    public Provider addProvider(Provider provider) {
        provider.setCreateAt(LocalDateTime.now());
        provider.setStatus(true);
        return providerRepository.save(provider);
    }

    // Manager functionality
    // Add provider by DTO (without product list)
    @Override
    public Provider addProviderByDTO(CreateProviderDTO providerDTO){
        Provider provider = dtoMapper.CreateProviderDTOToProvider(providerDTO);
        provider.setCreateAt(LocalDateTime.now());
        provider.setStatus(true);
        return providerRepository.save(provider);
    }

    // Manager functionality
    // Add provider list by DTO (without product list)
    @Override
    public List<Provider> addProviderListByDTO(List<CreateProviderDTO> createProviderDTOs){
//        return dtoMapper.CreateProviderDTOsToProviders(createProviderDTOs); //not setting status, created at, products list
        List<Provider> providers = new ArrayList<>();
        for(CreateProviderDTO item : createProviderDTOs){
            providers.add(addProviderByDTO(item));
        }
        return providers;
    }

    // Manager functionality
    // Get provider by id
    @Override
    public Provider getProviderById(Long providerId) {
        return providerRepository.findById(providerId).get();
    }

    // Manager functionality
    // Get all providers
    @Override
    public List<Provider> getAllProvider() {
        return (List<Provider>) providerRepository.findAll() ;
    }

    // Manager functionality
    // Delete provider by id
    @Override
    public void deleteProvider(Long providerId) {
        providerRepository.deleteById(providerId);
    }

    // Manager functionality
    // Update provider or create a new provider
    @Override
    public Provider updateProvider(Provider provider, Long providerId) {
        // Check if the product with the specified ID exists
        Provider existingProvider = providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Provider with id " + providerId + " not found"));
        // Update the existingProvider
        if(provider.getProviderName()!=null){
            existingProvider.setProviderName(provider.getProviderName());
        }
        if(provider.getProviderAddress()!=null){
            existingProvider.setProviderAddress(provider.getProviderAddress());
        }
        if(provider.getProviderEmail()!=null){
            existingProvider.setProviderEmail(provider.getProviderEmail());
        }
        if(provider.getProviderPhone()!=null){
            existingProvider.setProviderPhone(provider.getProviderPhone());
        }
        // createdAt field is by default created
        if(provider.getProductSet()!=null){
            existingProvider.setProductSet(provider.getProductSet());
        }
        return providerRepository.save(existingProvider);
    }

    // Manager functionality
    // Update provider or create a new provider
    @Override
    public Provider updateProviderByDTO(UpdateProviderDTO providerDTO, Long providerId) {
        // Check if the product with the specified ID exists
        Provider existingProvider = providerRepository.findById(providerId)
                .orElseThrow(() -> new EntityNotFoundException("Provider with id " + providerId + " not found"));
        if(providerDTO.getProviderName()!=null){
            existingProvider.setProviderName(providerDTO.getProviderName());
        }
        if(providerDTO.getProviderAddress()!=null){
            existingProvider.setProviderAddress(providerDTO.getProviderAddress());
        }
        if(providerDTO.getProviderEmail()!=null){
            existingProvider.setProviderEmail(providerDTO.getProviderEmail());
        }
        if(providerDTO.getProviderPhone()!=null){
            existingProvider.setProviderPhone(providerDTO.getProviderPhone());
        }
        //Provider provider = dtoMapper.UpdateProviderDTOToProvider(providerDTO); //deprecated
        return providerRepository.save(existingProvider);
    }
}
