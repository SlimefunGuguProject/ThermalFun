package com.github.mmm1245.thermalfun;

public enum EAbility {
    FIRE(1, "Fire ability"),
    LAVA(2, "Lava ability"),
    FIREBALL(3, "Fireball shooting ability"),
    FIRE_RES(4, "Fire resistance ability");
    public final byte id;
    public final String userFriendyName;
    EAbility(int id, String userFriendyName) {
        this.id = (byte) id;
        this.userFriendyName = userFriendyName;
    }
    public static EAbility forId(int id){
        for(EAbility ability : values()){
            if(ability.id == id)
                return ability;
        }
        return null;
    }
}
