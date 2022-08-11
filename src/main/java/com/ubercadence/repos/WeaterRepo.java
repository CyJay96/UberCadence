package com.ubercadence.repos;

import com.ubercadence.domain.Weather;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WeaterRepo extends JpaRepository<Weather, Long> {
}
