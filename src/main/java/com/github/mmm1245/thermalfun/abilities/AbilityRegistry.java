package com.github.mmm1245.thermalfun.abilities;

import com.github.mmm1245.thermalfun.api.Ability;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

public class AbilityRegistry {
    private final HashMap<String, Ability> abilities;
    private List<String> keysCache;
    public AbilityRegistry() {
        this.abilities = new HashMap<>();
    }
    public boolean register(Ability ability){
        if(abilities.containsKey(ability.key()))
            return false;
        abilities.put(ability.key().toString(), ability);
        this.keysCache = null;
        return true;
    }
    public Ability get(String key){
        return abilities.get(key);
    }
    public List<String> getKeys(){
        if(this.keysCache == null)
            this.keysCache = this.abilities.keySet().stream().toList();
        return this.keysCache;
    }
    public void clear(){
        this.abilities.clear();
        this.keysCache = null;
    }
}
