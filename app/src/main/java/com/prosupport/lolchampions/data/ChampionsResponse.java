package com.prosupport.lolchampions.data;

import java.util.HashMap;
import java.util.Map;

public class ChampionsResponse {
    public String type;
    public String version;
    public Map<String, Champion> data = new HashMap<>();
}
