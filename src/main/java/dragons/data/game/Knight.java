package dragons.data.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Knight {

    private String name;
    private Integer attack;
    private Integer armor;
    private Integer agility;
    private Integer endurance;

    public Knight(@JsonProperty("name") String name,
                  @JsonProperty("attack") Integer attack,
                  @JsonProperty("armor") Integer armor,
                  @JsonProperty("agility") Integer agility,
                  @JsonProperty("endurance") Integer endurance) {
        this.name = name;
        this.attack = attack;
        this.armor = armor;
        this.agility = agility;
        this.endurance = endurance;
    }

    public String getName() {
        return name;
    }

    public Integer getAttack() {
        return attack;
    }

    public Integer getArmor() {
        return armor;
    }

    public Integer getAgility() {
        return agility;
    }

    public Integer getEndurance() {
        return endurance;
    }
}
