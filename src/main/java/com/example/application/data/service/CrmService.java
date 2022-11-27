package com.example.application.data.service;

import com.example.application.data.entity.ServicesList;
import com.example.application.data.repository.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CrmService {
    private final OrderRepository orderRepository;
    private final PersonalRepository personalRepository;
    private final RoleRepository roleRepository;
    private final ServicesListRepository servicesListRepository;
    private final StatusesRepository statusesRepository;
    private final UserRepository userRepository;

    public CrmService(OrderRepository orderRepository,
                      PersonalRepository personalRepository,
                      RoleRepository roleRepository,
                      ServicesListRepository servicesListRepository,
                      StatusesRepository statusesRepository,
                      UserRepository userRepository) {

        this.orderRepository = orderRepository;
        this.personalRepository = personalRepository;
        this.roleRepository = roleRepository;
        this.servicesListRepository = servicesListRepository;
        this.statusesRepository = statusesRepository;
        this.userRepository = userRepository;
    }

    public List<ServicesList> findAllServices(String filterText) {
        if(filterText == null || filterText.isEmpty()) {
            return servicesListRepository.findAll();
        } else {
            return servicesListRepository.search(filterText);
        }
    }

    public long countServices() {
        return servicesListRepository.count();
    }

    public void deleteService(ServicesList service) {
        servicesListRepository.delete(service);
    }

    public void saveService(ServicesList service) {
        if(service == null) {
            System.err.println("Service is null.");
            return;
        }
        servicesListRepository.save(service);
    }
}
