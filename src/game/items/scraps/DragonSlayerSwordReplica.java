package game.items.scraps;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.positions.Location;
import edu.monash.fit2099.engine.weapons.WeaponItem;
import game.actors.Status;
import game.actors.actions.AttackAction;
import game.items.actions.Purchasable;

import java.util.Random;

/**
 * A class that represents a Dragon Slayer Sword in the game.
 */
public class DragonSlayerSwordReplica extends WeaponItem implements Purchasable {
    private final Random random = new Random();
    private final int cost;

    public DragonSlayerSwordReplica() {
        super("DragonSlayerSwordReplica", 'x', 50, "slashes", 75);
        cost = 100;
    }

    @Override
    public String purchaseBy(Actor actor) {
        int purchaseRNG = random.nextInt(100);
        int purchaseChance = 50;
        if (purchaseRNG <= purchaseChance) {
            actor.addItemToInventory(this);
            return String.format("%s successfully purchased %s", actor, this);
        } else {
            return String.format("%d credits are taken from %s, but %s doesn't receive anything in return!",getCost(), actor, actor);
        }
    }

    @Override
    public int getCost() {
        return cost;
    }

    public ActionList allowableActions(Actor otherActor, Location location){
        ActionList actions = new ActionList();
        if (otherActor.hasCapability(Status.HOSTILE_TO_PLAYER)) {
            actions.add(new AttackAction(otherActor, location.toString(), this));
        }
        return actions;
    }
}
