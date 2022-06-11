package com.github.mmm1245.thermalfun.abilities;

import org.bukkit.NamespacedKey;

import java.util.Collections;
import java.util.HashMap;
import java.util.Set;

public class AbilityRegistry {
    private final HashMap<String,Ability> abilities;
    public AbilityRegistry() {
        this.abilities = new HashMap<>();
    }
    public boolean register(Ability ability){
        if(abilities.containsKey(ability.key()))
            return false;
        abilities.put(ability.key().toString(), ability);
        return true;
    }
    public Ability get(String key){
        return abilities.get(key);
    }
    public Set<String> getAll(){
        return Collections.unmodifiableSet(this.abilities.keySet());
    }
    public void clear(){
        this.abilities.clear();
    }
}
