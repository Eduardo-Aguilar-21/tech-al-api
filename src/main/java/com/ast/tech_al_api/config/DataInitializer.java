package com.ast.tech_al_api.config;

import com.ast.tech_al_api.entities.Organization;
import com.ast.tech_al_api.entities.UserEntity;
import com.ast.tech_al_api.enums.UserRole;
import com.ast.tech_al_api.enums.UserStatus;
import com.ast.tech_al_api.repositories.UserRepository;
import org.springframework.stereotype.Component;
import org.springframework.boot.CommandLineRunner;

import org.springframework.beans.factory.annotation.Autowired;
import com.ast.tech_al_api.repositories.OrganizationRepository;

@Component
public class DataInitializer implements CommandLineRunner {
        @Autowired
        private OrganizationRepository organizationRepository;
        private UserRepository userRepository;

        @Override
        public void run(String... args) throws Exception {
            /*
            if (organizationRepository.count() == 0) {

                Organization org1 = new Organization("ppp");
                organizationRepository.save(org1);


                UserEntity user = new UserEntity("user123", "securePass", "user@email.com", UserRole.ADMIN, UserStatus.ACTIVE, org1);
                userRepository.save(user);
                System.out.println("Datos iniciales insertados.");
            }

             */
            System.out.println("Datos iniciales insertados.");
        }
}
