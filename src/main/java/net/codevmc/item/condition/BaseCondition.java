package net.codevmc.item.condition;

public abstract class BaseCondition implements Condition{

    public boolean equals(Object o){
        if((o instanceof Condition)) return false;
        if(o==null)return false;
        Condition otherCondition = (Condition) o;
        return this.ConditionName().equals(otherCondition.ConditionName());
    }

    public int hashCode(){
        return this.ConditionName().hashCode();
    }
}
