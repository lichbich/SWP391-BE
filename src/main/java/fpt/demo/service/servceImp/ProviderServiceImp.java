package fpt.demo.service.servceImp;






import fpt.demo.model.Provider;
import fpt.demo.repository.ProviderRepository;
import fpt.demo.service.ProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProviderServiceImp implements ProviderService {


    @Autowired
    ProviderRepository providerRepository;

    @Override
    public Provider addProvider(Provider provider) {
        provider.setCreateAt(LocalDateTime.now());
        provider.setStatus(true);
        return providerRepository.save(provider);
    }

    @Override
    public Provider getProviderById(Long providerId) {
        return providerRepository.findById(providerId).get();
    }

    @Override
    public List<Provider> getAllProvider() {
        return (List<Provider>) providerRepository.findAll() ;
    }

    @Override
    public void deleteProvider(Long providerId) {
        providerRepository.deleteById(providerId);
    }

    @Override
    public Provider updateProvider(Provider provider, Long providerId) {
        provider.setProviderId(providerId);
        return providerRepository.save(provider);
    }
}
