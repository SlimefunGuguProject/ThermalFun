package com.github.mmm1245.thermalfun;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import java.util.HashMap;
import java.util.UUID;

public class PlayerHeatStorage {
    private final HashMap<UUID, HeatValues> db;
    public PlayerHeatStorage() {
        this.db = new HashMap<>();
    }
    public void loadPlayer(Player player){
        int max = player.getPersistentDataContainer().getOrDefault(ThermalFunMain.getKeys().HEAT_MAX, PersistentDataType.INTEGER, 1);
        int current = player.getPersistentDataContainer().getOrDefault(ThermalFunMain.getKeys().HEAT_CURRENT, PersistentDataType.INTEGER, 1);
        db.put(player.getUniqueId(), new HeatValues(max, current));
    }
    public void savePlayer(Player player){
        HeatValues values = db.get(player.getUniqueId());
        player.getPersistentDataContainer().set(ThermalFunMain.getKeys().HEAT_MAX, PersistentDataType.INTEGER, values.max);
        player.getPersistentDataContainer().set(ThermalFunMain.getKeys().HEAT_CURRENT, PersistentDataType.INTEGER, values.current);
    }
    public HeatValues forPlayer(Player player){
        return db.get(player.getUniqueId());
    }

    public class HeatValues {
        public int max;
        public int current;
        public HeatValues(int max, int current) {
            this.max = max;
            this.current = current;
        }
    }
}
