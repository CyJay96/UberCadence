package com.ubercadence.repos;

import com.ubercadence.domain.Weather;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeatherRepo extends JpaRepository<Weather, Long> {

    Page<Weather> findAll(Pageable pageable);

}
