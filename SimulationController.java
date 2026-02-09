@RestController
public class SimulationController {

    @GetMapping("/runSimulation")
    public List<SeatStep> runSimulation(){
        Simulation sim = new Simulation();
        return sim.run();

    }
}
