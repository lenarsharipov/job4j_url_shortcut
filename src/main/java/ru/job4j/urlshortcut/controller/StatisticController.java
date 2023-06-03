package ru.job4j.urlshortcut.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.job4j.urlshortcut.dto.StatUrlDTO;
import ru.job4j.urlshortcut.service.UrlService;

import java.util.List;

/**
 * Statistics providing controller.
 */
@RestController
@RequestMapping("/statistic")
@AllArgsConstructor
public class StatisticController {
    /**
     * Url Service
     */
    private final UrlService urlService;

    /**
     * Get list of all persisted urls with call statistics.
     * @return ResponseEntity of StatUrlDto.
     */
    @GetMapping("/")
    public ResponseEntity<List<StatUrlDTO>> getStatistic() {
        return new ResponseEntity<>(
                urlService.findAll(),
                HttpStatus.OK
        );
    }

}
