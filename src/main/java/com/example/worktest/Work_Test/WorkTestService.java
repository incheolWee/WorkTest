package com.example.worktest.Work_Test;

import java.util.List;
import java.util.Optional;

public interface WorkTestService {
    WorkTestDTO convertToDto(WorkTest work);
    WorkTest convertToEntity(WorkTestDTO workDTO);
    WorkTestDTO createWork(WorkTestDTO workDTO);
    Optional<WorkTestDTO> getWorkById(Long id); // Return Optional<WorkDTO> to handle null values
    List<WorkTestDTO> getAllWorks();
    WorkTestDTO updateWork(Long id, WorkTestDTO workDTO);
    void deleteWork(Long id);
    WorkTestDTO updateOpenDate(Long id);
}
