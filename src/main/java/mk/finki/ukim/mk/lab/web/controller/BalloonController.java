package mk.finki.ukim.mk.lab.web.controller;

import com.sun.net.httpserver.Authenticator;
import mk.finki.ukim.mk.lab.model.Balloon;
import mk.finki.ukim.mk.lab.model.Manufacturer;
import mk.finki.ukim.mk.lab.service.BalloonService;
import mk.finki.ukim.mk.lab.service.ManufacturerService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(value = {"","/balloons"})
public class BalloonController {
    private final BalloonService balloonService;
    private final ManufacturerService manufacturerService;

    public BalloonController(BalloonService balloonService, ManufacturerService manufacturerService) {
        this.balloonService = balloonService;
        this.manufacturerService = manufacturerService;
    }

    @GetMapping
    public String getBalloonsPage(@RequestParam(required = false) String error, @RequestParam(required = false) String manufacturerCountry,
                                  @RequestParam(required = false) String manufacturerName, Model model, Authentication authentication) {





        List<Balloon> balloons = balloonService.filteredByManufacturerNameOrCountry(manufacturerName,manufacturerCountry);
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);

        if(balloons != null){
            model.addAttribute("balloons", balloons);
            return "listBalloons";
        }
        if (error != null && !error.isEmpty()) {
            model.addAttribute("hasError", true);
            model.addAttribute("error", error);
        }
        balloons = balloonService.listAll();
        model.addAttribute("balloons", balloons);
        return "listBalloons";
    }

    @PostMapping
    public String proceedWithOrder(HttpServletRequest request) {
        String chosenColor = request.getParameter("color");
        request.getSession().setAttribute("color", chosenColor);
        return "redirect:/selectBalloon";
    }

    @PostMapping("/add")
    public String saveBalloon(@RequestParam String name,
                              @RequestParam String description,
                              @RequestParam Long manufacturerId,
                              @RequestParam(required = false) Long balloonId) {
        balloonService.save(name, description, manufacturerId, balloonId);
        return "redirect:/balloons";
    }

    @DeleteMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        this.balloonService.deleteById(id);
        return "redirect:/balloons";
    }

    @GetMapping("/edit-form/{id}")
    public String getEditBalloonPage(@PathVariable Long id, Model model) {

        if (balloonService.findById(id).isPresent()) {
            Balloon balloon = balloonService.findById(id).get();
            List<Manufacturer> manufacturers = manufacturerService.findAll();
            model.addAttribute("manufacturers", manufacturers);
            model.addAttribute("balloon", balloon);
            model.addAttribute("bodycontent", "add-balloon");

            return "master-template";
        }
        return "redirect:/balloons?error=BalloonNotFound";
    }

    @GetMapping("/add-form")
    public String getAddBalloonPage(Model model) {
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        model.addAttribute("manufacturers", manufacturers);
        model.addAttribute("bodycontent", "add-balloon");

        return "master-template";
    }

}
