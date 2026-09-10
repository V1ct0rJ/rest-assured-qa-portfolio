package com.qa.portfolio.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class SubjectDto {

    @JsonProperty("id")
    private String id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("status")
    private String status;

    @JsonProperty("dangerLevel")
    private int dangerLevel;

    // Alapértelmezett konstruktor (szükséges a Jackson deszerializációhoz)
    public SubjectDto() {}

    public SubjectDto(String name, String status, int dangerLevel) {
        this.name = name;
        this.status = status;
        this.dangerLevel = dangerLevel;
    }

    // Getterek és Setterek
    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public int getDangerLevel() { return dangerLevel; }
    public void setDangerLevel(int dangerLevel) { this.dangerLevel = dangerLevel; }
}