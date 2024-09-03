package com.example.worktest.Work;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class WorkServiceImpl implements WorkService {

    @Autowired
    private WorkRepository workRepository;

    DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"); //데이터 형식 설정
    @Override
    public WorkDTO convertToDto(Work work) {
        if (work == null) {
            return null;
        }
        return new WorkDTO(
                work.getId(),
                work.getUserId(),
                work.getFileId(),
                work.isShared(),
                work.isTrashed(),
                work.isFinish(),
                work.getCreateDate() != null ? work.getCreateDate().format(FORMATTER) : null,
                work.getUpdateDate() != null ? work.getUpdateDate().format(FORMATTER) : null,
                work.getDeleteDate() != null ? work.getDeleteDate().format(FORMATTER) : null,
                work.getOpenDate() != null ? work.getOpenDate().format(FORMATTER) : null
        );
    }

    @Override
    public Work convertToEntity(WorkDTO workDTO) {
        if (workDTO == null) {
            return null;
        }
        return new Work(
                workDTO.getId(),
                workDTO.getUserId(),
                workDTO.getFileId(),
                workDTO.isShared(),
                workDTO.isTrashed(),
                workDTO.isFinish(),
                workDTO.getCreateDate() != null ? LocalDateTime.parse(workDTO.getCreateDate(), FORMATTER) : null,
                workDTO.getUpdateDate() != null ? LocalDateTime.parse(workDTO.getUpdateDate(), FORMATTER) : null,
                workDTO.getDeleteDate() != null ? LocalDateTime.parse(workDTO.getDeleteDate(), FORMATTER) : null,
                workDTO.getOpenDate() != null ? LocalDateTime.parse(workDTO.getOpenDate(), FORMATTER) : null
        );
    }

    @Override
    public List<WorkDTO> getAllWorks() { //모든 작업 가져오기
        return workRepository.findAll()
                .stream() // stream() 메소드는 컬렉션을 스트림으로 변환
                .map(this::convertToDto) //  map() 메소드는 스트림의 각 요소에 함수를 적용
                .collect(Collectors.toList()); // collect() 메소드는 스트림의 요소를 수집
    }

    @Override
    public Optional<WorkDTO> getWorkById(Long id) { //id로 작업 찾기
        return workRepository.findById(id) //id로 작업 찾기
                .map(this::convertToDto); //작업을 WorkDTO로 변환
    }

    @Override
    public WorkDTO createWork(WorkDTO workDTO) { //작업 생성
        Work work = convertToEntity(workDTO); //WorkDTO를 Work로 변환
        Work savedWork = workRepository.save(work); //작업 저장
        return convertToDto(savedWork); //작업을 WorkDTO로 변환
    }

    @Override
    public WorkDTO updateWork(Long id, WorkDTO workDTO) { //작업 업데이트
        Optional<Work> optionalWork = workRepository.findById(id); //id로 작업 찾기
        if (optionalWork.isPresent()) { //작업이 존재하면
            Work work = optionalWork.get();
            work.setUserId(workDTO.getUserId());
            work.setFileId(workDTO.getFileId());
            work.setShared(workDTO.isShared());
            work.setTrashed(workDTO.isTrashed());
            work.setFinish(workDTO.isFinish());
            work.setCreateDate(workDTO.getCreateDate() != null ? LocalDateTime.parse(workDTO.getCreateDate(), FORMATTER) : null);
            work.setUpdateDate(workDTO.getUpdateDate() != null ? LocalDateTime.parse(workDTO.getUpdateDate(), FORMATTER) : null);
            work.setDeleteDate(workDTO.getDeleteDate() != null ? LocalDateTime.parse(workDTO.getDeleteDate(), FORMATTER) : null);
            work.setOpenDate(workDTO.getOpenDate() != null ? LocalDateTime.parse(workDTO.getOpenDate(), FORMATTER) : null);
            Work updatedWork = workRepository.save(work); //작업 업데이트
            return convertToDto(updatedWork); //작업을 WorkDTO로 변환
        } else {
            return null; //작업이 존재하지 않으면 null 반환
        }
    }

    @Override
    public void deleteWork(Long id) { //작업 id로 삭제
        workRepository.deleteById(id); //작업 삭제
    }
    @Override
    public WorkDTO updateOpenDate(Long id){
        Optional<Work> optionalWork = workRepository.findById(id); //id로 작업 찾기
        if (optionalWork.isPresent()) { //작업이 존재하면
            Work work = optionalWork.get();
            work.setOpenDate(LocalDateTime.now());
            Work updatedWork = workRepository.save(work); //작업 업데이트
            return convertToDto(updatedWork); //작업을 WorkDTO로 변환
        } else {
            throw new RuntimeException("Work entity with ID " + id + " not found.");
        }
    }
}
