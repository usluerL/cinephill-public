package com.byusluer.movierest.model.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterResponse {

    private String actorName;
    private String movieName;
    private String charName;
    private String answer;

    public CharacterResponse(String actorName, String movieName, String charName) {
        this.actorName = actorName;
        this.movieName = movieName;
        this.charName = charName;
        this.answer = String.format("%s" +" character name in " + "%s"+ " is "+ "%s",actorName,movieName,charName);
    }
}
