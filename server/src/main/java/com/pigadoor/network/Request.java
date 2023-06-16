package com.pigadoor.network;

import com.pigadoor.data.SpaceMarine;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

public class Request implements Serializable {

    @Serial
    private static final long serialVersionUID = 1234567L;
    private String command;
    private SpaceMarine spaceMarine;
    private Integer key;
    private Integer health;
    private Float height;
    private List<Request> script;

    public Request() {}

    public String getCommand() {
        return command;
    }

    public SpaceMarine getSpaceMarine() {
        return spaceMarine;
    }

    public Integer getKey() {
        return key;
    }

    public Integer getHealth() {
        return health;
    }

    public Float getHeight() {
        return height;
    }

    public List<Request> getScript() {
        return script;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    public void setSpaceMarine(SpaceMarine spaceMarine) {
        this.spaceMarine = spaceMarine;
    }

    public void setKey(Integer key) {
        this.key = key;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public void setScript(List<Request> script) {
        this.script = script;
    }

    @Override
    public String toString() {
        return "Request{" +
                "command='" + command + '\'' +
                ", spaceMarine=" + spaceMarine +
                ", key=" + key +
                ", health=" + health +
                ", height=" + height +
                ", script=" + script +
                '}';
    }

}
