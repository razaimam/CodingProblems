package org.example;

import java.util.*;

public class AssembleBots {

    /**
     * Problem:
     * We have a list of parts, where each part belongs to a robot and is in the format: "RobotName_part".
     * Given a list of all parts and the required parts to assemble a full robot,
     * return a list of robot names that can be fully assembled.
     *
     * Example:
     * allParts = ["Rosie_claw", "Rosie_sensors", "Optimus_case", ...]
     * requiredParts = "sensors,case,speaker,wheels"
     * Output: ["Optimus", "Rosie"]
     */

    public static void main(String[] args) {
        String[] allParts = {
                "Rosie_claw", "Rosie_sensors", "Dustie_case", "Optimus_sensors",
                "Rust_sensors", "Rosie_case", "Rust_case", "Optimus_speaker",
                "Rosie_wheels", "Rosie_speaker", "Dustie_case", "Dustie_arms",
                "Rust_claw", "Dustie_case", "Dustie_speaker", "Optimus_case",
                "Optimus_wheels", "Rust_legs", "Optimus_sensors"
        };

        String required1 = "sensors,case,speaker,wheels";
        String required2 = "sensors,case,speaker,wheels,claw";
        String required3 = "sensors,case,screws";

        System.out.println(getBots(allParts, required1)); // [Rosie, Optimus]
        System.out.println(getBots(allParts, required2)); // [Rosie]
        System.out.println(getBots(allParts, required3)); // []
    }

    /**
     * Returns a list of robot names that can be fully assembled.
     *
     * @param allParts       Array of parts in "RobotName_part" format.
     * @param requiredParts  Comma-separated string of required parts.
     * @return List of robot names that have all required parts.
     */
    public static List<String> getBots(String[] allParts, String requiredParts) {
        // Map<RobotName, Set<PartName>> to store each robot and the parts it has
        Map<String, Set<String>> robotPartsMap = new HashMap<>();

        // Build the robot-to-parts map
        for (String item : allParts) {
            String[] split = item.split("_"); // e.g., "Rosie_claw" → ["Rosie", "claw"]
            String robotName = split[0];
            String partName = split[1];

            // Add the part to this robot's set
            robotPartsMap.putIfAbsent(robotName, new HashSet<>());
            robotPartsMap.get(robotName).add(partName);
        }

        // Convert required parts into a Set for quick lookup
        Set<String> requiredSet = new HashSet<>(Arrays.asList(requiredParts.split(",")));

        // Result list of robots that meet the requirement
        List<String> eligibleRobots = new ArrayList<>();

        // Check each robot if it has all required parts
        for (Map.Entry<String, Set<String>> entry : robotPartsMap.entrySet()) {
            if (entry.getValue().containsAll(requiredSet)) {
                eligibleRobots.add(entry.getKey());
            }
        }

        return eligibleRobots;
    }
}