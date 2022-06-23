package br.com.trindade.itau.config;

import br.com.trindade.itau.domain.repository.PixRepository;
import br.com.trindade.itau.domain.service.validation.PixKeyAndValueValidationService;
import br.com.trindade.itau.domain.service.PixService;
import br.com.trindade.itau.domain.service.validation.PixValidationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    PixService pixService(PixKeyAndValueValidationService pixKeyAndValueValidationService, PixRepository repository,
                          PixValidationService pixValidationService) {
        return new PixService(repository, pixKeyAndValueValidationService, pixValidationService);
    }

    @Bean
    PixKeyAndValueValidationService pixKeyAndValueValidationService() {
        return new PixKeyAndValueValidationService();
    }

    @Bean
    PixValidationService pixValidationService(PixRepository repository){
        return new PixValidationService(repository);
    }
}
