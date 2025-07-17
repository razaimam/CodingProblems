package org.example;

import java.io.*;
import java.util.*;


public class AssembleBots {


    /*
We have a bin of robot parts in a factory. Each part goes to a robot with a specific, unique name. Each part will be described by a string, with the name of the robot and the part name separated by an underscore, like "Rosie_arm".

All robots are made of the same types of parts, and we have a string of all of the parts required to form a complete robot. Given a list of available parts, return the collection of robot names for which we can build at least one complete robot.

Sample Input:

all_parts = [
    "Rosie_claw",
    "Rosie_sensors",
    "Dustie_case",
    "Optimus_sensors",
    "Rust_sensors",
    "Rosie_case",
    "Rust_case",
    "Optimus_speaker",
    "Rosie_wheels",
    "Rosie_speaker",
    "Dustie_case",
    "Dustie_arms",
    "Rust_claw",
    "Dustie_case",
    "Dustie_speaker",
    "Optimus_case",
    "Optimus_wheels",
    "Rust_legs",
    "Optimus_sensors" ]

required_parts_1 = "sensors,case,speaker,wheels"

- iteration:
   sensors: list of robots [A, B,  C]
     case:  list of robots [B, E, F]
      ? (two lists) = ??

required_parts_2 = "sensors,case,speaker,wheels,claw"
required_parts_3 = "sensors,case,screws"

Expected Output (output can be in any order):

get_robots(all_parts, required_parts_1) => ["Optimus", "Rosie"]
get_robots(all_parts, required_parts_2) => ["Rosie"]
get_robots(all_parts, required_parts_3) => []

N: Number of elements in all_parts
P: Number of elements in required_parts
*/




        public static void main(String[] argv) {
            String required_parts_1 = "sensors,case,speaker,wheels";
            String required_parts_2 = "sensors,case,speaker,wheels,claw";
            String required_parts_3 = "sensors,case,screws";

            String[] all_parts = {
                    "Rosie_claw",
                    "Rosie_sensors",
                    "Dustie_case",
                    "Optimus_sensors",
                    "Rust_sensors",
                    "Rosie_case",
                    "Rust_case",
                    "Optimus_speaker",
                    "Rosie_wheels",
                    "Rosie_speaker",
                    "Dustie_case",
                    "Dustie_arms",
                    "Rust_claw",
                    "Dustie_case",
                    "Dustie_speaker",
                    "Optimus_case",
                    "Optimus_wheels",
                    "Rust_legs",
                    "Optimus_sensors"};

            List<String> pbots =   getBot(all_parts, required_parts_1);
            System.out.println(pbots);
            Arrays.asList();

        }
        public static List<String> getBot(String[] all_parts, String required_parts_1){
            Map<String, List<String>> partTobotsMap = new HashMap();

            Set<String> botset = new HashSet();

            for(String str : all_parts){
                String[] strs =  str.split("_");
                botset.add(strs[0]);
                List<String> botList = partTobotsMap.containsKey(strs[1]) ? partTobotsMap.get(strs[1]) : new ArrayList<>();
                botList.add(strs[0]);
                partTobotsMap.put(strs[1], botList);
            }
            List<String> pbots = new ArrayList<>();

            for(String bot : botset ) {
                boolean notThebot = false;
                for(String part : required_parts_1.split(",")) {
                    List<String> botHasParts = partTobotsMap.get(part);
                    if( botHasParts.contains(bot)){
                        continue;
                    } else {
                        notThebot = true;
                        break;
                    }
                }
                if(!notThebot){
                    pbots.add(bot);
                }
            }

            return pbots;

        }
    }

