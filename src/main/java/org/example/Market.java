package org.example;

import java.util.ArrayList;
import java.util.List;

public class Market implements QueueBehavoir, MarketBehavoir{
    private List<Actor> actorList = new ArrayList<>();
    @Override
    public void acceptToMarket(Actor actor) {
        takeInQueue(actor);
        System.out.println(actor.getName() + " Entered shop");
    }

    @Override
    public void releaseFromMarket(List<Actor> actors) {
        for (Actor actor : actors) {
            actorList.remove(actor);
            System.out.println(actor.getName() + " quit the shop");
        }
    }

    @Override
    public void update() {
        takeOrders();
        giveOrders();
        releaseFromQueue();
    }

    @Override
    public void takeInQueue(Actor actor) {
        actorList.add(actor);
        System.out.println(actor.getName() + " take in queue");
    }

    @Override
    public void takeOrders() {
        for (Actor actor : actorList) {
            if(!actor.isMakeOrder) {
                actor.setMakeOrder(true);
                System.out.println(actor.getName() + " made an order");
            }
        }
    }

    @Override
    public void giveOrders() {
        for (Actor actor : actorList) {
            if(actor.isMakeOrder) {
                actor.setTakeOrder(true);
                System.out.println(actor.getName() + " take an order");
            }
        }
    }

    @Override
    public void releaseFromQueue() {
        List<Actor> releasedActors = new ArrayList<>();
        for (Actor actor : actorList) {
            if(actor.isTakeOrder()) {
                releasedActors.add(actor);
                System.out.println(actor.getName() + " quit the queue");
            }
        }
        releaseFromMarket(releasedActors);
    }
}
