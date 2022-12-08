package com.maro.roomescapediary.repository;

import com.maro.roomescapediary.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Integer>, StoreCustomRepository {

}
