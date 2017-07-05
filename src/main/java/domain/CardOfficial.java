package domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 * Created by Mocart on 06-Jul-17.
 */
@Entity
@DiscriminatorValue("OFFICIAL")
public class CardOfficial extends Card {
}
