package com.Main.Controller;

import com.Main.Entity.Note;
import com.Main.Service.NotesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    @Autowired
    private NotesService notesService;

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> updateWeather(@RequestBody String weather)
    {
        weather = weather.replace("+", " ");
        weather = weather.substring(0, weather.length()-1);
        notesService.setWeatherNote(weather);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }

}
