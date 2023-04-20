package ru.appline.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/",consumes ="application/json", produces = "application/json")
public class CompassService {

    private Map<String, String> compassMap = new HashMap<String, String>();

    public CompassService() {
        compassMap.put("North", "316-45");
        compassMap.put("North-East", "46-135");
        compassMap.put("East", "136-225");
        compassMap.put("South-East", "226-315");
        compassMap.put("South", "316-45");
        compassMap.put("South-West", "46-135");
        compassMap.put("West", "136-225");
        compassMap.put("North-West", "226-315");
    }

    @PostMapping(value = "/",consumes ="application/json", produces = "application/json")
    public String setCompass(@RequestBody Map<String, String> map) {
        this.compassMap = map;
        return "Compass has been set";
    }

    @GetMapping(value = "/",consumes ="application/json", produces = "application/json")
    public String getDirection(@RequestParam int degree) {
        String direction = "";

        for (Map.Entry<String, String> entry : compassMap.entrySet()) {
            String[] degrees = entry.getValue().split("-");
            int start = Integer.parseInt(degrees[0]);
            int end = Integer.parseInt(degrees[1]);

            if (degree >= start && degree <= end) {
                direction = entry.getKey();
                break;
            } else if (start > end) {
                if (degree >= start && degree <= 359 || degree >= 0 && degree <= end) {
                    direction = entry.getKey();
                    break;
                }
            }
        }

        return "{\"Side\": \"" + direction + "\"}";
    }
}