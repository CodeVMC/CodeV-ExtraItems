package net.codevmc.extraItems.item.condition;

public abstract class BaseCondition implements Condition{

    public boolean equals(Object o){
        if((o instanceof Condition)) return false;
        if(o==null)return false;
        Condition otherCondition = (Condition) o;
        return this.conditionName().equals(otherCondition.conditionName());
    }

    public int hashCode(){
        return this.conditionName().hashCode();
    }
}
