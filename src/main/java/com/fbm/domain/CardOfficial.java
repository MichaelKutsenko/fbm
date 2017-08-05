package com.fbm.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * The individual type of card.
 * Created by Mocart on 06-Jul-17.
 *
 * @see Card
 */
@Entity
@DiscriminatorValue("OFFICIAL")
public class CardOfficial extends Card {
}