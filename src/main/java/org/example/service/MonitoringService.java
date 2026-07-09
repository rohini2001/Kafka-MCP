package org.example.service;

import org.springframework.stereotype.Service;

@Service
public class MonitoringService {

    public String health(){

        return "Kafka Cluster is Healthy";

    }

}