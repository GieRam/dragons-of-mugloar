package dragons.entities.game;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Solution {

    private Dragon dragon;

    public Solution(@JsonProperty("dragon") Dragon dragon) {
        this.dragon = dragon;
    }

    public Dragon getDragon() {
        return dragon;
    }
}
