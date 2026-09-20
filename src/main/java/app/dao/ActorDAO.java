package app.dao;

import app.entities.Actor;

public interface ActorDAO extends DAO<Actor> {


    Actor findByMdbId(int mdbId);

}

