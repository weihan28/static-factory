package game.items.fruits;

import edu.monash.fit2099.engine.actions.ActionList;
import edu.monash.fit2099.engine.actors.Actor;
import edu.monash.fit2099.engine.items.Item;
import game.items.scraps.Purchasable;

public class EnergyDrink extends Item implements Consumable, Purchasable {
    protected final int cost = 10;
    private final int healAmount = 1;

    public EnergyDrink() {
        super("Energy Drink", '*', true);
    }

    @Override
    public String consumedBy(Actor actor) {
        actor.heal(healAmount);
        actor.removeItemFromInventory(this);
        return String.format("%s heals %s by %d", this, actor,healAmount);
    }
    @Override
    public ActionList allowableActions(Actor owner) {
        ActionList actions = super.allowableActions(owner);
        actions.add(new ConsumeAction(this));
        return actions;
    }

    @Override
    public String purchaseBy(Actor actor) {
        actor.addItemToInventory(this);
        return "You have successfully purchased " + this;
    }

    @Override
    public int getCost() {
        return cost;
    }
}
