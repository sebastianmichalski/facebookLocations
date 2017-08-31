package com.fb.locations.model;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * Facebook Place POJO
 */
@Data
@AllArgsConstructor
public class FacebookPlace {
    private String name;
    private Double latitude;
    private Double longitude;
}
