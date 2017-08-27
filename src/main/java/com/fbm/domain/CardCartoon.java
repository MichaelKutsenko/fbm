package com.fbm.domain;

import com.fbm.constants.CardType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * The individual type of card.
 * Created by Mocart on 06-Jul-17.
 *
 * @see Card
 */
@Entity
@DiscriminatorValue("CARTOON")
public class CardCartoon extends Card {

    @Override
    public String determinateType() {
        return CardType.CARTOON.getType();
    }
}
