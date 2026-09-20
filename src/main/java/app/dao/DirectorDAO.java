package app.dao;

import app.entities.Director;

public interface DirectorDAO extends DAO<Director> {
    Director findByMdbId(int mdbId);

}
