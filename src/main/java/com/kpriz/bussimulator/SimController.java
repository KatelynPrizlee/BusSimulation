package com.kpriz.bussimulator;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api")
public class SimController{

    @GetMapping("/runSim")
    public Seat[][] runSimulation(){
        BusManager busSim = new BusManager();
        return busSim.runSim();
    }
    
}
