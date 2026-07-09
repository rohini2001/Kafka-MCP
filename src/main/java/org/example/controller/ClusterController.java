package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.service.ClusterService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/cluster")
@RequiredArgsConstructor
public class ClusterController {

    private final ClusterService clusterService;

    @GetMapping
    public Object getClusterInfo(){

        return clusterService.getClusterInfo();

    }

}