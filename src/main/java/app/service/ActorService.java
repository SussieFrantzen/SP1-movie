package app.service;

import app.dao.ActorDAO;
import app.dtos.ActorDTO;
import app.entities.Actor;

import java.util.List;

public class ActorService {

    private final ActorDAO actorDAO;

    public ActorService(ActorDAO actorDAO) {
        this.actorDAO = actorDAO;
    }

    public ActorDTO createActor(ActorDTO dto) {
        Actor actor = convertToEntity(dto);

        Actor savedActor = actorDAO.create(actor);

        return convertToDTO(savedActor);
    }

    public ActorDTO getActor(int id) {
        Actor actor = actorDAO.findById(id);

        if (actor == null) {
            return null;
        }

        return convertToDTO(actor);
    }

    public List<ActorDTO> getAllActors() {
        return actorDAO.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public ActorDTO updateActor(ActorDTO dto) {
        Actor actor = convertToEntity(dto);

        Actor updatedActor = actorDAO.update(actor);

        return convertToDTO(updatedActor);
    }

    public void removeActor(int id) {
        actorDAO.remove(id);
    }

    private Actor convertToEntity(ActorDTO dto) {
        Actor actor = new Actor();

        actor.setId(dto.getId());
        actor.setMdbId(dto.getMdbId());
        actor.setName(dto.getName());

        return actor;
    }


    private ActorDTO convertToDTO(Actor actor) {
        ActorDTO dto = new ActorDTO();

        dto.setId(actor.getId());
        dto.setMdbId(actor.getMdbId());
        dto.setName(actor.getName());

        return dto;
    }


    public ActorDTO getActorByMdbId(int mdbId) {

        Actor actor = actorDAO.findByMdbId(mdbId);

        if (actor == null) {
            return null;
        }

        return convertToDTO(actor);
    }


}

