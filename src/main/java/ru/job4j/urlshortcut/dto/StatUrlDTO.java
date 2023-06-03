package ru.job4j.urlshortcut.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * DTO with statistics of calls.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatUrlDTO {
    /**
     * Url name
     */
    private String url;

    /**
     * Total calls count
     */
    private int total;
}
