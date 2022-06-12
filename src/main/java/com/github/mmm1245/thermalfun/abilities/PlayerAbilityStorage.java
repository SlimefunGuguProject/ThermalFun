package com.github.mmm1245.thermalfun.abilities;

import com.github.mmm1245.thermalfun.ThermalFunMain;
import com.github.mmm1245.thermalfun.api.Ability;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;
import java.util.stream.Collectors;

public class PlayerAbilityStorage {
    private final HashMap<UUID, AbilitiesList> db;
    public PlayerAbilityStorage() {
        this.db = new HashMap<>();
    }
    public void loadPlayer(Player player){
        String abilitiesRaw = player.getPersistentDataContainer().getOrDefault(ThermalFunMain.getKeys().PLAYER_ABILITIES, PersistentDataType.STRING, "");
        ArrayList<Ability> abilities = new ArrayList<>();
        String[] split = abilitiesRaw.split(",");
        for(String str : split){
            Ability ability = ThermalFunMain.getAbilityRegistery().get(str);
            if(ability != null)
                abilities.add(ability);
        }
        db.put(player.getUniqueId(), new AbilitiesList(abilities));
    }
    public void savePlayer(Player player){
        List<Ability> abilities = db.get(player.getUniqueId()).getAll();
        String str = abilities.stream().map(ability -> ability.key().toString()).collect(Collectors.joining(","));
        player.getPersistentDataContainer().set(ThermalFunMain.getKeys().PLAYER_ABILITIES, PersistentDataType.STRING, str);
        db.remove(player.getUniqueId());
    }
    public AbilitiesList forPlayer(Player player){
        return db.get(player.getUniqueId());
    }

    public class AbilitiesList {
        private ArrayList<Ability> abilities;
        private int current;
        private AbilitiesList(ArrayList<Ability> abilities) {
            this.abilities = abilities;
            this.current = 0;
        }
        public Ability getCurrent(){
            if(abilities.isEmpty())
                return null;
            return abilities.get(current);
        }
        public void next(){
            if(abilities.isEmpty())
                return;
            this.current = (current+1)%abilities.size();
        }
        public boolean learn(Ability ability){
            if(abilities.contains(ability))
                return false;
            abilities.add(ability);
            return true;
        }
        public boolean revoke(Ability ability){
            if(!abilities.contains(ability))
                return false;
            int index = abilities.indexOf(ability);
            if(current >= index && current != 0)
                current--;
            abilities.remove(ability);
            return true;
        }
        public List<Ability> getAll(){
            return Collections.unmodifiableList(abilities);
        }
    }
}
