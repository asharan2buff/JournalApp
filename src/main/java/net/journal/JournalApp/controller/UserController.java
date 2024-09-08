package net.journal.JournalApp.controller;

import net.journal.JournalApp.api.response.WeatherResponse;
import net.journal.JournalApp.entity.User;
import net.journal.JournalApp.service.UserService;
import net.journal.JournalApp.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private WeatherService weatherService;

//    @GetMapping
//    public List<User> getUser(){
//        return userService.getAll();
//    }

    @PutMapping//in basic auth give username and password
    public ResponseEntity<?> updateUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//spring security already has username. No need to pass it explicitly
        String userName = authentication.getName();
        User userInDb = userService.findByUsername(userName);
            userInDb.setUsername(user.getUsername());
            userInDb.setPassword(user.getPassword());
            userService.saveEntry(userInDb);
            return new ResponseEntity<>(userInDb, HttpStatus.OK);
    }

    @DeleteMapping//in basic auth give username and password
    public ResponseEntity<?> deleteUser(@RequestBody User user){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();//spring security already has username. No need to pass it explicitly
        String userName = authentication.getName();
        userService.deleteByUsername(userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> greeting() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        WeatherResponse weatherResponse = weatherService.getFirstWeatherInfo();
        String greeting = "";
        if (weatherResponse != null && weatherResponse.getTemperature() != null
                && weatherResponse.getTemperature().getMetric() != null) {
            double value = weatherResponse.getTemperature().getMetric().getValue();
            String unit = weatherResponse.getTemperature().getMetric().getUnit();
            String type = weatherResponse.getWeatherText();
            greeting = ", Weather feels like " + value + " " + unit+" and is " +type;
        }
        return new ResponseEntity<>("Hi " + authentication.getName() + greeting, HttpStatus.OK);
    }


}
