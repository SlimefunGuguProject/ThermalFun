package com.github.mmm1245.thermalfun;

import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.*;

public class PlayerAbilityStorage {
    private final HashMap<UUID, AbilitiesList> db;
    public PlayerAbilityStorage() {
        this.db = new HashMap<>();
    }
    public void loadPlayer(Player player){
        byte[] abilitiesRaw = player.getPersistentDataContainer().getOrDefault(ThermalFunMain.getKeys().PLAYER_ABILITIES, PersistentDataType.BYTE_ARRAY, new byte[0]);
        ArrayList<EAbility> abilities = new ArrayList<>();  //todo: maybe use streams
        for(byte id : abilitiesRaw){
            EAbility ability = EAbility.forId(id);
            if(abilities != null)
                abilities.add(ability);
        }
        db.put(player.getUniqueId(), new AbilitiesList(abilities));
    }
    public void savePlayer(Player player){
        List<EAbility> abilities = db.get(player.getUniqueId()).getAll();
        byte[] abilitiesRaw = new byte[abilities.size()];   //todo: maybe use streams
        for(int i = 0;i < abilities.size();i++){
            abilitiesRaw[i] = abilities.get(i).id;
        }

        player.getPersistentDataContainer().set(ThermalFunMain.getKeys().PLAYER_ABILITIES, PersistentDataType.BYTE_ARRAY, abilitiesRaw);
        db.remove(player.getUniqueId());
    }
    public AbilitiesList forPlayer(Player player){
        return db.get(player.getUniqueId());
    }

    public class AbilitiesList {
        private ArrayList<EAbility> abilities;  //todo: use set
        private int current;
        private AbilitiesList(ArrayList<EAbility> abilities) {
            this.abilities = abilities;
            this.current = 0;
        }
        public EAbility getCurrent(){
            if(abilities.isEmpty())
                return null;
            return abilities.get(current);
        }
        public void next(){
            if(abilities.isEmpty())
                return;
            this.current = (current+1)%abilities.size();
        }
        public boolean learn(EAbility ability){
            if(abilities.contains(ability))
                return false;
            abilities.add(ability);
            return true;
        }
        public boolean revoke(EAbility ability){
            if(!abilities.contains(ability))
                return false;
            int index = abilities.indexOf(ability);
            if(current >= index && current != 0)
                current--;
            abilities.remove(ability);
            return true;
        }
        public List<EAbility> getAll(){
            return Collections.unmodifiableList(abilities);
        }
    }
}
