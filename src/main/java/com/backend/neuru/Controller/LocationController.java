package com.backend.neuru.Controller;


import com.backend.neuru.DTO.CityDTO;
import com.backend.neuru.DTO.LocationDTO;
import com.backend.neuru.DTO.PostDTO;
import com.backend.neuru.DTO.ResponseDTO;
import com.backend.neuru.Service.LocationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping(value = "/api/location")
@RequiredArgsConstructor
@Validated
public class LocationController {
    @Autowired
    private final LocationService locationService;

    // city 등록 API
    @PostMapping(value = "/city")
    public ResponseDTO<?> registerCity(@RequestBody CityDTO.cityRegisterDTO cityRegisterDTO) {
        return locationService.registerCity(cityRegisterDTO);
    }

    // city 조회 API
    @GetMapping(value = "/city")
    public ResponseDTO<?> getCity() {
        return locationService.getCities();
    }

    // 장소 등록 API
    @PostMapping(value = "")
    public ResponseDTO<?> registerLocation(@RequestBody LocationDTO.locationRegisterDTO locationRegisterDTO) {
        return locationService.registerLocation(locationRegisterDTO);
    }

    // 장소 조회 API
    @GetMapping(value = "")
    public ResponseDTO<?> getLocation(@RequestParam int category, @RequestParam Long walkway_id) throws IOException {
        return locationService.getLocations(category, walkway_id);
    }

    // 서울맵 화장실 요청 및 장소 DB 등록 API
    @PostMapping("/fetchToiletData")
    public ResponseDTO<?>  fetchToiletData(@RequestParam Long walk_id)
    {
        try{
            return locationService.fetchToiletAndSave(walk_id);
        } catch (IOException e) {
            return ResponseDTO.error("서울맵에 화장실 리스트 가져와서 DB 저장 실패");
        }
    }

    // 서울맵 충전기 요청 및 장소 DB 등록 API
    @PostMapping("/fetchChargeData")
    public ResponseDTO<?> fetchChargeData(@RequestParam Long walk_id)
    {
        try{
            return locationService.fetchChargeAndSave(walk_id);
        } catch (IOException e) {
            return ResponseDTO.error("서울맵에 충전기 리스트 가져와서 DB 저장 실패");
        }
    }

    // 장소 광고 유무 수정 API
    @PutMapping(value = "/{id}")
    public ResponseDTO<?> putLocation(@PathVariable("id") Long id, @RequestParam Boolean is_ad) throws IOException {
        return locationService.putLocation(id, is_ad);
    }
}
