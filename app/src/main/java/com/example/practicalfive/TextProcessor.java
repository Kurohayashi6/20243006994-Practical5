package com.example.practicalfive;

public class TextProcessor {

    public String processMunicipality(String municipality) {
        if (municipality == null) {
            return null;
        }

        String trimmed = municipality.trim();
        if (trimmed.isEmpty()) {
            return null;
        }

        if (trimmed.equalsIgnoreCase("beijing")) {
            return "Beijing";
        } else if (trimmed.equalsIgnoreCase("shanghai")) {
            return "Shanghai";
        } else if (trimmed.equalsIgnoreCase("tianjin")) {
            return "Tianjin";
        } else if (trimmed.equalsIgnoreCase("chongqing")) {
            return "Chongqing";
        } else {
            return null;
        }
    }
}
