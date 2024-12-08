package inha.ELDERLYMONITORING.domain.dangerevent.repository;

import inha.ELDERLYMONITORING.domain.dangerevent.DangerEvent;
import org.springframework.data.jpa.repository.JpaRepository;


public interface DangerEventRepository extends JpaRepository<DangerEvent, Long> {

}
