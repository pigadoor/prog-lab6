package com.pigadoor.wrappers;

import com.pigadoor.application.CollectionManager;
import com.pigadoor.data.Chapter;
import com.pigadoor.data.Coordinates;
import com.pigadoor.data.MeleeWeapon;

import java.time.LocalDateTime;

/**
 * Wrapper of Main class that stored in collection
 */
public class SpaceMarineWrapper {

    private int id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private String creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float health; //Значение поля должно быть больше 0
    private Long heartCount; //Значение поля должно быть больше 0, Максимальное значение поля: 3
    private float height; //Поле не может быть null
    private MeleeWeapon meleeWeapon; //Поле не может быть null
    private Chapter chapter; //Поле может быть null

    public SpaceMarineWrapper() {}

    public SpaceMarineWrapper(CollectionManager collectionStorage,
                              String name,
                              Coordinates coordinates,
                              float health,
                              Long heartCount,
                              int height,
                              MeleeWeapon meleeWeapon,
                              Chapter chapter){
        this.id = collectionStorage.getMaxId() + 1;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = null;
        this.health = health;
        this.heartCount = heartCount;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }
    public SpaceMarineWrapper(int id,
                              String name,
                              Coordinates coordinates,
                              float health,
                              Long heartCount,
                              int height,
                              MeleeWeapon meleeWeapon,
                              Chapter chapter){
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = null;
        this.health = health;
        this.heartCount = heartCount;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    public SpaceMarineWrapper(int id, String name, Coordinates coordinates, LocalDateTime creationDate, int health,
                              Long heartCount, float height, MeleeWeapon meleeWeapon, Chapter chapter) {
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = null;
        this.health = health;
        this.heartCount = heartCount;
        this.height = height;
        this.meleeWeapon = meleeWeapon;
        this.chapter = chapter;
    }

    @Override
    public String toString() {
        return "Person{"
                + "id=" + id
                + ", name='" + name + '\''
                + ", coordinates=" + coordinates
                + ", creationDate=" + creationDate
                + ", health=" + health
                + ", heartCount=" + heartCount
                + ", height=" + height
                + ", meleeWeapon=" + meleeWeapon
                + ", chapter=" + chapter
                + '}';
    }



    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public float getHealth() {
        return health;
    }

    public long getHeartCount() {
        return heartCount;
    }

    public float getHeight() {
        return height;
    }

    public MeleeWeapon getMeleeWeapon() {
        return meleeWeapon;
    }

    public Chapter getChapter() {
        return chapter;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void setHeartCount(Long heartCount) {
        this.heartCount = heartCount;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public void setMeleeWeapon(MeleeWeapon meleeWeapon) {
        this.meleeWeapon = meleeWeapon;
    }

    public void setChapter(Chapter chapter) {
        this.chapter = chapter;
    }

    public Coordinates getCoordinates() {
        return coordinates;
    }

    public String getCreationDate() {
        return creationDate;
    }
}