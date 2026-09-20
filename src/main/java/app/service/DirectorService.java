package app.service;

import app.dao.DirectorDAO;
import app.dtos.DirectorDTO;
import app.entities.Director;

import java.util.List;

public class DirectorService {

    private final DirectorDAO directorDAO;

    public DirectorService(DirectorDAO directorDAO) {
        this.directorDAO = directorDAO;
    }

    public DirectorDTO createDirector(DirectorDTO dto) {
        Director director = convertToEntity(dto);

        Director savedDirector = directorDAO.create(director);

        return convertToDTO(savedDirector);
    }

    public DirectorDTO getDirector(int id) {
        Director director = directorDAO.findById(id);

        if (director == null) {
            return null;
        }

        return convertToDTO(director);
    }

    public List<DirectorDTO> getAllDirectors() {
        return directorDAO.findAll()
                .stream()
                .map(this::convertToDTO)
                .toList();
    }

    public DirectorDTO updateDirector(DirectorDTO dto) {
        Director director = convertToEntity(dto);

        Director updatedDirector = directorDAO.update(director);

        return convertToDTO(updatedDirector);
    }

    public void removeDirector(int id) {
        directorDAO.remove(id);
    }

    private Director convertToEntity(DirectorDTO dto) {
        Director director = new Director();

        director.setId(dto.getId());
        director.setMdbId(dto.getMdbId());
        director.setName(dto.getName());

        return director;
    }

    private DirectorDTO convertToDTO(Director director) {
        DirectorDTO dto = new DirectorDTO();

        dto.setId(director.getId());
        dto.setMdbId(director.getMdbId());
        dto.setName(director.getName());

        return dto;
    }

    public DirectorDTO getDirectorByMdbId(int mdbId) {

        Director director = directorDAO.findByMdbId(mdbId);

        if (director == null) {
            return null;
        }

        return convertToDTO(director);
    }


}
