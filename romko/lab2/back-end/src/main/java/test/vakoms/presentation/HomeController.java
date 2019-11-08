package test.vakoms.presentation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import test.vakoms.infrastructure.persistence.*;

@Controller
public class HomeController
{
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private FareRepository fareRepository;

    @RequestMapping("/showUsers")
    public String users(Model model)
    {
        model.addAttribute("users", userRepository.findAll());
        return "users_list";
    }

    @RequestMapping("/showRooms")
    public String rooms(Model model)
    {
        model.addAttribute("rooms", locationRepository.findAll());
        return "rooms_list";
    }

    @RequestMapping("/showRoles")
    public String roles(Model model)
    {
        model.addAttribute("roles", roleRepository.findAll());
        return "roles_list";
    }

    @RequestMapping("/showDevices")
    public String devices(Model model)
    {
        model.addAttribute("devices", carRepository.findAll());
        return "devices_list";
    }

    @RequestMapping("/showBeacons")
    public String beacons(Model model)
    {
        model.addAttribute("beacons", fareRepository.findAll());
        return "beacons_list";
    }
}
