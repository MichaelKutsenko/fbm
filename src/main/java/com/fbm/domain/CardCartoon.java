package com.fbm.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Mocart on 06-Jul-17.
 */
@Entity
@DiscriminatorValue("CARTOON")
public class CardCartoon extends Card {


}
