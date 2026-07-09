package org.example.service;

import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.admin.AdminClient;
import org.apache.kafka.clients.admin.DescribeClusterResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClusterService {

    private final AdminClient adminClient;

    public DescribeClusterResult getClusterInfo(){

        return adminClient.describeCluster();

    }

}